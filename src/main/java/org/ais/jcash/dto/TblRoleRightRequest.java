package org.ais.jcash.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class TblRoleRightRequest {


    private String authorizeAllowed;

    private String hideYn;

    private String insertAllowed;

    private String selectAllowed;

    private String updateAllowed;

    private long menuId;

    private long roleId;


    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getAuthorizeAllowed() {
        return authorizeAllowed;
    }

    public void setAuthorizeAllowed(String authorizeAllowed) {
        this.authorizeAllowed = authorizeAllowed;
    }

    public String getHideYn() {
        return hideYn;
    }

    public void setHideYn(String hideYn) {
        this.hideYn = hideYn;
    }

    public String getInsertAllowed() {
        return insertAllowed;
    }

    public void setInsertAllowed(String insertAllowed) {
        this.insertAllowed = insertAllowed;
    }

    public String getSelectAllowed() {
        return selectAllowed;
    }

    public void setSelectAllowed(String selectAllowed) {
        this.selectAllowed = selectAllowed;
    }

    public String getUpdateAllowed() {
        return updateAllowed;
    }

    public void setUpdateAllowed(String updateAllowed) {
        this.updateAllowed = updateAllowed;
    }
}
