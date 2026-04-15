package org.ais.jcash.WsdlT24Api.dto.balanceinquiry;

public class Data {
    public String accountStatus;
    public String ledgerBalance;
    public String accountNo;
    public String accountType;
    public String workingBalance;
    public String accountCurrency;

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(String ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getWorkingBalance() {
        return workingBalance;
    }

    public void setWorkingBalance(String workingBalance) {
        this.workingBalance = workingBalance;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }
}
