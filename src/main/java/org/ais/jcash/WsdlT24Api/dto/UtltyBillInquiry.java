package org.ais.jcash.WsdlT24Api.dto;

import org.ais.jcash.WsdlT24Api.model.UtilityBillInquiryRequest;

public class UtltyBillInquiry {
    XmlHeaderInput xmlHeaderInput;
    UtilityBillInquiryRequest utilityBillInquiryRequest;

    public XmlHeaderInput getXmlHeaderInput() {
        return xmlHeaderInput;
    }

    public void setXmlHeaderInput(XmlHeaderInput xmlHeaderInput) {
        this.xmlHeaderInput = xmlHeaderInput;
    }

    public UtilityBillInquiryRequest getUtilityBillInquiryRequest() {
        return utilityBillInquiryRequest;
    }

    public void setUtilityBillInquiryRequest(UtilityBillInquiryRequest utilityBillInquiryRequest) {
        this.utilityBillInquiryRequest = utilityBillInquiryRequest;
    }
}
