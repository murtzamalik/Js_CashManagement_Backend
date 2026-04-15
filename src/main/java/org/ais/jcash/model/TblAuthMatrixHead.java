package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_AUTH_MATRIX_HEAD database table.
 * 
 */
@Entity
@Table(name="TBL_AUTH_MATRIX_HEAD")
@NamedQuery(name="TblAuthMatrixHead.findAll", query="SELECT t FROM TblAuthMatrixHead t")
public class TblAuthMatrixHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_AUTH_MATRIX_HEAD_AUTHMATRIXHEADID_GENERATOR", sequenceName="TBL_AUTH_MATRIX_HEAD_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_AUTH_MATRIX_HEAD_AUTHMATRIXHEADID_GENERATOR")
	@Column(name="AUTH_MATRIX_HEAD_ID")
	private long authMatrixHeadId;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FROM_AMOUNT")
	private BigDecimal fromAmount;

	@Column(name="IS_SEQUENTIAL")
	private String isSequential;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="TO_AMOUNT")
	private BigDecimal toAmount;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAuthHead
	@JsonIgnore
	@OneToMany(mappedBy="tblAuthMatrixHead")
	private List<TblAuthHead> tblAuthHeads;

	//bi-directional many-to-one association to TblAuthMatrixDetail
	@JsonIgnore
	@OneToMany(mappedBy="tblAuthMatrixHead")
	private List<TblAuthMatrixDetail> tblAuthMatrixDetails;

	//bi-directional many-to-one association to TblCompany
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private TblCompany tblCompany;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblAuthMatrixHead() {
	}

	public long getAuthMatrixHeadId() {
		return this.authMatrixHeadId;
	}

	public void setAuthMatrixHeadId(long authMatrixHeadId) {
		this.authMatrixHeadId = authMatrixHeadId;
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

	public BigDecimal getFromAmount() {
		return this.fromAmount;
	}

	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}

	public String getIsSequential() {
		return this.isSequential;
	}

	public void setIsSequential(String isSequential) {
		this.isSequential = isSequential;
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

	public BigDecimal getToAmount() {
		return this.toAmount;
	}

	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
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
		tblAuthHead.setTblAuthMatrixHead(this);

		return tblAuthHead;
	}

	public TblAuthHead removeTblAuthHead(TblAuthHead tblAuthHead) {
		getTblAuthHeads().remove(tblAuthHead);
		tblAuthHead.setTblAuthMatrixHead(null);

		return tblAuthHead;
	}

	public List<TblAuthMatrixDetail> getTblAuthMatrixDetails() {
		return this.tblAuthMatrixDetails;
	}

	public void setTblAuthMatrixDetails(List<TblAuthMatrixDetail> tblAuthMatrixDetails) {
		this.tblAuthMatrixDetails = tblAuthMatrixDetails;
	}

	public TblAuthMatrixDetail addTblAuthMatrixDetail(TblAuthMatrixDetail tblAuthMatrixDetail) {
		getTblAuthMatrixDetails().add(tblAuthMatrixDetail);
		tblAuthMatrixDetail.setTblAuthMatrixHead(this);

		return tblAuthMatrixDetail;
	}

	public TblAuthMatrixDetail removeTblAuthMatrixDetail(TblAuthMatrixDetail tblAuthMatrixDetail) {
		getTblAuthMatrixDetails().remove(tblAuthMatrixDetail);
		tblAuthMatrixDetail.setTblAuthMatrixHead(null);

		return tblAuthMatrixDetail;
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

}