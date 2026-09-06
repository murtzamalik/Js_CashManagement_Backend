package org.ais.jcash.dto;

import java.util.List;

public class ApproveCountriesRequest {

    private  List<Long> countryId ;

    private String mcStatus;



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


}
