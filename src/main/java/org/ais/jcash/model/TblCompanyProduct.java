package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_COMPANY_PRODUCT database table.
 * 
 */
@Entity
@Table(name="TBL_COMPANY_PRODUCT")
@NamedQuery(name="TblCompanyProduct.findAll", query="SELECT t FROM TblCompanyProduct t")
public class TblCompanyProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_COMPANY_PRODUCT_COMPANYPRODUCTID_GENERATOR", sequenceName="TBL_COMPANY_PRODUCT_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_COMPANY_PRODUCT_COMPANYPRODUCTID_GENERATOR")
	@Column(name="COMPANY_PRODUCT_ID")
	private long companyProductId;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	private String closed;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSED_DATE")
	private Date closedDate;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSED_TILL")
	private Date closedTill;

	@Column(name="CLOSURE_REASON")
	private String closureReason;

	@Column(name="COURIER_ID")
	private BigDecimal courierId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CUST_ACCOUNT_CR")
	private String custAccountCr;

	@Column(name="CUST_ACCOUNT_DR")
	private String custAccountDr;

	@Column(name="CUST_COLLECTION_ACCOUNT")
	private String custCollectionAccount;

	@Column(name="DEALER_LIST_REQUIRED")
	private String dealerListRequired;

	@Column(name="DEFERRED_UPTO")
	private String deferredUpto;

	private String discountable;

	@Column(name="DOCUMENT_RECEIVED")
	private String documentReceived;

	@Column(name="ENRICHED_DATA")
	private String enrichedData;

	@Column(name="FUND_CREDIT_DAYS")
	private BigDecimal fundCreditDays;

	@Column(name="GUARANTEED_FUND")
	private String guaranteedFund;

	@Column(name="INTEREST_RECOVERY_ACCOUNT")
	private String interestRecoveryAccount;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	private String narration;

	@Column(name="OFFSITE_PRINTING")
	private String offsitePrinting;

	@Column(name="ONLINE_INVOICE")
	private String onlineInvoice;

	@Temporal(TemporalType.DATE)
	@Column(name="REOPENING_DATE")
	private Date reopeningDate;

	@Column(name="SERVICE_RECOVERY_ACCOUNT")
	private String serviceRecoveryAccount;

	@Column(name="STATIONARY_ID")
	private BigDecimal stationaryId;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblCompany
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private TblCompany tblCompany;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblCompanyProduct() {
	}

	public long getCompanyProductId() {
		return this.companyProductId;
	}

	public void setCompanyProductId(long companyProductId) {
		this.companyProductId = companyProductId;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getCheckerComments() {
		return this.checkerComments;
	}

	public void setCheckerComments(String checkerComments) {
		this.checkerComments = checkerComments;
	}

	public BigDecimal getCheckerId() {
		return this.checkerId;
	}

	public void setCheckerId(BigDecimal checkerId) {
		this.checkerId = checkerId;
	}

	public String getClosed() {
		return this.closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public Date getClosedDate() {
		return this.closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public Date getClosedTill() {
		return this.closedTill;
	}

	public void setClosedTill(Date closedTill) {
		this.closedTill = closedTill;
	}

	public String getClosureReason() {
		return this.closureReason;
	}

	public void setClosureReason(String closureReason) {
		this.closureReason = closureReason;
	}

	public BigDecimal getCourierId() {
		return this.courierId;
	}

	public void setCourierId(BigDecimal courierId) {
		this.courierId = courierId;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public String getCustAccountCr() {
		return this.custAccountCr;
	}

	public void setCustAccountCr(String custAccountCr) {
		this.custAccountCr = custAccountCr;
	}

	public String getCustAccountDr() {
		return this.custAccountDr;
	}

	public void setCustAccountDr(String custAccountDr) {
		this.custAccountDr = custAccountDr;
	}

	public String getCustCollectionAccount() {
		return this.custCollectionAccount;
	}

	public void setCustCollectionAccount(String custCollectionAccount) {
		this.custCollectionAccount = custCollectionAccount;
	}

	public String getDealerListRequired() {
		return this.dealerListRequired;
	}

	public void setDealerListRequired(String dealerListRequired) {
		this.dealerListRequired = dealerListRequired;
	}

	public String getDeferredUpto() {
		return this.deferredUpto;
	}

	public void setDeferredUpto(String deferredUpto) {
		this.deferredUpto = deferredUpto;
	}

	public String getDiscountable() {
		return this.discountable;
	}

	public void setDiscountable(String discountable) {
		this.discountable = discountable;
	}

	public String getDocumentReceived() {
		return this.documentReceived;
	}

	public void setDocumentReceived(String documentReceived) {
		this.documentReceived = documentReceived;
	}

	public String getEnrichedData() {
		return this.enrichedData;
	}

	public void setEnrichedData(String enrichedData) {
		this.enrichedData = enrichedData;
	}

	public BigDecimal getFundCreditDays() {
		return this.fundCreditDays;
	}

	public void setFundCreditDays(BigDecimal fundCreditDays) {
		this.fundCreditDays = fundCreditDays;
	}

	public String getGuaranteedFund() {
		return this.guaranteedFund;
	}

	public void setGuaranteedFund(String guaranteedFund) {
		this.guaranteedFund = guaranteedFund;
	}

	public String getInterestRecoveryAccount() {
		return this.interestRecoveryAccount;
	}

	public void setInterestRecoveryAccount(String interestRecoveryAccount) {
		this.interestRecoveryAccount = interestRecoveryAccount;
	}

	public Date getLastupdatedate() {
		return this.lastupdatedate;
	}

	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}

	public BigDecimal getLastupdateuser() {
		return this.lastupdateuser;
	}

	public void setLastupdateuser(BigDecimal lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public String getMcStatus() {
		return this.mcStatus;
	}

	public void setMcStatus(String mcStatus) {
		this.mcStatus = mcStatus;
	}

	public String getNarration() {
		return this.narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getOffsitePrinting() {
		return this.offsitePrinting;
	}

	public void setOffsitePrinting(String offsitePrinting) {
		this.offsitePrinting = offsitePrinting;
	}

	public String getOnlineInvoice() {
		return this.onlineInvoice;
	}

	public void setOnlineInvoice(String onlineInvoice) {
		this.onlineInvoice = onlineInvoice;
	}

	public Date getReopeningDate() {
		return this.reopeningDate;
	}

	public void setReopeningDate(Date reopeningDate) {
		this.reopeningDate = reopeningDate;
	}

	public String getServiceRecoveryAccount() {
		return this.serviceRecoveryAccount;
	}

	public void setServiceRecoveryAccount(String serviceRecoveryAccount) {
		this.serviceRecoveryAccount = serviceRecoveryAccount;
	}

	public BigDecimal getStationaryId() {
		return this.stationaryId;
	}

	public void setStationaryId(BigDecimal stationaryId) {
		this.stationaryId = stationaryId;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblCompany getTblCompany() {
		return this.tblCompany;
	}

	public void setTblCompany(TblCompany tblCompany) {
		this.tblCompany = tblCompany;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

}