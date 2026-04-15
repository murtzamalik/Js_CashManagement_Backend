package org.ais.jcash.dto;

import java.math.BigDecimal;
import java.util.Date;

public class UpdateProvinceRequest {

    private long provinceId;

    private String provinceCode;

    private String provinceName;

    private String isActive;

    private BigDecimal isDeleted;

    private BigDecimal countryId;

    private String mcStatus;

    private BigDecimal checkerId;

    private String checkerComments;

    private Date checkDate;

    private BigDecimal createUser;

    private Date createDate;

    private BigDecimal lastUpdateUser;

    private Date lastUpdateDate;

    private BigDecimal updateIndex;

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public BigDecimal getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(BigDecimal isDeleted) {
        this.isDeleted = isDeleted;
    }

    public BigDecimal getCountryId() {
        return countryId;
    }

    public void setCountryId(BigDecimal countryId) {
        this.countryId = countryId;
    }

    public String getMcStatus() {
        return mcStatus;
    }

    public void setMcStatus(String mcStatus) {
        this.mcStatus = mcStatus;
    }

    public BigDecimal getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(BigDecimal checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckerComments() {
        return checkerComments;
    }

    public void setCheckerComments(String checkerComments) {
        this.checkerComments = checkerComments;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public BigDecimal getCreateUser() {
        return createUser;
    }

    public void setCreateUser(BigDecimal createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(BigDecimal lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public BigDecimal getUpdateIndex() {
        return updateIndex;
    }

    public void setUpdateIndex(BigDecimal updateIndex) {
        this.updateIndex = updateIndex;
    }
}
