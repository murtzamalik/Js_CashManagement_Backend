package org.ais.jcash.dto;


public class InitiateSingleTransactionRequest {

    private String docType;

    private String docNo;

    private String mobileNo;

    private String benActNo;

    private String benEmail;

    private Long payModeId;

    private Long benBankId;

    private String securityDeviceCode;

    private long productId;

    private String custRef;

    private long transferAmnt;

    private String beneficiaryName;

    private String beneficiaryAddress;

    private long debitAcctNoId;

    private String accountTitle;

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAddress() {
        return beneficiaryAddress;
    }

    public void setBeneficiaryAddress(String beneficiaryAddress) {
        this.beneficiaryAddress = beneficiaryAddress;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getBenActNo() {
        return benActNo;
    }

    public void setBenActNo(String benActNo) {
        this.benActNo = benActNo;
    }

    public String getBenEmail() {
        return benEmail;
    }

    public void setBenEmail(String benEmail) {
        this.benEmail = benEmail;
    }

    public Long getPayModeId() {
        return payModeId;
    }

    public void setPayModeId(Long payModeId) {
        this.payModeId = payModeId;
    }

    public Long getBenBankId() {
        return benBankId;
    }

    public void setBenBankId(Long benBankId) {
        this.benBankId = benBankId;
    }

    public String getSecurityDeviceCode() {
        return securityDeviceCode;
    }

    public void setSecurityDeviceCode(String securityDeviceCode) {
        this.securityDeviceCode = securityDeviceCode;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getDebitAcctNoId() {
        return debitAcctNoId;
    }

    public void setDebitAcctNoId(long debitAcctNoId) {
        this.debitAcctNoId = debitAcctNoId;
    }

    public String getCustRef() {
        return custRef;
    }

    public void setCustRef(String custRef) {
        this.custRef = custRef;
    }

    public long getTransferAmnt() {
        return transferAmnt;
    }

    public void setTransferAmnt(long transferAmnt) {
        this.transferAmnt = transferAmnt;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }
}
