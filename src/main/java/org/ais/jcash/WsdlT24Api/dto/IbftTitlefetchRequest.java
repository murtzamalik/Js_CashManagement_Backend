package org.ais.jcash.WsdlT24Api.dto;

import org.ais.jcash.WsdlT24Api.model.IBFTTitleFetchRequest;

public class IbftTitlefetchRequest {
    XmlHeaderInput xmlHeaderInput;

    IBFTTitleFetchRequest ibftTitleFetchRequest;

    public XmlHeaderInput getXmlHeaderInput() {
        return xmlHeaderInput;
    }

    public void setXmlHeaderInput(XmlHeaderInput xmlHeaderInput) {
        this.xmlHeaderInput = xmlHeaderInput;
    }

    public IBFTTitleFetchRequest getIbftTitleFetchRequest() {
        return ibftTitleFetchRequest;
    }

    public void setIbftTitleFetchRequest(IBFTTitleFetchRequest ibftTitleFetchRequest) {
        this.ibftTitleFetchRequest = ibftTitleFetchRequest;
    }
}
