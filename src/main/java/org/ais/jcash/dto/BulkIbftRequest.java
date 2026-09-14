package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BulkIbftRequest {

    @NotNull
    private String securityDeviceCode;

    private long productId;

    private long debitAcctNoId;

    private String batchRef;

    @NotEmpty
    private List<BulkIbftRowRequest> rows;

    public String getSecurityDeviceCode() {
        return securityDeviceCode;
    }

    public void setSecurityDeviceCode(String securityDeviceCode) {
        this.securityDeviceCode = securityDeviceCode;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getDebitAcctNoId() {
        return debitAcctNoId;
    }

    public void setDebitAcctNoId(long debitAcctNoId) {
        this.debitAcctNoId = debitAcctNoId;
    }

    public String getBatchRef() {
        return batchRef;
    }

    public void setBatchRef(String batchRef) {
        this.batchRef = batchRef;
    }

    public List<BulkIbftRowRequest> getRows() {
        return rows;
    }

    public void setRows(List<BulkIbftRowRequest> rows) {
        this.rows = rows;
    }
}
