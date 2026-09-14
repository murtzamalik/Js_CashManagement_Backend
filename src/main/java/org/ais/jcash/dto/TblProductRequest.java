package org.ais.jcash.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class TblProductRequest {

    private long productId;

    private Date checkDate;

    private String checkerComments;

    private BigDecimal checkerId;

    private String closed;

    private Date closedDate;

    private Date closedTill;

    private String closureReason;

    private String corrBankExpense;

    private String courierExpense;

    private Date createdate;

    private String feeCommision;

    private String floatRevenueAccount;

    private String interestRevenueAccount;

    private Date lastupdatedate;

    private String mailExpense;

    private String masterProductCode;

    private String masterProductName;

    private String mcStatus;

    private String miscellaneousExpense;

    private String productBehavior;

    private BigDecimal productManagerId;

    private String productNature;

    private String productRevenueAccount;

    private String productSundryAccount;

    private String productType;

    private Date reopeningDate;

    private String subProductCode;

    private String subProductName;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckerComments() {
        return checkerComments;
    }

    public void setCheckerComments(String checkerComments) {
        this.checkerComments = checkerComments;
    }

    public BigDecimal getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(BigDecimal checkerId) {
        this.checkerId = checkerId;
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

    public String getCorrBankExpense() {
        return corrBankExpense;
    }

    public void setCorrBankExpense(String corrBankExpense) {
        this.corrBankExpense = corrBankExpense;
    }

    public String getCourierExpense() {
        return courierExpense;
    }

    public void setCourierExpense(String courierExpense) {
        this.courierExpense = courierExpense;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getFeeCommision() {
        return feeCommision;
    }

    public void setFeeCommision(String feeCommision) {
        this.feeCommision = feeCommision;
    }

    public String getFloatRevenueAccount() {
        return floatRevenueAccount;
    }

    public void setFloatRevenueAccount(String floatRevenueAccount) {
        this.floatRevenueAccount = floatRevenueAccount;
    }

    public String getInterestRevenueAccount() {
        return interestRevenueAccount;
    }

    public void setInterestRevenueAccount(String interestRevenueAccount) {
        this.interestRevenueAccount = interestRevenueAccount;
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public String getMailExpense() {
        return mailExpense;
    }

    public void setMailExpense(String mailExpense) {
        this.mailExpense = mailExpense;
    }

    public String getMasterProductCode() {
        return masterProductCode;
    }

    public void setMasterProductCode(String masterProductCode) {
        this.masterProductCode = masterProductCode;
    }

    public String getMasterProductName() {
        return masterProductName;
    }

    public void setMasterProductName(String masterProductName) {
        this.masterProductName = masterProductName;
    }

    public String getMcStatus() {
        return mcStatus;
    }

    public void setMcStatus(String mcStatus) {
        this.mcStatus = mcStatus;
    }

    public String getMiscellaneousExpense() {
        return miscellaneousExpense;
    }

    public void setMiscellaneousExpense(String miscellaneousExpense) {
        this.miscellaneousExpense = miscellaneousExpense;
    }

    public String getProductBehavior() {
        return productBehavior;
    }

    public void setProductBehavior(String productBehavior) {
        this.productBehavior = productBehavior;
    }

    public BigDecimal getProductManagerId() {
        return productManagerId;
    }

    public void setProductManagerId(BigDecimal productManagerId) {
        this.productManagerId = productManagerId;
    }

    public String getProductNature() {
        return productNature;
    }

    public void setProductNature(String productNature) {
        this.productNature = productNature;
    }

    public String getProductRevenueAccount() {
        return productRevenueAccount;
    }

    public void setProductRevenueAccount(String productRevenueAccount) {
        this.productRevenueAccount = productRevenueAccount;
    }

    public String getProductSundryAccount() {
        return productSundryAccount;
    }

    public void setProductSundryAccount(String productSundryAccount) {
        this.productSundryAccount = productSundryAccount;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Date getReopeningDate() {
        return reopeningDate;
    }

    public void setReopeningDate(Date reopeningDate) {
        this.reopeningDate = reopeningDate;
    }

    public String getSubProductCode() {
        return subProductCode;
    }

    public void setSubProductCode(String subProductCode) {
        this.subProductCode = subProductCode;
    }

    public String getSubProductName() {
        return subProductName;
    }

    public void setSubProductName(String subProductName) {
        this.subProductName = subProductName;
    }
}
