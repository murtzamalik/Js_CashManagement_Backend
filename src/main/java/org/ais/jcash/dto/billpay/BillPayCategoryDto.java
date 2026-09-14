package org.ais.jcash.dto.billpay;

import java.util.List;

public class BillPayCategoryDto {
    private String code;
    private String name;
    private String description;
    private String icon; // primeicon class hint for UI

    public BillPayCategoryDto() {
    }

    public BillPayCategoryDto(String code, String name, String description, String icon) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.icon = icon;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
