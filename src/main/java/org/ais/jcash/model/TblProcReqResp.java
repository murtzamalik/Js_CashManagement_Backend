package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_PROC_REQ_RESP database table.
 * 
 */
@Entity
@Table(name="TBL_PROC_REQ_RESP")
@NamedQuery(name="TblProcReqResp.findAll", query="SELECT t FROM TblProcReqResp t")
public class TblProcReqResp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PROC_REQ_RESP_PROCREQRESPID_GENERATOR", sequenceName="TBL_PROC_REQ_RESP_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PROC_REQ_RESP_PROCREQRESPID_GENERATOR")
	@Column(name="PROC_REQ_RESP_ID")
	private long procReqRespId;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="P_ACTION")
	private String pAction;

	@Column(name="P_AUTH_COMPLETE")
	private String pAuthComplete;

	@Column(name="P_AUTH_DETAIL_ID")
	private BigDecimal pAuthDetailId;

	@Column(name="P_STATUS")
	private BigDecimal pStatus;

	@Column(name="P_STATUSDESCR")
	private String pStatusdescr;

	@Column(name="P_TRANS_HEAD_ID")
	private BigDecimal pTransHeadId;

	@Column(name="P_USER_ID")
	private BigDecimal pUserId;

	private BigDecimal updateindex;

	public TblProcReqResp() {
	}

	public long getProcReqRespId() {
		return this.procReqRespId;
	}

	public void setProcReqRespId(long procReqRespId) {
		this.procReqRespId = procReqRespId;
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

	public String getPAction() {
		return this.pAction;
	}

	public void setPAction(String pAction) {
		this.pAction = pAction;
	}

	public String getPAuthComplete() {
		return this.pAuthComplete;
	}

	public void setPAuthComplete(String pAuthComplete) {
		this.pAuthComplete = pAuthComplete;
	}

	public BigDecimal getPAuthDetailId() {
		return this.pAuthDetailId;
	}

	public void setPAuthDetailId(BigDecimal pAuthDetailId) {
		this.pAuthDetailId = pAuthDetailId;
	}

	public BigDecimal getPStatus() {
		return this.pStatus;
	}

	public void setPStatus(BigDecimal pStatus) {
		this.pStatus = pStatus;
	}

	public String getPStatusdescr() {
		return this.pStatusdescr;
	}

	public void setPStatusdescr(String pStatusdescr) {
		this.pStatusdescr = pStatusdescr;
	}

	public BigDecimal getPTransHeadId() {
		return this.pTransHeadId;
	}

	public void setPTransHeadId(BigDecimal pTransHeadId) {
		this.pTransHeadId = pTransHeadId;
	}

	public BigDecimal getPUserId() {
		return this.pUserId;
	}

	public void setPUserId(BigDecimal pUserId) {
		this.pUserId = pUserId;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

}