package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_BANK database table.
 * 
 */
@Entity
@Table(name="LKP_BANK")
@NamedQuery(name="LkpBank.findAll", query="SELECT l FROM LkpBank l")
public class LkpBank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_BANK_BANKID_GENERATOR", sequenceName="LKP_BANK_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_BANK_BANKID_GENERATOR")
	@Column(name="BANK_ID")
	private long bankId;

	@Column(name="BANK_CODE")
	private String bankCode;

	@Column(name="BANK_DESCRIPTION")
	private String bankDescription;

	@Column(name="BANK_IMD")
	private String bankImd;

	@Column(name="BANK_NAME")
	private String bankName;

	@Column(name="BANK_WEBSITE")
	private String bankWebsite;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	@Column(name="CONTACT_NO")
	private String contactNo;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="IS_IBFT")
	private String isIbft;

	@Column(name="IS_PRINCIPAL")
	private String isPrincipal;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpBranch
	@JsonIgnore
	@OneToMany(mappedBy="lkpBank")
	private List<LkpBranch> lkpBranches;

	//bi-directional many-to-one association to TblAccount
	@JsonIgnore
	@OneToMany(mappedBy="lkpBank")
	private List<TblAccount> tblAccounts;

	public LkpBank() {
	}

	public long getBankId() {
		return this.bankId;
	}

	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankDescription() {
		return this.bankDescription;
	}

	public void setBankDescription(String bankDescription) {
		this.bankDescription = bankDescription;
	}

	public String getBankImd() {
		return this.bankImd;
	}

	public void setBankImd(String bankImd) {
		this.bankImd = bankImd;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankWebsite() {
		return this.bankWebsite;
	}

	public void setBankWebsite(String bankWebsite) {
		this.bankWebsite = bankWebsite;
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

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsIbft() {
		return this.isIbft;
	}

	public void setIsIbft(String isIbft) {
		this.isIbft = isIbft;
	}

	public String getIsPrincipal() {
		return this.isPrincipal;
	}

	public void setIsPrincipal(String isPrincipal) {
		this.isPrincipal = isPrincipal;
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

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<LkpBranch> getLkpBranches() {
		return this.lkpBranches;
	}

	public void setLkpBranches(List<LkpBranch> lkpBranches) {
		this.lkpBranches = lkpBranches;
	}

	public LkpBranch addLkpBranch(LkpBranch lkpBranch) {
		getLkpBranches().add(lkpBranch);
		lkpBranch.setLkpBank(this);

		return lkpBranch;
	}

	public LkpBranch removeLkpBranch(LkpBranch lkpBranch) {
		getLkpBranches().remove(lkpBranch);
		lkpBranch.setLkpBank(null);

		return lkpBranch;
	}

	public List<TblAccount> getTblAccounts() {
		return this.tblAccounts;
	}

	public void setTblAccounts(List<TblAccount> tblAccounts) {
		this.tblAccounts = tblAccounts;
	}

	public TblAccount addTblAccount(TblAccount tblAccount) {
		getTblAccounts().add(tblAccount);
		tblAccount.setLkpBank(this);

		return tblAccount;
	}

	public TblAccount removeTblAccount(TblAccount tblAccount) {
		getTblAccounts().remove(tblAccount);
		tblAccount.setLkpBank(null);

		return tblAccount;
	}

}