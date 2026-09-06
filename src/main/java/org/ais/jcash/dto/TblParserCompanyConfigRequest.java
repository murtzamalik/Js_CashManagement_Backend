package org.ais.jcash.dto;

import org.ais.jcash.model.TblCompany;
import org.ais.jcash.model.TblParserHead;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_PARSER_COMPANY_CONFIG database table.
 * 
 */
public class TblParserCompanyConfigRequest {

	private long companyId;

	private long parserHeadId;

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getParserHeadId() {
		return parserHeadId;
	}

	public void setParserHeadId(long parserHeadId) {
		this.parserHeadId = parserHeadId;
	}
}