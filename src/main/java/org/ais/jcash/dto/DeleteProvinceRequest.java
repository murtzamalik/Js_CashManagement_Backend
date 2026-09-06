package org.ais.jcash.dto;

import java.math.BigDecimal;

public class DeleteProvinceRequest {

    private long provinceId;

    private BigDecimal isDeleted;

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public BigDecimal getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(BigDecimal isDeleted) {
        this.isDeleted = isDeleted;
    }
}
