package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_PRODUCT_COLLECTION database table.
 * 
 */
@Entity
@Table(name="TBL_PRODUCT_COLLECTION")
@NamedQuery(name="TblProductCollection.findAll", query="SELECT t FROM TblProductCollection t")
public class TblProductCollection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PRODUCT_COLLECTION_PRODUCTCOLLECTIONID_GENERATOR", sequenceName="TBL_PRODUCT_COLLECTION_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PRODUCT_COLLECTION_PRODUCTCOLLECTIONID_GENERATOR")
	@Column(name="PRODUCT_COLLECTION_ID")
	private long productCollectionId;

	@Column(name="BENEFICIARY_ACCOUNT_NO")
	private String beneficiaryAccountNo;

	@Column(name="BENEFICIARY_ACCOUNT_TITLE")
	private String beneficiaryAccountTitle;

	@Column(name="BENEFICIARY_ADDRESS")
	private String beneficiaryAddress;

	@Column(name="BENEFICIARY_BANK")
	private String beneficiaryBank;

	@Column(name="BENEFICIARY_EMAIL")
	private String beneficiaryEmail;

	@Column(name="BENEFICIARY_NAME")
	private String beneficiaryName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="DOCUMENT_NO")
	private String documentNo;

	@Column(name="DOCUMENT_TYPE")
	private String documentType;

	private String lodgement;

	private String loquidation;

	@Column(name="MOBILE_NO")
	private String mobileNo;

	@Column(name="PAYMENT_MODE")
	private String paymentMode;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="PRODUCT_NATURE")
	private String productNature;

	//bi-directional many-to-one association to TblProduct
	@JsonIgnore
	@OneToMany(mappedBy="tblProductCollection")
	private List<TblProduct> tblProducts;

	public TblProductCollection() {
	}

	public long getProductCollectionId() {
		return this.productCollectionId;
	}

	public void setProductCollectionId(long productCollectionId) {
		this.productCollectionId = productCollectionId;
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

	public String getBeneficiaryBank() {
		return this.beneficiaryBank;
	}

	public void setBeneficiaryBank(String beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
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

	public String getLodgement() {
		return this.lodgement;
	}

	public void setLodgement(String lodgement) {
		this.lodgement = lodgement;
	}

	public String getLoquidation() {
		return this.loquidation;
	}

	public void setLoquidation(String loquidation) {
		this.loquidation = loquidation;
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

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNature() {
		return this.productNature;
	}

	public void setProductNature(String productNature) {
		this.productNature = productNature;
	}

	public List<TblProduct> getTblProducts() {
		return this.tblProducts;
	}

	public void setTblProducts(List<TblProduct> tblProducts) {
		this.tblProducts = tblProducts;
	}

	public TblProduct addTblProduct(TblProduct tblProduct) {
		getTblProducts().add(tblProduct);
		tblProduct.setTblProductCollection(this);

		return tblProduct;
	}

	public TblProduct removeTblProduct(TblProduct tblProduct) {
		getTblProducts().remove(tblProduct);
		tblProduct.setTblProductCollection(null);

		return tblProduct;
	}

}