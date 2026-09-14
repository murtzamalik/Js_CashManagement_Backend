package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateAuthMatrixDetailRequest {


    private Long srNo;

    private long authMatrixDetailId;

    private long userId;

    private long userLevelId;

    public Long getSrNo() {
        return srNo;
    }

    public void setSrNo(Long srNo) {
        this.srNo = srNo;
    }

    public long getAuthMatrixDetailId() {
        return authMatrixDetailId;
    }

    public void setAuthMatrixDetailId(long authMatrixDetailId) {
        this.authMatrixDetailId = authMatrixDetailId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(long userLevelId) {
        this.userLevelId = userLevelId;
    }
}
