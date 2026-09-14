package org.ais.jcash.dto.billpay;

public class BillPayCompanyDto {
    private String code;
    private String name;
    private String categoryCode;
    private String consumerLabel;

    public BillPayCompanyDto() {
    }

    public BillPayCompanyDto(String code, String name, String categoryCode, String consumerLabel) {
        this.code = code;
        this.name = name;
        this.categoryCode = categoryCode;
        this.consumerLabel = consumerLabel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getConsumerLabel() {
        return consumerLabel;
    }

    public void setConsumerLabel(String consumerLabel) {
        this.consumerLabel = consumerLabel;
    }
}
