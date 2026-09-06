package org.ais.jcash.dto;

import org.ais.jcash.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:10 PM
 * Project : jcash
 */
public class CompanyResponse {

    private TblCompany tblCompany;

    private List<TblUser> tblUser = new ArrayList<>();

    private List<TblCompanyProduct> companyProducts = new ArrayList<>();

    private List<TblAccount> companyAccounts = new ArrayList<>();

    List<TblUserAccountProduct> tblUserAccountProducts =new ArrayList<>();

    List<TblAuthMatrixHead> tblAuthMatrixHeads  =new ArrayList<>();

    List<TblParserHead> tblParserHeads  =new ArrayList<>();

    public List<TblUser> getTblUser() {
        return tblUser;
    }

    public void setTblUser(List<TblUser> tblUser) {
        this.tblUser = tblUser;
    }

    public List<TblCompanyProduct> getCompanyProducts() {
        return companyProducts;
    }

    public void setCompanyProducts(List<TblCompanyProduct> companyProducts) {
        this.companyProducts = companyProducts;
    }

    public List<TblAccount> getCompanyAccounts() {
        return companyAccounts;
    }

    public void setCompanyAccounts(List<TblAccount> companyAccounts) {
        this.companyAccounts = companyAccounts;
    }

    public TblCompany getTblCompany() {
        return tblCompany;
    }

    public void setTblCompany(TblCompany tblCompany) {
        this.tblCompany = tblCompany;
    }

    public List<TblUserAccountProduct> getTblUserAccountProducts() {
        return tblUserAccountProducts;
    }

    public void setTblUserAccountProducts(List<TblUserAccountProduct> tblUserAccountProducts) {
        this.tblUserAccountProducts = tblUserAccountProducts;
    }

    public List<TblAuthMatrixHead> getTblAuthMatrixHeads() {
        return tblAuthMatrixHeads;
    }

    public void setTblAuthMatrixHeads(List<TblAuthMatrixHead> tblAuthMatrixHeads) {
        this.tblAuthMatrixHeads = tblAuthMatrixHeads;
    }

    public List<TblParserHead> getTblParserHeads() {
        return tblParserHeads;
    }

    public void setTblParserHeads(List<TblParserHead> tblParserHeads) {
        this.tblParserHeads = tblParserHeads;
    }
}
