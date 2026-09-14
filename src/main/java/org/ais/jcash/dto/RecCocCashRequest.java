package org.ais.jcash.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RecCocCashRequest {

    private long branchId;

    private long companyId;

    private long productId;

    private long cashoverCounterId;

    private String benName;

    private String benAccountNo;

    private String benAccountTitle;

    private String benAddress;

    private BigDecimal transAmount;

    private String paymentMode;

    private String documentType;

    private Date expiryDate;

    private String mobileNo;

    private String documentNo;

    private String iftAccountNo;

    private String iftAccountTitle;

    private String iftBranchCode;

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCashoverCounterId() {
        return cashoverCounterId;
    }

    public void setCashoverCounterId(long cashoverCounterId) {
        this.cashoverCounterId = cashoverCounterId;
    }

    public String getBenName() {
        return benName;
    }

    public void setBenName(String benName) {
        this.benName = benName;
    }

    public String getBenAccountNo() {
        return benAccountNo;
    }

    public void setBenAccountNo(String benAccountNo) {
        this.benAccountNo = benAccountNo;
    }

    public String getBenAccountTitle() {
        return benAccountTitle;
    }

    public void setBenAccountTitle(String benAccountTitle) {
        this.benAccountTitle = benAccountTitle;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public String getIftAccountNo() {
        return iftAccountNo;
    }

    public void setIftAccountNo(String iftAccountNo) {
        this.iftAccountNo = iftAccountNo;
    }

    public String getIftAccountTitle() {
        return iftAccountTitle;
    }

    public void setIftAccountTitle(String iftAccountTitle) {
        this.iftAccountTitle = iftAccountTitle;
    }

    public String getIftBranchCode() {
        return iftBranchCode;
    }

    public void setIftBranchCode(String iftBranchCode) {
        this.iftBranchCode = iftBranchCode;
    }

    public String getBenAddress() {
        return benAddress;
    }

    public void setBenAddress(String benAddress) {
        this.benAddress = benAddress;
    }
}
