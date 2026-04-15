package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TblAuthMatrixHeadBranchRequest {


    private Long fromAmount;

    private String isSequential;

    private Long toAmount;

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

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
