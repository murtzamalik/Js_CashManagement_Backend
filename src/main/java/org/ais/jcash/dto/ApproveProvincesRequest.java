package org.ais.jcash.dto;

import java.util.List;

public class ApproveProvincesRequest {

    private List<Long> provinceId ;

    private String mcStatus;

    private long checkerId;

    public List<Long> getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(List<Long> provinceId) {
        this.provinceId = provinceId;
    }

    public String getMcStatus() {
        return mcStatus;
    }

    public void setMcStatus(String mcStatus) {
        this.mcStatus = mcStatus;
    }

    public long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(long checkerId) {
        this.checkerId = checkerId;
    }
}
