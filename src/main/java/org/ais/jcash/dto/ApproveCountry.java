package org.ais.jcash.dto;

import java.awt.*;
import java.math.BigDecimal;

public class ApproveCountry {

    private long countryId;

    private String mcStatus;

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public String getMcStatus() {
        return mcStatus;
    }

    public void setMcStatus(String mcStatus) {
        this.mcStatus = mcStatus;
    }
}
