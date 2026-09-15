package org.ais.jcash.workflow.dto;

import java.math.BigDecimal;
import java.util.Date;

/** Request to park a new payment into the CMS mock workflow. */
public class CmsParkPaymentRequest {
    private String productCode;
    private BigDecimal amount;
    private String currency = "PKR";
    private String custRef;
    private String debitAccount;
    private String benBankName;
    private String benBankImd;
    private String benIban;
    private String benTitle;
    private String benPhone;
    private Long transHeadId;
    private String idempotencyKey;
    private String payloadJson;
    private Date scheduleDate;

    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
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
    public Long getTransHeadId() { return transHeadId; }
    public void setTransHeadId(Long transHeadId) { this.transHeadId = transHeadId; }
    public String getIdempotencyKey() { return idempotencyKey; }
    public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; }
    public String getPayloadJson() { return payloadJson; }
    public void setPayloadJson(String payloadJson) { this.payloadJson = payloadJson; }
    public Date getScheduleDate() { return scheduleDate; }
    public void setScheduleDate(Date scheduleDate) { this.scheduleDate = scheduleDate; }
}
