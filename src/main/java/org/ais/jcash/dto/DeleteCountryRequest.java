package org.ais.jcash.dto;

import java.math.BigDecimal;

public class DeleteCountryRequest {

    private long countryId;

    private BigDecimal isDeleted;

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public BigDecimal getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(BigDecimal isDeleted) {
        this.isDeleted = isDeleted;
    }
}
