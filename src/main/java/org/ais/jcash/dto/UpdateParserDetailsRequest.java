package org.ais.jcash.dto;

import java.math.BigDecimal;

public class UpdateParserDetailsRequest {

    private long parserDetailId;

    private String columnName;

    private String columnType;

    private String fixedLength;

    private String isMandatory;

    private BigDecimal maxLength;

    private BigDecimal minLength;

    public long getParserDetailId() {
        return parserDetailId;
    }

    public void setParserDetailId(long parserDetailId) {
        this.parserDetailId = parserDetailId;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getFixedLength() {
        return fixedLength;
    }

    public void setFixedLength(String fixedLength) {
        this.fixedLength = fixedLength;
    }

    public String getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(String isMandatory) {
        this.isMandatory = isMandatory;
    }

    public BigDecimal getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(BigDecimal maxLength) {
        this.maxLength = maxLength;
    }

    public BigDecimal getMinLength() {
        return minLength;
    }

    public void setMinLength(BigDecimal minLength) {
        this.minLength = minLength;
    }
}
