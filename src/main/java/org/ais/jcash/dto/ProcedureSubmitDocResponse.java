package org.ais.jcash.dto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/10/2022
 * Time: 12:57 PM
 * Project : jcash
 */
public class ProcedureSubmitDocResponse {


    private String authComplete;

    private int status;

    private String statusDescr;


    public String getAuthComplete() {
        return authComplete;
    }

    public void setAuthComplete(String authComplete) {
        this.authComplete = authComplete;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusDescr() {
        return statusDescr;
    }

    public void setStatusDescr(String statusDescr) {
        this.statusDescr = statusDescr;
    }
}
