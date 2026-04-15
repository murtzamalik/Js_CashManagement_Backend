package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CompanyProductParserRequest {

    private long productId;


    private long parserId;

    @NotEmpty(message = "Please Select a File")
    @NotNull
    private String fileName;


    private String fileBase64;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getFileBase64() {
        return fileBase64;
    }

    public void setFileBase64(String fileBase64) {
        this.fileBase64 = fileBase64;
    }

    public long getParserId() {
        return parserId;
    }

    public void setParserId(long parserId) {
        this.parserId = parserId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
