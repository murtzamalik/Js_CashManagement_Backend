package org.ais.jcash.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewTransactionDetailResponse {

    private Date depositDate;

    private String depositSlipNo;

    private String depositSlipAmount;

    private BigDecimal noOfCheques;

    private BigDecimal noOfInvoices;

    private String depositorCellNo;

    private String depositorEmail;

    private Date lodgementCdate;

    private String lodgementStatus;

    private String lodgementReason;

    private String ldUserName;

    private  Date liquidationDate;

    private String liquidationStatus;

    private String liquidationReason;

    private String lqUserName;

    private BigDecimal bankId;

    private String beneficiaryAccountNumber;

    private String beneficiaryAccountTitle;

    private String beneficiaryName;

    private String beneficiaryAddress;

    private String beneficiaryEmail;

    private  String customerReferenceNumber;

    private BigDecimal paymentModeId;

    private BigDecimal branchId;

    private  String collectionAccountNumber;

    private String debitAccountNumber;

    List<ReviewTransactionDetailRequest2> authDetails = new ArrayList<>();


    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public String getDepositSlipNo() {
        return depositSlipNo;
    }

    public void setDepositSlipNo(String depositSlipNo) {
        this.depositSlipNo = depositSlipNo;
    }

    public String getDepositSlipAmount() {
        return depositSlipAmount;
    }

    public void setDepositSlipAmount(String depositSlipAmount) {
        this.depositSlipAmount = depositSlipAmount;
    }

    public BigDecimal getNoOfCheques() {
        return noOfCheques;
    }

    public void setNoOfCheques(BigDecimal noOfCheques) {
        this.noOfCheques = noOfCheques;
    }

    public BigDecimal getNoOfInvoices() {
        return noOfInvoices;
    }

    public void setNoOfInvoices(BigDecimal noOfInvoices) {
        this.noOfInvoices = noOfInvoices;
    }

    public String getDepositorCellNo() {
        return depositorCellNo;
    }

    public void setDepositorCellNo(String depositorCellNo) {
        this.depositorCellNo = depositorCellNo;
    }

    public String getDepositorEmail() {
        return depositorEmail;
    }

    public void setDepositorEmail(String depositorEmail) {
        this.depositorEmail = depositorEmail;
    }

    public Date getLodgementCdate() {
        return lodgementCdate;
    }

    public void setLodgementCdate(Date lodgementCdate) {
        this.lodgementCdate = lodgementCdate;
    }

    public String getLodgementStatus() {
        return lodgementStatus;
    }

    public void setLodgementStatus(String lodgementStatus) {
        this.lodgementStatus = lodgementStatus;
    }

    public String getLodgementReason() {
        return lodgementReason;
    }

    public void setLodgementReason(String lodgementReason) {
        this.lodgementReason = lodgementReason;
    }

    public String getLdUserName() {
        return ldUserName;
    }

    public void setLdUserName(String ldUserName) {
        this.ldUserName = ldUserName;
    }

    public Date getLiquidationDate() {
        return liquidationDate;
    }

    public void setLiquidationDate(Date liquidationDate) {
        this.liquidationDate = liquidationDate;
    }

    public String getLiquidationStatus() {
        return liquidationStatus;
    }

    public void setLiquidationStatus(String liquidationStatus) {
        this.liquidationStatus = liquidationStatus;
    }

    public String getLiquidationReason() {
        return liquidationReason;
    }

    public void setLiquidationReason(String liquidationReason) {
        this.liquidationReason = liquidationReason;
    }

    public String getLqUserName() {
        return lqUserName;
    }

    public void setLqUserName(String lqUserName) {
        this.lqUserName = lqUserName;
    }

    public BigDecimal getBankId() {
        return bankId;
    }

    public void setBankId(BigDecimal bankId) {
        this.bankId = bankId;
    }

    public String getBeneficiaryAccountNumber() {
        return beneficiaryAccountNumber;
    }

    public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
        this.beneficiaryAccountNumber = beneficiaryAccountNumber;
    }

    public String getBeneficiaryAccountTitle() {
        return beneficiaryAccountTitle;
    }

    public void setBeneficiaryAccountTitle(String beneficiaryAccountTitle) {
        this.beneficiaryAccountTitle = beneficiaryAccountTitle;
    }

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

    public String getBeneficiaryEmail() {
        return beneficiaryEmail;
    }

    public void setBeneficiaryEmail(String beneficiaryEmail) {
        this.beneficiaryEmail = beneficiaryEmail;
    }

    public String getCustomerReferenceNumber() {
        return customerReferenceNumber;
    }

    public void setCustomerReferenceNumber(String customerReferenceNumber) {
        this.customerReferenceNumber = customerReferenceNumber;
    }

    public BigDecimal getPaymentModeId() {
        return paymentModeId;
    }

    public void setPaymentModeId(BigDecimal paymentModeId) {
        this.paymentModeId = paymentModeId;
    }

    public BigDecimal getBranchId() {
        return branchId;
    }

    public void setBranchId(BigDecimal branchId) {
        this.branchId = branchId;
    }

    public String getCollectionAccountNumber() {
        return collectionAccountNumber;
    }

    public void setCollectionAccountNumber(String collectionAccountNumber) {
        this.collectionAccountNumber = collectionAccountNumber;
    }

    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public List<ReviewTransactionDetailRequest2> getAuthDetails() {
        return authDetails;
    }

    public void setAuthDetails(List<ReviewTransactionDetailRequest2> authDetails) {
        this.authDetails = authDetails;
    }
}
