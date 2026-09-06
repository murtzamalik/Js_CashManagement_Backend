package org.ais.jcash.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReviewTransactionResponse {

    private BigDecimal transHeadId;

    private String masterProductName;

    private String companyName;

    private Date transDate;

    private BigDecimal transAmount;

    private String createUser;

    private String authStatus;

    private Date authDate;

    private String authUser;

    private  String authComments;


    public BigDecimal getTransHeadId() {
        return transHeadId;
    }

    public void setTransHeadId(BigDecimal transHeadId) {
        this.transHeadId = transHeadId;
    }

    public String getMasterProductName() {
        return masterProductName;
    }

    public void setMasterProductName(String masterProductName) {
        this.masterProductName = masterProductName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }

    public String getAuthUser() {
        return authUser;
    }

    public void setAuthUser(String authUser) {
        this.authUser = authUser;
    }

    public String getAuthComments() {
        return authComments;
    }

    public void setAuthComments(String authComments) {
        this.authComments = authComments;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}
