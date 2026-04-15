package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_PARSER_COMPANY_CONFIG database table.
 * 
 */
@Entity
@Table(name="TBL_PARSER_COMPANY_CONFIG")
@NamedQuery(name="TblParserCompanyConfig.findAll", query="SELECT t FROM TblParserCompanyConfig t")
public class TblParserCompanyConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PARSER_COMPANY_CONFIG_PARSERCOMPANYCONFIGID_GENERATOR", sequenceName="TBL_PARSER_COMPANY_CONFIG_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PARSER_COMPANY_CONFIG_PARSERCOMPANYCONFIGID_GENERATOR")
	@Column(name="PARSER_COMPANY_CONFIG_ID")
	private long parserCompanyConfigId;

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

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblFileHead
	@JsonIgnore
	@OneToMany(mappedBy="tblParserCompanyConfig")
	private List<TblFileHead> tblFileHeads;

	//bi-directional many-to-one association to TblCompany
	@ManyToOne
	@JoinColumn(name="COMPANY_ID")
	private TblCompany tblCompany;

	//bi-directional many-to-one association to TblParserHead
	@ManyToOne
	@JoinColumn(name="PARSER_HEAD_ID")
	private TblParserHead tblParserHead;

	//bi-directional many-to-one association to TblProduct
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private TblProduct tblProduct;

	public TblParserCompanyConfig() {
	}

	public long getParserCompanyConfigId() {
		return this.parserCompanyConfigId;
	}

	public void setParserCompanyConfigId(long parserCompanyConfigId) {
		this.parserCompanyConfigId = parserCompanyConfigId;
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

	public List<TblFileHead> getTblFileHeads() {
		return this.tblFileHeads;
	}

	public void setTblFileHeads(List<TblFileHead> tblFileHeads) {
		this.tblFileHeads = tblFileHeads;
	}

	public TblFileHead addTblFileHead(TblFileHead tblFileHead) {
		getTblFileHeads().add(tblFileHead);
		tblFileHead.setTblParserCompanyConfig(this);

		return tblFileHead;
	}

	public TblFileHead removeTblFileHead(TblFileHead tblFileHead) {
		getTblFileHeads().remove(tblFileHead);
		tblFileHead.setTblParserCompanyConfig(null);

		return tblFileHead;
	}

	public TblCompany getTblCompany() {
		return this.tblCompany;
	}

	public void setTblCompany(TblCompany tblCompany) {
		this.tblCompany = tblCompany;
	}

	public TblParserHead getTblParserHead() {
		return this.tblParserHead;
	}

	public void setTblParserHead(TblParserHead tblParserHead) {
		this.tblParserHead = tblParserHead;
	}

	public TblProduct getTblProduct() {
		return this.tblProduct;
	}

	public void setTblProduct(TblProduct tblProduct) {
		this.tblProduct = tblProduct;
	}

}