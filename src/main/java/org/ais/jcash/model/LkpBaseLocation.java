package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_BASE_LOCATION database table.
 * 
 */
@Entity
@Table(name="LKP_BASE_LOCATION")
@NamedQuery(name="LkpBaseLocation.findAll", query="SELECT l FROM LkpBaseLocation l")
public class LkpBaseLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_BASE_LOCATION_BASELOCATIONID_GENERATOR", sequenceName="LKP_BASE_LOCATION_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_BASE_LOCATION_BASELOCATIONID_GENERATOR")
	@Column(name="BASE_LOCATION_ID")
	private long baseLocationId;

	@Column(name="BASE_LOCATION_CODE")
	private String baseLocationCode;

	@Column(name="BASE_LOCATION_DESCR")
	private String baseLocationDescr;

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

	@Column(name="CUTOFF_TIME")
	private String cutoffTime;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="PRINT_LOCATION")
	private String printLocation;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblUser
	@JsonIgnore
	@OneToMany(mappedBy="lkpBaseLocation")
	private List<TblUser> tblUsers;

	public LkpBaseLocation() {
	}

	public long getBaseLocationId() {
		return this.baseLocationId;
	}

	public void setBaseLocationId(long baseLocationId) {
		this.baseLocationId = baseLocationId;
	}

	public String getBaseLocationCode() {
		return this.baseLocationCode;
	}

	public void setBaseLocationCode(String baseLocationCode) {
		this.baseLocationCode = baseLocationCode;
	}

	public String getBaseLocationDescr() {
		return this.baseLocationDescr;
	}

	public void setBaseLocationDescr(String baseLocationDescr) {
		this.baseLocationDescr = baseLocationDescr;
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

	public String getCutoffTime() {
		return this.cutoffTime;
	}

	public void setCutoffTime(String cutoffTime) {
		this.cutoffTime = cutoffTime;
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

	public String getPrintLocation() {
		return this.printLocation;
	}

	public void setPrintLocation(String printLocation) {
		this.printLocation = printLocation;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblUser> getTblUsers() {
		return this.tblUsers;
	}

	public void setTblUsers(List<TblUser> tblUsers) {
		this.tblUsers = tblUsers;
	}

	public TblUser addTblUser(TblUser tblUser) {
		getTblUsers().add(tblUser);
		tblUser.setLkpBaseLocation(this);

		return tblUser;
	}

	public TblUser removeTblUser(TblUser tblUser) {
		getTblUsers().remove(tblUser);
		tblUser.setLkpBaseLocation(null);

		return tblUser;
	}

}