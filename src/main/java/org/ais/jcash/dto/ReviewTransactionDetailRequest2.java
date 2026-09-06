package org.ais.jcash.dto;

import java.util.Date;

public class ReviewTransactionDetailRequest2 {
    private String roleDescription;

    private String userName;

    private String authStatus;

    private String authComments;

    private Date authDate;

    private String remarks;

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getAuthComments() {
        return authComments;
    }

    public void setAuthComments(String authComments) {
        this.authComments = authComments;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
