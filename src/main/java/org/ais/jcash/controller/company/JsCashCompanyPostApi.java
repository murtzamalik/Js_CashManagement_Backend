package org.ais.jcash.controller.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashFinService;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.WsdlT24Api.dto.balanceinquiry.Root;
import org.ais.jcash.WsdlT24Api.service.T24MockSupport;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.*;
import org.ais.jcash.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:01 PM
 * Project : jcash
 */


@Api(value = "Cash Management Api Non Financial Post Api", description = "POST NON FIN API For Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashCompanyPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashCompanyPostApi.class);

    @Autowired
    private JsCashNonFinService jsCashNonFinService;

    @Autowired
    private JsCashFinService jsCashFinService;

    @Autowired
    private T24MockSupport t24MockSupport;


    @RequestMapping(value = "/saveCompanyGroup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveCompanyGroup(@Valid @RequestBody TblCompanyGroupRequest companyGroupRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyGroup(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblCompanyGroup tblCompanyGroup = new TblCompanyGroup();

                tblCompanyGroup.setGroupCode(companyGroupRequest.getGroupCode());
                tblCompanyGroup.setGroupName(companyGroupRequest.getGroupName());
                tblCompanyGroup.setRelationship(companyGroupRequest.getRelationship());
                tblCompanyGroup.setAccountNo(companyGroupRequest.getAccountNo());
                tblCompanyGroup.setDiscountable(companyGroupRequest.getDiscountable());
                tblCompanyGroup.setCreditLimit(companyGroupRequest.getCreditLimit());
                tblCompanyGroup.setOutstandingAmount(companyGroupRequest.getOutstandingAmount());
                tblCompanyGroup.setClosed(companyGroupRequest.getClosed());
                tblCompanyGroup.setClosureReason(companyGroupRequest.getClosureReason());
                tblCompanyGroup.setClosedDate(companyGroupRequest.getClosedDate());
                tblCompanyGroup.setClosedTill(companyGroupRequest.getClosedTill());
                tblCompanyGroup.setReopeningDate(companyGroupRequest.getReopeningDate());
                tblCompanyGroup.setCisNo(companyGroupRequest.getCisNo());
                tblCompanyGroup.setContactNo(companyGroupRequest.getContactNo());
                tblCompanyGroup.setContactPerson(companyGroupRequest.getContactPerson());
                tblCompanyGroup.setAddress1(companyGroupRequest.getAddress1());
                tblCompanyGroup.setAddress2(companyGroupRequest.getAddress2());
                tblCompanyGroup.setAddress3(companyGroupRequest.getAddress3());
                tblCompanyGroup.setFaxNo(companyGroupRequest.getFaxNo());
                tblCompanyGroup.setMobileNo(companyGroupRequest.getMobileNo());
                tblCompanyGroup.setEmail(companyGroupRequest.getEmail());
                tblCompanyGroup.setUrl(companyGroupRequest.getUrl());
                tblCompanyGroup.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));


                tblCompanyGroup = jsCashNonFinService.saveCompanyGroup(tblCompanyGroup);

                if (tblCompanyGroup != null && tblCompanyGroup.getCompanyGroupId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblCompanyGroup);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveCompanyProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveCompanyProfile(@Valid @RequestBody TblCompanyRequest companyProfileRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyProfile(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblCompany tblCompanyProfile = new TblCompany();

                tblCompanyProfile.setCompanyCode(companyProfileRequest.getCompanyCode());
                tblCompanyProfile.setCompanyName(companyProfileRequest.getCompanyName());
                tblCompanyProfile.setCustomerType(companyProfileRequest.getCustomerType());
                tblCompanyProfile.setProductManager(companyProfileRequest.getProductManager());
                tblCompanyProfile.setRelationshipManager(companyProfileRequest.getRelationshipManager());
                tblCompanyProfile.setAccountNo(companyProfileRequest.getAccountNo());
                tblCompanyProfile.setDiscountable(companyProfileRequest.getDiscountable());
                tblCompanyProfile.setCreditLimit(companyProfileRequest.getCreditLimit());
                tblCompanyProfile.setOutstandingAmount(companyProfileRequest.getOutstandingAmount());
                tblCompanyProfile.setClosed(companyProfileRequest.getClosed());
                tblCompanyProfile.setClosureReason(companyProfileRequest.getClosureReason());
                tblCompanyProfile.setClosedDate(companyProfileRequest.getClosedDate());
                tblCompanyProfile.setClosedTill(companyProfileRequest.getClosedTill());
                tblCompanyProfile.setReopeningDate(companyProfileRequest.getReopeningDate());
                tblCompanyProfile.setContactPerson(companyProfileRequest.getContactPerson());
                tblCompanyProfile.setBackupContact(companyProfileRequest.getBackupContact());
                tblCompanyProfile.setContactNo(companyProfileRequest.getContactNo());
                tblCompanyProfile.setAddress1(companyProfileRequest.getAddress1());
                tblCompanyProfile.setAddress2(companyProfileRequest.getAddress2());
                tblCompanyProfile.setAddress3(companyProfileRequest.getAddress3());
                tblCompanyProfile.setFaxNo1(companyProfileRequest.getFaxNo1());
                tblCompanyProfile.setFaxNo2(companyProfileRequest.getFaxNo2());
                tblCompanyProfile.setFaxNo3(companyProfileRequest.getFaxNo3());
                tblCompanyProfile.setTelexNo(companyProfileRequest.getTelexNo());
                tblCompanyProfile.setMobileNo(companyProfileRequest.getMobileNo());
                tblCompanyProfile.setUrl(companyProfileRequest.getUrl());
                tblCompanyProfile.setEmail(companyProfileRequest.getEmail());
                tblCompanyProfile.setBackupEmail1(companyProfileRequest.getBackupEmail1());
                tblCompanyProfile.setBackupEmail2(companyProfileRequest.getBackupEmail2());
                tblCompanyProfile.setUbCustomer(companyProfileRequest.getUbCustomer());
                tblCompanyProfile.setUbCompanyCode(companyProfileRequest.getUbCompanyCode());
                tblCompanyProfile.setAccountingEntryRequest(companyProfileRequest.getAccountingEntryRequest());
                tblCompanyProfile.setAccountingEntryType(companyProfileRequest.getAccountingEntryType());
                tblCompanyProfile.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));


                TblCompanyGroup tblCompanyGroup = new TblCompanyGroup();
                LkpArea lkpArea = new LkpArea();

                tblCompanyGroup.setCompanyGroupId(Long.valueOf(companyProfileRequest.getCompanyGroupId()));
                lkpArea.setAreaId(companyProfileRequest.getAreaId());

                tblCompanyProfile.setTblCompanyGroup(tblCompanyGroup);
                tblCompanyProfile.setLkpArea(lkpArea);

                tblCompanyProfile = jsCashNonFinService.saveCompanyProfile(tblCompanyProfile);

                if (tblCompanyProfile != null && tblCompanyProfile.getCompanyId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyProfile(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblCompanyProfile);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyProfile(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveCompanyProfile(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyProfile();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyProfile(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyProfile();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveCompanyProfile(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveCompanyProducts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveCompanyProducts(@Valid @RequestBody TblCompanyProductRequest saveCompanyProductsRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyProducts(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblCompanyProduct tblCompanyProduct = new TblCompanyProduct();


                tblCompanyProduct.setCourierId(saveCompanyProductsRequest.getCourierId());
                tblCompanyProduct.setEnrichedData(saveCompanyProductsRequest.getEnrichedData());
                tblCompanyProduct.setDiscountable(saveCompanyProductsRequest.getDiscountable());
                tblCompanyProduct.setDealerListRequired(saveCompanyProductsRequest.getDealerListRequired());
                tblCompanyProduct.setClosed(saveCompanyProductsRequest.getClosed());
                tblCompanyProduct.setClosureReason(saveCompanyProductsRequest.getClosureReason());
                tblCompanyProduct.setClosedDate(saveCompanyProductsRequest.getClosedDate());
                tblCompanyProduct.setClosedTill(saveCompanyProductsRequest.getClosedTill());
                tblCompanyProduct.setReopeningDate(saveCompanyProductsRequest.getReopeningDate());
                tblCompanyProduct.setDocumentReceived(saveCompanyProductsRequest.getDocumentReceived());
                tblCompanyProduct.setDeferredUpto(saveCompanyProductsRequest.getDeferredUpto());
                tblCompanyProduct.setFundCreditDays(saveCompanyProductsRequest.getFundCreditDays());
                tblCompanyProduct.setCustAccountCr(saveCompanyProductsRequest.getCustAccountCr());
                tblCompanyProduct.setCustAccountDr(saveCompanyProductsRequest.getCustAccountDr());
                tblCompanyProduct.setInterestRecoveryAccount(saveCompanyProductsRequest.getInterestRecoveryAccount());
                tblCompanyProduct.setServiceRecoveryAccount(saveCompanyProductsRequest.getServiceRecoveryAccount());
                tblCompanyProduct.setCustCollectionAccount(saveCompanyProductsRequest.getCustCollectionAccount());
                tblCompanyProduct.setGuaranteedFund(saveCompanyProductsRequest.getGuaranteedFund());
                tblCompanyProduct.setOnlineInvoice(saveCompanyProductsRequest.getOnlineInvoice());
                tblCompanyProduct.setOffsitePrinting(saveCompanyProductsRequest.getOffsitePrinting());
                tblCompanyProduct.setStationaryId(BigDecimal.valueOf(saveCompanyProductsRequest.getStationaryId()));
                tblCompanyProduct.setNarration(saveCompanyProductsRequest.getNarration());
                tblCompanyProduct.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));


                TblCompany tblCompanyProfile = new TblCompany();
                TblProduct tblProduct = new TblProduct();

                tblCompanyProfile.setCompanyId(saveCompanyProductsRequest.getCompanyId());
                tblProduct.setProductId(saveCompanyProductsRequest.getProductId());

                tblCompanyProduct.setTblCompany(tblCompanyProfile);
                tblCompanyProduct.setTblProduct(tblProduct);

                tblCompanyProduct = jsCashNonFinService.saveCompanyProducts(tblCompanyProduct);


                if (tblCompanyProduct != null && tblCompanyProduct.getCompanyProductId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyProducts(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblCompanyProduct);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyProducts(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveCompanyProducts(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyProducts();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyProducts(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyProducts();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveCompanyProducts(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveCompanyAccounts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveCompanyAccounts(@Valid @RequestBody TblAccountRequest saveAccountRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyAccounts(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblAccount tblAccount = new TblAccount();


                tblAccount.setAccountName(saveAccountRequest.getAccountName());
                tblAccount.setAccountNo(saveAccountRequest.getAccountNo());
                tblAccount.setAllowEntry(saveAccountRequest.getAllowEntry());
                tblAccount.setClosed(saveAccountRequest.getClosed());
                tblAccount.setClosedDate(saveAccountRequest.getClosedDate());
                tblAccount.setClosedTill(saveAccountRequest.getClosedTill());
                tblAccount.setClosureReason(saveAccountRequest.getClosureReason());
                tblAccount.setEntity(saveAccountRequest.getEntity());
                tblAccount.setHostAccountNo(saveAccountRequest.getHostAccountNo());
                tblAccount.setReopeningDate(saveAccountRequest.getReopeningDate());
                tblAccount.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                LkpBank lkpBank = new LkpBank();
                LkpBranch lkpBranch = new LkpBranch();
                TblCompany tblCompany = new TblCompany();

                lkpBank.setBankId(saveAccountRequest.getBankId());
                lkpBranch.setBranchId(saveAccountRequest.getBranchId());
                tblCompany.setCompanyId(saveAccountRequest.getCompanyId());

                tblAccount.setLkpBank(lkpBank);
                tblAccount.setLkpBranch(lkpBranch);
                tblAccount.setTblCompany(tblCompany);


                tblAccount = jsCashNonFinService.saveCompanyAccounts(tblAccount);


                if (tblAccount != null && tblAccount.getAccountId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyAccounts(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblAccount);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyAccounts(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveCompanyAccounts(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyAccounts();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyAccounts();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveCompanyAccounts(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveCompanyUserAccountsProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveCompanyUserAccountsProduct(@Valid @RequestBody TblUserAccountProductRequest saveCompanyAccountsRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyUserAccountsProduct(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblUserAccountProduct tblUserAccountProduct = new TblUserAccountProduct();


                tblUserAccountProduct.setIsActive(saveCompanyAccountsRequest.getIsActive());
                tblUserAccountProduct.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                TblCompany tblCompany = new TblCompany();
                TblAccount tblAccount = new TblAccount();
                TblUser tblUser = new TblUser();
                TblProduct tblProduct = new TblProduct();

                tblCompany.setCompanyId(saveCompanyAccountsRequest.getCompanyId());
                tblAccount.setAccountId(saveCompanyAccountsRequest.getAccountId());
                tblUser.setUserId(saveCompanyAccountsRequest.getUserId());
                tblProduct.setProductId(saveCompanyAccountsRequest.getProductId());

                tblUserAccountProduct.setTblAccount(tblAccount);
                tblUserAccountProduct.setTblCompany(tblCompany);
                tblUserAccountProduct.setTblProduct(tblProduct);
                tblUserAccountProduct.setTblUser(tblUser);


                tblUserAccountProduct = jsCashNonFinService.saveCompanyUserAccountsProduct(tblUserAccountProduct);


                if (tblUserAccountProduct != null && tblUserAccountProduct.getUserAccountProductId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyUserAccountsProduct(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblUserAccountProduct);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyUserAccountsProduct(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveCompanyUserAccountsProduct(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyUserAccountsProduct();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyUserAccountsProduct(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyUserAccountsProduct();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveCompanyUserAccountsProduct(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveCompanyUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveCompanyUser(@Valid @RequestBody TblUserRequest saveTblUserRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == saveUser(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblUser tblUser = new TblUser();

                tblUser.setContactNo(saveTblUserRequest.getContactNo());
                tblUser.setDepartment(saveTblUserRequest.getDepartment());
                tblUser.setDesignation(saveTblUserRequest.getDesignation());
                tblUser.setEmail(saveTblUserRequest.getEmail());
                tblUser.setEmployeeNo(saveTblUserRequest.getEmployeeNo());
                tblUser.setPassword(saveTblUserRequest.getPassword());
                tblUser.setProfileExpiry(saveTblUserRequest.getProfileExpiry());
                tblUser.setUserCode(saveTblUserRequest.getUserCode());
                tblUser.setUserGroup(saveTblUserRequest.getUserGroup());
                tblUser.setUserName(saveTblUserRequest.getUserName().toUpperCase());
                tblUser.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));
                tblUser.setAllowEntry(saveTblUserRequest.getAllowEntry());

                LkpBaseLocation lkpBaseLocation = new LkpBaseLocation();
                TblCompany tblCompany = new TblCompany();
                LkpUserType lkpUserType = new LkpUserType();

                lkpBaseLocation.setBaseLocationId(saveTblUserRequest.getBaseLocation());
                tblCompany.setCompanyId(saveTblUserRequest.getCompanyId());

                lkpUserType.setUserTypeId(saveTblUserRequest.getUserTypeId());


                tblUser.setLkpBaseLocation(lkpBaseLocation);
                tblUser.setTblCompany(tblCompany);
               tblUser.setRoleId(saveTblUserRequest.getRoleId());
                tblUser.setLkpUserType(lkpUserType);

                tblUser = jsCashNonFinService.saveTblUser(tblUser);


                if (tblUser != null && tblUser.getUserId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyUser(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblUser);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyUser(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }

            } else {
                LOG.info("\n EXITING THIS METHOD == saveCompanyUser(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyUser();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyUser();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveCompanyUser(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveAuthorizeCompanyProfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveAuthorizeCompanyProfile(@RequestBody AuthorizationCompanyProfileRequest authorizationCompanyProfileRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == saveAuthorizeCompanyProfile(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                String companyId = String.valueOf(authorizationCompanyProfileRequest.getCompanyId());
                TblCompany tblCompany = jsCashNonFinService.getcompany(companyId);

                tblCompany.setMcStatus(authorizationCompanyProfileRequest.getMcStatus());
                tblCompany.setCheckDate(new Date());
                tblCompany.setCheckerComments(authorizationCompanyProfileRequest.getMcComments());
                tblCompany.setCheckerId(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                tblCompany.setLastupdatedate(new Date());
                tblCompany.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                tblCompany.setUpdateindex(tblCompany.getUpdateindex() != null ? new BigDecimal(tblCompany.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                tblCompany = jsCashNonFinService.saveCompanyProfile(tblCompany);

                if (tblCompany != null && tblCompany.getCompanyId() > 0) {
                    List<TblUser> tblUsers = jsCashNonFinService.getAllusers(companyId);
                    for (TblUser tblUser : tblUsers) {
                        tblUser.setMcStatus(authorizationCompanyProfileRequest.getMcStatus());
                        tblUser.setCheckDate(new Date());
                        tblUser.setCheckerComments(authorizationCompanyProfileRequest.getMcComments());
                        tblUser.setCheckerId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblUser.setLastupdatedate(new Date());
                        tblUser.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblUser.setUpdateindex(tblUser.getUpdateindex() != null ? new BigDecimal(tblUser.getUpdateindex().longValue() + 1) : new BigDecimal(1));
                        tblUser = jsCashNonFinService.authorizeUserAndRole(tblUser);

                    }


                    List<TblCompanyProduct> tblCompanyProducts = jsCashNonFinService.getAllCompanyProducts(companyId);
                    for (TblCompanyProduct tblCompanyProduct : tblCompanyProducts) {
                        tblCompanyProduct.setMcStatus(authorizationCompanyProfileRequest.getMcStatus());
                        tblCompanyProduct.setCheckDate(new Date());
                        tblCompanyProduct.setCheckerComments(authorizationCompanyProfileRequest.getMcComments());
                        tblCompanyProduct.setCheckerId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblCompanyProduct.setLastupdatedate(new Date());
                        tblCompanyProduct.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblCompanyProduct.setUpdateindex(tblCompanyProduct.getUpdateindex() != null ? new BigDecimal(tblCompanyProduct.getUpdateindex().longValue() + 1) : new BigDecimal(1));
                        tblCompanyProduct = jsCashNonFinService.saveCompanyProducts(tblCompanyProduct);
                    }

                    List<TblAccount> tblAccounts = jsCashNonFinService.getAllAccounts(companyId);
                    for (TblAccount tblAccount : tblAccounts) {
                        tblAccount.setMcStatus(authorizationCompanyProfileRequest.getMcStatus());
                        tblAccount.setCheckDate(new Date());
                        tblAccount.setCheckerComments(authorizationCompanyProfileRequest.getMcComments());
                        tblAccount.setCheckerId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblAccount.setLastupdatedate(new Date());
                        tblAccount.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblAccount.setUpdateindex(tblAccount.getUpdateindex() != null ? new BigDecimal(tblAccount.getUpdateindex().longValue() + 1) : new BigDecimal(1));
                        tblAccount = jsCashNonFinService.saveCompanyAccounts(tblAccount);
                    }

                    List<TblUserAccountProduct> tblUserAccountProducts = jsCashNonFinService.getUserAccountProduct(companyId);
                    for (TblUserAccountProduct tblUserAccountProduct : tblUserAccountProducts) {
                        tblUserAccountProduct.setMcStatus(authorizationCompanyProfileRequest.getMcStatus());
                        tblUserAccountProduct.setCheckDate(new Date());
                        tblUserAccountProduct.setCheckerComments(authorizationCompanyProfileRequest.getMcComments());
                        tblUserAccountProduct.setCheckerId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblUserAccountProduct.setLastupdatedate(new Date());
                        tblUserAccountProduct.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblUserAccountProduct.setUpdateindex(tblUserAccountProduct.getUpdateindex() != null ? new BigDecimal(tblUserAccountProduct.getUpdateindex().longValue() + 1) : new BigDecimal(1));
                        tblUserAccountProduct = jsCashNonFinService.saveCompanyUserAccountsProduct(tblUserAccountProduct);
                    }

                    List<TblParserCompanyConfig> tblParserCompanyConfigs = jsCashNonFinService.getParserCompanyConfig(companyId);
                    for (TblParserCompanyConfig tblParserCompanyConfig : tblParserCompanyConfigs) {
                        tblParserCompanyConfig.setMcStatus(authorizationCompanyProfileRequest.getMcStatus());
                        tblParserCompanyConfig.setCheckDate(new Date());
                        tblParserCompanyConfig.setCheckerComments(authorizationCompanyProfileRequest.getMcComments());
                        tblParserCompanyConfig.setCheckerId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblParserCompanyConfig.setLastupdatedate(new Date());
                        tblParserCompanyConfig.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblParserCompanyConfig.setUpdateindex(tblParserCompanyConfig.getUpdateindex() != null ? new BigDecimal(tblParserCompanyConfig.getUpdateindex().longValue() + 1) : new BigDecimal(1));
                        tblParserCompanyConfig = jsCashNonFinService.saveParserCompanyConfig(tblParserCompanyConfig);
                    }

                    List<TblAuthMatrixHead> tblAuthMatrixHeads = jsCashNonFinService.getCompanyAuthMatrix(companyId);
                    for (TblAuthMatrixHead tblAuthMatrixHead : tblAuthMatrixHeads) {
                        tblAuthMatrixHead.setMcStatus(authorizationCompanyProfileRequest.getMcStatus());
                        tblAuthMatrixHead.setCheckDate(new Date());
                        tblAuthMatrixHead.setCheckerComments(authorizationCompanyProfileRequest.getMcComments());
                        tblAuthMatrixHead.setCheckerId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblAuthMatrixHead.setLastupdatedate(new Date());
                        tblAuthMatrixHead.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblAuthMatrixHead.setUpdateindex(tblAuthMatrixHead.getUpdateindex() != null ? new BigDecimal(tblAuthMatrixHead.getUpdateindex().longValue() + 1) : new BigDecimal(1));
                        tblAuthMatrixHead = jsCashNonFinService.authorizeAuthMatrix(tblAuthMatrixHead);
                    }


                } else {
                    LOG.info("\n EXITING THIS METHOD == saveAuthorizeCompanyProfile(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Cannot Authorize Company", "Some Problem Occured");
                }

                if (tblCompany != null && tblCompany.getCompanyId() > 0) {

                    LOG.info("\n EXITING THIS METHOD == saveAuthorizeCompanyProfile(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Company Authorization Successfull.!!! ..|..", tblCompany);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveAuthorizeCompanyProfile(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Authorizing Company", "Some Problem Occured");
                }
            } else {

                LOG.info("\n EXITING THIS METHOD == saveAuthorizeCompanyProfile(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }


        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveAuthorizeCompanyProfile();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveAuthorizeCompanyProfile(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == saveAuthorizeCompanyProfile();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveAuthorizeCompanyProfile(); OF CLASS = JsCashCompanyPostApi \n\n\n");

            LOG.info("\n EXITING THIS METHOD == saveAuthorizeCompanyProfile(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
        }
    }

    @RequestMapping(value = "/saveCompanyAuthMatrix", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveCompanyAuthMatrix(@Valid @RequestBody TblAuthMatrixHeadRequest saveTblAuthMatrixHeadRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyAuthMatrix(); ");


        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblAuthMatrixHead tblAuthMatrixHead = new TblAuthMatrixHead();


                tblAuthMatrixHead.setFromAmount(new BigDecimal(saveTblAuthMatrixHeadRequest.getFromAmount()));
                tblAuthMatrixHead.setToAmount(new BigDecimal(saveTblAuthMatrixHeadRequest.getToAmount()));
                tblAuthMatrixHead.setIsSequential(saveTblAuthMatrixHeadRequest.getIsSequential());
                tblAuthMatrixHead.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                TblCompany tblCompany = new TblCompany();
                TblProduct tblProduct = new TblProduct();

                tblCompany.setCompanyId(saveTblAuthMatrixHeadRequest.getCompanyId());
                tblProduct.setProductId(saveTblAuthMatrixHeadRequest.getProductId());

                tblAuthMatrixHead.setTblCompany(tblCompany);
                tblAuthMatrixHead.setTblProduct(tblProduct);


                tblAuthMatrixHead = jsCashNonFinService.saveTblAuthMatrixHead(tblAuthMatrixHead);


                if (tblAuthMatrixHead != null && tblAuthMatrixHead.getAuthMatrixHeadId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrix(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblAuthMatrixHead);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrix(); OF CLASS = JsCashCompanyPostApi \n\n\n");

                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {

                LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrix(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyAuthMatrix();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrix(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyAuthMatrix();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrix(); OF CLASS = JsCashCompanyPostApi \n\n\n");

            LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrix(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
        }

    }

    @RequestMapping(value = "/saveCompanyAuthMatrixDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveCompanyAuthMatrixDetail(@Valid @RequestBody TblAuthMatrixDetailRequest saveTblAuthMatrixDetailRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyAuthMatrixDetail(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblAuthMatrixDetail tblAuthMatrixDetail = new TblAuthMatrixDetail();


                tblAuthMatrixDetail.setSrNo(saveTblAuthMatrixDetailRequest.getSrNo() == null ? null : new BigDecimal(saveTblAuthMatrixDetailRequest.getSrNo()));
                tblAuthMatrixDetail.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                TblAuthMatrixHead tblAuthMatrixHead = new TblAuthMatrixHead();
                TblUser tblUser = new TblUser();
                LkpUserAuthLevel tblUserLevel = new LkpUserAuthLevel();


                tblAuthMatrixHead.setAuthMatrixHeadId(saveTblAuthMatrixDetailRequest.getAuthMatrixHeadId());
                tblUser.setUserId(saveTblAuthMatrixDetailRequest.getUserId());
                tblUserLevel.setUserAuthLevelId(saveTblAuthMatrixDetailRequest.getUserLevelId());

                tblAuthMatrixDetail.setTblAuthMatrixHead(tblAuthMatrixHead);
                tblAuthMatrixDetail.setTblUser(tblUser);
                tblAuthMatrixDetail.setLkpUserAuthLevel(tblUserLevel);

                tblAuthMatrixDetail = jsCashNonFinService.saveTblAuthMatrixDetail(tblAuthMatrixDetail);


                if (tblAuthMatrixDetail != null && tblAuthMatrixDetail.getAuthMatrixDetailId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrixDetail(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblAuthMatrixDetail);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrixDetail(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }

            } else {
                LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrixDetail(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyAuthMatrixDetail();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrixDetail(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == saveCompanyAuthMatrixDetail();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveCompanyAuthMatrixDetail(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveParserCompanyConfig", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveParserCompanyConfig(@Valid @RequestBody TblParserCompanyConfigRequest tblParserCompanyConfigRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == saveParserCompanyConfig(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblParserCompanyConfig tblParserCompanyConfig = new TblParserCompanyConfig();

                TblCompany tblCompany = new TblCompany();
                TblParserHead tblParserHead = jsCashNonFinService.getParserById(String.valueOf(tblParserCompanyConfigRequest.getParserHeadId()));

                tblCompany.setCompanyId(tblParserCompanyConfigRequest.getCompanyId());
                tblParserHead.setParserHeadId(tblParserCompanyConfigRequest.getParserHeadId());


                tblParserCompanyConfig.setTblParserHead(tblParserHead);
                tblParserCompanyConfig.setTblCompany(tblCompany);
                tblParserCompanyConfig.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));
                tblParserCompanyConfig.setTblProduct(tblParserHead.getTblProduct());

                tblParserCompanyConfig = jsCashNonFinService.saveParserCompanyConfig(tblParserCompanyConfig);

                if (tblParserCompanyConfig != null && tblParserCompanyConfig.getParserCompanyConfigId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveParserCompanyConfig(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblParserCompanyConfig);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveParserCompanyConfig(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveParserCompanyConfig(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveParserCompanyConfig();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveParserCompanyConfig(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == saveParserCompanyConfig();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveParserCompanyConfig(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/checkOtp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> checkOtp(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == checkOtp(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblOtp tblOtp = jsCashNonFinService.checkOtpAgainstUser(loggedUserDetail.getUserId());
                if (tblOtp != null) {
                    HashMap<String, String> mapresp = new HashMap<>();

                    mapresp.put("expire", "Y");
                    LOG.info("\n EXITING THIS METHOD == checkOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Security Code Already Generated..!!", mapresp);
                } else {
                    HashMap<String, String> mapresp = new HashMap<>();

                    mapresp.put("expire", "N");

                    ///////////////////////////////////////////////////////

                    String otppin = generateRrnNumber(4);
                    TblOtp securitycodetblOtp = new TblOtp();
                    Date date = new Date(new Date().getTime() + 28800000);
                    securitycodetblOtp.setEffectiveTo(date);
                    securitycodetblOtp.setEffectiveFrom(new Date());
                    securitycodetblOtp.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                    LkpOtpType lkpOtpType = new LkpOtpType();
                    lkpOtpType.setOtpTypeId(3);
                    securitycodetblOtp.setLkpOtpType(lkpOtpType);
                    securitycodetblOtp.setOtpin(otppin);
                    securitycodetblOtp.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                    jsCashNonFinService.saveOtp(securitycodetblOtp);

                    if (securitycodetblOtp != null && securitycodetblOtp.getOtpId() > 0) {

                        TblUser tblUser = jsCashNonFinService.getUserById(Long.valueOf(loggedUserDetail.getUserId()));
                        TblSmsMsgEmail tblSmsMsgEmail = new TblSmsMsgEmail();
//                        tblSmsMsgEmail.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblSmsMsgEmail.setContactEmail(tblUser.getEmail());
                        tblSmsMsgEmail.setText(securitycodetblOtp.getOtpin());
//                        tblSmsMsgEmail.setOtpId(BigDecimal.valueOf(securitycodetblOtp.getOtpId()));
                        tblSmsMsgEmail.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                        tblSmsMsgEmail = jsCashNonFinService.saveEmail(tblSmsMsgEmail);

                        List<String> Email = new ArrayList<>();
                        Email.add(tblUser.getEmail());
                        String subject = "Generate OTP";
                        String body = " Your OTP ------ " + otppin;
                        // sendEmail(Email, "", subject, body, null, null);


                        if (securitycodetblOtp != null) {
                            LOG.info("\n EXITING THIS METHOD == checkOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.OK, "OTP Generated Successfully Your OTP IS ### " + otppin, null);
                        } else {
                            LOG.info("\n EXITING THIS METHOD == checkOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went wrong while generating OTP", "Something went wrong while generating OTP");
                        }
                    }

                    ////////////////////////////////////////////////////////


                    LOG.info("\n EXITING THIS METHOD == checkOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "OTP Send via Email/SMS", mapresp);
                }

            } else {
                LOG.info("\n EXITING THIS METHOD == checkOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email", "No User Found Against This Email");
            }


        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == checkOtp();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == checkOtp(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == checkOtp();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == checkOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/generateCompanyOtp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> generateCompanyOtp(@Valid @RequestBody GenerateCompanyOtpRequest generateCompanyOtpRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == generateCompanyOtp(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                if (generateCompanyOtpRequest.getRadioButton().equalsIgnoreCase("E")) {

                    TblOtp tblOtp = jsCashNonFinService.checkOtpAgainstUser(loggedUserDetail.getUserId());

                    tblOtp.setIsExpired("Y");
                    tblOtp.setLastupdatedate(new Date());
                    tblOtp.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                    tblOtp.setUpdateindex(tblOtp.getUpdateindex() != null ? new BigDecimal(tblOtp.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                    jsCashNonFinService.saveOtp(tblOtp);

                    if (tblOtp != null) {
                        LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.OK, "Otp Expired Successfully", tblOtp);
                    } else {
                        LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went wrong during generated OTP", "Soemthing went wrong during generated OTP");
                    }

                } else if (generateCompanyOtpRequest.getRadioButton().equalsIgnoreCase("H")) {

                    String otppin = generateRrnNumber();

                    TblOtp tblOtp = new TblOtp();
                    Date sysdate = new Date();
                    tblOtp.setEffectiveTo(addHoursToJavaUtilDate(sysdate, 1));
                    tblOtp.setEffectiveFrom(sysdate);
                    tblOtp.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                    LkpOtpType lkpOtpType = new LkpOtpType();
                    lkpOtpType.setOtpTypeId(2);
                    tblOtp.setLkpOtpType(lkpOtpType);
                    tblOtp.setOtpin(otppin);
                    tblOtp.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                    jsCashNonFinService.saveOtp(tblOtp);

                    if (tblOtp != null && tblOtp.getOtpId() > 0) {

                        TblUser tblUser = jsCashNonFinService.getUserById(Long.valueOf(loggedUserDetail.getUserId()));
                        TblSmsMsgEmail tblSmsMsgEmail = new TblSmsMsgEmail();
//                        tblSmsMsgEmail.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblSmsMsgEmail.setContactEmail(tblUser.getEmail());
                        tblSmsMsgEmail.setText(tblOtp.getOtpin());
//                        tblSmsMsgEmail.setOtpId(BigDecimal.valueOf(tblOtp.getOtpId()));
                        tblSmsMsgEmail.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblSmsMsgEmail.setMessageType("E");

                        tblSmsMsgEmail = jsCashNonFinService.saveEmail(tblSmsMsgEmail);

                        List<String> Email = new ArrayList<>();
                        Email.add(tblUser.getEmail());
                        String subject = "Generate OTP";
                        String body = " Your OTP" + otppin;
//                        sendEmail(Email, "", subject, body, "", "");


                        if (tblOtp != null) {
                            LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.OK, "Otp Generated Successfully", tblOtp);
                        } else {
                            LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went wrong while generating OTP", "Something went wrong while generating OTP");
                        }
                    } else {
                        LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went wrong while generating OTP", "Something went wrong while generating OTP");
                    }

                } else if (generateCompanyOtpRequest.getRadioButton().equalsIgnoreCase("D")) {

                    String otppin = generateRrnNumber();

                    TblOtp tblOtp = new TblOtp();
                    Date sysdate = new Date();
                    tblOtp.setEffectiveTo(addDaysToJavaUtilDate(sysdate, 1));
                    tblOtp.setEffectiveFrom(sysdate);
                    tblOtp.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                    LkpOtpType lkpOtpType = new LkpOtpType();
                    lkpOtpType.setOtpTypeId(2);
                    tblOtp.setLkpOtpType(lkpOtpType);
                    tblOtp.setOtpin(otppin);
                    tblOtp.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                    jsCashNonFinService.saveOtp(tblOtp);

                    if (tblOtp != null && tblOtp.getOtpId() > 0) {

                        TblUser tblUser = jsCashNonFinService.getUserById(Long.valueOf(loggedUserDetail.getUserId()));
                        TblSmsMsgEmail tblSmsMsgEmail = new TblSmsMsgEmail();
//                        tblSmsMsgEmail.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblSmsMsgEmail.setContactEmail(tblUser.getEmail());
                        tblSmsMsgEmail.setText(tblOtp.getOtpin());
//                        tblSmsMsgEmail.setOtpId(BigDecimal.valueOf(tblOtp.getOtpId()));
                        tblSmsMsgEmail.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                        tblSmsMsgEmail = jsCashNonFinService.saveEmail(tblSmsMsgEmail);

                        List<String> Email = new ArrayList<>();
                        Email.add(tblUser.getEmail());
                        String subject = "Generate OTP";
                        String body = " Your OTP" + otppin;
                        //     sendEmail(Email, "", subject, body, "", "");


                        if (tblOtp != null) {
                            LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.OK, "Otp Generated Successfully", tblOtp);
                        } else {
                            LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went wrong while generating OTP", "Something went wrong while generating OTP");
                        }
                    } else {
                        LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went wrong while generating OTP", "Something went wrong while generating OTP");
                    }

                } else if (generateCompanyOtpRequest.getRadioButton().equalsIgnoreCase("W")) {

                    String otppins = generateRrnNumber();

                    TblOtp tblOtps = new TblOtp();
                    Date sysdate = new Date();
                    tblOtps.setEffectiveTo(addDaysToJavaUtilDate(sysdate, 7));
                    tblOtps.setEffectiveFrom(sysdate);
                    tblOtps.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                    LkpOtpType lkpOtpTypes = new LkpOtpType();
                    lkpOtpTypes.setOtpTypeId(2);
                    tblOtps.setLkpOtpType(lkpOtpTypes);
                    tblOtps.setOtpin(otppins);
                    tblOtps.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                    jsCashNonFinService.saveOtp(tblOtps);
                    if (tblOtps != null && tblOtps.getOtpId() > 0) {

                        TblUser tblUser = jsCashNonFinService.getUserById(Long.valueOf(loggedUserDetail.getUserId()));
                        TblSmsMsgEmail tblSmsMsgEmail = new TblSmsMsgEmail();
//                        tblSmsMsgEmail.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblSmsMsgEmail.setContactEmail(tblUser.getEmail());
                        tblSmsMsgEmail.setText(tblOtps.getOtpin());
//                        tblSmsMsgEmail.setOtpId(BigDecimal.valueOf(tblOtps.getOtpId()));
                        tblSmsMsgEmail.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                        tblSmsMsgEmail = jsCashNonFinService.saveEmail(tblSmsMsgEmail);

                        List<String> Email = new ArrayList<>();
                        Email.add(tblUser.getEmail());
                        String subject = "Generate OTP";
                        String body = " Your OTP" + otppins;
//                        sendEmail(Email, "", subject, body, "", "");

                        if (tblOtps != null) {
                            LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.OK, "Otp Generated Successfully!!", tblOtps);
                        } else {
                            LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went wrong while generating OTP", "Something went wrong while generating OTP");
                        }
                    } else {
                        LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went wrong while generating OTP", "Something went wrong while generating OTP");
                    }

                } else if (generateCompanyOtpRequest.getRadioButton().equalsIgnoreCase("M")) {

                    String otppinss = generateRrnNumber();

                    TblOtp tblOtpss = new TblOtp();
                    Date sysdate = new Date();
                    tblOtpss.setEffectiveTo(addDaysToJavaUtilDate(sysdate, 30));
                    tblOtpss.setEffectiveFrom(sysdate);
                    tblOtpss.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                    LkpOtpType lkpOtpTypess = new LkpOtpType();
                    lkpOtpTypess.setOtpTypeId(2);
                    tblOtpss.setLkpOtpType(lkpOtpTypess);
                    tblOtpss.setOtpin(otppinss);
                    tblOtpss.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                    jsCashNonFinService.saveOtp(tblOtpss);

                    if (tblOtpss != null && tblOtpss.getOtpId() > 0) {

                        TblUser tblUser = jsCashNonFinService.getUserById(Long.valueOf(loggedUserDetail.getUserId()));
                        TblSmsMsgEmail tblSmsMsgEmail = new TblSmsMsgEmail();
//                        tblSmsMsgEmail.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                        tblSmsMsgEmail.setContactEmail(tblUser.getEmail());
                        tblSmsMsgEmail.setText(tblOtpss.getOtpin());
//                        tblSmsMsgEmail.setOtpId(BigDecimal.valueOf(tblOtpss.getOtpId()));
                        tblSmsMsgEmail.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                        tblSmsMsgEmail = jsCashNonFinService.saveEmail(tblSmsMsgEmail);

                        List<String> Email = new ArrayList<>();
                        Email.add(tblUser.getEmail());
                        String subject = "Generate OTP";
                        String body = " Your OTP" + otppinss;
//                        sendEmail(Email, "", subject, body, "", "");

                        if (tblOtpss != null) {
                            LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.OK, "OTP Generated Successfully", tblOtpss);
                        } else {
                            LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went wrong while generating OTP", "Something went wrong while generating OTP");
                        }

                    } else {
                        LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went Wrong", "Something went Wrong");
                    }

                } else {
                    LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Something went Wrong", "Something went Wrong");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == generateCompanyOtp();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == generateCompanyOtp();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == generateCompanyOtp(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }

    @RequestMapping(value = "/verifySecurityOtpPin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> verifySecurityOtpPin(@Valid @RequestBody SecurityPinRequest securityPinRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == verifySecurityOtpPin(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblOtp tblOtp = jsCashNonFinService.verifySecurityOtpin(loggedUserDetail.getUserId(), securityPinRequest.getSecurityPin());

                if (tblOtp != null) {
                    tblOtp.setIsVerified("Y");
                    tblOtp.setOtpTries(tblOtp.getOtpTries() != null ? new BigDecimal(tblOtp.getOtpTries().longValue() + 1) : new BigDecimal(1));
                    tblOtp.setLastupdatedate(new Date());
                    tblOtp.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                    tblOtp.setUpdateindex(tblOtp.getUpdateindex() != null ? new BigDecimal(tblOtp.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                    tblOtp = jsCashNonFinService.saveOtp(tblOtp);

                    if (tblOtp != null) {
                        LOG.info("\n EXITING THIS METHOD == verifySecurityOtpPin(); OF CLASS = verifySecurityOtpPin \n\n\n");
                        return getResponseFormat(HttpStatus.OK, "OTP Verified Successfully", tblOtp.getIsVerified());
                    } else {
                        LOG.info("\n EXITING THIS METHOD == verifySecurityOtpPin(); OF CLASS = verifySecurityOtpPin \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Internal Error", null);
                    }

                } else {
                    TblOtp wrongupdatetblOtp = jsCashNonFinService.getSecurityOtpForWrongTry(loggedUserDetail.getUserId());

                    wrongupdatetblOtp.setIsVerified("N");
                    wrongupdatetblOtp.setOtpTries(wrongupdatetblOtp.getOtpTries() != null ? new BigDecimal(wrongupdatetblOtp.getOtpTries().longValue() + 1) : new BigDecimal(1));
                    wrongupdatetblOtp.setLastupdatedate(new Date());
                    wrongupdatetblOtp.setLastupdateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                    wrongupdatetblOtp.setUpdateindex(wrongupdatetblOtp.getUpdateindex() != null ? new BigDecimal(wrongupdatetblOtp.getUpdateindex().longValue() + 1) : new BigDecimal(1));

                    wrongupdatetblOtp = jsCashNonFinService.updateOtp(wrongupdatetblOtp);


                    if (wrongupdatetblOtp != null) {
                        LOG.info("\n EXITING THIS METHOD == verifySecurityOtpPin(); OF CLASS = verifySecurityOtpPin \n\n\n");
                        return getResponseFormat(HttpStatus.OK, "Wrong OTP ", "N");
                    } else {
                        LOG.info("\n EXITING THIS METHOD == verifySecurityOtpPin(); OF CLASS = verifySecurityOtpPin \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Internal Error", null);
                    }

                }

            } else {
                LOG.info("\n EXITING THIS METHOD == verifySecurityOtpPin(); OF CLASS = verifySecurityOtpPin \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email", "No User Found Against This Email");
            }


        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == verifySecurityOtpPin();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == verifySecurityOtpPin(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == verifySecurityOtpPin();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == verifySecurityOtpPin(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/verifySecurityDeviceCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> verifySecurityDeviceCode(@Valid @RequestBody SecurityPinRequest securityPinRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyPostApi \n METHOD == verifySecurityDeviceCode(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                // MOCK demo unlock — accept fixed device code when T24 mock is ON (no OTP row required)
                if (t24MockSupport.isMockEnabled()
                        && t24MockSupport.isMockSecurityDeviceCode(securityPinRequest.getSecurityPin())) {
                    LOG.info("\n EXITING THIS METHOD == verifySecurityDeviceCode(); MOCK device code accepted \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "OTP Verified Successfully (MOCK)", "Y");
                }

                TblOtp tblOtp = jsCashNonFinService.verifySecurityDeviceCode(loggedUserDetail.getUserId(), securityPinRequest.getSecurityPin());

                if (tblOtp != null) {
                    tblOtp.setIsVerified("Y");

                    tblOtp = jsCashNonFinService.saveOtp(tblOtp);

                    if (tblOtp != null) {
                        LOG.info("\n EXITING THIS METHOD == verifySecurityOtpPin(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.OK, "OTP Verified Successfully", tblOtp.getIsVerified());
                    } else {
                        LOG.info("\n EXITING THIS METHOD == verifySecurityOtpPin(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Internal Error", null);
                    }

                } else {
                    LOG.info("\n EXITING THIS METHOD == verifySecurityDeviceCode(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Wrong Device Code ", "N");
                }

            } else {
                LOG.info("\n EXITING THIS METHOD == verifySecurityDeviceCode(); OF CLASS = JsCashCompanyPostApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email", "No User Found Against This Email");
            }


        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == verifySecurityDeviceCode();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == verifySecurityDeviceCode(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyPostApi \n METHOD == verifySecurityDeviceCode();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == verifySecurityDeviceCode(); OF CLASS = JsCashCompanyPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/company/authorizeTransaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> branchAuthorizeTransaction(@Valid @RequestBody BranchAuthorizeTransactionRequest branchAuthorizeTransactionRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCollectionPostApi \n METHOD == authorizeTransaction(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                ProcedureSubmitDocResponse branchAuthorizeResponse = jsCashFinService.callProcedureBranchAuthorizeTransaction(branchAuthorizeTransactionRequest, loggedUserDetail.getUserId());

                if (branchAuthorizeResponse != null) {
                    if (branchAuthorizeResponse.getStatus() == 1) {
                        LOG.info("\n EXITING THIS METHOD == authorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.OK, branchAuthorizeResponse.getStatusDescr(), branchAuthorizeResponse.getStatusDescr());
                    } else {
                        LOG.info("\n EXITING THIS METHOD == authorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error on Auth Matrix  " + branchAuthorizeResponse.getStatusDescr(), branchAuthorizeResponse.getStatusDescr());
                    }
                } else {
                    LOG.info("\n EXITING THIS METHOD == authorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Calling Auth procedure", "Error While Calling Auth procedure");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == authorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        } catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == authorizeTransaction();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == authorizeTransaction(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == authorizeTransaction();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == authorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }
}



    