package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TblAuthMatrixDetailBranchRequest {


    private Long srNo;

    private long authMatrixHeadId;


    private long userId;

    private long userLevelId;

    private long roleId;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public Long getSrNo() {
        return srNo;
    }

    public void setSrNo(Long srNo) {
        this.srNo = srNo;
    }

    public long getAuthMatrixHeadId() {
        return authMatrixHeadId;
    }

    public void setAuthMatrixHeadId(long authMatrixHeadId) {
        this.authMatrixHeadId = authMatrixHeadId;
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
