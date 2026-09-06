package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_CASH_OVER_COUNTER database table.
 * 
 */
@Entity
@Table(name="TBL_CASH_OVER_COUNTER")
@NamedQuery(name="TblCashOverCounter.findAll", query="SELECT t FROM TblCashOverCounter t")
public class TblCashOverCounter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_CASH_OVER_COUNTER_CASHOVERCOUNTERID_GENERATOR", sequenceName="TBL_CASH_OVER_COUNTER_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_CASH_OVER_COUNTER_CASHOVERCOUNTERID_GENERATOR")
	@Column(name="CASH_OVER_COUNTER_ID")
	private long cashOverCounterId;

	@Column(name="ACCOUNT_NO")
	private String accountNo;

	@Column(name="ACCOUNT_TITLE")
	private String accountTitle;

	@Column(name="BRANCH_CODE")
	private String branchCode;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DOCUMENT_NO")
	private String documentNo;

	@Column(name="DOCUMENT_TYPE")
	private String documentType;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DATE")
	private Date expiryDate;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	@Column(name="PAYMENT_MODE")
	private String paymentMode;

	private String status;

	private BigDecimal updateindex;

	private BigDecimal xpin;

	//bi-directional many-to-one association to TblTransHead
	@ManyToOne
	@JoinColumn(name="TRANS_HEAD_ID2")
	private TblTransHead tblTransHead1;

	//bi-directional many-to-one association to TblTransHead
	@ManyToOne
	@JoinColumn(name="TRANS_HEAD_ID1")
	private TblTransHead tblTransHead2;

	public TblCashOverCounter() {
	}

	public long getCashOverCounterId() {
		return this.cashOverCounterId;
	}

	public void setCashOverCounterId(long cashOverCounterId) {
		this.cashOverCounterId = cashOverCounterId;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountTitle() {
		return this.accountTitle;
	}

	public void setAccountTitle(String accountTitle) {
		this.accountTitle = accountTitle;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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

	public String getDocumentNo() {
		return this.documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public BigDecimal getXpin() {
		return this.xpin;
	}

	public void setXpin(BigDecimal xpin) {
		this.xpin = xpin;
	}

	public TblTransHead getTblTransHead1() {
		return this.tblTransHead1;
	}

	public void setTblTransHead1(TblTransHead tblTransHead1) {
		this.tblTransHead1 = tblTransHead1;
	}

	public TblTransHead getTblTransHead2() {
		return this.tblTransHead2;
	}

	public void setTblTransHead2(TblTransHead tblTransHead2) {
		this.tblTransHead2 = tblTransHead2;
	}

}