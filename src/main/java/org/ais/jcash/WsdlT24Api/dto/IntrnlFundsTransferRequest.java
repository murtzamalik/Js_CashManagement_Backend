package org.ais.jcash.WsdlT24Api.dto;

import org.ais.jcash.WsdlT24Api.model.InternalFundsTransferRequest;
import org.ais.jcash.WsdlT24Api.model.InternalFundsTransferTitleFetchRequest;

public class IntrnlFundsTransferRequest {
    XmlHeaderInput xmlHeaderInput;
    InternalFundsTransferRequest internalFundsTransferRequest;
    InternalFundsTransferTitleFetchRequest internalFundsTransferTitleFetchRequest;

    public XmlHeaderInput getXmlHeaderInput() {
        return xmlHeaderInput;
    }

    public void setXmlHeaderInput(XmlHeaderInput xmlHeaderInput) {
        this.xmlHeaderInput = xmlHeaderInput;
    }

    public InternalFundsTransferRequest getInternalFundsTransferRequest() {
        return internalFundsTransferRequest;
    }

    public void setInternalFundsTransferRequest(InternalFundsTransferRequest internalFundsTransferRequest) {
        this.internalFundsTransferRequest = internalFundsTransferRequest;
    }

    public InternalFundsTransferTitleFetchRequest getInternalFundsTransferTitleFetchRequest() {
        return internalFundsTransferTitleFetchRequest;
    }

    public void setInternalFundsTransferTitleFetchRequest(InternalFundsTransferTitleFetchRequest internalFundsTransferTitleFetchRequest) {
        this.internalFundsTransferTitleFetchRequest = internalFundsTransferTitleFetchRequest;
    }
}
