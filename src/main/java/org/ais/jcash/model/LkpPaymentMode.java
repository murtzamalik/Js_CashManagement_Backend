package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the LKP_PAYMENT_MODE database table.
 * 
 */
@Entity
@Table(name="LKP_PAYMENT_MODE")
@NamedQuery(name="LkpPaymentMode.findAll", query="SELECT l FROM LkpPaymentMode l")
public class LkpPaymentMode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="LKP_PAYMENT_MODE_PAYMENTMODEID_GENERATOR", sequenceName="LKP_PAYMENT_MODE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LKP_PAYMENT_MODE_PAYMENTMODEID_GENERATOR")
	@Column(name="PAYMENT_MODE_ID")
	private long paymentModeId;

	private String behaviour;

	@Column(name="BEHAVIOUR_TYPE")
	private String behaviourType;

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

	private String description;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="PAY_TYPE")
	private String payType;

	@Column(name="PAYMENT_MODE_CODE")
	private String paymentModeCode;

	@Column(name="SYMBOL_TRAN_TYPE")
	private String symbolTranType;

	@Column(name="SYMBOL_TRANS_DESC")
	private String symbolTransDesc;

	private BigDecimal updateindex;

	public LkpPaymentMode() {
	}

	public long getPaymentModeId() {
		return this.paymentModeId;
	}

	public void setPaymentModeId(long paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getBehaviour() {
		return this.behaviour;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	public String getBehaviourType() {
		return this.behaviourType;
	}

	public void setBehaviourType(String behaviourType) {
		this.behaviourType = behaviourType;
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

	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPaymentModeCode() {
		return this.paymentModeCode;
	}

	public void setPaymentModeCode(String paymentModeCode) {
		this.paymentModeCode = paymentModeCode;
	}

	public String getSymbolTranType() {
		return this.symbolTranType;
	}

	public void setSymbolTranType(String symbolTranType) {
		this.symbolTranType = symbolTranType;
	}

	public String getSymbolTransDesc() {
		return this.symbolTransDesc;
	}

	public void setSymbolTransDesc(String symbolTransDesc) {
		this.symbolTransDesc = symbolTransDesc;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

}