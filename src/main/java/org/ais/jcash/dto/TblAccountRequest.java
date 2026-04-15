package org.ais.jcash.dto;

import org.ais.jcash.model.LkpBank;
import org.ais.jcash.model.LkpBranch;
import org.ais.jcash.model.TblCompany;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class TblAccountRequest {
    @NotEmpty (message = "Please Enter Valid Account Name")
    @NotNull (message = "Please Enter Valid Account Name")
    private String accountName;
    @NotEmpty (message = "Please Enter Valid Account Name")
    @NotNull (message = "Please Enter Valid Account Name")
    private String accountNo;
    @NotEmpty (message = "Allowed entry can't be empty")
    @NotNull (message = "Allowed entry can't be empty")
    private String allowEntry;

    private String closed;

    private Date closedDate;

    private Date closedTill;

    private String closureReason;
    @NotEmpty (message = "Please enter Entity")
    @NotNull (message = "Please enter Entity")
    private String entity;

    @NotEmpty (message = "Host account Number Can't be Empty")
    @NotNull
    private String hostAccountNo;

    private Date reopeningDate;


    private long bankId;

    private long branchId;


    private long companyId;

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

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}