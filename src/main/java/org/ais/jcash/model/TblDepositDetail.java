package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_DEPOSIT_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_DEPOSIT_DETAIL")
@NamedQuery(name="TblDepositDetail.findAll", query="SELECT t FROM TblDepositDetail t")
public class TblDepositDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_DEPOSIT_DETAIL_DEPOSITDETAILID_GENERATOR", sequenceName="TBL_DEPOSIT_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_DEPOSIT_DETAIL_DEPOSITDETAILID_GENERATOR")
	@Column(name="DEPOSIT_DETAIL_ID")
	private long depositDetailId;

	@Column(name="CHEQUE_AMOUNT")
	private BigDecimal chequeAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="CHEQUE_DATE")
	private Date chequeDate;

	@Column(name="CHEQUE_NO")
	private String chequeNo;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	//bi-directional many-to-one association to LkpBranch
	@ManyToOne
	@JoinColumn(name="BRANCH_ID")
	private LkpBranch lkpBranch;

	//bi-directional many-to-one association to TblTransHead
	@ManyToOne
	@JoinColumn(name="TRANS_HEAD_ID")
	private TblTransHead tblTransHead;

	public TblDepositDetail() {
	}

	public long getDepositDetailId() {
		return this.depositDetailId;
	}

	public void setDepositDetailId(long depositDetailId) {
		this.depositDetailId = depositDetailId;
	}

	public BigDecimal getChequeAmount() {
		return this.chequeAmount;
	}

	public void setChequeAmount(BigDecimal chequeAmount) {
		this.chequeAmount = chequeAmount;
	}

	public Date getChequeDate() {
		return this.chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getChequeNo() {
		return this.chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
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

	public LkpBranch getLkpBranch() {
		return this.lkpBranch;
	}

	public void setLkpBranch(LkpBranch lkpBranch) {
		this.lkpBranch = lkpBranch;
	}

	public TblTransHead getTblTransHead() {
		return this.tblTransHead;
	}

	public void setTblTransHead(TblTransHead tblTransHead) {
		this.tblTransHead = tblTransHead;
	}

}