package org.ais.jcash.dto;

import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UpdateLodgmentRequest {

    private List<Integer> transHeadIds;

    private String lodgementReason;

    private String lodgementStatus;

    private String liquidationReason;

    private String liquidationStatus;


    public String getLiquidationReason() {
        return liquidationReason;
    }

    public void setLiquidationReason(String liquidationReason) {
        this.liquidationReason = liquidationReason;
    }

    public String getLiquidationStatus() {
        return liquidationStatus;
    }

    public void setLiquidationStatus(String liquidationStatus) {
        this.liquidationStatus = liquidationStatus;
    }

    public List<Integer> getTransHeadIds() {
        return transHeadIds;
    }

    public void setTransHeadIds(List<Integer> transHeadIds) {
        this.transHeadIds = transHeadIds;
    }

    public String getLodgementReason() {
        return lodgementReason;
    }

    public void setLodgementReason(String lodgementReason) {
        this.lodgementReason = lodgementReason;
    }

    public String getLodgementStatus() {
        return lodgementStatus;
    }

    public void setLodgementStatus(String lodgementStatus) {
        this.lodgementStatus = lodgementStatus;
    }
}
