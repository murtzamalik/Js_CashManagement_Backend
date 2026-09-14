package org.ais.jcash.WsdlT24Api.model;

public class BalanceInquiryResponse {
    public String accountNumber;
    public String accountType;
    public String accountCurrency;
    public String accountStatus;
    public String workingBalance;
    public String ledgerBalance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getWorkingBalance() {
        return workingBalance;
    }

    public void setWorkingBalance(String workingBalance) {
        this.workingBalance = workingBalance;
    }

    public String getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(String ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }
}
