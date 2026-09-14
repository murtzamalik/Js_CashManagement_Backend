package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class UpdateAccountRequest {
    @NotEmpty (message = "Please enter Account Name")
    @NotNull
    private String accountName;
    @NotEmpty (message = "Please enter account number")
    @NotNull
    private String accountNo;
    @NotEmpty (message = "Please select Allow Entry")
    @NotNull
    private String allowEntry;

    private String closed;

    private Date closedDate;

    private Date closedTill;

    private String closureReason;
    @NotEmpty (message = "Please enter Entity")
    @NotNull
    private String entity;

    @NotEmpty (message = "Host Account Number can't be empty")
    @NotNull
    private String hostAccountNo;

    private Date reopeningDate;

    private long bankId;

    private long branchId;

    private long accountId;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAllowEntry() {
        return allowEntry;
    }

    public void setAllowEntry(String allowEntry) {
        this.allowEntry = allowEntry;
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

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getHostAccountNo() {
        return hostAccountNo;
    }

    public void setHostAccountNo(String hostAccountNo) {
        this.hostAccountNo = hostAccountNo;
    }

    public Date getReopeningDate() {
        return reopeningDate;
    }

    public void setReopeningDate(Date reopeningDate) {
        this.reopeningDate = reopeningDate;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}