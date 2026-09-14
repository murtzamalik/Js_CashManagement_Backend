package org.ais.jcash.dto;

public class BulkIbftRowRequest {

    private Long benBankId;

    private String bankName;

    private String iban;

    private String accountTitle;

    private String mobileNo;

    private long transferAmnt;

    private String custRef;

    public Long getBenBankId() {
        return benBankId;
    }

    public void setBenBankId(Long benBankId) {
        this.benBankId = benBankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public long getTransferAmnt() {
        return transferAmnt;
    }

    public void setTransferAmnt(long transferAmnt) {
        this.transferAmnt = transferAmnt;
    }

    public String getCustRef() {
        return custRef;
    }

    public void setCustRef(String custRef) {
        this.custRef = custRef;
    }
}
