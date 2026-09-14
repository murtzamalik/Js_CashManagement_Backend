package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_AUTH_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_AUTH_DETAIL")
@NamedQuery(name="TblAuthDetail.findAll", query="SELECT t FROM TblAuthDetail t")
public class TblAuthDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_AUTH_DETAIL_AUTHDETAILID_GENERATOR", sequenceName="TBL_AUTH_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_AUTH_DETAIL_AUTHDETAILID_GENERATOR")
	@Column(name="AUTH_DETAIL_ID")
	private long authDetailId;

	@Column(name="AUTH_COMMENTS")
	private String authComments;

	@Temporal(TemporalType.DATE)
	@Column(name="AUTH_DATE")
	private Date authDate;

	@Column(name="AUTH_STATUS")
	private String authStatus;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String remarks;

	@Column(name="SR_NO")
	private BigDecimal srNo;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to LkpUserAuthLevel
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="USER_AUTH_LEVEL_ID")
	private LkpUserAuthLevel lkpUserAuthLevel;

	//bi-directional many-to-one association to TblAuthHead
	@ManyToOne
	@JoinColumn(name="AUTH_HEAD_ID")
	private TblAuthHead tblAuthHead;

	//bi-directional many-to-one association to TblAuthMatrixDetail
	@ManyToOne
	@JoinColumn(name="AUTH_MATRIX_DETAIL_ID")
	private TblAuthMatrixDetail tblAuthMatrixDetail;

	//bi-directional many-to-one association to TblRole
	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private TblRole tblRole;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TblUser tblUser;

	public TblAuthDetail() {
	}

	public long getAuthDetailId() {
		return this.authDetailId;
	}

	public void setAuthDetailId(long authDetailId) {
		this.authDetailId = authDetailId;
	}

	public String getAuthComments() {
		return this.authComments;
	}

	public void setAuthComments(String authComments) {
		this.authComments = authComments;
	}

	public Date getAuthDate() {
		return this.authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	public String getAuthStatus() {
		return this.authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
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

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public LkpUserAuthLevel getLkpUserAuthLevel() {
		return this.lkpUserAuthLevel;
	}

	public void setLkpUserAuthLevel(LkpUserAuthLevel lkpUserAuthLevel) {
		this.lkpUserAuthLevel = lkpUserAuthLevel;
	}

	public TblAuthHead getTblAuthHead() {
		return this.tblAuthHead;
	}

	public void setTblAuthHead(TblAuthHead tblAuthHead) {
		this.tblAuthHead = tblAuthHead;
	}

	public TblAuthMatrixDetail getTblAuthMatrixDetail() {
		return this.tblAuthMatrixDetail;
	}

	public void setTblAuthMatrixDetail(TblAuthMatrixDetail tblAuthMatrixDetail) {
		this.tblAuthMatrixDetail = tblAuthMatrixDetail;
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