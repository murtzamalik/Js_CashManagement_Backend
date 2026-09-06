package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_COMPANY database table.
 * 
 */
@Entity
@Table(name="TBL_COMPANY")
@NamedQuery(name="TblCompany.findAll", query="SELECT t FROM TblCompany t")
public class TblCompany implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_COMPANY_COMPANYID_GENERATOR", sequenceName="TBL_COMPANY_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_COMPANY_COMPANYID_GENERATOR")
	@Column(name="COMPANY_ID")
	private long companyId;

	@Column(name="ACCOUNT_NO")
	private String accountNo;

	@Column(name="ACCOUNTING_ENTRY_REQUEST")
	private String accountingEntryRequest;

	@Column(name="ACCOUNTING_ENTRY_TYPE")
	private String accountingEntryType;

	@Column(name="ADDRESS_1")
	private String address1;

	@Column(name="ADDRESS_2")
	private String address2;

	@Column(name="ADDRESS_3")
	private String address3;

	@Column(name="BACKUP_CONTACT")
	private String backupContact;

	@Column(name="BACKUP_EMAIL_1")
	private String backupEmail1;

	@Column(name="BACKUP_EMAIL_2")
	private String backupEmail2;

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

	@Column(name="COMPANY_CODE")
	private String companyCode;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="CONTACT_NO")
	private String contactNo;

	@Column(name="CONTACT_PERSON")
	private String contactPerson;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="CREDIT_LIMIT")
	private BigDecimal creditLimit;

	@Column(name="CUSTOMER_TYPE")
	private String customerType;

	private String discountable;

	private String email;

	@Column(name="FAX_NO_1")
	private String faxNo1;

	@Column(name="FAX_NO_2")
	private String faxNo2;

	@Column(name="FAX_NO_3")
	private String faxNo3;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	@Column(name="OUTSTANDING_AMOUNT")
	private BigDecimal outstandingAmount;

	@Column(name="PRODUCT_MANAGER")
	private String productManager;

	@Column(name="RELATIONSHIP_MANAGER")
	private String relationshipManager;

	@Temporal(TemporalType.DATE)
	@Column(name="REOPENING_DATE")
	private Date reopeningDate;

	@Column(name="TELEX_NO")
	private String telexNo;

	@Column(name="UB_COMPANY_CODE")
	private String ubCompanyCode;

	@Column(name="UB_CUSTOMER")
	private String ubCustomer;

	private BigDecimal updateindex;

	private String url;

	//bi-directional many-to-one association to TblAccount
	@OneToMany(mappedBy="tblCompany")
	private List<TblAccount> tblAccounts;

	//bi-directional many-to-one association to TblAuthMatrixHead
	@OneToMany(mappedBy="tblCompany")
	private List<TblAuthMatrixHead> tblAuthMatrixHeads;

	//bi-directional many-to-one association to LkpArea
	@ManyToOne
	@JoinColumn(name="AREA_ID")
	private LkpArea lkpArea;

	//bi-directional many-to-one association to TblCompanyGroup
	@ManyToOne
	@JoinColumn(name="COMPANY_GROUP_ID")
	private TblCompanyGroup tblCompanyGroup;

	//bi-directional many-to-one association to TblCompanyProduct
	@OneToMany(mappedBy="tblCompany")
	private List<TblCompanyProduct> tblCompanyProducts;

	//bi-directional many-to-one association to TblParserCompanyConfig
	@OneToMany(mappedBy="tblCompany")
	private List<TblParserCompanyConfig> tblParserCompanyConfigs;

	//bi-directional many-to-one association to TblTransHead
	@OneToMany(mappedBy="tblCompany")
	private List<TblTransHead> tblTransHeads;

	//bi-directional many-to-one association to TblUser
	@OneToMany(mappedBy="tblCompany")
	private List<TblUser> tblUsers;

	//bi-directional many-to-one association to TblUserAccountProduct
	@OneToMany(mappedBy="tblCompany")
	private List<TblUserAccountProduct> tblUserAccountProducts;

	public TblCompany() {
	}

	public long getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountingEntryRequest() {
		return this.accountingEntryRequest;
	}

	public void setAccountingEntryRequest(String accountingEntryRequest) {
		this.accountingEntryRequest = accountingEntryRequest;
	}

	public String getAccountingEntryType() {
		return this.accountingEntryType;
	}

	public void setAccountingEntryType(String accountingEntryType) {
		this.accountingEntryType = accountingEntryType;
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

	public String getBackupContact() {
		return this.backupContact;
	}

	public void setBackupContact(String backupContact) {
		this.backupContact = backupContact;
	}

	public String getBackupEmail1() {
		return this.backupEmail1;
	}

	public void setBackupEmail1(String backupEmail1) {
		this.backupEmail1 = backupEmail1;
	}

	public String getBackupEmail2() {
		return this.backupEmail2;
	}

	public void setBackupEmail2(String backupEmail2) {
		this.backupEmail2 = backupEmail2;
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

	public String getCompanyCode() {
		return this.companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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

	public String getFaxNo1() {
		return this.faxNo1;
	}

	public void setFaxNo1(String faxNo1) {
		this.faxNo1 = faxNo1;
	}

	public String getFaxNo2() {
		return this.faxNo2;
	}

	public void setFaxNo2(String faxNo2) {
		this.faxNo2 = faxNo2;
	}

	public String getFaxNo3() {
		return this.faxNo3;
	}

	public void setFaxNo3(String faxNo3) {
		this.faxNo3 = faxNo3;
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

	public String getProductManager() {
		return this.productManager;
	}

	public void setProductManager(String productManager) {
		this.productManager = productManager;
	}

	public String getRelationshipManager() {
		return this.relationshipManager;
	}

	public void setRelationshipManager(String relationshipManager) {
		this.relationshipManager = relationshipManager;
	}

	public Date getReopeningDate() {
		return this.reopeningDate;
	}

	public void setReopeningDate(Date reopeningDate) {
		this.reopeningDate = reopeningDate;
	}

	public String getTelexNo() {
		return this.telexNo;
	}

	public void setTelexNo(String telexNo) {
		this.telexNo = telexNo;
	}

	public String getUbCompanyCode() {
		return this.ubCompanyCode;
	}

	public void setUbCompanyCode(String ubCompanyCode) {
		this.ubCompanyCode = ubCompanyCode;
	}

	public String getUbCustomer() {
		return this.ubCustomer;
	}

	public void setUbCustomer(String ubCustomer) {
		this.ubCustomer = ubCustomer;
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

	public List<TblAccount> getTblAccounts() {
		return this.tblAccounts;
	}

	public void setTblAccounts(List<TblAccount> tblAccounts) {
		this.tblAccounts = tblAccounts;
	}

	public TblAccount addTblAccount(TblAccount tblAccount) {
		getTblAccounts().add(tblAccount);
		tblAccount.setTblCompany(this);

		return tblAccount;
	}

	public TblAccount removeTblAccount(TblAccount tblAccount) {
		getTblAccounts().remove(tblAccount);
		tblAccount.setTblCompany(null);

		return tblAccount;
	}

	public List<TblAuthMatrixHead> getTblAuthMatrixHeads() {
		return this.tblAuthMatrixHeads;
	}

	public void setTblAuthMatrixHeads(List<TblAuthMatrixHead> tblAuthMatrixHeads) {
		this.tblAuthMatrixHeads = tblAuthMatrixHeads;
	}

	public TblAuthMatrixHead addTblAuthMatrixHead(TblAuthMatrixHead tblAuthMatrixHead) {
		getTblAuthMatrixHeads().add(tblAuthMatrixHead);
		tblAuthMatrixHead.setTblCompany(this);

		return tblAuthMatrixHead;
	}

	public TblAuthMatrixHead removeTblAuthMatrixHead(TblAuthMatrixHead tblAuthMatrixHead) {
		getTblAuthMatrixHeads().remove(tblAuthMatrixHead);
		tblAuthMatrixHead.setTblCompany(null);

		return tblAuthMatrixHead;
	}

	public LkpArea getLkpArea() {
		return this.lkpArea;
	}

	public void setLkpArea(LkpArea lkpArea) {
		this.lkpArea = lkpArea;
	}

	public TblCompanyGroup getTblCompanyGroup() {
		return this.tblCompanyGroup;
	}

	public void setTblCompanyGroup(TblCompanyGroup tblCompanyGroup) {
		this.tblCompanyGroup = tblCompanyGroup;
	}

	public List<TblCompanyProduct> getTblCompanyProducts() {
		return this.tblCompanyProducts;
	}

	public void setTblCompanyProducts(List<TblCompanyProduct> tblCompanyProducts) {
		this.tblCompanyProducts = tblCompanyProducts;
	}

	public TblCompanyProduct addTblCompanyProduct(TblCompanyProduct tblCompanyProduct) {
		getTblCompanyProducts().add(tblCompanyProduct);
		tblCompanyProduct.setTblCompany(this);

		return tblCompanyProduct;
	}

	public TblCompanyProduct removeTblCompanyProduct(TblCompanyProduct tblCompanyProduct) {
		getTblCompanyProducts().remove(tblCompanyProduct);
		tblCompanyProduct.setTblCompany(null);

		return tblCompanyProduct;
	}

	public List<TblParserCompanyConfig> getTblParserCompanyConfigs() {
		return this.tblParserCompanyConfigs;
	}

	public void setTblParserCompanyConfigs(List<TblParserCompanyConfig> tblParserCompanyConfigs) {
		this.tblParserCompanyConfigs = tblParserCompanyConfigs;
	}

	public TblParserCompanyConfig addTblParserCompanyConfig(TblParserCompanyConfig tblParserCompanyConfig) {
		getTblParserCompanyConfigs().add(tblParserCompanyConfig);
		tblParserCompanyConfig.setTblCompany(this);

		return tblParserCompanyConfig;
	}

	public TblParserCompanyConfig removeTblParserCompanyConfig(TblParserCompanyConfig tblParserCompanyConfig) {
		getTblParserCompanyConfigs().remove(tblParserCompanyConfig);
		tblParserCompanyConfig.setTblCompany(null);

		return tblParserCompanyConfig;
	}

	public List<TblTransHead> getTblTransHeads() {
		return this.tblTransHeads;
	}

	public void setTblTransHeads(List<TblTransHead> tblTransHeads) {
		this.tblTransHeads = tblTransHeads;
	}

	public TblTransHead addTblTransHead(TblTransHead tblTransHead) {
		getTblTransHeads().add(tblTransHead);
		tblTransHead.setTblCompany(this);

		return tblTransHead;
	}

	public TblTransHead removeTblTransHead(TblTransHead tblTransHead) {
		getTblTransHeads().remove(tblTransHead);
		tblTransHead.setTblCompany(null);

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
		tblUser.setTblCompany(this);

		return tblUser;
	}

	public TblUser removeTblUser(TblUser tblUser) {
		getTblUsers().remove(tblUser);
		tblUser.setTblCompany(null);

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
		tblUserAccountProduct.setTblCompany(this);

		return tblUserAccountProduct;
	}

	public TblUserAccountProduct removeTblUserAccountProduct(TblUserAccountProduct tblUserAccountProduct) {
		getTblUserAccountProducts().remove(tblUserAccountProduct);
		tblUserAccountProduct.setTblCompany(null);

		return tblUserAccountProduct;
	}

}