package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_ROLE database table.
 * 
 */
@Entity
@Table(name="TBL_ROLE")
@NamedQuery(name="TblRole.findAll", query="SELECT t FROM TblRole t")
public class TblRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_ROLE_ROLEID_GENERATOR", sequenceName="TBL_ROLE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_ROLE_ROLEID_GENERATOR")
	@Column(name="ROLE_ID")
	private long roleId;

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

	@Column(name="ROLE_DESCR")
	private String roleDescr;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAuthDetail
	@OneToMany(mappedBy="tblRole")
	private List<TblAuthDetail> tblAuthDetails;

	//bi-directional many-to-one association to TblAuthMatrixDetail
	@OneToMany(mappedBy="tblRole")
	private List<TblAuthMatrixDetail> tblAuthMatrixDetails;

	//bi-directional many-to-one association to TblRoleRight
	@OneToMany(mappedBy="tblRole")
	private List<TblRoleRight> tblRoleRights;

	//bi-directional many-to-one association to TblUserRole
	@OneToMany(mappedBy="tblRole")
	private List<TblUserRole> tblUserRoles;

	public TblRole() {
	}

	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
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

	public String getRoleDescr() {
		return this.roleDescr;
	}

	public void setRoleDescr(String roleDescr) {
		this.roleDescr = roleDescr;
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
		tblAuthDetail.setTblRole(this);

		return tblAuthDetail;
	}

	public TblAuthDetail removeTblAuthDetail(TblAuthDetail tblAuthDetail) {
		getTblAuthDetails().remove(tblAuthDetail);
		tblAuthDetail.setTblRole(null);

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
		tblAuthMatrixDetail.setTblRole(this);

		return tblAuthMatrixDetail;
	}

	public TblAuthMatrixDetail removeTblAuthMatrixDetail(TblAuthMatrixDetail tblAuthMatrixDetail) {
		getTblAuthMatrixDetails().remove(tblAuthMatrixDetail);
		tblAuthMatrixDetail.setTblRole(null);

		return tblAuthMatrixDetail;
	}

	public List<TblRoleRight> getTblRoleRights() {
		return this.tblRoleRights;
	}

	public void setTblRoleRights(List<TblRoleRight> tblRoleRights) {
		this.tblRoleRights = tblRoleRights;
	}

	public TblRoleRight addTblRoleRight(TblRoleRight tblRoleRight) {
		getTblRoleRights().add(tblRoleRight);
		tblRoleRight.setTblRole(this);

		return tblRoleRight;
	}

	public TblRoleRight removeTblRoleRight(TblRoleRight tblRoleRight) {
		getTblRoleRights().remove(tblRoleRight);
		tblRoleRight.setTblRole(null);

		return tblRoleRight;
	}

	public List<TblUserRole> getTblUserRoles() {
		return this.tblUserRoles;
	}

	public void setTblUserRoles(List<TblUserRole> tblUserRoles) {
		this.tblUserRoles = tblUserRoles;
	}

	public TblUserRole addTblUserRole(TblUserRole tblUserRole) {
		getTblUserRoles().add(tblUserRole);
		tblUserRole.setTblRole(this);

		return tblUserRole;
	}

	public TblUserRole removeTblUserRole(TblUserRole tblUserRole) {
		getTblUserRoles().remove(tblUserRole);
		tblUserRole.setTblRole(null);

		return tblUserRole;
	}

}