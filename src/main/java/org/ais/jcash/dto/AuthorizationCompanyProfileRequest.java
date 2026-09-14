package org.ais.jcash.dto;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/18/2022
 * Time: 10:04 AM
 * Project : jcash
 */

public class AuthorizationCompanyProfileRequest {

    private String mcStatus;

    private long companyId;

    private String mcComments;

    public String getMcStatus() {
        return mcStatus;
    }

    public void setMcStatus(String mcStatus) {
        this.mcStatus = mcStatus;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getMcComments() {
        return mcComments;
    }

    public void setMcComments(String mcComments) {
        this.mcComments = mcComments;
    }
}
