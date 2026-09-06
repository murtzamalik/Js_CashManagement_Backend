package org.ais.jcash.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ais.jcash.model.TblAuthMatrixDetail;
import org.ais.jcash.model.TblCompany;
import org.ais.jcash.model.TblProduct;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TblAuthMatrixHeadRequest {


    private Long fromAmount;

    private String isSequential;


    private Long toAmount;


    private long companyId;


    private long productId;


    public Long getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(Long fromAmount) {
        this.fromAmount = fromAmount;
    }

    public String getIsSequential() {
        return isSequential;
    }

    public void setIsSequential(String isSequential) {
        this.isSequential = isSequential;
    }

    public Long getToAmount() {
        return toAmount;
    }

    public void setToAmount(Long toAmount) {
        this.toAmount = toAmount;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
