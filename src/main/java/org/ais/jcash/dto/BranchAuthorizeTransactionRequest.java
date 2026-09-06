package org.ais.jcash.dto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/10/2022
 * Time: 4:42 PM
 * Project : jcash
 */
public class BranchAuthorizeTransactionRequest {

    private long transHeadId;

    private long authDetailId;

    private String status;

    private String reason;

    public long getTransHeadId() {
        return transHeadId;
    }

    public void setTransHeadId(long transHeadId) {
        this.transHeadId = transHeadId;
    }

    public long getAuthDetailId() {
        return authDetailId;
    }

    public void setAuthDetailId(long authDetailId) {
        this.authDetailId = authDetailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
