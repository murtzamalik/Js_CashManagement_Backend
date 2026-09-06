package org.ais.jcash.WsdlT24Api.dto;

import org.ais.jcash.WsdlT24Api.model.UtilityBillInquiryRequest;

public class UtltyBillInquiryRequest {

    XmlHeaderInput xmlHeaderInput;

    UtilityBillInquiryRequest  utilityBillInquiryRequest;

    public XmlHeaderInput getXmlHeaderInput() {
        return xmlHeaderInput;
    }

    public void setXmlHeaderInput(XmlHeaderInput xmlHeaderInput) {
        this.xmlHeaderInput = xmlHeaderInput;
    }

    public UtilityBillInquiryRequest getUtiltyBillInquiryRequest() {
        return utilityBillInquiryRequest;
    }

    public void setUtiltyBillInquiryRequest(UtilityBillInquiryRequest utiltyBillInquiryRequest) {
        this.utilityBillInquiryRequest = utiltyBillInquiryRequest;
    }
}
