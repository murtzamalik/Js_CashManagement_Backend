package org.ais.jcash.WsdlT24Api.model;

public class IBFTTitleFetchResponse {
    public String fromAccount;
    public String toAccount;
    public String toBankIMD;
    public String amount;
    public String toAccountTitle;
    public String toBranchName;
    public String toBankName;

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getToBankIMD() {
        return toBankIMD;
    }

    public void setToBankIMD(String toBankIMD) {
        this.toBankIMD = toBankIMD;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getToAccountTitle() {
        return toAccountTitle;
    }

    public void setToAccountTitle(String toAccountTitle) {
        this.toAccountTitle = toAccountTitle;
    }

    public String getToBranchName() {
        return toBranchName;
    }

    public void setToBranchName(String toBranchName) {
        this.toBranchName = toBranchName;
    }

    public String getToBankName() {
        return toBankName;
    }

    public void setToBankName(String toBankName) {
        this.toBankName = toBankName;
    }
}
