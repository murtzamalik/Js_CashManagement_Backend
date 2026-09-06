package org.ais.jcash.dto;

import java.util.List;

public class DeleteCountriesRequest {

    private List<Long> countryId ;

    private String mcStatus;

    private long checkerId;

    public List<Long> getCountryId() {
        return countryId;
    }

    public void setCountryId(List<Long> countryId) {
        this.countryId = countryId;
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
