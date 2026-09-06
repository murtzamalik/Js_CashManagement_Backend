package org.ais.jcash.WsdlT24Api.model;

public class InternalFundsTransferRequest {
    public String FromAccount;
    public String ToAccount;
    public String Amount;

    public String getFromAccount() {
        return FromAccount;
    }

    public void setFromAccount(String fromAccount) {
        FromAccount = fromAccount;
    }

    public String getToAccount() {
        return ToAccount;
    }

    public void setToAccount(String toAccount) {
        ToAccount = toAccount;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }
}
