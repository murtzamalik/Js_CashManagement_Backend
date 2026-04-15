package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_USER_AUTH_LEVEL database table.
 * 
 */
@Entity
@Table(name="LKP_USER_AUTH_LEVEL")
@NamedQuery(name="LkpUserAuthLevel.findAll", query="SELECT l FROM LkpUserAuthLevel l")
public class LkpUserAuthLevel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_USER_AUTH_LEVEL_USERAUTHLEVELID_GENERATOR", sequenceName="LKP_USER_AUTH_LEVEL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_USER_AUTH_LEVEL_USERAUTHLEVELID_GENERATOR")
	@Column(name="USER_AUTH_LEVEL_ID")
	private long userAuthLevelId;

	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DATE")
	private Date checkDate;

	@Column(name="CHECKER_COMMENTS")
	private String checkerComments;

	@Column(name="CHECKER_ID")
	private BigDecimal checkerId;

	private String code;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAuthDetail
	@JsonIgnore
	@OneToMany(mappedBy="lkpUserAuthLevel")
	private List<TblAuthDetail> tblAuthDetails;

	//bi-directional many-to-one association to TblAuthMatrixDetail
	@JsonIgnore
	@OneToMany(mappedBy="lkpUserAuthLevel")
	private List<TblAuthMatrixDetail> tblAuthMatrixDetails;

	public LkpUserAuthLevel() {
	}

	public long getUserAuthLevelId() {
		return this.userAuthLevelId;
	}

	public void setUserAuthLevelId(long userAuthLevelId) {
		this.userAuthLevelId = userAuthLevelId;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<TblAuthDetail> getTblAuthDetails() {
		return this.tblAuthDetails;
	}

	public void setTblAuthDetails(List<TblAuthDetail> tblAuthDetails) {
		this.tblAuthDetails = tblAuthDetails;
	}

	public TblAuthDetail addTblAuthDetail(TblAuthDetail tblAuthDetail) {
		getTblAuthDetails().add(tblAuthDetail);
		tblAuthDetail.setLkpUserAuthLevel(this);

		return tblAuthDetail;
	}

	public TblAuthDetail removeTblAuthDetail(TblAuthDetail tblAuthDetail) {
		getTblAuthDetails().remove(tblAuthDetail);
		tblAuthDetail.setLkpUserAuthLevel(null);

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
		tblAuthMatrixDetail.setLkpUserAuthLevel(this);

		return tblAuthMatrixDetail;
	}

	public TblAuthMatrixDetail removeTblAuthMatrixDetail(TblAuthMatrixDetail tblAuthMatrixDetail) {
		getTblAuthMatrixDetails().remove(tblAuthMatrixDetail);
		tblAuthMatrixDetail.setLkpUserAuthLevel(null);

		return tblAuthMatrixDetail;
	}

}