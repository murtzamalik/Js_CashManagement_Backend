package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_TRANS_HEAD database table.
 * 
 */
@Entity
@Table(name="TBL_TRANS_HEAD")
@NamedQuery(name="TblTransHead.findAll", query="SELECT t FROM TblTransHead t")
public class TblTransHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_TRANS_HEAD_TRANSHEADID_GENERATOR", sequenceName="TBL_TRANS_HEAD_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_TRANS_HEAD_TRANSHEADID_GENERATOR")
	@Column(name="TRANS_HEAD_ID")
	private long transHeadId;

	@Column(name="AUTH_COMMENTS")
	private String authComments;

	@Temporal(TemporalType.DATE)
	@Column(name="AUTH_DATE")
	private Date authDate;

	@Column(name="AUTH_STATUS")
	private String authStatus;

	@Column(name="AUTH_USER_ID")
	private BigDecimal authUserId;

	@Column(name="BENEFICIARY_ACCOUNT_NO")
	private String beneficiaryAccountNo;

	@Column(name="BENEFICIARY_ACCOUNT_TITLE")
	private String beneficiaryAccountTitle;

	@Column(name="BENEFICIARY_ADDRESS")
	private String beneficiaryAddress;

	@Column(name="BENEFICIARY_BANK_ID")
	private BigDecimal beneficiaryBankId;

	@Column(name="BENEFICIARY_EMAIL")
	private String beneficiaryEmail;

	@Column(name="BENEFICIARY_NAME")
	private String beneficiaryName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CUSTOMER_REFERENCE")
	private String customerReference;

	@Temporal(TemporalType.DATE)
	@Column(name="DEPOSIT_DATE")
	private Date depositDate;

	@Column(name="DEPOSIT_SLIP_AMOUNT")
	private BigDecimal depositSlipAmount;

	@Column(name="DEPOSIT_SLIP_NO")
	private String depositSlipNo;

	@Column(name="DEPOSITOR_CELL_NO")
	private String depositorCellNo;

	@Column(name="DEPOSITOR_EMAIL")
	private String depositorEmail;

	@Column(name="FILE_DETAIL_ID")
	private BigDecimal fileDetailId;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Temporal(TemporalType.DATE)
	@Column(name="LIQUIDATION_CDATE")
	private Date liquidationCdate;

	@Column(name="LIQUIDATION_REASON")
	private String liquidationReason;

	@Column(name="LIQUIDATION_STATUS")
	private String liquidationStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="LODGEMENT_CDATE")
	private Date lodgementCdate;

	@Column(name="LODGEMENT_REASON")
	private String lodgementReason;

	@Column(name="LODGEMENT_STATUS")
	private String lodgementStatus;

	@Column(name="NO_OF_CHEQUES")
	private BigDecimal noOfCheques;

	@Column(name="NO_OF_INVOICES")
	private BigDecimal noOfInvoices;

	@Column(name="PAYMENT_MODE_ID")
	private BigDecimal paymentModeId;

	@Column(name="SECURITY_DEVICE_CODE")
	private String securityDeviceCode;

	@Column(name="TRANS_AMOUNT")
	private BigDecimal transAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="TRANS_DATE")
	private Date transDate;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAuthHead
	@OneToMany(mappedBy="tblTransHead")
	private List<TblAuthHead> tblAuthHeads;

	//bi-directional many-to-one association to TblCashOverCounter
	@OneToMany(mappedBy="tblTransHead1")
	private List<TblCashOverCounter> tblCashOverCounters1;

	//bi-directional many-to-one association to TblCashOverCounter
	@OneToMany(mappedBy="tblTransHead2")
	private List<TblCashOverCounter> tblCashOverCounters2;

	//bi-directional many-to-one association to TblDepositDetail
	@OneToMany(mappedBy="tblTransHead")
	private List<TblDepositDetail> tblDepositDetails;

	//bi-directional many-to-one association to TblSmsMsgEmail
	@OneToMany(mappedBy="tblTransHead")
	private List<TblSmsMsgEmail> tblSmsMsgEmails;

	//bi-directional many-to-one association to LkpBranch
	@ManyToOne
	@JoinColumn(name="BRANCH_ID")
	private LkpBranch lkpBranch;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="COLLECTION_ACCOUNT_ID")
	private TblAccount tblAccount1;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="DR_ACCOUNT_ID")
	private TblAccount tblAccount2;

	//bi-directional many-to-one association to TblCompany
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private TblCompany tblCompany;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	//bi-directional many-to-one association to TblRequest
	@ManyToOne
	@JoinColumn(name="REQUEST_ID")
	private TblRequest tblRequest;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="LODGEMENT_USER_ID")
	private TblUser tblUser1;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="LIQUIDATION_USER_ID")
	private TblUser tblUser2;

	public TblTransHead() {
	}

	public long getTransHeadId() {
		return this.transHeadId;
	}

	public void setTransHeadId(long transHeadId) {
		this.transHeadId = transHeadId;
	}

	public String getAuthComments() {
		return this.authComments;
	}

	public void setAuthComments(String authComments) {
		this.authComments = authComments;
	}

	public Date getAuthDate() {
		return this.authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	public String getAuthStatus() {
		return this.authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public BigDecimal getAuthUserId() {
		return this.authUserId;
	}

	public void setAuthUserId(BigDecimal authUserId) {
		this.authUserId = authUserId;
	}

	public String getBeneficiaryAccountNo() {
		return this.beneficiaryAccountNo;
	}

	public void setBeneficiaryAccountNo(String beneficiaryAccountNo) {
		this.beneficiaryAccountNo = beneficiaryAccountNo;
	}

	public String getBeneficiaryAccountTitle() {
		return this.beneficiaryAccountTitle;
	}

	public void setBeneficiaryAccountTitle(String beneficiaryAccountTitle) {
		this.beneficiaryAccountTitle = beneficiaryAccountTitle;
	}

	public String getBeneficiaryAddress() {
		return this.beneficiaryAddress;
	}

	public void setBeneficiaryAddress(String beneficiaryAddress) {
		this.beneficiaryAddress = beneficiaryAddress;
	}

	public BigDecimal getBeneficiaryBankId() {
		return this.beneficiaryBankId;
	}

	public void setBeneficiaryBankId(BigDecimal beneficiaryBankId) {
		this.beneficiaryBankId = beneficiaryBankId;
	}

	public String getBeneficiaryEmail() {
		return this.beneficiaryEmail;
	}

	public void setBeneficiaryEmail(String beneficiaryEmail) {
		this.beneficiaryEmail = beneficiaryEmail;
	}

	public String getBeneficiaryName() {
		return this.beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
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

	public String getCustomerReference() {
		return this.customerReference;
	}

	public void setCustomerReference(String customerReference) {
		this.customerReference = customerReference;
	}

	public Date getDepositDate() {
		return this.depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

	public BigDecimal getDepositSlipAmount() {
		return this.depositSlipAmount;
	}

	public void setDepositSlipAmount(BigDecimal depositSlipAmount) {
		this.depositSlipAmount = depositSlipAmount;
	}

	public String getDepositSlipNo() {
		return this.depositSlipNo;
	}

	public void setDepositSlipNo(String depositSlipNo) {
		this.depositSlipNo = depositSlipNo;
	}

	public String getDepositorCellNo() {
		return this.depositorCellNo;
	}

	public void setDepositorCellNo(String depositorCellNo) {
		this.depositorCellNo = depositorCellNo;
	}

	public String getDepositorEmail() {
		return this.depositorEmail;
	}

	public void setDepositorEmail(String depositorEmail) {
		this.depositorEmail = depositorEmail;
	}

	public BigDecimal getFileDetailId() {
		return this.fileDetailId;
	}

	public void setFileDetailId(BigDecimal fileDetailId) {
		this.fileDetailId = fileDetailId;
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

	public Date getLiquidationCdate() {
		return this.liquidationCdate;
	}

	public void setLiquidationCdate(Date liquidationCdate) {
		this.liquidationCdate = liquidationCdate;
	}

	public String getLiquidationReason() {
		return this.liquidationReason;
	}

	public void setLiquidationReason(String liquidationReason) {
		this.liquidationReason = liquidationReason;
	}

	public String getLiquidationStatus() {
		return this.liquidationStatus;
	}

	public void setLiquidationStatus(String liquidationStatus) {
		this.liquidationStatus = liquidationStatus;
	}

	public Date getLodgementCdate() {
		return this.lodgementCdate;
	}

	public void setLodgementCdate(Date lodgementCdate) {
		this.lodgementCdate = lodgementCdate;
	}

	public String getLodgementReason() {
		return this.lodgementReason;
	}

	public void setLodgementReason(String lodgementReason) {
		this.lodgementReason = lodgementReason;
	}

	public String getLodgementStatus() {
		return this.lodgementStatus;
	}

	public void setLodgementStatus(String lodgementStatus) {
		this.lodgementStatus = lodgementStatus;
	}

	public BigDecimal getNoOfCheques() {
		return this.noOfCheques;
	}

	public void setNoOfCheques(BigDecimal noOfCheques) {
		this.noOfCheques = noOfCheques;
	}

	public BigDecimal getNoOfInvoices() {
		return this.noOfInvoices;
	}

	public void setNoOfInvoices(BigDecimal noOfInvoices) {
		this.noOfInvoices = noOfInvoices;
	}

	public BigDecimal getPaymentModeId() {
		return this.paymentModeId;
	}

	public void setPaymentModeId(BigDecimal paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getSecurityDeviceCode() {
		return this.securityDeviceCode;
	}

	public void setSecurityDeviceCode(String securityDeviceCode) {
		this.securityDeviceCode = securityDeviceCode;
	}

	public BigDecimal getTransAmount() {
		return this.transAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public Date getTransDate() {
		return this.transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblAuthHead> getTblAuthHeads() {
		return this.tblAuthHeads;
	}

	public void setTblAuthHeads(List<TblAuthHead> tblAuthHeads) {
		this.tblAuthHeads = tblAuthHeads;
	}

	public TblAuthHead addTblAuthHead(TblAuthHead tblAuthHead) {
		getTblAuthHeads().add(tblAuthHead);
		tblAuthHead.setTblTransHead(this);

		return tblAuthHead;
	}

	public TblAuthHead removeTblAuthHead(TblAuthHead tblAuthHead) {
		getTblAuthHeads().remove(tblAuthHead);
		tblAuthHead.setTblTransHead(null);

		return tblAuthHead;
	}

	public List<TblCashOverCounter> getTblCashOverCounters1() {
		return this.tblCashOverCounters1;
	}

	public void setTblCashOverCounters1(List<TblCashOverCounter> tblCashOverCounters1) {
		this.tblCashOverCounters1 = tblCashOverCounters1;
	}

	public TblCashOverCounter addTblCashOverCounters1(TblCashOverCounter tblCashOverCounters1) {
		getTblCashOverCounters1().add(tblCashOverCounters1);
		tblCashOverCounters1.setTblTransHead1(this);

		return tblCashOverCounters1;
	}

	public TblCashOverCounter removeTblCashOverCounters1(TblCashOverCounter tblCashOverCounters1) {
		getTblCashOverCounters1().remove(tblCashOverCounters1);
		tblCashOverCounters1.setTblTransHead1(null);

		return tblCashOverCounters1;
	}

	public List<TblCashOverCounter> getTblCashOverCounters2() {
		return this.tblCashOverCounters2;
	}

	public void setTblCashOverCounters2(List<TblCashOverCounter> tblCashOverCounters2) {
		this.tblCashOverCounters2 = tblCashOverCounters2;
	}

	public TblCashOverCounter addTblCashOverCounters2(TblCashOverCounter tblCashOverCounters2) {
		getTblCashOverCounters2().add(tblCashOverCounters2);
		tblCashOverCounters2.setTblTransHead2(this);

		return tblCashOverCounters2;
	}

	public TblCashOverCounter removeTblCashOverCounters2(TblCashOverCounter tblCashOverCounters2) {
		getTblCashOverCounters2().remove(tblCashOverCounters2);
		tblCashOverCounters2.setTblTransHead2(null);

		return tblCashOverCounters2;
	}

	public List<TblDepositDetail> getTblDepositDetails() {
		return this.tblDepositDetails;
	}

	public void setTblDepositDetails(List<TblDepositDetail> tblDepositDetails) {
		this.tblDepositDetails = tblDepositDetails;
	}

	public TblDepositDetail addTblDepositDetail(TblDepositDetail tblDepositDetail) {
		getTblDepositDetails().add(tblDepositDetail);
		tblDepositDetail.setTblTransHead(this);

		return tblDepositDetail;
	}

	public TblDepositDetail removeTblDepositDetail(TblDepositDetail tblDepositDetail) {
		getTblDepositDetails().remove(tblDepositDetail);
		tblDepositDetail.setTblTransHead(null);

		return tblDepositDetail;
	}

	public List<TblSmsMsgEmail> getTblSmsMsgEmails() {
		return this.tblSmsMsgEmails;
	}

	public void setTblSmsMsgEmails(List<TblSmsMsgEmail> tblSmsMsgEmails) {
		this.tblSmsMsgEmails = tblSmsMsgEmails;
	}

	public TblSmsMsgEmail addTblSmsMsgEmail(TblSmsMsgEmail tblSmsMsgEmail) {
		getTblSmsMsgEmails().add(tblSmsMsgEmail);
		tblSmsMsgEmail.setTblTransHead(this);

		return tblSmsMsgEmail;
	}

	public TblSmsMsgEmail removeTblSmsMsgEmail(TblSmsMsgEmail tblSmsMsgEmail) {
		getTblSmsMsgEmails().remove(tblSmsMsgEmail);
		tblSmsMsgEmail.setTblTransHead(null);

		return tblSmsMsgEmail;
	}

	public LkpBranch getLkpBranch() {
		return this.lkpBranch;
	}

	public void setLkpBranch(LkpBranch lkpBranch) {
		this.lkpBranch = lkpBranch;
	}

	public TblAccount getTblAccount1() {
		return this.tblAccount1;
	}

	public void setTblAccount1(TblAccount tblAccount1) {
		this.tblAccount1 = tblAccount1;
	}

	public TblAccount getTblAccount2() {
		return this.tblAccount2;
	}

	public void setTblAccount2(TblAccount tblAccount2) {
		this.tblAccount2 = tblAccount2;
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

	public TblRequest getTblRequest() {
		return this.tblRequest;
	}

	public void setTblRequest(TblRequest tblRequest) {
		this.tblRequest = tblRequest;
	}

	public TblUser getTblUser1() {
		return this.tblUser1;
	}

	public void setTblUser1(TblUser tblUser1) {
		this.tblUser1 = tblUser1;
	}

	public TblUser getTblUser2() {
		return this.tblUser2;
	}

	public void setTblUser2(TblUser tblUser2) {
		this.tblUser2 = tblUser2;
	}

}