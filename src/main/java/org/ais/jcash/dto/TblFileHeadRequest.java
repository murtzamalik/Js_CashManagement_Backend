package org.ais.jcash.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class TblFileHeadRequest {

    private long fileHeadId;

    private Date checkDate;

    private String checkerComments;

    private BigDecimal checkerId;

    private Date createdate;

    private BigDecimal createuser;
    @NotEmpty (message = "File Name can't be empty")
    @NotNull
    private String fileName;

    private Date lastupdatedate;

    private BigDecimal lastupdateuser;

    private String mcStatus;

    private String reference01Title;

    private String reference02Title;

    private String reference03Title;

    private String reference04Title;

    private String reference05Title;

    private String reference06Title;

    private String reference07Title;

    private String reference08Title;

    private String reference09Title;

    private String reference10Title;

    private String reference11Title;

    private String reference12Title;

    private String reference13Title;

    private String reference14Title;

    private BigDecimal updateindex;

    public long getFileHeadId() {
        return fileHeadId;
    }

    public void setFileHeadId(long fileHeadId) {
        this.fileHeadId = fileHeadId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getCheckerComments() {
        return checkerComments;
    }

    public void setCheckerComments(String checkerComments) {
        this.checkerComments = checkerComments;
    }

    public BigDecimal getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(BigDecimal checkerId) {
        this.checkerId = checkerId;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public BigDecimal getCreateuser() {
        return createuser;
    }

    public void setCreateuser(BigDecimal createuser) {
        this.createuser = createuser;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getLastupdatedate() {
        return lastupdatedate;
    }

    public void setLastupdatedate(Date lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    public BigDecimal getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(BigDecimal lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    public String getMcStatus() {
        return mcStatus;
    }

    public void setMcStatus(String mcStatus) {
        this.mcStatus = mcStatus;
    }

    public String getReference01Title() {
        return reference01Title;
    }

    public void setReference01Title(String reference01Title) {
        this.reference01Title = reference01Title;
    }

    public String getReference02Title() {
        return reference02Title;
    }

    public void setReference02Title(String reference02Title) {
        this.reference02Title = reference02Title;
    }

    public String getReference03Title() {
        return reference03Title;
    }

    public void setReference03Title(String reference03Title) {
        this.reference03Title = reference03Title;
    }

    public String getReference04Title() {
        return reference04Title;
    }

    public void setReference04Title(String reference04Title) {
        this.reference04Title = reference04Title;
    }

    public String getReference05Title() {
        return reference05Title;
    }

    public void setReference05Title(String reference05Title) {
        this.reference05Title = reference05Title;
    }

    public String getReference06Title() {
        return reference06Title;
    }

    public void setReference06Title(String reference06Title) {
        this.reference06Title = reference06Title;
    }

    public String getReference07Title() {
        return reference07Title;
    }

    public void setReference07Title(String reference07Title) {
        this.reference07Title = reference07Title;
    }

    public String getReference08Title() {
        return reference08Title;
    }

    public void setReference08Title(String reference08Title) {
        this.reference08Title = reference08Title;
    }

    public String getReference09Title() {
        return reference09Title;
    }

    public void setReference09Title(String reference09Title) {
        this.reference09Title = reference09Title;
    }

    public String getReference10Title() {
        return reference10Title;
    }

    public void setReference10Title(String reference10Title) {
        this.reference10Title = reference10Title;
    }

    public String getReference11Title() {
        return reference11Title;
    }

    public void setReference11Title(String reference11Title) {
        this.reference11Title = reference11Title;
    }

    public String getReference12Title() {
        return reference12Title;
    }

    public void setReference12Title(String reference12Title) {
        this.reference12Title = reference12Title;
    }

    public String getReference13Title() {
        return reference13Title;
    }

    public void setReference13Title(String reference13Title) {
        this.reference13Title = reference13Title;
    }

    public String getReference14Title() {
        return reference14Title;
    }

    public void setReference14Title(String reference14Title) {
        this.reference14Title = reference14Title;
    }

    public BigDecimal getUpdateindex() {
        return updateindex;
    }

    public void setUpdateindex(BigDecimal updateindex) {
        this.updateindex = updateindex;
    }
}
