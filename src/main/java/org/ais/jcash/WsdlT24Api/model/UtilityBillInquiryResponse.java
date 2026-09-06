package org.ais.jcash.WsdlT24Api.model;

public class UtilityBillInquiryResponse {

    public String fromAccount;
    public String utilityCompanyCode;
    public String utilityConsumerNumber;
    public String subscriberNameOrConnectionType;
    public String billingMonth;
    public String totalAmountPayableWithinDueDate;
    public String paymentDueDate;
    public String totalAmountPayableAfterDueDate;
    public String billStatus;
    public String netCED;
    public String netWithholdingTax;

    public String getNetWithholdingTax() {
        return netWithholdingTax;
    }

    public void setNetWithholdingTax(String netWithholdingTax) {
        this.netWithholdingTax = netWithholdingTax;
    }


    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getUtilityCompanyCode() {
        return utilityCompanyCode;
    }

    public void setUtilityCompanyCode(String utilityCompanyCode) {
        this.utilityCompanyCode = utilityCompanyCode;
    }

    public String getUtilityConsumerNumber() {
        return utilityConsumerNumber;
    }

    public void setUtilityConsumerNumber(String utilityConsumerNumber) {
        this.utilityConsumerNumber = utilityConsumerNumber;
    }

    public String getSubscriberNameOrConnectionType() {
        return subscriberNameOrConnectionType;
    }

    public void setSubscriberNameOrConnectionType(String subscriberNameOrConnectionType) {
        this.subscriberNameOrConnectionType = subscriberNameOrConnectionType;
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(String billingMonth) {
        this.billingMonth = billingMonth;
    }

    public String getTotalAmountPayableWithinDueDate() {
        return totalAmountPayableWithinDueDate;
    }

    public void setTotalAmountPayableWithinDueDate(String totalAmountPayableWithinDueDate) {
        this.totalAmountPayableWithinDueDate = totalAmountPayableWithinDueDate;
    }

    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getTotalAmountPayableAfterDueDate() {
        return totalAmountPayableAfterDueDate;
    }

    public void setTotalAmountPayableAfterDueDate(String totalAmountPayableAfterDueDate) {
        this.totalAmountPayableAfterDueDate = totalAmountPayableAfterDueDate;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getNetCED() {
        return netCED;
    }

    public void setNetCED(String netCED) {
        this.netCED = netCED;
    }

}
