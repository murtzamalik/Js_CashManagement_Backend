package org.ais.jcash.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class UpdateCompanyProductsRequest {

    private long companyProductId;

    private BigDecimal courierId;
    @NotEmpty (message = "Customer Account to be Credited can't be empty")
    @NotNull
    private String custAccountCr;
    @NotEmpty (message = "Customer account to be Debited can't be empty")
    @NotNull
    private String custAccountDr;
    @NotEmpty (message = "Customer collection account can't be empty")
    @NotNull
    private String custCollectionAccount;

    private String documentReceived;

    private String enrichedData;

    private String guaranteedFund;

    private String interestRecoveryAccount;

    private String closed;

    private Date closedDate;

    private Date closedTill;

    private String closureReason;

    private String dealerListRequired;

    private String deferredUpto;

    private String discountable;

    private BigDecimal fundCreditDays;

    private String narration;

    private String offsitePrinting;

    private String onlineInvoice;

    private Date reopeningDate;

    private String serviceRecoveryAccount;

    private BigDecimal stationaryId;

    public long getCompanyProductId() {
        return companyProductId;
    }

    public void setCompanyProductId(long companyProductId) {
        this.companyProductId = companyProductId;
    }

    public BigDecimal getCourierId() {
        return courierId;
    }

    public void setCourierId(BigDecimal courierId) {
        this.courierId = courierId;
    }

    public String getCustAccountCr() {
        return custAccountCr;
    }

    public void setCustAccountCr(String custAccountCr) {
        this.custAccountCr = custAccountCr;
    }

    public String getCustAccountDr() {
        return custAccountDr;
    }

    public void setCustAccountDr(String custAccountDr) {
        this.custAccountDr = custAccountDr;
    }

    public String getCustCollectionAccount() {
        return custCollectionAccount;
    }

    public void setCustCollectionAccount(String custCollectionAccount) {
        this.custCollectionAccount = custCollectionAccount;
    }

    public String getDocumentReceived() {
        return documentReceived;
    }

    public void setDocumentReceived(String documentReceived) {
        this.documentReceived = documentReceived;
    }

    public String getEnrichedData() {
        return enrichedData;
    }

    public void setEnrichedData(String enrichedData) {
        this.enrichedData = enrichedData;
    }

    public String getGuaranteedFund() {
        return guaranteedFund;
    }

    public void setGuaranteedFund(String guaranteedFund) {
        this.guaranteedFund = guaranteedFund;
    }

    public String getInterestRecoveryAccount() {
        return interestRecoveryAccount;
    }

    public void setInterestRecoveryAccount(String interestRecoveryAccount) {
        this.interestRecoveryAccount = interestRecoveryAccount;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public Date getClosedTill() {
        return closedTill;
    }

    public void setClosedTill(Date closedTill) {
        this.closedTill = closedTill;
    }

    public String getClosureReason() {
        return closureReason;
    }

    public void setClosureReason(String closureReason) {
        this.closureReason = closureReason;
    }

    public String getDealerListRequired() {
        return dealerListRequired;
    }

    public void setDealerListRequired(String dealerListRequired) {
        this.dealerListRequired = dealerListRequired;
    }

    public String getDeferredUpto() {
        return deferredUpto;
    }

    public void setDeferredUpto(String deferredUpto) {
        this.deferredUpto = deferredUpto;
    }

    public String getDiscountable() {
        return discountable;
    }

    public void setDiscountable(String discountable) {
        this.discountable = discountable;
    }

    public BigDecimal getFundCreditDays() {
        return fundCreditDays;
    }

    public void setFundCreditDays(BigDecimal fundCreditDays) {
        this.fundCreditDays = fundCreditDays;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getOffsitePrinting() {
        return offsitePrinting;
    }

    public void setOffsitePrinting(String offsitePrinting) {
        this.offsitePrinting = offsitePrinting;
    }

    public String getOnlineInvoice() {
        return onlineInvoice;
    }

    public void setOnlineInvoice(String onlineInvoice) {
        this.onlineInvoice = onlineInvoice;
    }

    public Date getReopeningDate() {
        return reopeningDate;
    }

    public void setReopeningDate(Date reopeningDate) {
        this.reopeningDate = reopeningDate;
    }

    public String getServiceRecoveryAccount() {
        return serviceRecoveryAccount;
    }

    public void setServiceRecoveryAccount(String serviceRecoveryAccount) {
        this.serviceRecoveryAccount = serviceRecoveryAccount;
    }

    public BigDecimal getStationaryId() {
        return stationaryId;
    }

    public void setStationaryId(BigDecimal stationaryId) {
        this.stationaryId = stationaryId;
    }
}
