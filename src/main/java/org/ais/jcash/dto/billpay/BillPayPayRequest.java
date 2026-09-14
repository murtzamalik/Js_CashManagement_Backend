package org.ais.jcash.dto.billpay;

import javax.validation.constraints.NotBlank;

public class BillPayPayRequest {

    @NotBlank
    private String companyCode;

    @NotBlank
    private String consumerNumber;

    @NotBlank
    private String amount;

    private String fromAccount;

    private String categoryCode;

    private String companyName;

    private String billingMonth;

    private String subscriberName;

    private String securityDeviceCode;

    private String custRef;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(String consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(String billingMonth) {
        this.billingMonth = billingMonth;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getSecurityDeviceCode() {
        return securityDeviceCode;
    }

    public void setSecurityDeviceCode(String securityDeviceCode) {
        this.securityDeviceCode = securityDeviceCode;
    }

    public String getCustRef() {
        return custRef;
    }

    public void setCustRef(String custRef) {
        this.custRef = custRef;
    }
}
