package org.ais.jcash.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

public class TblCompanyRequest {

    private String accountNo;
    @NotEmpty (message = "Please Enter account number")
    @NotNull
    private String accountingEntryRequest;
    @NotEmpty   (message = "Please Enter Accounting Entry Request")
    @NotNull
    private String accountingEntryType;

    private String address1;

    private String address2;

    private String address3;

    private String backupContact;

    private String backupEmail1;

    private String backupEmail2;

    private String closed;

    private Date closedDate;

    private Date closedTill;

    private String closureReason;
    @NotEmpty (message = "Please Enter Company Code")
    @NotNull (message = "Please Enter Company Code")
    private String companyCode;
    @NotEmpty (message = "Please Enter Company Name")
    @NotNull (message = "Please Enter Company Name")
    private String companyName;
    @NotEmpty (message = "Please Enter valid contact number")
    @NotNull
    private String contactNo;
    @NotEmpty (message = "Please Enter Contact Person")
    @NotNull
    private String contactPerson;

    private BigDecimal creditLimit;
    @NotEmpty (message = "Please Enter Customer Type")
    @NotNull
    private String customerType;

    private String discountable;

    @NotEmpty (message = "Please enter E-Mail")
    @NotNull
    private String email;

    private String faxNo1;

    private String faxNo2;

    private String faxNo3;
    @NotEmpty (message = "Please enter Mobile Number")
    @NotNull
    private String mobileNo;

    private BigDecimal outstandingAmount;

    private String productManager;
    @NotEmpty (message = "Please Enter Relationship Manager")
    @NotNull
    private String relationshipManager;

    private Date reopeningDate;

    private String telexNo;

    private String ubCompanyCode;

    private String ubCustomer;

    private BigDecimal updateindex;

    private String url;


    private long companyGroupId;

    private long areaId;


    public long getCompanyGroupId() {
        return companyGroupId;
    }

    public void setCompanyGroupId(long companyGroupId) {
        this.companyGroupId = companyGroupId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountingEntryRequest() {
        return accountingEntryRequest;
    }

    public void setAccountingEntryRequest(String accountingEntryRequest) {
        this.accountingEntryRequest = accountingEntryRequest;
    }

    public String getAccountingEntryType() {
        return accountingEntryType;
    }

    public void setAccountingEntryType(String accountingEntryType) {
        this.accountingEntryType = accountingEntryType;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getBackupContact() {
        return backupContact;
    }

    public void setBackupContact(String backupContact) {
        this.backupContact = backupContact;
    }

    public String getBackupEmail1() {
        return backupEmail1;
    }

    public void setBackupEmail1(String backupEmail1) {
        this.backupEmail1 = backupEmail1;
    }

    public String getBackupEmail2() {
        return backupEmail2;
    }

    public void setBackupEmail2(String backupEmail2) {
        this.backupEmail2 = backupEmail2;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getClosedTill() {
        return closedTill;
    }

    public void setClosedTill(Date closedTill) {
        this.closedTill = closedTill;
    }

    public String getClosureReason() {
        return closureReason;
    }

    public void setClosureReason(String closureReason) {
        this.closureReason = closureReason;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }


    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDiscountable() {
        return discountable;
    }

    public void setDiscountable(String discountable) {
        this.discountable = discountable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaxNo1() {
        return faxNo1;
    }

    public void setFaxNo1(String faxNo1) {
        this.faxNo1 = faxNo1;
    }

    public String getFaxNo2() {
        return faxNo2;
    }

    public void setFaxNo2(String faxNo2) {
        this.faxNo2 = faxNo2;
    }

    public String getFaxNo3() {
        return faxNo3;
    }

    public void setFaxNo3(String faxNo3) {
        this.faxNo3 = faxNo3;
    }


    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public BigDecimal getOutstandingAmount() {
        return outstandingAmount;
    }

    public void setOutstandingAmount(BigDecimal outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public String getProductManager() {
        return productManager;
    }

    public void setProductManager(String productManager) {
        this.productManager = productManager;
    }

    public String getRelationshipManager() {
        return relationshipManager;
    }

    public void setRelationshipManager(String relationshipManager) {
        this.relationshipManager = relationshipManager;
    }

    public Date getReopeningDate() {
        return reopeningDate;
    }

    public void setReopeningDate(Date reopeningDate) {
        this.reopeningDate = reopeningDate;
    }

    public String getTelexNo() {
        return telexNo;
    }

    public void setTelexNo(String telexNo) {
        this.telexNo = telexNo;
    }

    public String getUbCompanyCode() {
        return ubCompanyCode;
    }

    public void setUbCompanyCode(String ubCompanyCode) {
        this.ubCompanyCode = ubCompanyCode;
    }

    public String getUbCustomer() {
        return ubCustomer;
    }

    public void setUbCustomer(String ubCustomer) {
        this.ubCustomer = ubCustomer;
    }

    public BigDecimal getUpdateindex() {
        return updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
}
