package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_USER database table.
 * 
 */
@Entity
@Table(name="TBL_USER")
@NamedQuery(name="TblUser.findAll", query="SELECT t FROM TblUser t")
public class TblUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_USER_USERID_GENERATOR", sequenceName="TBL_USER_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_USER_USERID_GENERATOR")
	@Column(name="USER_ID")
	private long userId;

	@Column(name="ALLOW_ENTRY")
	private String allowEntry;

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

	private String department;

	private String designation;

	private String email;

	@Column(name="EMPLOYEE_NO")
	private String employeeNo;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name="PROFILE_EXPIRY")
	private Date profileExpiry;

	private BigDecimal updateindex;

	@Column(name="USER_CODE")
	private String userCode;

	@Column(name="USER_GROUP")
	private String userGroup;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to TblAuthDetail
	@OneToMany(mappedBy="tblUser")
	private List<TblAuthDetail> tblAuthDetails;

	//bi-directional many-to-one association to TblAuthMatrixDetail
	@OneToMany(mappedBy="tblUser")
	private List<TblAuthMatrixDetail> tblAuthMatrixDetails;

	//bi-directional many-to-one association to TblSmsMsgEmail
	@OneToMany(mappedBy="tblUser")
	private List<TblSmsMsgEmail> tblSmsMsgEmails;

	//bi-directional many-to-one association to TblTransHead
	@OneToMany(mappedBy="tblUser1")
	private List<TblTransHead> tblTransHeads1;

	//bi-directional many-to-one association to TblTransHead
	@OneToMany(mappedBy="tblUser2")
	private List<TblTransHead> tblTransHeads2;

	//bi-directional many-to-one association to LkpBaseLocation
	@ManyToOne
	@JoinColumn(name="BASE_LOCATION_ID")
	private LkpBaseLocation lkpBaseLocation;

	//bi-directional many-to-one association to LkpBranch
	@ManyToOne
	@JoinColumn(name="BRANCH_ID")
	private LkpBranch lkpBranch;

	//bi-directional many-to-one association to LkpUserType
	@ManyToOne
	@JoinColumn(name="USER_TYPE_ID")
	private LkpUserType lkpUserType;

	//bi-directional many-to-one association to TblAccount
	@ManyToOne
	@JoinColumn(name="TILL_ACCOUNT_ID")
	private TblAccount tblAccount;

	//bi-directional many-to-one association to TblCompany
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private TblCompany tblCompany;

	//bi-directional many-to-one association to TblUserAccountProduct
	@OneToMany(mappedBy="tblUser")
	private List<TblUserAccountProduct> tblUserAccountProducts;

	//bi-directional many-to-one association to TblUserLoginHistory
	@OneToMany(mappedBy="tblUser")
	private List<TblUserLoginHistory> tblUserLoginHistories;

	//bi-directional many-to-one association to TblUserRole
	@OneToMany(mappedBy="tblUser")
	private List<TblUserRole> tblUserRoles;

	public TblUser() {
	}

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeNo() {
		return this.employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getProfileExpiry() {
		return this.profileExpiry;
	}

	public void setProfileExpiry(Date profileExpiry) {
		this.profileExpiry = profileExpiry;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<TblAuthDetail> getTblAuthDetails() {
		return this.tblAuthDetails;
	}

	public void setTblAuthDetails(List<TblAuthDetail> tblAuthDetails) {
		this.tblAuthDetails = tblAuthDetails;
	}

	public TblAuthDetail addTblAuthDetail(TblAuthDetail tblAuthDetail) {
		getTblAuthDetails().add(tblAuthDetail);
		tblAuthDetail.setTblUser(this);

		return tblAuthDetail;
	}

	public TblAuthDetail removeTblAuthDetail(TblAuthDetail tblAuthDetail) {
		getTblAuthDetails().remove(tblAuthDetail);
		tblAuthDetail.setTblUser(null);

		return tblAuthDetail;
	}

	public List<TblAuthMatrixDetail> getTblAuthMatrixDetails() {
		return this.tblAuthMatrixDetails;
	}

	public void setTblAuthMatrixDetails(List<TblAuthMatrixDetail> tblAuthMatrixDetails) {
		this.tblAuthMatrixDetails = tblAuthMatrixDetails;
	}

	public TblAuthMatrixDetail addTblAuthMatrixDetail(TblAuthMatrixDetail tblAuthMatrixDetail) {
		getTblAuthMatrixDetails().add(tblAuthMatrixDetail);
		tblAuthMatrixDetail.setTblUser(this);

		return tblAuthMatrixDetail;
	}

	public TblAuthMatrixDetail removeTblAuthMatrixDetail(TblAuthMatrixDetail tblAuthMatrixDetail) {
		getTblAuthMatrixDetails().remove(tblAuthMatrixDetail);
		tblAuthMatrixDetail.setTblUser(null);

		return tblAuthMatrixDetail;
	}

	public List<TblSmsMsgEmail> getTblSmsMsgEmails() {
		return this.tblSmsMsgEmails;
	}

	public void setTblSmsMsgEmails(List<TblSmsMsgEmail> tblSmsMsgEmails) {
		this.tblSmsMsgEmails = tblSmsMsgEmails;
	}

	public TblSmsMsgEmail addTblSmsMsgEmail(TblSmsMsgEmail tblSmsMsgEmail) {
		getTblSmsMsgEmails().add(tblSmsMsgEmail);
		tblSmsMsgEmail.setTblUser(this);

		return tblSmsMsgEmail;
	}

	public TblSmsMsgEmail removeTblSmsMsgEmail(TblSmsMsgEmail tblSmsMsgEmail) {
		getTblSmsMsgEmails().remove(tblSmsMsgEmail);
		tblSmsMsgEmail.setTblUser(null);

		return tblSmsMsgEmail;
	}

	public List<TblTransHead> getTblTransHeads1() {
		return this.tblTransHeads1;
	}

	public void setTblTransHeads1(List<TblTransHead> tblTransHeads1) {
		this.tblTransHeads1 = tblTransHeads1;
	}

	public TblTransHead addTblTransHeads1(TblTransHead tblTransHeads1) {
		getTblTransHeads1().add(tblTransHeads1);
		tblTransHeads1.setTblUser1(this);

		return tblTransHeads1;
	}

	public TblTransHead removeTblTransHeads1(TblTransHead tblTransHeads1) {
		getTblTransHeads1().remove(tblTransHeads1);
		tblTransHeads1.setTblUser1(null);

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
		tblTransHeads2.setTblUser2(this);

		return tblTransHeads2;
	}

	public TblTransHead removeTblTransHeads2(TblTransHead tblTransHeads2) {
		getTblTransHeads2().remove(tblTransHeads2);
		tblTransHeads2.setTblUser2(null);

		return tblTransHeads2;
	}

	public LkpBaseLocation getLkpBaseLocation() {
		return this.lkpBaseLocation;
	}

	public void setLkpBaseLocation(LkpBaseLocation lkpBaseLocation) {
		this.lkpBaseLocation = lkpBaseLocation;
	}

	public LkpBranch getLkpBranch() {
		return this.lkpBranch;
	}

	public void setLkpBranch(LkpBranch lkpBranch) {
		this.lkpBranch = lkpBranch;
	}

	public LkpUserType getLkpUserType() {
		return this.lkpUserType;
	}

	public void setLkpUserType(LkpUserType lkpUserType) {
		this.lkpUserType = lkpUserType;
	}

	public TblAccount getTblAccount() {
		return this.tblAccount;
	}

	public void setTblAccount(TblAccount tblAccount) {
		this.tblAccount = tblAccount;
	}

	public TblCompany getTblCompany() {
		return this.tblCompany;
	}

	public void setTblCompany(TblCompany tblCompany) {
		this.tblCompany = tblCompany;
	}

	public List<TblUserAccountProduct> getTblUserAccountProducts() {
		return this.tblUserAccountProducts;
	}

	public void setTblUserAccountProducts(List<TblUserAccountProduct> tblUserAccountProducts) {
		this.tblUserAccountProducts = tblUserAccountProducts;
	}

	public TblUserAccountProduct addTblUserAccountProduct(TblUserAccountProduct tblUserAccountProduct) {
		getTblUserAccountProducts().add(tblUserAccountProduct);
		tblUserAccountProduct.setTblUser(this);

		return tblUserAccountProduct;
	}

	public TblUserAccountProduct removeTblUserAccountProduct(TblUserAccountProduct tblUserAccountProduct) {
		getTblUserAccountProducts().remove(tblUserAccountProduct);
		tblUserAccountProduct.setTblUser(null);

		return tblUserAccountProduct;
	}

	public List<TblUserLoginHistory> getTblUserLoginHistories() {
		return this.tblUserLoginHistories;
	}

	public void setTblUserLoginHistories(List<TblUserLoginHistory> tblUserLoginHistories) {
		this.tblUserLoginHistories = tblUserLoginHistories;
	}

	public TblUserLoginHistory addTblUserLoginHistory(TblUserLoginHistory tblUserLoginHistory) {
		getTblUserLoginHistories().add(tblUserLoginHistory);
		tblUserLoginHistory.setTblUser(this);

		return tblUserLoginHistory;
	}

	public TblUserLoginHistory removeTblUserLoginHistory(TblUserLoginHistory tblUserLoginHistory) {
		getTblUserLoginHistories().remove(tblUserLoginHistory);
		tblUserLoginHistory.setTblUser(null);

		return tblUserLoginHistory;
	}

	public List<TblUserRole> getTblUserRoles() {
		return this.tblUserRoles;
	}

	public void setTblUserRoles(List<TblUserRole> tblUserRoles) {
		this.tblUserRoles = tblUserRoles;
	}

	public TblUserRole addTblUserRole(TblUserRole tblUserRole) {
		getTblUserRoles().add(tblUserRole);
		tblUserRole.setTblUser(this);

		return tblUserRole;
	}

	public TblUserRole removeTblUserRole(TblUserRole tblUserRole) {
		getTblUserRoles().remove(tblUserRole);
		tblUserRole.setTblUser(null);

		return tblUserRole;
	}

}