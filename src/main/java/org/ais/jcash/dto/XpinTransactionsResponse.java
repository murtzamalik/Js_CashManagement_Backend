package org.ais.jcash.dto;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/25/2022
 * Time: 4:15 PM
 * Project : jcash
 */
public class XpinTransactionsResponse {


    private long cashoverCounterId;

    private long transheadId;

    private long companyid;

    private long productId;

    private String  benName;

    private String benAddress;

    private String mobileNo;

    private String docType;

    private String docNo;

    private String remitterName;

    private BigDecimal transAmount;

    private String transdate;

    public long getCashoverCounterId() {
        return cashoverCounterId;
    }

    public void setCashoverCounterId(long cashoverCounterId) {
        this.cashoverCounterId = cashoverCounterId;
    }

    public long getTransheadId() {
        return transheadId;
    }

    public void setTransheadId(long transheadId) {
        this.transheadId = transheadId;
    }

    public long getCompanyid() {
        return companyid;
    }

    public void setCompanyid(long companyid) {
        this.companyid = companyid;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getBenName() {
        return benName;
    }

    public void setBenName(String benName) {
        this.benName = benName;
    }

    public String getBenAddress() {
        return benAddress;
    }

    public void setBenAddress(String benAddress) {
        this.benAddress = benAddress;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocNo() {
        return docNo;
    }

    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    public String getRemitterName() {
        return remitterName;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public String getTransdate() {
        return transdate;
    }

    public void setTransdate(String transdate) {
        this.transdate = transdate;
    }
}
