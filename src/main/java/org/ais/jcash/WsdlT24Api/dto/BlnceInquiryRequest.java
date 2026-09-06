package org.ais.jcash.WsdlT24Api.dto;

import org.ais.jcash.WsdlT24Api.dto.XmlHeaderInput;
import org.ais.jcash.WsdlT24Api.model.BalanceInquiryRequest;

public class BlnceInquiryRequest {
    XmlHeaderInput xmlHeaderInput;
    BalanceInquiryRequest balanceInquiryRequest;

    public XmlHeaderInput getXmlHeaderInput() {
        return xmlHeaderInput;
    }

    public void setXmlHeaderInput(XmlHeaderInput xmlHeaderInput) {
        this.xmlHeaderInput = xmlHeaderInput;
    }

    public BalanceInquiryRequest getBalanceInquiryRequest() {
        return balanceInquiryRequest;
    }

    public void setBalanceInquiryRequest(BalanceInquiryRequest balanceInquiryRequest) {
        this.balanceInquiryRequest = balanceInquiryRequest;
    }
}
