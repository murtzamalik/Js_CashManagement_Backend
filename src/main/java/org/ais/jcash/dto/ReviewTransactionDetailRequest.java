package org.ais.jcash.dto;

import java.math.BigDecimal;

public class ReviewTransactionDetailRequest {

    private BigDecimal transHeadId;

    public BigDecimal getTransHeadId() {
        return transHeadId;
    }

    public void setTransHeadId(BigDecimal transHeadId) {
        this.transHeadId = transHeadId;
    }
}
