package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_BRANCH database table.
 * 
 */
@Entity
@Table(name="LKP_BRANCH")
@NamedQuery(name="LkpBranch.findAll", query="SELECT l FROM LkpBranch l")
public class LkpBranch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_BRANCH_BRANCHID_GENERATOR", sequenceName="LKP_BRANCH_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_BRANCH_BRANCHID_GENERATOR")
	@Column(name="BRANCH_ID")
	private long branchId;

	@Column(name="BRANCH_ADDRESS")
	private String branchAddress;

	@Column(name="BRANCH_CODE")
	private String branchCode;

	@Column(name="BRANCH_DESCRIPTION")
	private String branchDescription;

	@Column(name="BRANCH_NAME")
	private String branchName;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	@Column(name="CONTACT_NO")
	private String contactNo;

	@Column(name="COTC_GL")
	private String cotcGl;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="INTRA_CITY_CLEARING_GL")
	private String intraCityClearingGl;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="IS_PRINCIPAL")
	private String isPrincipal;

	@Column(name="IS_PRINTING_LOCATION")
	private String isPrintingLocation;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="NORMAL_CLEARING_GL")
	private String normalClearingGl;

	@Column(name="SAME_DAY_CLEARING_GL")
	private String sameDayClearingGl;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpBank
	@ManyToOne
	@JoinColumn(name="BANK_ID")
	private LkpBank lkpBank;

	//bi-directional many-to-one association to LkpCity
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private LkpCity lkpCity;

	//bi-directional many-to-one association to LkpCountry
	@ManyToOne
	@JoinColumn(name="COUNTRY_ID")
	private LkpCountry lkpCountry;

	//bi-directional many-to-one association to LkpProvince
	@ManyToOne
	@JoinColumn(name="PROVINCE_ID")
	private LkpProvince lkpProvince;

	//bi-directional many-to-one association to TblAccount
	@OneToMany(mappedBy="lkpBranch")
	private List<TblAccount> tblAccounts;

	//bi-directional many-to-one association to TblDepositDetail
	@OneToMany(mappedBy="lkpBranch")
	private List<TblDepositDetail> tblDepositDetails;

	//bi-directional many-to-one association to TblTransHead
	@OneToMany(mappedBy="lkpBranch")
	private List<TblTransHead> tblTransHeads;

	//bi-directional many-to-one association to TblUser
	@OneToMany(mappedBy="lkpBranch")
	private List<TblUser> tblUsers;

	public LkpBranch() {
	}

	public long getBranchId() {
		return this.branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public String getBranchAddress() {
		return this.branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchCode() {
		return this.branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchDescription() {
		return this.branchDescription;
	}

	public void setBranchDescription(String branchDescription) {
		this.branchDescription = branchDescription;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCotcGl() {
		return this.cotcGl;
	}

	public void setCotcGl(String cotcGl) {
		this.cotcGl = cotcGl;
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

	public String getIntraCityClearingGl() {
		return this.intraCityClearingGl;
	}

	public void setIntraCityClearingGl(String intraCityClearingGl) {
		this.intraCityClearingGl = intraCityClearingGl;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsPrincipal() {
		return this.isPrincipal;
	}

	public void setIsPrincipal(String isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

	public String getIsPrintingLocation() {
		return this.isPrintingLocation;
	}

	public void setIsPrintingLocation(String isPrintingLocation) {
		this.isPrintingLocation = isPrintingLocation;
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

	public String getNormalClearingGl() {
		return this.normalClearingGl;
	}

	public void setNormalClearingGl(String normalClearingGl) {
		this.normalClearingGl = normalClearingGl;
	}

	public String getSameDayClearingGl() {
		return this.sameDayClearingGl;
	}

	public void setSameDayClearingGl(String sameDayClearingGl) {
		this.sameDayClearingGl = sameDayClearingGl;
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

	public LkpCity getLkpCity() {
		return this.lkpCity;
	}

	public void setLkpCity(LkpCity lkpCity) {
		this.lkpCity = lkpCity;
	}

	public LkpCountry getLkpCountry() {
		return this.lkpCountry;
	}

	public void setLkpCountry(LkpCountry lkpCountry) {
		this.lkpCountry = lkpCountry;
	}

	public LkpProvince getLkpProvince() {
		return this.lkpProvince;
	}

	public void setLkpProvince(LkpProvince lkpProvince) {
		this.lkpProvince = lkpProvince;
	}

	public List<TblAccount> getTblAccounts() {
		return this.tblAccounts;
	}

	public void setTblAccounts(List<TblAccount> tblAccounts) {
		this.tblAccounts = tblAccounts;
	}

	public TblAccount addTblAccount(TblAccount tblAccount) {
		getTblAccounts().add(tblAccount);
		tblAccount.setLkpBranch(this);

		return tblAccount;
	}

	public TblAccount removeTblAccount(TblAccount tblAccount) {
		getTblAccounts().remove(tblAccount);
		tblAccount.setLkpBranch(null);

		return tblAccount;
	}

	public List<TblDepositDetail> getTblDepositDetails() {
		return this.tblDepositDetails;
	}

	public void setTblDepositDetails(List<TblDepositDetail> tblDepositDetails) {
		this.tblDepositDetails = tblDepositDetails;
	}

	public TblDepositDetail addTblDepositDetail(TblDepositDetail tblDepositDetail) {
		getTblDepositDetails().add(tblDepositDetail);
		tblDepositDetail.setLkpBranch(this);

		return tblDepositDetail;
	}

	public TblDepositDetail removeTblDepositDetail(TblDepositDetail tblDepositDetail) {
		getTblDepositDetails().remove(tblDepositDetail);
		tblDepositDetail.setLkpBranch(null);

		return tblDepositDetail;
	}

	public List<TblTransHead> getTblTransHeads() {
		return this.tblTransHeads;
	}

	public void setTblTransHeads(List<TblTransHead> tblTransHeads) {
		this.tblTransHeads = tblTransHeads;
	}

	public TblTransHead addTblTransHead(TblTransHead tblTransHead) {
		getTblTransHeads().add(tblTransHead);
		tblTransHead.setLkpBranch(this);

		return tblTransHead;
	}

	public TblTransHead removeTblTransHead(TblTransHead tblTransHead) {
		getTblTransHeads().remove(tblTransHead);
		tblTransHead.setLkpBranch(null);

		return tblTransHead;
	}

	public List<TblUser> getTblUsers() {
		return this.tblUsers;
	}

	public void setTblUsers(List<TblUser> tblUsers) {
		this.tblUsers = tblUsers;
	}

	public TblUser addTblUser(TblUser tblUser) {
		getTblUsers().add(tblUser);
		tblUser.setLkpBranch(this);

		return tblUser;
	}

	public TblUser removeTblUser(TblUser tblUser) {
		getTblUsers().remove(tblUser);
		tblUser.setLkpBranch(null);

		return tblUser;
	}

}