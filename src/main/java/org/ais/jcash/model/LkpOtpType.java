package org.ais.jcash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the LKP_OTP_TYPE database table.
 * 
 */
@Entity
@Table(name="LKP_OTP_TYPE")
@NamedQuery(name="LkpOtpType.findAll", query="SELECT l FROM LkpOtpType l")
public class LkpOtpType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_OTP_TYPE_OTPTYPEID_GENERATOR", sequenceName="LKP_OTP_TYPE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_OTP_TYPE_OTPTYPEID_GENERATOR")
	@Column(name="OTP_TYPE_ID")
	private long otpTypeId;

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

	@Column(name="EXPIRY_MINUTES")
	private BigDecimal expiryMinutes;

	@Column(name="EXPIRY_TRIES")
	private BigDecimal expiryTries;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblOtp
	@JsonIgnore
	@OneToMany(mappedBy="lkpOtpType")
	private List<TblOtp> tblOtps;

	public LkpOtpType() {
	}

	public long getOtpTypeId() {
		return this.otpTypeId;
	}

	public void setOtpTypeId(long otpTypeId) {
		this.otpTypeId = otpTypeId;
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

	public BigDecimal getExpiryMinutes() {
		return this.expiryMinutes;
	}

	public void setExpiryMinutes(BigDecimal expiryMinutes) {
		this.expiryMinutes = expiryMinutes;
	}

	public BigDecimal getExpiryTries() {
		return this.expiryTries;
	}

	public void setExpiryTries(BigDecimal expiryTries) {
		this.expiryTries = expiryTries;
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

	public List<TblOtp> getTblOtps() {
		return this.tblOtps;
	}

	public void setTblOtps(List<TblOtp> tblOtps) {
		this.tblOtps = tblOtps;
	}

	public TblOtp addTblOtp(TblOtp tblOtp) {
		getTblOtps().add(tblOtp);
		tblOtp.setLkpOtpType(this);

		return tblOtp;
	}

	public TblOtp removeTblOtp(TblOtp tblOtp) {
		getTblOtps().remove(tblOtp);
		tblOtp.setLkpOtpType(null);

		return tblOtp;
	}

}