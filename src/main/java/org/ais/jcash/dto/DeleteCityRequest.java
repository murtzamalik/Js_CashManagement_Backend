package org.ais.jcash.dto;

import java.math.BigDecimal;

public class DeleteCityRequest {
    private long cityId;

    private BigDecimal isDeleted;

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public BigDecimal getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(BigDecimal isDeleted) {
        this.isDeleted = isDeleted;
    }
}
