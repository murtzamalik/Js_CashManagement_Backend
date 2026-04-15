package org.ais.jcash.dto;

import java.util.List;

public class DeleteCitiesRequest {

    private List<Long> cityId ;

    private String mcStatus;

    private long checkerId;

    public List<Long> getCityId() {
        return cityId;
    }

    public void setCityId(List<Long> cityId) {
        this.cityId = cityId;
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
