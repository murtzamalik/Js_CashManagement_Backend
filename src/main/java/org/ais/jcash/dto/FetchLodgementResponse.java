package org.ais.jcash.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/8/2022
 * Time: 11:45 AM
 * Project : jcash
 */
public class FetchLodgementResponse {


    private BigDecimal transHeadId;

    private BigDecimal collectioAccountId;

    private String companyName;

    private String branchName;

    private String bankName;

    private String depositSlipNo;

    private String chqNo;

    private Date chqDate;

    private BigDecimal depositSlipAmount;


    public BigDecimal getTransHeadId() {
        return transHeadId;
    }

    public void setTransHeadId(BigDecimal transHeadId) {
        this.transHeadId = transHeadId;
    }

    public BigDecimal getCollectioAccountId() {
        return collectioAccountId;
    }

    public void setCollectioAccountId(BigDecimal collectioAccountId) {
        this.collectioAccountId = collectioAccountId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDepositSlipNo() {
        return depositSlipNo;
    }

    public void setDepositSlipNo(String depositSlipNo) {
        this.depositSlipNo = depositSlipNo;
    }

    public String getChqNo() {
        return chqNo;
    }

    public void setChqNo(String chqNo) {
        this.chqNo = chqNo;
    }

    public Date getChqDate() {
        return chqDate;
    }

    public void setChqDate(Date chqDate) {
        this.chqDate = chqDate;
    }

    public BigDecimal getDepositSlipAmount() {
        return depositSlipAmount;
    }

    public void setDepositSlipAmount(BigDecimal depositSlipAmount) {
        this.depositSlipAmount = depositSlipAmount;
    }
}
