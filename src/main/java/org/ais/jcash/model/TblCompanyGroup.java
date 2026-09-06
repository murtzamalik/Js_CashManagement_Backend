package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_COMPANY_GROUP database table.
 * 
 */
@Entity
@Table(name="TBL_COMPANY_GROUP")
@NamedQuery(name="TblCompanyGroup.findAll", query="SELECT t FROM TblCompanyGroup t")
public class TblCompanyGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_COMPANY_GROUP_COMPANYGROUPID_GENERATOR", sequenceName="TBL_COMPANY_GROUP_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_COMPANY_GROUP_COMPANYGROUPID_GENERATOR")
	@Column(name="COMPANY_GROUP_ID")
	private long companyGroupId;

	@Column(name="ACCOUNT_NO")
	private String accountNo;

	@Column(name="ADDRESS_1")
	private String address1;

	@Column(name="ADDRESS_2")
	private String address2;

	@Column(name="ADDRESS_3")
	private String address3;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	@Column(name="CIS_NO")
	private BigDecimal cisNo;

	private String closed;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSED_DATE")
	private Date closedDate;

	@Temporal(TemporalType.DATE)
	@Column(name="CLOSED_TILL")
	private Date closedTill;

	@Column(name="CLOSURE_REASON")
	private String closureReason;

	@Column(name="CONTACT_NO")
	private String contactNo;

	@Column(name="CONTACT_PERSON")
	private String contactPerson;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CREDIT_LIMIT")
	private BigDecimal creditLimit;

	private String discountable;

	private String email;

	@Column(name="FAX_NO")
	private String faxNo;

	@Column(name="GROUP_CODE")
	private String groupCode;

	@Column(name="GROUP_NAME")
	private String groupName;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	@Column(name="OUTSTANDING_AMOUNT")
	private BigDecimal outstandingAmount;

	private String relationship;

	@Temporal(TemporalType.DATE)
	@Column(name="REOPENING_DATE")
	private Date reopeningDate;

	private BigDecimal updateindex;

	private String url;

	//bi-directional many-to-one association to TblCompany
	@OneToMany(mappedBy="tblCompanyGroup")
	private List<TblCompany> tblCompanies;

	//bi-directional many-to-one association to LkpArea
	@ManyToOne
	@JoinColumn(name="AREA_ID")
	private LkpArea lkpArea;

	public TblCompanyGroup() {
	}

	public long getCompanyGroupId() {
		return this.companyGroupId;
	}

	public void setCompanyGroupId(long companyGroupId) {
		this.companyGroupId = companyGroupId;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
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

	public BigDecimal getCisNo() {
		return this.cisNo;
	}

	public void setCisNo(BigDecimal cisNo) {
		this.cisNo = cisNo;
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

	public String getContactNo() {
		return this.contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
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

	public BigDecimal getCreditLimit() {
		return this.creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getDiscountable() {
		return this.discountable;
	}

	public void setDiscountable(String discountable) {
		this.discountable = discountable;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFaxNo() {
		return this.faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public BigDecimal getOutstandingAmount() {
		return this.outstandingAmount;
	}

	public void setOutstandingAmount(BigDecimal outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TblCompany> getTblCompanies() {
		return this.tblCompanies;
	}

	public void setTblCompanies(List<TblCompany> tblCompanies) {
		this.tblCompanies = tblCompanies;
	}

	public TblCompany addTblCompany(TblCompany tblCompany) {
		getTblCompanies().add(tblCompany);
		tblCompany.setTblCompanyGroup(this);

		return tblCompany;
	}

	public TblCompany removeTblCompany(TblCompany tblCompany) {
		getTblCompanies().remove(tblCompany);
		tblCompany.setTblCompanyGroup(null);

		return tblCompany;
	}

	public LkpArea getLkpArea() {
		return this.lkpArea;
	}

	public void setLkpArea(LkpArea lkpArea) {
		this.lkpArea = lkpArea;
	}

}