package org.ais.jcash.dto;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/8/2022
 * Time: 11:43 AM
 * Project : jcash
 */
public class FetchLodgeLiquidationRequest {


    private String branchId;

    private String chqNo;

    private String invoiceNo;

    private String amount;

    private String productCollCode;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getChqNo() {
        return chqNo;
    }

    public void setChqNo(String chqNo) {
        this.chqNo = chqNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductCollCode() {
        return productCollCode;
    }

    public void setProductCollCode(String productCollCode) {
        this.productCollCode = productCollCode;
    }
}
