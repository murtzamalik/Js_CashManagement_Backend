package org.ais.jcash.dto;

import javax.validation.constraints.NotBlank;

public class IbftTitleFetchPaymentRequest {

    @NotBlank
    private String fromAccount;

    @NotBlank
    private String toAccount;

    @NotBlank
    private String toBankIMD;

    @NotBlank
    private String amount;

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
