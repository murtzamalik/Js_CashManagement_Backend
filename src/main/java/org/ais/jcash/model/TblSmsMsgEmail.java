package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_SMS_MSG_EMAIL database table.
 * 
 */
@Entity
@Table(name="TBL_SMS_MSG_EMAIL")
@NamedQuery(name="TblSmsMsgEmail.findAll", query="SELECT t FROM TblSmsMsgEmail t")
public class TblSmsMsgEmail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_SMS_MSG_EMAIL_SMSMSGEMAILID_GENERATOR", sequenceName="TBL_SMS_MSG_EMAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_SMS_MSG_EMAIL_SMSMSGEMAILID_GENERATOR")
	@Column(name="SMS_MSG_EMAIL_ID")
	private long smsMsgEmailId;

	@Column(name="CONTACT_EMAIL")
	private String contactEmail;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MESSAGE_TYPE")
	private String messageType;

	@Column(name="SEND_FLAG")
	private BigDecimal sendFlag;

	private String text;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblOtp
	@ManyToOne
	@JoinColumn(name="OTP_ID")
	private TblOtp tblOtp;

	//bi-directional many-to-one association to TblTransHead
	@ManyToOne
	@JoinColumn(name="TRANS_HEAD_ID")
	private TblTransHead tblTransHead;

	//bi-directional many-to-one association to TblUser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private TblUser tblUser;

	public TblSmsMsgEmail() {
	}

	public long getSmsMsgEmailId() {
		return this.smsMsgEmailId;
	}

	public void setSmsMsgEmailId(long smsMsgEmailId) {
		this.smsMsgEmailId = smsMsgEmailId;
	}

	public String getContactEmail() {
		return this.contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
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

	public String getMessageType() {
		return this.messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public BigDecimal getSendFlag() {
		return this.sendFlag;
	}

	public void setSendFlag(BigDecimal sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblOtp getTblOtp() {
		return this.tblOtp;
	}

	public void setTblOtp(TblOtp tblOtp) {
		this.tblOtp = tblOtp;
	}

	public TblTransHead getTblTransHead() {
		return this.tblTransHead;
	}

	public void setTblTransHead(TblTransHead tblTransHead) {
		this.tblTransHead = tblTransHead;
	}

	public TblUser getTblUser() {
		return this.tblUser;
	}

	public void setTblUser(TblUser tblUser) {
		this.tblUser = tblUser;
	}

}