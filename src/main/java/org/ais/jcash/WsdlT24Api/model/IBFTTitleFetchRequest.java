package org.ais.jcash.WsdlT24Api.model;

public class IBFTTitleFetchRequest {
    public String fromAccount;
    public String toAccount;
    public String toBankIMD;
    public String amount;

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
}
