package org.ais.jcash.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AddCityRequest {

    private String cityCode;

    private String cityName;

    private long provinceId;

    private long countryId;

    private String isActive;

    private BigDecimal checkerId;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public BigDecimal getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(BigDecimal checkerId) {
        this.checkerId = checkerId;
    }
}

