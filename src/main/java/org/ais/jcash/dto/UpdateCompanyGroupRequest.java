package org.ais.jcash.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class UpdateCompanyGroupRequest {

    private long companyGroupId;
    @NotEmpty (message = "Please Enter Account Number")
    @NotNull
    private String accountNo;

    private String address1;

    private String address2;

    private String address3;

    private BigDecimal cisNo;

    private String closureReason;
    @NotEmpty (message = "Please Enter Contact Number")
    @NotNull
    private String contactNo;
    @NotEmpty (message = "Please enter Contact Person")
    @NotNull
    private String contactPerson;

    private BigDecimal creditLimit;

    private String discountable;
    @NotEmpty (message = "Please enter E-Mail address")
    @NotNull
    private String email;

    private String faxNo;
    @NotEmpty (message = "Please select Group")
    @NotNull
    private String groupCode;
    @NotEmpty (message = "Please enter group name")
    @NotNull
    private String groupName;

    private Date lastupdatedate;

    private BigDecimal lastupdateuser;
    @NotEmpty (message = "Please enter Mobile Number")
    @NotNull
    private String mobileNo;

    private BigDecimal outstandingAmount;

    private String relationship;

    private BigDecimal updateindex;

    private String url;

    private Date closedDate;

    private Date closedTill;

    private Date reopeningDate;

    private String closed;

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

    public Date getReopeningDate() {
        return reopeningDate;
    }

    public void setReopeningDate(Date reopeningDate) {
        this.reopeningDate = reopeningDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public long getCompanyGroupId() {
        return companyGroupId;
    }

    public void setCompanyGroupId(long companyGroupId) {
        this.companyGroupId = companyGroupId;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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


    public String getClosureReason() {
        return closureReason;
    }

    public void setClosureReason(String closureReason) {
        this.closureReason = closureReason;
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

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public BigDecimal getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(BigDecimal lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
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

    public BigDecimal getCisNo() {
        return cisNo;
    }

    public void setCisNo(BigDecimal cisNo) {
        this.cisNo = cisNo;
    }
}
