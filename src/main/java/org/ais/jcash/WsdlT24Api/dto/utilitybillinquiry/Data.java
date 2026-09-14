package org.ais.jcash.WsdlT24Api.dto.utilitybillinquiry;

public class Data {
    public String paymentDueDate;
    public String utilityCompanyCode;
    public String billingMonth;
    public String totalAmountPayableWithinDueDate;
    public String billStatus;
    public String fromAccount;
    public String subcriberNameorConnectionType;
    public String totalAmountPayableAfterDueDate;
    public String utilityConsumerNumber;
    public String netWithHoldingTax;
    public String netCED;

    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getUtilityCompanyCode() {
        return utilityCompanyCode;
    }

    public void setUtilityCompanyCode(String utilityCompanyCode) {
        this.utilityCompanyCode = utilityCompanyCode;
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

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getSubcriberNameorConnectionType() {
        return subcriberNameorConnectionType;
    }

    public void setSubcriberNameorConnectionType(String subcriberNameorConnectionType) {
        this.subcriberNameorConnectionType = subcriberNameorConnectionType;
    }

    public String getTotalAmountPayableAfterDueDate() {
        return totalAmountPayableAfterDueDate;
    }

    public void setTotalAmountPayableAfterDueDate(String totalAmountPayableAfterDueDate) {
        this.totalAmountPayableAfterDueDate = totalAmountPayableAfterDueDate;
    }

    public String getUtilityConsumerNumber() {
        return utilityConsumerNumber;
    }

    public void setUtilityConsumerNumber(String utilityConsumerNumber) {
        this.utilityConsumerNumber = utilityConsumerNumber;
    }

    public String getNetWithHoldingTax() {
        return netWithHoldingTax;
    }

    public void setNetWithHoldingTax(String netWithHoldingTax) {
        this.netWithHoldingTax = netWithHoldingTax;
    }

    public String getNetCED() {
        return netCED;
    }

    public void setNetCED(String netCED) {
        this.netCED = netCED;
    }
}
