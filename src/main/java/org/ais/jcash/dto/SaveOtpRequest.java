package org.ais.jcash.dto;

import java.util.Date;

public class SaveOtpRequest {

    private String userId;

    private long userTyprId;

    private String otpPin;

    private Date effectiveFrom;

    private Date effectiveTo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getUserTyprId() {
        return userTyprId;
    }

    public void setUserTyprId(long userTyprId) {
        this.userTyprId = userTyprId;
    }

    public String getOtpPin() {
        return otpPin;
    }

    public void setOtpPin(String otpPin) {
        this.otpPin = otpPin;
    }

    public Date getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(Date effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public Date getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(Date effectiveTo) {
        this.effectiveTo = effectiveTo;
    }
}
