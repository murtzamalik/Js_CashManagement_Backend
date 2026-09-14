package org.ais.jcash.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class TblParserDetailRequest {

    private String columnName;

    private String columnType;

    private String fixedLength;

    private String isMandatory;

    private String isSearchable;

    private BigDecimal maxLength;

    private BigDecimal minLength;

    private BigDecimal sequence;

    private long parserHeadId;

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

    public long getParserHeadId() {
        return parserHeadId;
    }

    public void setParserHeadId(long parserHeadId) {
        this.parserHeadId = parserHeadId;
    }

    public String getIsSearchable() {
        return isSearchable;
    }

    public void setIsSearchable(String isSearchable) {
        this.isSearchable = isSearchable;
    }

    public BigDecimal getSequence() {
        return sequence;
    }

    public void setSequence(BigDecimal sequence) {
        this.sequence = sequence;
    }
}
