package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_FILE_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_FILE_DETAIL")
@NamedQuery(name="TblFileDetail.findAll", query="SELECT t FROM TblFileDetail t")
public class TblFileDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_FILE_DETAIL_FILEDETAILID_GENERATOR", sequenceName="TBL_FILE_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_FILE_DETAIL_FILEDETAILID_GENERATOR")
	@Column(name="FILE_DETAIL_ID")
	private long fileDetailId;

	@Column(name="COLLECTED_AMOUNT")
	private BigDecimal collectedAmount;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="INVOICE_NO")
	private String invoiceNo;

	@Column(name="IS_DEPOSITED")
	private String isDeposited;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	private String reference01;

	private String reference02;

	private String reference03;

	private String reference04;

	private String reference05;

	private String reference06;

	private String reference07;

	private String reference08;

	private String reference09;

	private String reference10;

	private String reference11;

	private String reference12;

	private String reference13;

	private String reference14;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblFileHead
	@ManyToOne
	@JoinColumn(name="FILE_HEAD_ID")
	private TblFileHead tblFileHead;

	public TblFileDetail() {
	}

	public long getFileDetailId() {
		return this.fileDetailId;
	}

	public void setFileDetailId(long fileDetailId) {
		this.fileDetailId = fileDetailId;
	}

	public BigDecimal getCollectedAmount() {
		return this.collectedAmount;
	}

	public void setCollectedAmount(BigDecimal collectedAmount) {
		this.collectedAmount = collectedAmount;
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

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getIsDeposited() {
		return this.isDeposited;
	}

	public void setIsDeposited(String isDeposited) {
		this.isDeposited = isDeposited;
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

	public String getReference01() {
		return this.reference01;
	}

	public void setReference01(String reference01) {
		this.reference01 = reference01;
	}

	public String getReference02() {
		return this.reference02;
	}

	public void setReference02(String reference02) {
		this.reference02 = reference02;
	}

	public String getReference03() {
		return this.reference03;
	}

	public void setReference03(String reference03) {
		this.reference03 = reference03;
	}

	public String getReference04() {
		return this.reference04;
	}

	public void setReference04(String reference04) {
		this.reference04 = reference04;
	}

	public String getReference05() {
		return this.reference05;
	}

	public void setReference05(String reference05) {
		this.reference05 = reference05;
	}

	public String getReference06() {
		return this.reference06;
	}

	public void setReference06(String reference06) {
		this.reference06 = reference06;
	}

	public String getReference07() {
		return this.reference07;
	}

	public void setReference07(String reference07) {
		this.reference07 = reference07;
	}

	public String getReference08() {
		return this.reference08;
	}

	public void setReference08(String reference08) {
		this.reference08 = reference08;
	}

	public String getReference09() {
		return this.reference09;
	}

	public void setReference09(String reference09) {
		this.reference09 = reference09;
	}

	public String getReference10() {
		return this.reference10;
	}

	public void setReference10(String reference10) {
		this.reference10 = reference10;
	}

	public String getReference11() {
		return this.reference11;
	}

	public void setReference11(String reference11) {
		this.reference11 = reference11;
	}

	public String getReference12() {
		return this.reference12;
	}

	public void setReference12(String reference12) {
		this.reference12 = reference12;
	}

	public String getReference13() {
		return this.reference13;
	}

	public void setReference13(String reference13) {
		this.reference13 = reference13;
	}

	public String getReference14() {
		return this.reference14;
	}

	public void setReference14(String reference14) {
		this.reference14 = reference14;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblFileHead getTblFileHead() {
		return this.tblFileHead;
	}

	public void setTblFileHead(TblFileHead tblFileHead) {
		this.tblFileHead = tblFileHead;
	}

}