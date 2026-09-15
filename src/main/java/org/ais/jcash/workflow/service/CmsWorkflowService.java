package org.ais.jcash.workflow.service;

import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.workflow.CmsTxnStatus;
import org.ais.jcash.workflow.dto.CmsParkPaymentRequest;
import org.ais.jcash.workflow.dto.CmsTxnDto;
import org.ais.jcash.workflow.dto.WorkflowActionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CmsWorkflowService {

    @Autowired
    private InMemoryCmsStore store;

    @Value("${jcash.workflow.mock-enabled:true}")
    private boolean workflowMockEnabled;

    @Value("${jcash.workflow.scheduler-cron-enabled:false}")
    private boolean schedulerCronEnabled;

    public boolean isWorkflowMockEnabled() {
        return workflowMockEnabled;
    }

    public CmsTxnDto park(LoggedUserDetail user, CmsParkPaymentRequest req) {
        if (req.getIdempotencyKey() != null) {
            for (CmsTxnDto existing : store.findByCompany(user.getCompanyId())) {
                if (req.getIdempotencyKey().equals(existing.getIdempotencyKey())) {
                    return existing;
                }
            }
        }
        Date now = new Date();
        CmsTxnDto txn = new CmsTxnDto();
        txn.setTxnId(store.nextTxnId());
        txn.setCompanyId(user.getCompanyId());
        txn.setProductCode(req.getProductCode() == null ? "IBFT" : req.getProductCode().toUpperCase(Locale.ROOT));
        txn.setStatus(CmsTxnStatus.PENDING_AUTH);
        txn.setAmount(req.getAmount() == null ? BigDecimal.ZERO : req.getAmount());
        txn.setCurrency(req.getCurrency() == null ? "PKR" : req.getCurrency());
        txn.setCustRef(req.getCustRef());
        txn.setDebitAccount(req.getDebitAccount());
        txn.setBenBankName(req.getBenBankName());
        txn.setBenBankImd(req.getBenBankImd());
        txn.setBenIban(normalizeIban(req.getBenIban()));
        txn.setBenTitle(req.getBenTitle());
        txn.setBenPhone(req.getBenPhone());
        txn.setTransHeadId(req.getTransHeadId());
        txn.setIdempotencyKey(req.getIdempotencyKey() != null ? req.getIdempotencyKey() : "CMS-" + txn.getTxnId());
        txn.setPayloadJson(req.getPayloadJson());
        txn.setApprovalsRequired(store.approvalsRequired(txn.getProductCode(), txn.getAmount()));
        txn.setApprovalsDone(0);
        txn.setMakerUserId(user.getUserId());
        txn.setMakerAt(now);
        txn.setCreateDate(now);
        txn.setLastUpdateDate(now);
        txn.setMockMode(true);
        txn.setNextApproverEmail("checker@dfs.demo");
        txn.setNextApproverMobile("03001234567");
        store.saveTxn(txn);
        store.addAudit(txn.getTxnId(), txn.getCompanyId(), null, CmsTxnStatus.PENDING_AUTH, "PARK",
                user.getUserId(), "Parked for authorization");
        return txn;
    }

    public CmsTxnDto approve(LoggedUserDetail user, WorkflowActionRequest req) {
        CmsTxnDto txn = requireOwned(user, req.getTxnId());
        if (!CmsTxnStatus.PENDING_AUTH.equals(txn.getStatus())) {
            throw new IllegalStateException("Only PENDING_AUTH can be approved. Current: " + txn.getStatus());
        }
        String from = txn.getStatus();
        txn.setApprovalsDone(txn.getApprovalsDone() + 1);
        txn.setAuthUserId(user.getUserId());
        txn.setAuthAt(new Date());
        txn.setLastUpdateDate(new Date());
        if (txn.getApprovalsDone() >= txn.getApprovalsRequired()) {
            txn.setStatus(CmsTxnStatus.AUTHORIZED);
            store.addAudit(txn.getTxnId(), txn.getCompanyId(), from, CmsTxnStatus.AUTHORIZED, "APPROVE",
                    user.getUserId(), req.getComments());
        } else {
            store.addAudit(txn.getTxnId(), txn.getCompanyId(), from, CmsTxnStatus.PENDING_AUTH, "APPROVE_PARTIAL",
                    user.getUserId(), "Approval " + txn.getApprovalsDone() + "/" + txn.getApprovalsRequired());
            txn.setNextApproverEmail("releaser@dfs.demo");
            txn.setNextApproverMobile("03007654321");
        }
        store.saveTxn(txn);
        return txn;
    }

    public CmsTxnDto release(LoggedUserDetail user, WorkflowActionRequest req) {
        CmsTxnDto txn = requireOwned(user, req.getTxnId());
        if (!CmsTxnStatus.AUTHORIZED.equals(txn.getStatus())
                && !CmsTxnStatus.INSTRUCTION_QUEUED.equals(txn.getStatus())
                && !CmsTxnStatus.AWAITING_SIGNATURE.equals(txn.getStatus())) {
            throw new IllegalStateException("Only AUTHORIZED (or RTGS queued) can be released. Current: " + txn.getStatus());
        }
        Date schedule = req.getScheduleDate();
        Date now = new Date();
        String from = txn.getStatus();
        txn.setReleaseUserId(user.getUserId());
        txn.setReleaseAt(now);
        txn.setLastUpdateDate(now);

        if (schedule != null && startOfDay(schedule).after(startOfDay(now))) {
            txn.setScheduleDate(schedule);
            txn.setStatus(CmsTxnStatus.SCHEDULED);
            store.addAudit(txn.getTxnId(), txn.getCompanyId(), from, CmsTxnStatus.SCHEDULED, "SCHEDULE",
                    user.getUserId(), req.getComments());
            store.saveTxn(txn);
            return txn;
        }

        return markPaid(txn, from, user.getUserId(), req.getComments());
    }

    public CmsTxnDto stop(LoggedUserDetail user, WorkflowActionRequest req) {
        CmsTxnDto txn = requireOwned(user, req.getTxnId());
        String st = txn.getStatus();
        if (!(CmsTxnStatus.PENDING_AUTH.equals(st) || CmsTxnStatus.AUTHORIZED.equals(st) || CmsTxnStatus.SCHEDULED.equals(st))) {
            throw new IllegalStateException("Cannot stop payment in status: " + st);
        }
        if (req.getStopReason() == null || req.getStopReason().trim().isEmpty()) {
            throw new IllegalArgumentException("Stop reason is required");
        }
        String from = txn.getStatus();
        txn.setStatus(CmsTxnStatus.STOPPED);
        txn.setStopReason(req.getStopReason().trim());
        txn.setLastUpdateDate(new Date());
        store.saveTxn(txn);
        store.addAudit(txn.getTxnId(), txn.getCompanyId(), from, CmsTxnStatus.STOPPED, "STOP",
                user.getUserId(), req.getStopReason());
        return txn;
    }

    public List<CmsTxnDto> processDueScheduled(LoggedUserDetail user) {
        Date today = startOfDay(new Date());
        List<CmsTxnDto> paid = new ArrayList<>();
        for (CmsTxnDto txn : store.findByCompany(user.getCompanyId())) {
            if (!CmsTxnStatus.SCHEDULED.equals(txn.getStatus())) continue;
            if (txn.getScheduleDate() == null) continue;
            if (!startOfDay(txn.getScheduleDate()).after(today)) {
                paid.add(markPaid(txn, CmsTxnStatus.SCHEDULED, user.getUserId(), "Scheduler run"));
            }
        }
        return paid;
    }

    @Scheduled(cron = "${jcash.workflow.scheduler-cron:0 */15 * * * *}")
    public void cronProcessScheduled() {
        if (!schedulerCronEnabled) return;
        Date today = startOfDay(new Date());
        for (CmsTxnDto txn : store.findAll()) {
            if (!CmsTxnStatus.SCHEDULED.equals(txn.getStatus())) continue;
            if (txn.getScheduleDate() == null) continue;
            if (!startOfDay(txn.getScheduleDate()).after(today)) {
                markPaid(txn, CmsTxnStatus.SCHEDULED, txn.getMakerUserId(), "Cron scheduler");
            }
        }
    }

    public CmsTxnDto get(LoggedUserDetail user, long txnId) {
        return requireOwned(user, txnId);
    }

    public Map<String, Object> queues(LoggedUserDetail user) {
        List<CmsTxnDto> all = store.findByCompany(user.getCompanyId());
        Map<String, Object> q = new LinkedHashMap<>();
        q.put("pendingAuth", filterStatus(all, CmsTxnStatus.PENDING_AUTH));
        q.put("authorized", filterStatus(all, CmsTxnStatus.AUTHORIZED));
        q.put("scheduled", filterStatus(all, CmsTxnStatus.SCHEDULED));
        q.put("paid", filterStatus(all, CmsTxnStatus.PAID));
        q.put("stopped", filterStatus(all, CmsTxnStatus.STOPPED));
        q.put("failed", filterStatus(all, CmsTxnStatus.FAILED));
        q.put("awaitingSignature", filterStatus(all, CmsTxnStatus.AWAITING_SIGNATURE));
        q.put("all", all.stream().map(CmsTxnDto::toMap).collect(Collectors.toList()));
        return q;
    }

    public List<CmsTxnDto> listCompany(LoggedUserDetail user) {
        return store.findByCompany(user.getCompanyId());
    }

    public Map<String, Object> dashboardSummary(LoggedUserDetail user, Date from, Date to) {
        List<CmsTxnDto> all = store.findByCompany(user.getCompanyId());
        Date start = from != null ? startOfDay(from) : startOfDay(new Date());
        Date end = to != null ? endOfDay(to) : endOfDay(new Date());
        List<CmsTxnDto> today = all.stream()
                .filter(t -> t.getCreateDate() != null && !t.getCreateDate().before(start) && !t.getCreateDate().after(end))
                .collect(Collectors.toList());
        long paidCount = today.stream().filter(t -> CmsTxnStatus.PAID.equals(t.getStatus())).count();
        BigDecimal paidAmt = today.stream()
                .filter(t -> CmsTxnStatus.PAID.equals(t.getStatus()))
                .map(CmsTxnDto::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalAmt = today.stream().map(CmsTxnDto::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("todayCount", today.size());
        m.put("todayAmount", totalAmt);
        m.put("paidCount", paidCount);
        m.put("paidAmount", paidAmt);
        m.put("successRate", today.isEmpty() ? 0 : Math.round(100.0 * paidCount / today.size()));
        m.put("pendingAuth", all.stream().filter(t -> CmsTxnStatus.PENDING_AUTH.equals(t.getStatus())).count());
        m.put("authorized", all.stream().filter(t -> CmsTxnStatus.AUTHORIZED.equals(t.getStatus())).count());
        m.put("scheduled", all.stream().filter(t -> CmsTxnStatus.SCHEDULED.equals(t.getStatus())).count());
        m.put("stopped", all.stream().filter(t -> CmsTxnStatus.STOPPED.equals(t.getStatus())).count());
        m.put("failed", all.stream().filter(t -> CmsTxnStatus.FAILED.equals(t.getStatus())).count());
        return m;
    }

    public List<Map<String, Object>> timeseries(LoggedUserDetail user, int days) {
        List<Map<String, Object>> series = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startOfDay(new Date()));
        cal.add(Calendar.DATE, -(days - 1));
        List<CmsTxnDto> all = store.findByCompany(user.getCompanyId());
        for (int i = 0; i < days; i++) {
            Date day = cal.getTime();
            Date dayEnd = endOfDay(day);
            List<CmsTxnDto> dayTxns = all.stream()
                    .filter(t -> t.getCreateDate() != null && !t.getCreateDate().before(day) && !t.getCreateDate().after(dayEnd))
                    .collect(Collectors.toList());
            Map<String, Object> point = new LinkedHashMap<>();
            point.put("date", day);
            point.put("count", dayTxns.size());
            point.put("amount", dayTxns.stream().map(CmsTxnDto::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
            series.add(point);
            cal.add(Calendar.DATE, 1);
        }
        return series;
    }

    public Map<String, Object> breakdown(LoggedUserDetail user, String by) {
        List<CmsTxnDto> all = store.findByCompany(user.getCompanyId());
        Map<String, Object> result = new LinkedHashMap<>();
        Map<String, Long> counts = new LinkedHashMap<>();
        Map<String, BigDecimal> amounts = new LinkedHashMap<>();
        for (CmsTxnDto t : all) {
            String key = "status".equalsIgnoreCase(by) ? t.getStatus() : t.getProductCode();
            counts.put(key, counts.getOrDefault(key, 0L) + 1);
            amounts.put(key, amounts.getOrDefault(key, BigDecimal.ZERO).add(t.getAmount()));
        }
        result.put("counts", counts);
        result.put("amounts", amounts);
        return result;
    }

    public List<Map<String, Object>> reportRows(LoggedUserDetail user, String reportCode, String statusFilter, String productFilter) {
        List<CmsTxnDto> all = store.findByCompany(user.getCompanyId());
        String code = reportCode == null ? "TXN_REGISTER" : reportCode.toUpperCase(Locale.ROOT);
        switch (code) {
            case "PENDING_AUTH":
                all = all.stream().filter(t -> CmsTxnStatus.PENDING_AUTH.equals(t.getStatus())).collect(Collectors.toList());
                break;
            case "PAID_RELEASED":
                all = all.stream().filter(t -> CmsTxnStatus.PAID.equals(t.getStatus()) || CmsTxnStatus.RELEASED.equals(t.getStatus())).collect(Collectors.toList());
                break;
            case "FAILED_STOPPED":
                all = all.stream().filter(t -> CmsTxnStatus.FAILED.equals(t.getStatus()) || CmsTxnStatus.STOPPED.equals(t.getStatus())).collect(Collectors.toList());
                break;
            case "SCHEDULED":
                all = all.stream().filter(t -> CmsTxnStatus.SCHEDULED.equals(t.getStatus())).collect(Collectors.toList());
                break;
            case "BILL_REGISTER":
                all = all.stream().filter(t -> "BILLPAY".equalsIgnoreCase(t.getProductCode())).collect(Collectors.toList());
                break;
            case "IBFT_REGISTER":
                all = all.stream().filter(t -> "IBFT".equalsIgnoreCase(t.getProductCode())).collect(Collectors.toList());
                break;
            case "SALARY_REGISTER":
                all = all.stream().filter(t -> "SALARY".equalsIgnoreCase(t.getProductCode())).collect(Collectors.toList());
                break;
            case "TAX_REGISTER":
                all = all.stream().filter(t -> "TAX".equalsIgnoreCase(t.getProductCode())).collect(Collectors.toList());
                break;
            case "AUDIT_TRAIL":
                return store.findAudits(null, user.getCompanyId());
            case "MATRIX_USAGE":
                return store.getMatrixBands();
            case "BENEFICIARY_LIST":
                return store.findBeneficiaries(user.getCompanyId());
            case "COLLECTIONS_STATUS":
                Map<String, Object> j = store.getCollectionsJourney(user.getCompanyId());
                List<Map<String, Object>> list = new ArrayList<>();
                if (j != null) list.add(j);
                return list;
            default:
                break;
        }
        if (statusFilter != null && !statusFilter.isEmpty()) {
            all = all.stream().filter(t -> statusFilter.equalsIgnoreCase(t.getStatus())).collect(Collectors.toList());
        }
        if (productFilter != null && !productFilter.isEmpty()) {
            all = all.stream().filter(t -> productFilter.equalsIgnoreCase(t.getProductCode())).collect(Collectors.toList());
        }
        return all.stream().map(CmsTxnDto::toMap).collect(Collectors.toList());
    }

    public String toCsv(List<Map<String, Object>> rows) {
        if (rows == null || rows.isEmpty()) {
            return "message\nNo data\n";
        }
        List<String> headers = new ArrayList<>(rows.get(0).keySet());
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(",", headers)).append("\n");
        for (Map<String, Object> row : rows) {
            List<String> cols = new ArrayList<>();
            for (String h : headers) {
                Object v = row.get(h);
                String s = v == null ? "" : String.valueOf(v).replace("\"", "\"\"");
                if (s.contains(",") || s.contains("\n")) s = "\"" + s + "\"";
                cols.add(s);
            }
            sb.append(String.join(",", cols)).append("\n");
        }
        return sb.toString();
    }

    public InMemoryCmsStore store() {
        return store;
    }

    private CmsTxnDto markPaid(CmsTxnDto txn, String from, Long userId, String comments) {
        // Lock beneficiary company-wide on successful IBFT
        if (txn.getBenIban() != null && "IBFT".equalsIgnoreCase(txn.getProductCode())) {
            Map<String, Object> bene = store.findBeneficiaryByIban(txn.getCompanyId(), txn.getBenIban());
            if (bene != null) {
                bene.put("locked", "Y");
                bene.put("lastUpdateDate", new Date());
                store.saveBeneficiary(bene);
            } else {
                Map<String, Object> nb = new HashMap<>();
                nb.put("beneficiaryId", store.nextBeneId());
                nb.put("companyId", txn.getCompanyId());
                nb.put("bankName", txn.getBenBankName());
                nb.put("bankImd", txn.getBenBankImd());
                nb.put("iban", txn.getBenIban());
                nb.put("accountTitle", txn.getBenTitle());
                nb.put("phone", txn.getBenPhone());
                nb.put("locked", "Y");
                nb.put("active", "Y");
                nb.put("createDate", new Date());
                store.saveBeneficiary(nb);
            }
        }
        if ("PO".equalsIgnoreCase(txn.getProductCode()) || "CHQ".equalsIgnoreCase(txn.getProductCode())) {
            txn.setInstrumentNo("PO" + System.currentTimeMillis() % 1000000);
            txn.setStatus(CmsTxnStatus.PRINTED);
            store.addAudit(txn.getTxnId(), txn.getCompanyId(), from, CmsTxnStatus.PRINTED, "PRINT", userId, comments);
        } else {
            txn.setStatus(CmsTxnStatus.PAID);
            txn.setPaidAt(new Date());
            store.addAudit(txn.getTxnId(), txn.getCompanyId(), from, CmsTxnStatus.PAID, "RELEASE_PAY", userId, comments);
        }
        txn.setLastUpdateDate(new Date());
        store.saveTxn(txn);
        return txn;
    }

    private CmsTxnDto requireOwned(LoggedUserDetail user, Long txnId) {
        if (txnId == null) throw new IllegalArgumentException("txnId required");
        CmsTxnDto txn = store.findTxn(txnId);
        if (txn == null) throw new IllegalArgumentException("Transaction not found");
        if (txn.getCompanyId() != user.getCompanyId()) {
            throw new IllegalStateException("Access denied for this company");
        }
        return txn;
    }

    private List<Map<String, Object>> filterStatus(List<CmsTxnDto> all, String status) {
        return all.stream().filter(t -> status.equals(t.getStatus())).map(CmsTxnDto::toMap).collect(Collectors.toList());
    }

    private String normalizeIban(String iban) {
        if (iban == null) return null;
        return iban.replaceAll("\\s+", "").toUpperCase(Locale.ROOT);
    }

    private Date startOfDay(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    private Date endOfDay(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }
}
