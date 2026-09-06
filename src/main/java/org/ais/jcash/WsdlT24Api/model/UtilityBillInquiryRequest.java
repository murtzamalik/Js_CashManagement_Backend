package org.ais.jcash.WsdlT24Api.model;

public class UtilityBillInquiryRequest {

    public String FromAccount;
    public String UtilityCompanyCode;
    public String UtilityConsumerNumber;

    public String getFromAccount() {
        return FromAccount;
    }

    public void setFromAccount(String fromAccount) {
        FromAccount = fromAccount;
    }

    public String getUtilityCompanyCode() {
        return UtilityCompanyCode;
    }

    public void setUtilityCompanyCode(String utilityCompanyCode) {
        UtilityCompanyCode = utilityCompanyCode;
    }

    public String getUtilityConsumerNumber() {
        return UtilityConsumerNumber;
    }

    public void setUtilityConsumerNumber(String utilityConsumerNumber) {
        UtilityConsumerNumber = utilityConsumerNumber;
    }
}
