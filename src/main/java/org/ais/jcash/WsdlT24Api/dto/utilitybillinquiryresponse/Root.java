package org.ais.jcash.WsdlT24Api.dto.utilitybillinquiryresponse;

public class Root {

    public int responsecode;

    public Data data;

    public String messages;

    public int getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(int responsecode) {
        this.responsecode = responsecode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
