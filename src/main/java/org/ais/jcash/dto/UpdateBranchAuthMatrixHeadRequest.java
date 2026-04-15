package org.ais.jcash.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ais.jcash.model.TblAuthMatrixDetail;
import org.ais.jcash.model.TblCompany;
import org.ais.jcash.model.TblProduct;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_AUTH_MATRIX_HEAD database table.
 * 
 */
public class UpdateBranchAuthMatrixHeadRequest{

	private long authMatrixHeadId;

	private BigDecimal fromAmount;

	private String isSequential;

	private BigDecimal toAmount;



	public BigDecimal getFromAmount() {
		return fromAmount;
	}

	public long getAuthMatrixHeadId() {
		return authMatrixHeadId;
	}

	public void setAuthMatrixHeadId(long authMatrixHeadId) {
		this.authMatrixHeadId = authMatrixHeadId;
	}

	public void setFromAmount(BigDecimal fromAmount) {
		this.fromAmount = fromAmount;
	}

	public String getIsSequential() {
		return isSequential;
	}

	public void setIsSequential(String isSequential) {
		this.isSequential = isSequential;
	}

	public BigDecimal getToAmount() {
		return toAmount;
	}

	public void setToAmount(BigDecimal toAmount) {
		this.toAmount = toAmount;
	}


}