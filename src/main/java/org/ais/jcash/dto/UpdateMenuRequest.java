package org.ais.jcash.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:34 PM
 * Project : jcash
 */
public class UpdateMenuRequest {


    private long menuId;

    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    private BigDecimal updateindex;

    private String MenuDescription;

    private String IconName;

    private String IconPath;

    private String MenuCode;

    private String MenuPath;

    private String MenuType;


    public String getMenuDescription() {
        return MenuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        MenuDescription = menuDescription;
    }

    public String getIconName() {
        return IconName;
    }

    public void setIconName(String iconName) {
        IconName = iconName;
    }

    public String getIconPath() {
        return IconPath;
    }

    public void setIconPath(String iconPath) {
        IconPath = iconPath;
    }

    public String getMenuCode() {
        return MenuCode;
    }

    public void setMenuCode(String menuCode) {
        MenuCode = menuCode;
    }

    public String getMenuPath() {
        return MenuPath;
    }

    public void setMenuPath(String menuPath) {
        MenuPath = menuPath;
    }

    public String getMenuType() {
        return MenuType;
    }

    public void setMenuType(String menuType) {
        MenuType = menuType;
    }

    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }


    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public BigDecimal getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(BigDecimal lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }
    public BigDecimal getUpdateindex() {
        return updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }
}
