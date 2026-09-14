package org.ais.jcash.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ais.jcash.model.LkpUserAuthLevel;
import org.ais.jcash.model.TblRole;

import java.math.BigDecimal;
import java.util.Date;

public class UpdateBranchAuthMatrixDetailRequest {

    private long authMatrixDetailId;

    private BigDecimal srNo;

    private long userAuthLevelId;

    private long roleId;

    public BigDecimal getSrNo() {
        return srNo;
    }

    public void setSrNo(BigDecimal srNo) {
        this.srNo = srNo;
    }

    public long getUserAuthLevelId() {
        return userAuthLevelId;
    }

    public void setUserAuthLevelId(long userAuthLevelId) {
        this.userAuthLevelId = userAuthLevelId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getAuthMatrixDetailId() {
        return authMatrixDetailId;
    }

    public void setAuthMatrixDetailId(long authMatrixDetailId) {
        this.authMatrixDetailId = authMatrixDetailId;
    }
}
