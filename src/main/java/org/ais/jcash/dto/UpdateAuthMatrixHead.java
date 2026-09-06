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


public class UpdateAuthMatrixHead {

	private Long fromAmount;

	private String isSequential;

	private Long toAmount;

	private long productId;

	private long authMatrixHeadId;

	public Long getFromAmount() {
		return fromAmount;
	}

	public void setFromAmount(Long fromAmount) {
		this.fromAmount = fromAmount;
	}

	public String getIsSequential() {
		return isSequential;
	}

	public void setIsSequential(String isSequential) {
		this.isSequential = isSequential;
	}

	public Long getToAmount() {
		return toAmount;
	}

	public void setToAmount(Long toAmount) {
		this.toAmount = toAmount;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getAuthMatrixHeadId() {
		return authMatrixHeadId;
	}

	public void setAuthMatrixHeadId(long authMatrixHeadId) {
		this.authMatrixHeadId = authMatrixHeadId;
	}
}