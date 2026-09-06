package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_PARSER_DETAIL database table.
 * 
 */
@Entity
@Table(name="TBL_PARSER_DETAIL")
@NamedQuery(name="TblParserDetail.findAll", query="SELECT t FROM TblParserDetail t")
public class TblParserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_PARSER_DETAIL_PARSERDETAILID_GENERATOR", sequenceName="TBL_PARSER_DETAIL_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_PARSER_DETAIL_PARSERDETAILID_GENERATOR")
	@Column(name="PARSER_DETAIL_ID")
	private long parserDetailId;

	@Column(name="COLUMN_NAME")
	private String columnName;

	@Column(name="COLUMN_TYPE")
	private String columnType;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	@Column(name="FIXED_LENGTH")
	private String fixedLength;

	@Column(name="IS_MANDATORY")
	private String isMandatory;

	@Column(name="IS_SEARCHABLE")
	private String isSearchable;

	@Temporal(TemporalType.DATE)
	private Date lastupdatedate;

	private BigDecimal lastupdateuser;

	@Column(name="MAX_LENGTH")
	private BigDecimal maxLength;

	@Column(name="MIN_LENGTH")
	private BigDecimal minLength;

	@Column(name="\"SEQUENCE\"")
	private BigDecimal sequence;

	private BigDecimal updateindex;

	//bi-directional many-to-one association to TblParserHead
	@ManyToOne
	@JoinColumn(name="PARSER_HEAD_ID")
	private TblParserHead tblParserHead;

	public TblParserDetail() {
	}

	public long getParserDetailId() {
		return this.parserDetailId;
	}

	public void setParserDetailId(long parserDetailId) {
		this.parserDetailId = parserDetailId;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return this.columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
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

	public String getFixedLength() {
		return this.fixedLength;
	}

	public void setFixedLength(String fixedLength) {
		this.fixedLength = fixedLength;
	}

	public String getIsMandatory() {
		return this.isMandatory;
	}

	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getIsSearchable() {
		return this.isSearchable;
	}

	public void setIsSearchable(String isSearchable) {
		this.isSearchable = isSearchable;
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

	public BigDecimal getMaxLength() {
		return this.maxLength;
	}

	public void setMaxLength(BigDecimal maxLength) {
		this.maxLength = maxLength;
	}

	public BigDecimal getMinLength() {
		return this.minLength;
	}

	public void setMinLength(BigDecimal minLength) {
		this.minLength = minLength;
	}

	public BigDecimal getSequence() {
		return this.sequence;
	}

	public void setSequence(BigDecimal sequence) {
		this.sequence = sequence;
	}

	public BigDecimal getUpdateindex() {
		return this.updateindex;
	}

	public void setUpdateindex(BigDecimal updateindex) {
		this.updateindex = updateindex;
	}

	public TblParserHead getTblParserHead() {
		return this.tblParserHead;
	}

	public void setTblParserHead(TblParserHead tblParserHead) {
		this.tblParserHead = tblParserHead;
	}

}