package org.ais.jcash.workflow.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class CmsTxnDto {
    private long txnId;
    private long companyId;
    private String productCode;
    private String status;
    private BigDecimal amount;
    private String currency = "PKR";
    private String custRef;
    private String debitAccount;
    private String benBankName;
    private String benBankImd;
    private String benIban;
    private String benTitle;
    private String benPhone;
    private Date scheduleDate;
    private Long transHeadId;
    private String idempotencyKey;
    private String nextApproverEmail;
    private String nextApproverMobile;
    private String stopReason;
    private int approvalsRequired = 1;
    private int approvalsDone = 0;
    private Long makerUserId;
    private Long authUserId;
    private Long releaseUserId;
    private Date makerAt;
    private Date authAt;
    private Date releaseAt;
    private Date paidAt;
    private boolean mockMode = true;
    private String payloadJson;
    private String instrumentNo;
    private Date createDate;
    private Date lastUpdateDate;

    public Map<String, Object> toMap() {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("txnId", txnId);
        m.put("companyId", companyId);
        m.put("productCode", productCode);
        m.put("status", status);
        m.put("amount", amount);
        m.put("currency", currency);
        m.put("custRef", custRef);
        m.put("debitAccount", debitAccount);
        m.put("benBankName", benBankName);
        m.put("benBankImd", benBankImd);
        m.put("benIban", benIban);
        m.put("benTitle", benTitle);
        m.put("benPhone", benPhone);
        m.put("scheduleDate", scheduleDate);
        m.put("transHeadId", transHeadId);
        m.put("idempotencyKey", idempotencyKey);
        m.put("nextApproverEmail", nextApproverEmail);
        m.put("nextApproverMobile", nextApproverMobile);
        m.put("stopReason", stopReason);
        m.put("approvalsRequired", approvalsRequired);
        m.put("approvalsDone", approvalsDone);
        m.put("makerUserId", makerUserId);
        m.put("authUserId", authUserId);
        m.put("releaseUserId", releaseUserId);
        m.put("makerAt", makerAt);
        m.put("authAt", authAt);
        m.put("releaseAt", releaseAt);
        m.put("paidAt", paidAt);
        m.put("mockMode", mockMode);
        m.put("payloadJson", payloadJson);
        m.put("instrumentNo", instrumentNo);
        m.put("createDate", createDate);
        m.put("lastUpdateDate", lastUpdateDate);
        return m;
    }

    public long getTxnId() { return txnId; }
    public void setTxnId(long txnId) { this.txnId = txnId; }
    public long getCompanyId() { return companyId; }
    public void setCompanyId(long companyId) { this.companyId = companyId; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getCustRef() { return custRef; }
    public void setCustRef(String custRef) { this.custRef = custRef; }
    public String getDebitAccount() { return debitAccount; }
    public void setDebitAccount(String debitAccount) { this.debitAccount = debitAccount; }
    public String getBenBankName() { return benBankName; }
    public void setBenBankName(String benBankName) { this.benBankName = benBankName; }
    public String getBenBankImd() { return benBankImd; }
    public void setBenBankImd(String benBankImd) { this.benBankImd = benBankImd; }
    public String getBenIban() { return benIban; }
    public void setBenIban(String benIban) { this.benIban = benIban; }
    public String getBenTitle() { return benTitle; }
    public void setBenTitle(String benTitle) { this.benTitle = benTitle; }
    public String getBenPhone() { return benPhone; }
    public void setBenPhone(String benPhone) { this.benPhone = benPhone; }
    public Date getScheduleDate() { return scheduleDate; }
    public void setScheduleDate(Date scheduleDate) { this.scheduleDate = scheduleDate; }
    public Long getTransHeadId() { return transHeadId; }
    public void setTransHeadId(Long transHeadId) { this.transHeadId = transHeadId; }
    public String getIdempotencyKey() { return idempotencyKey; }
    public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; }
    public String getNextApproverEmail() { return nextApproverEmail; }
    public void setNextApproverEmail(String nextApproverEmail) { this.nextApproverEmail = nextApproverEmail; }
    public String getNextApproverMobile() { return nextApproverMobile; }
    public void setNextApproverMobile(String nextApproverMobile) { this.nextApproverMobile = nextApproverMobile; }
    public String getStopReason() { return stopReason; }
    public void setStopReason(String stopReason) { this.stopReason = stopReason; }
    public int getApprovalsRequired() { return approvalsRequired; }
    public void setApprovalsRequired(int approvalsRequired) { this.approvalsRequired = approvalsRequired; }
    public int getApprovalsDone() { return approvalsDone; }
    public void setApprovalsDone(int approvalsDone) { this.approvalsDone = approvalsDone; }
    public Long getMakerUserId() { return makerUserId; }
    public void setMakerUserId(Long makerUserId) { this.makerUserId = makerUserId; }
    public Long getAuthUserId() { return authUserId; }
    public void setAuthUserId(Long authUserId) { this.authUserId = authUserId; }
    public Long getReleaseUserId() { return releaseUserId; }
    public void setReleaseUserId(Long releaseUserId) { this.releaseUserId = releaseUserId; }
    public Date getMakerAt() { return makerAt; }
    public void setMakerAt(Date makerAt) { this.makerAt = makerAt; }
    public Date getAuthAt() { return authAt; }
    public void setAuthAt(Date authAt) { this.authAt = authAt; }
    public Date getReleaseAt() { return releaseAt; }
    public void setReleaseAt(Date releaseAt) { this.releaseAt = releaseAt; }
    public Date getPaidAt() { return paidAt; }
    public void setPaidAt(Date paidAt) { this.paidAt = paidAt; }
    public boolean isMockMode() { return mockMode; }
    public void setMockMode(boolean mockMode) { this.mockMode = mockMode; }
    public String getPayloadJson() { return payloadJson; }
    public void setPayloadJson(String payloadJson) { this.payloadJson = payloadJson; }
    public String getInstrumentNo() { return instrumentNo; }
    public void setInstrumentNo(String instrumentNo) { this.instrumentNo = instrumentNo; }
    public Date getCreateDate() { return createDate; }
    public void setCreateDate(Date createDate) { this.createDate = createDate; }
    public Date getLastUpdateDate() { return lastUpdateDate; }
    public void setLastUpdateDate(Date lastUpdateDate) { this.lastUpdateDate = lastUpdateDate; }
}
