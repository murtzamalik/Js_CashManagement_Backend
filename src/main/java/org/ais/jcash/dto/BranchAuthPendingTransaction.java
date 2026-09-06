package org.ais.jcash.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/10/2022
 * Time: 4:03 PM
 * Project : jcash
 */
public class BranchAuthPendingTransaction {

    private BigDecimal transHeadId;

    private BigDecimal authDetailId;

    private String productName;

    private String companyName;

    private Date depositDate;

    private String depositSlipNo;

    private BigDecimal depositAmount;

    private Date authDate;

    private String authComents;

    public BigDecimal getTransHeadId() {
        return transHeadId;
    }

    public void setTransHeadId(BigDecimal transHeadId) {
        this.transHeadId = transHeadId;
    }

    public BigDecimal getAuthDetailId() {
        return authDetailId;
    }

    public void setAuthDetailId(BigDecimal authDetailId) {
        this.authDetailId = authDetailId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

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

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }

    public String getAuthComents() {
        return authComents;
    }

    public void setAuthComents(String authComents) {
        this.authComents = authComents;
    }
}
