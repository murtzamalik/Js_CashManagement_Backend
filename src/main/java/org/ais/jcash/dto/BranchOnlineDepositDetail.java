package org.ais.jcash.dto;

import org.ais.jcash.model.LkpBank;
import org.ais.jcash.model.TblTransHead;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/31/2022
 * Time: 11:04 AM
 * Project : jcash
 */
public class BranchOnlineDepositDetail {


    private BigDecimal chequeAmount;

    private Date chequeDate;

    private String chequeNo;


    private long branchId;


    public BigDecimal getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(BigDecimal chequeAmount) {
        this.chequeAmount = chequeAmount;
    }

    public Date getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Date chequeDate) {
        this.chequeDate = chequeDate;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }
}
