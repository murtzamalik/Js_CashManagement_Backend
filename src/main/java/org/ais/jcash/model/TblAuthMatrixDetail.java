package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_AUTH_MATRIX_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_AUTH_MATRIX_DETAIL")
@NamedQuery(name="TblAuthMatrixDetail.findAll", query="SELECT t FROM TblAuthMatrixDetail t")
public class TblAuthMatrixDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_AUTH_MATRIX_DETAIL_AUTHMATRIXDETAILID_GENERATOR", sequenceName="TBL_AUTH_MATRIX_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_AUTH_MATRIX_DETAIL_AUTHMATRIXDETAILID_GENERATOR")
	@Column(name="AUTH_MATRIX_DETAIL_ID")
	private long authMatrixDetailId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="SR_NO")
	private BigDecimal srNo;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAuthDetail
	@OneToMany(mappedBy="tblAuthMatrixDetail")
	private List<TblAuthDetail> tblAuthDetails;

	//bi-directional many-to-one association to LkpUserAuthLevel
	@ManyToOne
	@JoinColumn(name="USER_AUTH_LEVEL_ID")
	private LkpUserAuthLevel lkpUserAuthLevel;

	//bi-directional many-to-one association to TblAuthMatrixHead
	@ManyToOne
	@JoinColumn(name="AUTH_MATRIX_HEAD_ID")
	private TblAuthMatrixHead tblAuthMatrixHead;

	//bi-directional many-to-one association to TblRole
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private TblRole tblRole;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TblUser tblUser;

	public TblAuthMatrixDetail() {
	}

	public long getAuthMatrixDetailId() {
		return this.authMatrixDetailId;
	}

	public void setAuthMatrixDetailId(long authMatrixDetailId) {
		this.authMatrixDetailId = authMatrixDetailId;
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

	public BigDecimal getSrNo() {
		return this.srNo;
	}

	public void setSrNo(BigDecimal srNo) {
		this.srNo = srNo;
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
		tblAuthDetail.setTblAuthMatrixDetail(this);

		return tblAuthDetail;
	}

	public TblAuthDetail removeTblAuthDetail(TblAuthDetail tblAuthDetail) {
		getTblAuthDetails().remove(tblAuthDetail);
		tblAuthDetail.setTblAuthMatrixDetail(null);

		return tblAuthDetail;
	}

	public LkpUserAuthLevel getLkpUserAuthLevel() {
		return this.lkpUserAuthLevel;
	}

	public void setLkpUserAuthLevel(LkpUserAuthLevel lkpUserAuthLevel) {
		this.lkpUserAuthLevel = lkpUserAuthLevel;
	}

	public TblAuthMatrixHead getTblAuthMatrixHead() {
		return this.tblAuthMatrixHead;
	}

	public void setTblAuthMatrixHead(TblAuthMatrixHead tblAuthMatrixHead) {
		this.tblAuthMatrixHead = tblAuthMatrixHead;
	}

	public TblRole getTblRole() {
		return this.tblRole;
	}

	public void setTblRole(TblRole tblRole) {
		this.tblRole = tblRole;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

}