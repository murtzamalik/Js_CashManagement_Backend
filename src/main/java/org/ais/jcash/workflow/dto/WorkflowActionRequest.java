package org.ais.jcash.workflow.dto;

import java.util.Date;

public class WorkflowActionRequest {
    private Long txnId;
    private String comments;
    private String stopReason;
    private Date scheduleDate;
    private String idempotencyKey;

    public Long getTxnId() { return txnId; }
    public void setTxnId(Long txnId) { this.txnId = txnId; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    public String getStopReason() { return stopReason; }
    public void setStopReason(String stopReason) { this.stopReason = stopReason; }
    public Date getScheduleDate() { return scheduleDate; }
    public void setScheduleDate(Date scheduleDate) { this.scheduleDate = scheduleDate; }
    public String getIdempotencyKey() { return idempotencyKey; }
    public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; }
}
