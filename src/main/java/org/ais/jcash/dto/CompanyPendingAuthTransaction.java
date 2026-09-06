package org.ais.jcash.dto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 3/1/2022
 * Time: 12:21 PM
 * Project : jcash
 */


import java.math.BigDecimal;
import java.util.Date;

public class CompanyPendingAuthTransaction {


    private BigDecimal transHeadId;

    private  BigDecimal authDetailId;

    private String masterProductName;

    private String companyName;

    private String benName;

    private Date TransDate;

    private String docNo;

    private BigDecimal transAmount;

    private Date authDate;

    private  String authComments;

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

    public String getMasterProductName() {
        return masterProductName;
    }

    public void setMasterProductName(String masterProductName) {
        this.masterProductName = masterProductName;
    }


    public String getBenName() {
        return benName;
    }

    public void setBenName(String benName) {
        this.benName = benName;
    }

    public Date getTransDate() {
        return TransDate;
    }

    public void setTransDate(Date transDate) {
        TransDate = transDate;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }

    public String getAuthComments() {
        return authComments;
    }

    public void setAuthComments(String authComments) {
        this.authComments = authComments;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
