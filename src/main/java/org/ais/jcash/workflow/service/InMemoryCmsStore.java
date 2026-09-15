package org.ais.jcash.workflow.service;

import org.ais.jcash.workflow.dto.CmsTxnDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * In-memory CMS txn store for demo (survives until JVM restart).
 * Oracle DDL in scripts/cms_workflow_schema.sql can be applied later for persistence.
 */
@Component
public class InMemoryCmsStore {

    private final AtomicLong txnSeq = new AtomicLong(1000);
    private final AtomicLong auditSeq = new AtomicLong(1);
    private final AtomicLong beneSeq = new AtomicLong(1);
    private final Map<Long, CmsTxnDto> txns = new ConcurrentHashMap<>();
    private final List<Map<String, Object>> audits = new ArrayList<>();
    private final Map<Long, Map<String, Object>> beneficiaries = new ConcurrentHashMap<>();
    private final List<Map<String, Object>> matrixBands = new ArrayList<>();
    private final Map<String, Object> collectionsJourney = new ConcurrentHashMap<>();

    public InMemoryCmsStore() {
        Map<String, Object> b1 = new ConcurrentHashMap<>();
        b1.put("productCode", "IBFT");
        b1.put("fromAmount", new BigDecimal("0"));
        b1.put("toAmount", new BigDecimal("50000"));
        b1.put("approvalsRequired", 1);
        matrixBands.add(b1);
        Map<String, Object> b2 = new ConcurrentHashMap<>();
        b2.put("productCode", "IBFT");
        b2.put("fromAmount", new BigDecimal("50000.01"));
        b2.put("toAmount", new BigDecimal("999999999"));
        b2.put("approvalsRequired", 2);
        matrixBands.add(b2);
    }

    public long nextTxnId() {
        return txnSeq.incrementAndGet();
    }

    public void saveTxn(CmsTxnDto txn) {
        txns.put(txn.getTxnId(), txn);
    }

    public CmsTxnDto findTxn(long txnId) {
        return txns.get(txnId);
    }

    public List<CmsTxnDto> findByCompany(long companyId) {
        return txns.values().stream()
                .filter(t -> t.getCompanyId() == companyId)
                .sorted(Comparator.comparing(CmsTxnDto::getTxnId).reversed())
                .collect(Collectors.toList());
    }

    public List<CmsTxnDto> findByCompanyAndStatus(long companyId, String status) {
        return findByCompany(companyId).stream()
                .filter(t -> status.equalsIgnoreCase(t.getStatus()))
                .collect(Collectors.toList());
    }

    public List<CmsTxnDto> findAll() {
        return new ArrayList<>(txns.values());
    }

    public synchronized void addAudit(long txnId, long companyId, String from, String to, String action, Long userId, String comments) {
        Map<String, Object> a = new ConcurrentHashMap<>();
        a.put("auditId", auditSeq.incrementAndGet());
        a.put("txnId", txnId);
        a.put("companyId", companyId);
        a.put("fromStatus", from);
        a.put("toStatus", to);
        a.put("action", action);
        a.put("userId", userId);
        a.put("comments", comments);
        a.put("createDate", new Date());
        audits.add(a);
    }

    public List<Map<String, Object>> findAudits(Long txnId, Long companyId) {
        return audits.stream()
                .filter(a -> companyId == null || companyId.equals(((Number) a.get("companyId")).longValue()))
                .filter(a -> txnId == null || txnId.equals(((Number) a.get("txnId")).longValue()))
                .collect(Collectors.toList());
    }

    public int approvalsRequired(String productCode, BigDecimal amount) {
        if (amount == null) amount = BigDecimal.ZERO;
        String code = productCode == null ? "IBFT" : productCode.toUpperCase();
        for (Map<String, Object> band : matrixBands) {
            String pc = String.valueOf(band.get("productCode"));
            if (!pc.equalsIgnoreCase(code) && !"IBFT".equalsIgnoreCase(pc)) {
                continue;
            }
            if (!pc.equalsIgnoreCase(code) && !"BILLPAY".equalsIgnoreCase(code)
                    && !"SALARY".equalsIgnoreCase(code) && !"TAX".equalsIgnoreCase(code)
                    && !"RTGS".equalsIgnoreCase(code) && !"PO".equalsIgnoreCase(code)
                    && !"COC".equalsIgnoreCase(code)) {
                continue;
            }
            // Apply IBFT bands to IBFT-like products; default 1 for others unless matching product
            if (!pc.equalsIgnoreCase(code) && "IBFT".equalsIgnoreCase(pc)
                    && ("BILLPAY".equalsIgnoreCase(code) || "SALARY".equalsIgnoreCase(code)
                    || "TAX".equalsIgnoreCase(code) || "COC".equalsIgnoreCase(code))) {
                BigDecimal from = (BigDecimal) band.get("fromAmount");
                BigDecimal to = (BigDecimal) band.get("toAmount");
                if (amount.compareTo(from) >= 0 && amount.compareTo(to) <= 0) {
                    return ((Number) band.get("approvalsRequired")).intValue();
                }
            }
            if (pc.equalsIgnoreCase(code)) {
                BigDecimal from = (BigDecimal) band.get("fromAmount");
                BigDecimal to = (BigDecimal) band.get("toAmount");
                if (amount.compareTo(from) >= 0 && amount.compareTo(to) <= 0) {
                    return ((Number) band.get("approvalsRequired")).intValue();
                }
            }
        }
        // Fallback: use IBFT bands for any product
        for (Map<String, Object> band : matrixBands) {
            if (!"IBFT".equalsIgnoreCase(String.valueOf(band.get("productCode")))) continue;
            BigDecimal from = (BigDecimal) band.get("fromAmount");
            BigDecimal to = (BigDecimal) band.get("toAmount");
            if (amount.compareTo(from) >= 0 && amount.compareTo(to) <= 0) {
                return ((Number) band.get("approvalsRequired")).intValue();
            }
        }
        return 1;
    }

    public List<Map<String, Object>> getMatrixBands() {
        return new ArrayList<>(matrixBands);
    }

    public long nextBeneId() {
        return beneSeq.incrementAndGet();
    }

    public void saveBeneficiary(Map<String, Object> bene) {
        beneficiaries.put(((Number) bene.get("beneficiaryId")).longValue(), bene);
    }

    public Map<String, Object> findBeneficiary(long id) {
        return beneficiaries.get(id);
    }

    public List<Map<String, Object>> findBeneficiaries(long companyId) {
        return beneficiaries.values().stream()
                .filter(b -> ((Number) b.get("companyId")).longValue() == companyId)
                .filter(b -> !"N".equalsIgnoreCase(String.valueOf(b.getOrDefault("active", "Y"))))
                .collect(Collectors.toList());
    }

    public Map<String, Object> findBeneficiaryByIban(long companyId, String iban) {
        String norm = iban == null ? "" : iban.replaceAll("\\s+", "").toUpperCase();
        return beneficiaries.values().stream()
                .filter(b -> ((Number) b.get("companyId")).longValue() == companyId)
                .filter(b -> norm.equals(String.valueOf(b.get("iban")).replaceAll("\\s+", "").toUpperCase()))
                .findFirst()
                .orElse(null);
    }

    public Map<String, Object> getCollectionsJourney(long companyId) {
        Object o = collectionsJourney.get(String.valueOf(companyId));
        if (o instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> m = (Map<String, Object>) o;
            return m;
        }
        return null;
    }

    public void saveCollectionsJourney(long companyId, Map<String, Object> journey) {
        collectionsJourney.put(String.valueOf(companyId), journey);
    }
}
