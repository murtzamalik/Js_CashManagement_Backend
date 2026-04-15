package org.ais.jcash.dto;

import java.math.BigDecimal;
import java.util.Date;

public class UpdateParserHeadRequest {

    private long parserHeadId;

    private String code;

    private String description;

    private String isActive;

    public long getParserHeadId() {
        return parserHeadId;
    }

    public void setParserHeadId(long parserHeadId) {
        this.parserHeadId = parserHeadId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
