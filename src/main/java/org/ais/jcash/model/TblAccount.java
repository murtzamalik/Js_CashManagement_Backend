package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_ACCOUNT database table.
 * 
 */
@Entity
@Table(name="TBL_ACCOUNT")
@NamedQuery(name="TblAccount.findAll", query="SELECT t FROM TblAccount t")
public class TblAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ACCOUNT_ACCOUNTID_GENERATOR", sequenceName="TBL_ACCOUNT_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ACCOUNT_ACCOUNTID_GENERATOR")
	@Column(name="ACCOUNT_ID")
	private long accountId;

	@Column(name="ACCOUNT_NAME")
	private String accountName;

	@Column(name="ACCOUNT_NO")
	private String accountNo;

	@Column(name="ALLOW_ENTRY")
	private String allowEntry;

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

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String entity;

	@Column(name="HOST_ACCOUNT_NO")
	private String hostAccountNo;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="REOPENING_DATE")
	private Date reopeningDate;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpBank
	@ManyToOne
	@JoinColumn(name="BANK_ID")
	private LkpBank lkpBank;

	//bi-directional many-to-one association to LkpBranch
	@ManyToOne
	@JoinColumn(name="BRANCH_ID")
	private LkpBranch lkpBranch;

	//bi-directional many-to-one association to TblCompany
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private TblCompany tblCompany;

	//bi-directional many-to-one association to TblTransHead
	@JsonIgnore
	@OneToMany(mappedBy="tblAccount1")
	private List<TblTransHead> tblTransHeads1;

	//bi-directional many-to-one association to TblTransHead
	@JsonIgnore
	@OneToMany(mappedBy="tblAccount2")
	private List<TblTransHead> tblTransHeads2;

	//bi-directional many-to-one association to TblUser
	@JsonIgnore
	@OneToMany(mappedBy="tblAccount")
	private List<TblUser> tblUsers;

	//bi-directional many-to-one association to TblUserAccountProduct
	@JsonIgnore
	@OneToMany(mappedBy="tblAccount")
	private List<TblUserAccountProduct> tblUserAccountProducts;

	public TblAccount() {
	}

	public long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAllowEntry() {
		return this.allowEntry;
	}

	public void setAllowEntry(String allowEntry) {
		this.allowEntry = allowEntry;
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

	public String getEntity() {
		return this.entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getHostAccountNo() {
		return this.hostAccountNo;
	}

	public void setHostAccountNo(String hostAccountNo) {
		this.hostAccountNo = hostAccountNo;
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

	public Date getReopeningDate() {
		return this.reopeningDate;
	}

	public void setReopeningDate(Date reopeningDate) {
		this.reopeningDate = reopeningDate;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public LkpBank getLkpBank() {
		return this.lkpBank;
	}

	public void setLkpBank(LkpBank lkpBank) {
		this.lkpBank = lkpBank;
	}

	public LkpBranch getLkpBranch() {
		return this.lkpBranch;
	}

	public void setLkpBranch(LkpBranch lkpBranch) {
		this.lkpBranch = lkpBranch;
	}

	public TblCompany getTblCompany() {
		return this.tblCompany;
	}

	public void setTblCompany(TblCompany tblCompany) {
		this.tblCompany = tblCompany;
	}

	public List<TblTransHead> getTblTransHeads1() {
		return this.tblTransHeads1;
	}

	public void setTblTransHeads1(List<TblTransHead> tblTransHeads1) {
		this.tblTransHeads1 = tblTransHeads1;
	}

	public TblTransHead addTblTransHeads1(TblTransHead tblTransHeads1) {
		getTblTransHeads1().add(tblTransHeads1);
		tblTransHeads1.setTblAccount1(this);

		return tblTransHeads1;
	}

	public TblTransHead removeTblTransHeads1(TblTransHead tblTransHeads1) {
		getTblTransHeads1().remove(tblTransHeads1);
		tblTransHeads1.setTblAccount1(null);

		return tblTransHeads1;
	}

	public List<TblTransHead> getTblTransHeads2() {
		return this.tblTransHeads2;
	}

	public void setTblTransHeads2(List<TblTransHead> tblTransHeads2) {
		this.tblTransHeads2 = tblTransHeads2;
	}

	public TblTransHead addTblTransHeads2(TblTransHead tblTransHeads2) {
		getTblTransHeads2().add(tblTransHeads2);
		tblTransHeads2.setTblAccount2(this);

		return tblTransHeads2;
	}

	public TblTransHead removeTblTransHeads2(TblTransHead tblTransHeads2) {
		getTblTransHeads2().remove(tblTransHeads2);
		tblTransHeads2.setTblAccount2(null);

		return tblTransHeads2;
	}

	public List<TblUser> getTblUsers() {
		return this.tblUsers;
	}

	public void setTblUsers(List<TblUser> tblUsers) {
		this.tblUsers = tblUsers;
	}

	public TblUser addTblUser(TblUser tblUser) {
		getTblUsers().add(tblUser);
		tblUser.setTblAccount(this);

		return tblUser;
	}

	public TblUser removeTblUser(TblUser tblUser) {
		getTblUsers().remove(tblUser);
		tblUser.setTblAccount(null);

		return tblUser;
	}

	public List<TblUserAccountProduct> getTblUserAccountProducts() {
		return this.tblUserAccountProducts;
	}

	public void setTblUserAccountProducts(List<TblUserAccountProduct> tblUserAccountProducts) {
		this.tblUserAccountProducts = tblUserAccountProducts;
	}

	public TblUserAccountProduct addTblUserAccountProduct(TblUserAccountProduct tblUserAccountProduct) {
		getTblUserAccountProducts().add(tblUserAccountProduct);
		tblUserAccountProduct.setTblAccount(this);

		return tblUserAccountProduct;
	}

	public TblUserAccountProduct removeTblUserAccountProduct(TblUserAccountProduct tblUserAccountProduct) {
		getTblUserAccountProducts().remove(tblUserAccountProduct);
		tblUserAccountProduct.setTblAccount(null);

		return tblUserAccountProduct;
	}

}