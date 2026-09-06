package org.ais.jcash.controller.branch;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.WsdlT24Api.controller.WsdlT24Controller;
import org.ais.jcash.WsdlT24Api.model.InternalFundsTransferTitleFetchResponse;
import org.ais.jcash.WsdlT24Api.service.WsdlT24IServiceImpl;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.BranchUserRequest;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.dto.TblAuthMatrixDetailBranchRequest;
import org.ais.jcash.dto.TblAuthMatrixHeadBranchRequest;
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
import java.util.HashMap;

@Api(value = "JS Cash Management Api Login Post Api", description = "POST LOGIN  API For JS Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashBranchMngtPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashBranchMngtPostApi.class);
    @Autowired
    JsCashNonFinService jsCashNonFinService;

    @RequestMapping(value = "/saveBranchUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveBranchUser(@Valid @RequestBody BranchUserRequest branchUserRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtPostApi \n METHOD == saveBranchUser(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblUser tblUser = new TblUser();

                tblUser.setContactNo(branchUserRequest.getContactNo());
                tblUser.setDepartment(branchUserRequest.getDepartment());
                tblUser.setDesignation(branchUserRequest.getDesignation());
                tblUser.setEmail(branchUserRequest.getEmail());
                tblUser.setEmployeeNo(branchUserRequest.getEmployeeNo());
                tblUser.setPassword("12345");
                tblUser.setProfileExpiry(branchUserRequest.getProfileExpiry());
                tblUser.setUserCode(branchUserRequest.getUserCode());
                tblUser.setUserGroup(branchUserRequest.getUserGroup());
                tblUser.setUserName(branchUserRequest.getUserName().toUpperCase());
//                tblUser.setRoleId(branchUserRequest.getRoleId());
                tblUser.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                LkpBaseLocation lkpBaseLocation = new LkpBaseLocation();
                TblAccount tblAccount = new TblAccount();
                LkpUserType lkpUserType = new LkpUserType();

                lkpBaseLocation.setBaseLocationId(branchUserRequest.getBaseLocationId());
                tblAccount.setAccountId(branchUserRequest.getAccountId());
                lkpUserType.setUserTypeId(4);
                tblUser.setLkpUserType(lkpUserType);

                tblUser.setLkpBaseLocation(lkpBaseLocation);
                tblUser.setTblAccount(null);

                LkpBranch lkpBranch = new LkpBranch();
                lkpBranch.setBranchId(branchUserRequest.getBranchId());
                tblUser.setLkpBranch(lkpBranch);


                tblUser = jsCashNonFinService.saveBranchUser(tblUser);

                if (tblUser != null && tblUser.getUserId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveBranchUser(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblUser);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveBranchUser(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Saved", "No Record Saved");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveBranchUser(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashBranchMngtPostApi \n METHOD == saveBranchUser();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveBranchUser(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveBranchAuthMatrix", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveBranchAuthMatrix(@Valid @RequestBody TblAuthMatrixHeadBranchRequest saveTblAuthMatrixHeadBranchRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtPostApi \n METHOD == saveBranchAuthMatrix(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblAuthMatrixHead tblAuthMatrixHead = new TblAuthMatrixHead();


                tblAuthMatrixHead.setFromAmount(new BigDecimal(saveTblAuthMatrixHeadBranchRequest.getFromAmount()));
                tblAuthMatrixHead.setToAmount(new BigDecimal(saveTblAuthMatrixHeadBranchRequest.getToAmount()));
                tblAuthMatrixHead.setIsSequential(saveTblAuthMatrixHeadBranchRequest.getIsSequential());
                tblAuthMatrixHead.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                TblProduct tblProduct = new TblProduct();

                tblProduct.setProductId(saveTblAuthMatrixHeadBranchRequest.getProductId());
                tblAuthMatrixHead.setTblProduct(tblProduct);

                tblAuthMatrixHead = jsCashNonFinService.saveTblAuthMatrixHead(tblAuthMatrixHead);


                if (tblAuthMatrixHead != null && tblAuthMatrixHead.getAuthMatrixHeadId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveBranchAuthMatrix(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblAuthMatrixHead);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveBranchAuthMatrix(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");

                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Saved", "No Record Saved");
                }
            } else {

                LOG.info("\n EXITING THIS METHOD == saveBranchAuthMatrix(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashBranchMngtPostApi \n METHOD == saveBranchAuthMatrix();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveBranchAuthMatrix(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
            
            LOG.info("\n EXITING THIS METHOD == saveBranchAuthMatrix(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
        }
    
    }

    @RequestMapping(value = "/saveBranchAuthMatrixDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveBranchAuthMatrixDetail(@Valid @RequestBody TblAuthMatrixDetailBranchRequest tblAuthMatrixDetailBranchRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtPostApi \n METHOD == saveBranchAuthMatrixDetail(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblAuthMatrixDetail tblAuthMatrixDetail = new TblAuthMatrixDetail();


                tblAuthMatrixDetail.setSrNo(new BigDecimal(tblAuthMatrixDetailBranchRequest.getSrNo()));
                tblAuthMatrixDetail.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                TblAuthMatrixHead tblAuthMatrixHead = new TblAuthMatrixHead();
                LkpUserAuthLevel tblUserLevel = new LkpUserAuthLevel();


                tblAuthMatrixHead.setAuthMatrixHeadId(tblAuthMatrixDetailBranchRequest.getAuthMatrixHeadId());

                tblUserLevel.setUserAuthLevelId(tblAuthMatrixDetailBranchRequest.getUserLevelId());

                tblAuthMatrixDetail.setTblAuthMatrixHead(tblAuthMatrixHead);

                tblAuthMatrixDetail.setLkpUserAuthLevel(tblUserLevel);

                TblRole tblRole = new TblRole();
                tblRole.setRoleId(tblAuthMatrixDetailBranchRequest.getRoleId());
                tblAuthMatrixDetail.setTblRole(tblRole);

                tblAuthMatrixDetail = jsCashNonFinService.saveTblAuthMatrixDetail(tblAuthMatrixDetail);


                if (tblAuthMatrixDetail != null && tblAuthMatrixDetail.getAuthMatrixDetailId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveBranchAuthMatrixDetail(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully ", tblAuthMatrixDetail);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveBranchAuthMatrixDetail(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Saved", "No Record Saved");
                }

            } else {
                LOG.info("\n EXITING THIS METHOD == saveBranchAuthMatrixDetail(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashBranchMngtPostApi \n METHOD == saveBranchAuthMatrixDetail();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveBranchAuthMatrixDetail(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashBranchMngtPostApi \n METHOD == saveBranchAuthMatrixDetail();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveBranchAuthMatrixDetail(); OF CLASS = JsCashBranchMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

}
