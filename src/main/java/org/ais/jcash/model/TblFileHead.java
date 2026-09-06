package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_FILE_HEAD database table.
 * 
 */
@Entity
@Table(name="TBL_FILE_HEAD")
@NamedQuery(name="TblFileHead.findAll", query="SELECT t FROM TblFileHead t")
public class TblFileHead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_FILE_HEAD_FILEHEADID_GENERATOR", sequenceName="TBL_FILE_HEAD_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_FILE_HEAD_FILEHEADID_GENERATOR")
	@Column(name="FILE_HEAD_ID")
	private long fileHeadId;

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

	@Column(name="FILE_NAME")
	private String fileName;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MC_STATUS")
	private String mcStatus;

	@Column(name="REFERENCE01_TITLE")
	private String reference01Title;

	@Column(name="REFERENCE02_TITLE")
	private String reference02Title;

	@Column(name="REFERENCE03_TITLE")
	private String reference03Title;

	@Column(name="REFERENCE04_TITLE")
	private String reference04Title;

	@Column(name="REFERENCE05_TITLE")
	private String reference05Title;

	@Column(name="REFERENCE06_TITLE")
	private String reference06Title;

	@Column(name="REFERENCE07_TITLE")
	private String reference07Title;

	@Column(name="REFERENCE08_TITLE")
	private String reference08Title;

	@Column(name="REFERENCE09_TITLE")
	private String reference09Title;

	@Column(name="REFERENCE10_TITLE")
	private String reference10Title;

	@Column(name="REFERENCE11_TITLE")
	private String reference11Title;

	@Column(name="REFERENCE12_TITLE")
	private String reference12Title;

	@Column(name="REFERENCE13_TITLE")
	private String reference13Title;

	@Column(name="REFERENCE14_TITLE")
	private String reference14Title;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblFileDetail
	@OneToMany(mappedBy="tblFileHead")
	private List<TblFileDetail> tblFileDetails;

	//bi-directional many-to-one association to TblParserCompanyConfig
	@ManyToOne
	@JoinColumn(name="PARSER_COMPANY_CONFIG_ID")
	private TblParserCompanyConfig tblParserCompanyConfig;

	public TblFileHead() {
	}

	public long getFileHeadId() {
		return this.fileHeadId;
	}

	public void setFileHeadId(long fileHeadId) {
		this.fileHeadId = fileHeadId;
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

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getReference01Title() {
		return this.reference01Title;
	}

	public void setReference01Title(String reference01Title) {
		this.reference01Title = reference01Title;
	}

	public String getReference02Title() {
		return this.reference02Title;
	}

	public void setReference02Title(String reference02Title) {
		this.reference02Title = reference02Title;
	}

	public String getReference03Title() {
		return this.reference03Title;
	}

	public void setReference03Title(String reference03Title) {
		this.reference03Title = reference03Title;
	}

	public String getReference04Title() {
		return this.reference04Title;
	}

	public void setReference04Title(String reference04Title) {
		this.reference04Title = reference04Title;
	}

	public String getReference05Title() {
		return this.reference05Title;
	}

	public void setReference05Title(String reference05Title) {
		this.reference05Title = reference05Title;
	}

	public String getReference06Title() {
		return this.reference06Title;
	}

	public void setReference06Title(String reference06Title) {
		this.reference06Title = reference06Title;
	}

	public String getReference07Title() {
		return this.reference07Title;
	}

	public void setReference07Title(String reference07Title) {
		this.reference07Title = reference07Title;
	}

	public String getReference08Title() {
		return this.reference08Title;
	}

	public void setReference08Title(String reference08Title) {
		this.reference08Title = reference08Title;
	}

	public String getReference09Title() {
		return this.reference09Title;
	}

	public void setReference09Title(String reference09Title) {
		this.reference09Title = reference09Title;
	}

	public String getReference10Title() {
		return this.reference10Title;
	}

	public void setReference10Title(String reference10Title) {
		this.reference10Title = reference10Title;
	}

	public String getReference11Title() {
		return this.reference11Title;
	}

	public void setReference11Title(String reference11Title) {
		this.reference11Title = reference11Title;
	}

	public String getReference12Title() {
		return this.reference12Title;
	}

	public void setReference12Title(String reference12Title) {
		this.reference12Title = reference12Title;
	}

	public String getReference13Title() {
		return this.reference13Title;
	}

	public void setReference13Title(String reference13Title) {
		this.reference13Title = reference13Title;
	}

	public String getReference14Title() {
		return this.reference14Title;
	}

	public void setReference14Title(String reference14Title) {
		this.reference14Title = reference14Title;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public List<TblFileDetail> getTblFileDetails() {
		return this.tblFileDetails;
	}

	public void setTblFileDetails(List<TblFileDetail> tblFileDetails) {
		this.tblFileDetails = tblFileDetails;
	}

	public TblFileDetail addTblFileDetail(TblFileDetail tblFileDetail) {
		getTblFileDetails().add(tblFileDetail);
		tblFileDetail.setTblFileHead(this);

		return tblFileDetail;
	}

	public TblFileDetail removeTblFileDetail(TblFileDetail tblFileDetail) {
		getTblFileDetails().remove(tblFileDetail);
		tblFileDetail.setTblFileHead(null);

		return tblFileDetail;
	}

	public TblParserCompanyConfig getTblParserCompanyConfig() {
		return this.tblParserCompanyConfig;
	}

	public void setTblParserCompanyConfig(TblParserCompanyConfig tblParserCompanyConfig) {
		this.tblParserCompanyConfig = tblParserCompanyConfig;
	}

}