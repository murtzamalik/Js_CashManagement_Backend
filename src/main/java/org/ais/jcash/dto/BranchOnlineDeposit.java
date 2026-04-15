package org.ais.jcash.dto;

import org.ais.jcash.model.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/31/2022
 * Time: 10:50 AM
 * Project : jcash
 */
public class BranchOnlineDeposit {




    private Date depositDate;

    private BigDecimal depositSlipAmount;

    private String depositSlipNo;

    private String depositorCellNo;

    private String depositorEmail;

    private BigDecimal noOfCheques;

    private BigDecimal noOfInvoices;

    private String  paymentMode;

    private long accountId;

    private long companyId;

    private long productId;


    private List<BranchOnlineDepositDetail> branchOnlineDepositDetails = new ArrayList<>();


    private long fileDetailId;

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public BigDecimal getDepositSlipAmount() {
        return depositSlipAmount;
    }

    public void setDepositSlipAmount(BigDecimal depositSlipAmount) {
        this.depositSlipAmount = depositSlipAmount;
    }

    public String getDepositSlipNo() {
        return depositSlipNo;
    }

    public void setDepositSlipNo(String depositSlipNo) {
        this.depositSlipNo = depositSlipNo;
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

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
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

    public List<BranchOnlineDepositDetail> getBranchOnlineDepositDetails() {
        return branchOnlineDepositDetails;
    }

    public void setBranchOnlineDepositDetails(List<BranchOnlineDepositDetail> branchOnlineDepositDetails) {
        this.branchOnlineDepositDetails = branchOnlineDepositDetails;
    }

    public long getFileDetailId() {
        return fileDetailId;
    }

    public void setFileDetailId(long fileDetailId) {
        this.fileDetailId = fileDetailId;
    }
}
