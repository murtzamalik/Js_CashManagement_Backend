package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_PRODUCT database table.
 * 
 */
@Entity
@Table(name="TBL_PRODUCT")
@NamedQuery(name="TblProduct.findAll", query="SELECT t FROM TblProduct t")
public class TblProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PRODUCT_PRODUCTID_GENERATOR", sequenceName="TBL_PRODUCT_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PRODUCT_PRODUCTID_GENERATOR")
	@Column(name="PRODUCT_ID")
	private long productId;

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

	@Column(name="CORR_BANK_EXPENSE")
	private String corrBankExpense;

	@Column(name="COURIER_EXPENSE")
	private String courierExpense;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FEE_COMMISION")
	private String feeCommision;

	@Column(name="FLOAT_REVENUE_ACCOUNT")
	private String floatRevenueAccount;

	@Column(name="INTEREST_REVENUE_ACCOUNT")
	private String interestRevenueAccount;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MAIL_EXPENSE")
	private String mailExpense;

	@Column(name="MASTER_PRODUCT_CODE")
	private String masterProductCode;

	@Column(name="MASTER_PRODUCT_NAME")
	private String masterProductName;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="MISCELLANEOUS_EXPENSE")
	private String miscellaneousExpense;

	@Column(name="PRODUCT_BEHAVIOR")
	private String productBehavior;

	@Column(name="PRODUCT_MANAGER_ID")
	private BigDecimal productManagerId;

	@Column(name="PRODUCT_NATURE")
	private String productNature;

	@Column(name="PRODUCT_REVENUE_ACCOUNT")
	private String productRevenueAccount;

	@Column(name="PRODUCT_SUNDRY_ACCOUNT")
	private String productSundryAccount;

	@Column(name="PRODUCT_TYPE")
	private String productType;

	@Temporal(TemporalType.DATE)
	@Column(name="REOPENING_DATE")
	private Date reopeningDate;

	@Column(name="SUB_PRODUCT_CODE")
	private String subProductCode;

	@Column(name="SUB_PRODUCT_NAME")
	private String subProductName;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAuthMatrixHead
	@JsonIgnore
	@OneToMany(mappedBy="tblProduct")
	private List<TblAuthMatrixHead> tblAuthMatrixHeads;

	//bi-directional many-to-one association to TblCompanyProduct
	@JsonIgnore
	@OneToMany(mappedBy="tblProduct")
	private List<TblCompanyProduct> tblCompanyProducts;

	//bi-directional many-to-one association to TblParserCompanyConfig
	@JsonIgnore
	@OneToMany(mappedBy="tblProduct")
	private List<TblParserCompanyConfig> tblParserCompanyConfigs;

	//bi-directional many-to-one association to TblParserHead
	@JsonIgnore
	@OneToMany(mappedBy="tblProduct")
	private List<TblParserHead> tblParserHeads;

	//bi-directional many-to-one association to TblProductCollection
	@ManyToOne
	@JoinColumn(name="PRODUCT_COLLECTION_ID")
	private TblProductCollection tblProductCollection;

	//bi-directional many-to-one association to TblTransHead
	@JsonIgnore
	@OneToMany(mappedBy="tblProduct")
	private List<TblTransHead> tblTransHeads;

	//bi-directional many-to-one association to TblUserAccountProduct
	@JsonIgnore
	@OneToMany(mappedBy="tblProduct")
	private List<TblUserAccountProduct> tblUserAccountProducts;

	public TblProduct() {
	}

	public long getProductId() {
		return this.productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
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

	public String getCorrBankExpense() {
		return this.corrBankExpense;
	}

	public void setCorrBankExpense(String corrBankExpense) {
		this.corrBankExpense = corrBankExpense;
	}

	public String getCourierExpense() {
		return this.courierExpense;
	}

	public void setCourierExpense(String courierExpense) {
		this.courierExpense = courierExpense;
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

	public String getFeeCommision() {
		return this.feeCommision;
	}

	public void setFeeCommision(String feeCommision) {
		this.feeCommision = feeCommision;
	}

	public String getFloatRevenueAccount() {
		return this.floatRevenueAccount;
	}

	public void setFloatRevenueAccount(String floatRevenueAccount) {
		this.floatRevenueAccount = floatRevenueAccount;
	}

	public String getInterestRevenueAccount() {
		return this.interestRevenueAccount;
	}

	public void setInterestRevenueAccount(String interestRevenueAccount) {
		this.interestRevenueAccount = interestRevenueAccount;
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

	public String getMailExpense() {
		return this.mailExpense;
	}

	public void setMailExpense(String mailExpense) {
		this.mailExpense = mailExpense;
	}

	public String getMasterProductCode() {
		return this.masterProductCode;
	}

	public void setMasterProductCode(String masterProductCode) {
		this.masterProductCode = masterProductCode;
	}

	public String getMasterProductName() {
		return this.masterProductName;
	}

	public void setMasterProductName(String masterProductName) {
		this.masterProductName = masterProductName;
	}

	public String getMcStatus() {
		return this.mcStatus;
	}

	public void setMcStatus(String mcStatus) {
		this.mcStatus = mcStatus;
	}

	public String getMiscellaneousExpense() {
		return this.miscellaneousExpense;
	}

	public void setMiscellaneousExpense(String miscellaneousExpense) {
		this.miscellaneousExpense = miscellaneousExpense;
	}

	public String getProductBehavior() {
		return this.productBehavior;
	}

	public void setProductBehavior(String productBehavior) {
		this.productBehavior = productBehavior;
	}

	public BigDecimal getProductManagerId() {
		return this.productManagerId;
	}

	public void setProductManagerId(BigDecimal productManagerId) {
		this.productManagerId = productManagerId;
	}

	public String getProductNature() {
		return this.productNature;
	}

	public void setProductNature(String productNature) {
		this.productNature = productNature;
	}

	public String getProductRevenueAccount() {
		return this.productRevenueAccount;
	}

	public void setProductRevenueAccount(String productRevenueAccount) {
		this.productRevenueAccount = productRevenueAccount;
	}

	public String getProductSundryAccount() {
		return this.productSundryAccount;
	}

	public void setProductSundryAccount(String productSundryAccount) {
		this.productSundryAccount = productSundryAccount;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Date getReopeningDate() {
		return this.reopeningDate;
	}

	public void setReopeningDate(Date reopeningDate) {
		this.reopeningDate = reopeningDate;
	}

	public String getSubProductCode() {
		return this.subProductCode;
	}

	public void setSubProductCode(String subProductCode) {
		this.subProductCode = subProductCode;
	}

	public String getSubProductName() {
		return this.subProductName;
	}

	public void setSubProductName(String subProductName) {
		this.subProductName = subProductName;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblAuthMatrixHead> getTblAuthMatrixHeads() {
		return this.tblAuthMatrixHeads;
	}

	public void setTblAuthMatrixHeads(List<TblAuthMatrixHead> tblAuthMatrixHeads) {
		this.tblAuthMatrixHeads = tblAuthMatrixHeads;
	}

	public TblAuthMatrixHead addTblAuthMatrixHead(TblAuthMatrixHead tblAuthMatrixHead) {
		getTblAuthMatrixHeads().add(tblAuthMatrixHead);
		tblAuthMatrixHead.setTblProduct(this);

		return tblAuthMatrixHead;
	}

	public TblAuthMatrixHead removeTblAuthMatrixHead(TblAuthMatrixHead tblAuthMatrixHead) {
		getTblAuthMatrixHeads().remove(tblAuthMatrixHead);
		tblAuthMatrixHead.setTblProduct(null);

		return tblAuthMatrixHead;
	}

	public List<TblCompanyProduct> getTblCompanyProducts() {
		return this.tblCompanyProducts;
	}

	public void setTblCompanyProducts(List<TblCompanyProduct> tblCompanyProducts) {
		this.tblCompanyProducts = tblCompanyProducts;
	}

	public TblCompanyProduct addTblCompanyProduct(TblCompanyProduct tblCompanyProduct) {
		getTblCompanyProducts().add(tblCompanyProduct);
		tblCompanyProduct.setTblProduct(this);

		return tblCompanyProduct;
	}

	public TblCompanyProduct removeTblCompanyProduct(TblCompanyProduct tblCompanyProduct) {
		getTblCompanyProducts().remove(tblCompanyProduct);
		tblCompanyProduct.setTblProduct(null);

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
		tblParserCompanyConfig.setTblProduct(this);

		return tblParserCompanyConfig;
	}

	public TblParserCompanyConfig removeTblParserCompanyConfig(TblParserCompanyConfig tblParserCompanyConfig) {
		getTblParserCompanyConfigs().remove(tblParserCompanyConfig);
		tblParserCompanyConfig.setTblProduct(null);

		return tblParserCompanyConfig;
	}

	public List<TblParserHead> getTblParserHeads() {
		return this.tblParserHeads;
	}

	public void setTblParserHeads(List<TblParserHead> tblParserHeads) {
		this.tblParserHeads = tblParserHeads;
	}

	public TblParserHead addTblParserHead(TblParserHead tblParserHead) {
		getTblParserHeads().add(tblParserHead);
		tblParserHead.setTblProduct(this);

		return tblParserHead;
	}

	public TblParserHead removeTblParserHead(TblParserHead tblParserHead) {
		getTblParserHeads().remove(tblParserHead);
		tblParserHead.setTblProduct(null);

		return tblParserHead;
	}

	public TblProductCollection getTblProductCollection() {
		return this.tblProductCollection;
	}

	public void setTblProductCollection(TblProductCollection tblProductCollection) {
		this.tblProductCollection = tblProductCollection;
	}

	public List<TblTransHead> getTblTransHeads() {
		return this.tblTransHeads;
	}

	public void setTblTransHeads(List<TblTransHead> tblTransHeads) {
		this.tblTransHeads = tblTransHeads;
	}

	public TblTransHead addTblTransHead(TblTransHead tblTransHead) {
		getTblTransHeads().add(tblTransHead);
		tblTransHead.setTblProduct(this);

		return tblTransHead;
	}

	public TblTransHead removeTblTransHead(TblTransHead tblTransHead) {
		getTblTransHeads().remove(tblTransHead);
		tblTransHead.setTblProduct(null);

		return tblTransHead;
	}

	public List<TblUserAccountProduct> getTblUserAccountProducts() {
		return this.tblUserAccountProducts;
	}

	public void setTblUserAccountProducts(List<TblUserAccountProduct> tblUserAccountProducts) {
		this.tblUserAccountProducts = tblUserAccountProducts;
	}

	public TblUserAccountProduct addTblUserAccountProduct(TblUserAccountProduct tblUserAccountProduct) {
		getTblUserAccountProducts().add(tblUserAccountProduct);
		tblUserAccountProduct.setTblProduct(this);

		return tblUserAccountProduct;
	}

	public TblUserAccountProduct removeTblUserAccountProduct(TblUserAccountProduct tblUserAccountProduct) {
		getTblUserAccountProducts().remove(tblUserAccountProduct);
		tblUserAccountProduct.setTblProduct(null);

		return tblUserAccountProduct;
	}

}