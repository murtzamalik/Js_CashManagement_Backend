package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_AREA database table.
 * 
 */
@Entity
@Table(name="LKP_AREA")
@NamedQuery(name="LkpArea.findAll", query="SELECT l FROM LkpArea l")
public class LkpArea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_AREA_AREAID_GENERATOR", sequenceName="LKP_AREA_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_AREA_AREAID_GENERATOR")
	@Column(name="AREA_ID")
	private long areaId;

	@Column(name="AREA_CODE")
	private String areaCode;

	@Column(name="AREA_NAME")
	private String areaName;

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

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Temporal(TemporalType.DATE)
	@Column(name="REOPENING_DATE")
	private Date reopeningDate;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpCity
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private LkpCity lkpCity;

	//bi-directional many-to-one association to TblCompany
	@JsonIgnore
	@OneToMany(mappedBy="lkpArea")
	private List<TblCompany> tblCompanies;

	//bi-directional many-to-one association to TblCompanyGroup
	@JsonIgnore
	@OneToMany(mappedBy="lkpArea")
	private List<TblCompanyGroup> tblCompanyGroups;

	public LkpArea() {
	}

	public long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public LkpCity getLkpCity() {
		return this.lkpCity;
	}

	public void setLkpCity(LkpCity lkpCity) {
		this.lkpCity = lkpCity;
	}

	public List<TblCompany> getTblCompanies() {
		return this.tblCompanies;
	}

	public void setTblCompanies(List<TblCompany> tblCompanies) {
		this.tblCompanies = tblCompanies;
	}

	public TblCompany addTblCompany(TblCompany tblCompany) {
		getTblCompanies().add(tblCompany);
		tblCompany.setLkpArea(this);

		return tblCompany;
	}

	public TblCompany removeTblCompany(TblCompany tblCompany) {
		getTblCompanies().remove(tblCompany);
		tblCompany.setLkpArea(null);

		return tblCompany;
	}

	public List<TblCompanyGroup> getTblCompanyGroups() {
		return this.tblCompanyGroups;
	}

	public void setTblCompanyGroups(List<TblCompanyGroup> tblCompanyGroups) {
		this.tblCompanyGroups = tblCompanyGroups;
	}

	public TblCompanyGroup addTblCompanyGroup(TblCompanyGroup tblCompanyGroup) {
		getTblCompanyGroups().add(tblCompanyGroup);
		tblCompanyGroup.setLkpArea(this);

		return tblCompanyGroup;
	}

	public TblCompanyGroup removeTblCompanyGroup(TblCompanyGroup tblCompanyGroup) {
		getTblCompanyGroups().remove(tblCompanyGroup);
		tblCompanyGroup.setLkpArea(null);

		return tblCompanyGroup;
	}

}