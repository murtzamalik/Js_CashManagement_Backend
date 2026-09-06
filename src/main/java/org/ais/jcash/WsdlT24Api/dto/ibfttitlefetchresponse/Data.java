package org.ais.jcash.WsdlT24Api.dto.ibfttitlefetchresponse;

public class Data{
    public String toAccount;
    public String amount;
    public String toBranchName;
    public String fromAccount;
    public String toBankName;
    public String toBankIMD;
    public String toAccountTitle;

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getToBranchName() {
        return toBranchName;
    }

    public void setToBranchName(String toBranchName) {
        this.toBranchName = toBranchName;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToBankName() {
        return toBankName;
    }

    public void setToBankName(String toBankName) {
        this.toBankName = toBankName;
    }

    public String getToBankIMD() {
        return toBankIMD;
    }

    public void setToBankIMD(String toBankIMD) {
        this.toBankIMD = toBankIMD;
    }

    public String getToAccountTitle() {
        return toAccountTitle;
    }

    public void setToAccountTitle(String toAccountTitle) {
        this.toAccountTitle = toAccountTitle;
    }
}
