package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_AUTH_HEAD database table.
 * 
 */
@Entity
@Table(name="TBL_AUTH_HEAD")
@NamedQuery(name="TblAuthHead.findAll", query="SELECT t FROM TblAuthHead t")
public class TblAuthHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_AUTH_HEAD_AUTHHEADID_GENERATOR", sequenceName="TBL_AUTH_HEAD_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_AUTH_HEAD_AUTHHEADID_GENERATOR")
	@Column(name="AUTH_HEAD_ID")
	private long authHeadId;

	@Column(name="AUTH_STATUS")
	private String authStatus;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="IS_SEQUENTIAL")
	private String isSequential;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblAuthDetail
	@JsonIgnore
	@OneToMany(mappedBy="tblAuthHead")
	private List<TblAuthDetail> tblAuthDetails;

	//bi-directional many-to-one association to TblAuthMatrixHead
	@ManyToOne
	@JoinColumn(name="AUTH_MATRIX_HEAD_ID")
	private TblAuthMatrixHead tblAuthMatrixHead;

	//bi-directional many-to-one association to TblTransHead
	@ManyToOne
	@JoinColumn(name="TRANS_HEAD_ID")
	private TblTransHead tblTransHead;

	public TblAuthHead() {
	}

	public long getAuthHeadId() {
		return this.authHeadId;
	}

	public void setAuthHeadId(long authHeadId) {
		this.authHeadId = authHeadId;
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
		tblAuthDetail.setTblAuthHead(this);

		return tblAuthDetail;
	}

	public TblAuthDetail removeTblAuthDetail(TblAuthDetail tblAuthDetail) {
		getTblAuthDetails().remove(tblAuthDetail);
		tblAuthDetail.setTblAuthHead(null);

		return tblAuthDetail;
	}

	public TblAuthMatrixHead getTblAuthMatrixHead() {
		return this.tblAuthMatrixHead;
	}

	public void setTblAuthMatrixHead(TblAuthMatrixHead tblAuthMatrixHead) {
		this.tblAuthMatrixHead = tblAuthMatrixHead;
	}

	public TblTransHead getTblTransHead() {
		return this.tblTransHead;
	}

	public void setTblTransHead(TblTransHead tblTransHead) {
		this.tblTransHead = tblTransHead;
	}

}