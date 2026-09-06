package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_COUNTRY database table.
 * 
 */
@Entity
@Table(name="LKP_COUNTRY")
@NamedQuery(name="LkpCountry.findAll", query="SELECT l FROM LkpCountry l")
public class LkpCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_COUNTRY_COUNTRYID_GENERATOR", sequenceName="LKP_COUNTRY_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_COUNTRY_COUNTRYID_GENERATOR")
	@Column(name="COUNTRY_ID")
	private long countryId;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	@Column(name="COUNTRY_CODE")
	private String countryCode;

	@Column(name="COUNTRY_NAME")
	private String countryName;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_ACTIVE")
	private String isActive;

	@Column(name="IS_DELETED")
	private BigDecimal isDeleted;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	private BigDecimal updateindex;
	@JsonIgnore
	//bi-directional many-to-one association to LkpBranch
	@OneToMany(mappedBy="lkpCountry")
	private List<LkpBranch> lkpBranches;

	@JsonIgnore
	//bi-directional many-to-one association to LkpProvince
	@OneToMany(mappedBy="lkpCountry")
	private List<LkpProvince> lkpProvinces;

	@JsonIgnore
	//bi-directional many-to-one association to LkpRegion
	@OneToMany(mappedBy="lkpCountry")
	private List<LkpRegion> lkpRegions;

	public LkpCountry() {
	}

	public long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
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

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
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

	public BigDecimal getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(BigDecimal isDeleted) {
		this.isDeleted = isDeleted;
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
		lkpBranch.setLkpCountry(this);

		return lkpBranch;
	}

	public LkpBranch removeLkpBranch(LkpBranch lkpBranch) {
		getLkpBranches().remove(lkpBranch);
		lkpBranch.setLkpCountry(null);

		return lkpBranch;
	}

	public List<LkpProvince> getLkpProvinces() {
		return this.lkpProvinces;
	}

	public void setLkpProvinces(List<LkpProvince> lkpProvinces) {
		this.lkpProvinces = lkpProvinces;
	}

	public LkpProvince addLkpProvince(LkpProvince lkpProvince) {
		getLkpProvinces().add(lkpProvince);
		lkpProvince.setLkpCountry(this);

		return lkpProvince;
	}

	public LkpProvince removeLkpProvince(LkpProvince lkpProvince) {
		getLkpProvinces().remove(lkpProvince);
		lkpProvince.setLkpCountry(null);

		return lkpProvince;
	}

	public List<LkpRegion> getLkpRegions() {
		return this.lkpRegions;
	}

	public void setLkpRegions(List<LkpRegion> lkpRegions) {
		this.lkpRegions = lkpRegions;
	}

	public LkpRegion addLkpRegion(LkpRegion lkpRegion) {
		getLkpRegions().add(lkpRegion);
		lkpRegion.setLkpCountry(this);

		return lkpRegion;
	}

	public LkpRegion removeLkpRegion(LkpRegion lkpRegion) {
		getLkpRegions().remove(lkpRegion);
		lkpRegion.setLkpCountry(null);

		return lkpRegion;
	}

}