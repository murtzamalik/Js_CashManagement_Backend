package org.ais.jcash.dto;

public class UpdateCompanyAuthMatrixRequest {

    private long authMatrixHeadId;

    private String mcStatus;

    private String checkerComments;

    public long getAuthMatrixHeadId() {
        return authMatrixHeadId;
    }

    public void setAuthMatrixHeadId(long authMatrixHeadId) {
        this.authMatrixHeadId = authMatrixHeadId;
    }

    public String getMcStatus() {
        return mcStatus;
    }

    public void setMcStatus(String mcStatus) {
        this.mcStatus = mcStatus;
    }

    public String getCheckerComments() {
        return checkerComments;
    }

    public void setCheckerComments(String checkerComments) {
        this.checkerComments = checkerComments;
    }
}
