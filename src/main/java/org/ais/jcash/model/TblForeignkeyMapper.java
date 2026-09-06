package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TBL_FOREIGNKEY_MAPPER database table.
 * 
 */
@Entity
@Table(name="TBL_FOREIGNKEY_MAPPER")
@NamedQuery(name="TblForeignkeyMapper.findAll", query="SELECT t FROM TblForeignkeyMapper t")
public class TblForeignkeyMapper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_FOREIGNKEY_MAPPER_FOREIGNKEYMAPPERID_GENERATOR", sequenceName="TBL_FOREIGNKEY_MAPPER_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_FOREIGNKEY_MAPPER_FOREIGNKEYMAPPERID_GENERATOR")
	@Column(name="FOREIGNKEY_MAPPER_ID")
	private long foreignkeyMapperId;

	@Column(name="FOREIGNKEY_COL_NAME")
	private String foreignkeyColName;

	@Column(name="REF_COL_NAME")
	private String refColName;

	@Column(name="REF_QUERY")
	private String refQuery;

	@Column(name="REF_TABLE_NAME")
	private String refTableName;

	@Column(name="VAL_COL_NAME")
	private String valColName;

	public TblForeignkeyMapper() {
	}

	public long getForeignkeyMapperId() {
		return this.foreignkeyMapperId;
	}

	public void setForeignkeyMapperId(long foreignkeyMapperId) {
		this.foreignkeyMapperId = foreignkeyMapperId;
	}

	public String getForeignkeyColName() {
		return this.foreignkeyColName;
	}

	public void setForeignkeyColName(String foreignkeyColName) {
		this.foreignkeyColName = foreignkeyColName;
	}

	public String getRefColName() {
		return this.refColName;
	}

	public void setRefColName(String refColName) {
		this.refColName = refColName;
	}

	public String getRefQuery() {
		return this.refQuery;
	}

	public void setRefQuery(String refQuery) {
		this.refQuery = refQuery;
	}

	public String getRefTableName() {
		return this.refTableName;
	}

	public void setRefTableName(String refTableName) {
		this.refTableName = refTableName;
	}

	public String getValColName() {
		return this.valColName;
	}

	public void setValColName(String valColName) {
		this.valColName = valColName;
	}

}