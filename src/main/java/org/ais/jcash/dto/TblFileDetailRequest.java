package org.ais.jcash.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class TblFileDetailRequest {

    private long fileDetailId;

    private Date createdate;

    private BigDecimal createuser;

    private String reference01;

    private String reference02;

    private String reference03;

    private String reference04;

    private String reference05;

    private String reference06;

    private String reference07;

    private String reference08;

    private String reference09;

    private String reference10;

    private String reference11;

    private String reference12;

    private String reference13;

    private String reference14;


    public long getFileDetailId() {
        return fileDetailId;
    }

    public void setFileDetailId(long fileDetailId) {
        this.fileDetailId = fileDetailId;
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

    public String getReference01() {
        return reference01;
    }

    public void setReference01(String reference01) {
        this.reference01 = reference01;
    }

    public String getReference02() {
        return reference02;
    }

    public void setReference02(String reference02) {
        this.reference02 = reference02;
    }

    public String getReference03() {
        return reference03;
    }

    public void setReference03(String reference03) {
        this.reference03 = reference03;
    }

    public String getReference04() {
        return reference04;
    }

    public void setReference04(String reference04) {
        this.reference04 = reference04;
    }

    public String getReference05() {
        return reference05;
    }

    public void setReference05(String reference05) {
        this.reference05 = reference05;
    }

    public String getReference06() {
        return reference06;
    }

    public void setReference06(String reference06) {
        this.reference06 = reference06;
    }

    public String getReference07() {
        return reference07;
    }

    public void setReference07(String reference07) {
        this.reference07 = reference07;
    }

    public String getReference08() {
        return reference08;
    }

    public void setReference08(String reference08) {
        this.reference08 = reference08;
    }

    public String getReference09() {
        return reference09;
    }

    public void setReference09(String reference09) {
        this.reference09 = reference09;
    }

    public String getReference10() {
        return reference10;
    }

    public void setReference10(String reference10) {
        this.reference10 = reference10;
    }

    public String getReference11() {
        return reference11;
    }

    public void setReference11(String reference11) {
        this.reference11 = reference11;
    }

    public String getReference12() {
        return reference12;
    }

    public void setReference12(String reference12) {
        this.reference12 = reference12;
    }

    public String getReference13() {
        return reference13;
    }

    public void setReference13(String reference13) {
        this.reference13 = reference13;
    }

    public String getReference14() {
        return reference14;
    }

    public void setReference14(String reference14) {
        this.reference14 = reference14;
    }
}
