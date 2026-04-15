package org.ais.jcash.controller.collection;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashFinService;
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
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:01 PM
 * Project : jcash
 */


@Api(value = "Cash Management Collection Post Api", description = "POST COllection API For Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/fin/collection")
public class JsCashCollectionPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashCollectionPostApi.class);

    @Autowired
    private JsCashFinService jsCashFinService;


    @RequestMapping(value = "/branchOnlineDeposit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> branchOnlineDeposit(@Valid @RequestBody BranchOnlineDeposit branchOnlineDeposit, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCollectionPostApi \n METHOD == branchOnlineDeposit(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblTransHead tblTransHead = new TblTransHead();

                tblTransHead.setDepositDate(branchOnlineDeposit.getDepositDate());
                tblTransHead.setDepositSlipAmount(branchOnlineDeposit.getDepositSlipAmount());
                tblTransHead.setDepositSlipNo(branchOnlineDeposit.getDepositSlipNo());
                tblTransHead.setDepositorCellNo(branchOnlineDeposit.getDepositorCellNo());
                tblTransHead.setDepositorEmail(branchOnlineDeposit.getDepositorEmail());
                tblTransHead.setNoOfCheques(branchOnlineDeposit.getNoOfCheques());
                tblTransHead.setNoOfInvoices(branchOnlineDeposit.getNoOfInvoices());
                tblTransHead.setTransAmount(branchOnlineDeposit.getDepositSlipAmount());
                tblTransHead.setFileDetailId(BigDecimal.valueOf(branchOnlineDeposit.getFileDetailId()));
                tblTransHead.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));


                TblAccount tblAccount = new TblAccount();
                TblCompany tblCompany = new TblCompany();
                TblProduct tblProduct = new TblProduct();
                LkpBranch lkpBranch = new LkpBranch();

                tblAccount.setAccountId(branchOnlineDeposit.getAccountId());
                tblCompany.setCompanyId(branchOnlineDeposit.getCompanyId());
                tblProduct.setProductId(branchOnlineDeposit.getProductId());
                lkpBranch.setBranchId(loggedUserDetail.getBranchId());

//                tblTransHead.setTblAccount(tblAccount);
                tblTransHead.setTblCompany(tblCompany);
                tblTransHead.setTblProduct(tblProduct);
                tblTransHead.setLkpBranch(lkpBranch);


//                tblTransHead.setBranchOnlineDepositDetails(branchOnlineDeposit.getBranchOnlineDepositDetails());


//                tblTransHead = jsCashFinService.branchOnlineDeposit(tblTransHead);

                if (tblTransHead != null && tblTransHead.getTransHeadId() > 0) {

                    ProcedureSubmitDocResponse callProcedureSubmitDoc = jsCashFinService.callProcedureSubmitDoc(tblTransHead.getTransHeadId(), loggedUserDetail.getUserId());
                    if (callProcedureSubmitDoc != null) {

                        if (callProcedureSubmitDoc.getStatus() == 1) {
                            TblProductCollection tblProductCollection = jsCashFinService.getCollectionAgainstProduct(tblTransHead.getTblProduct().getProductId());
                            if (callProcedureSubmitDoc.getAuthComplete().equalsIgnoreCase("Y")) {

                                if (tblProductCollection.getLodgement().equalsIgnoreCase("N") && tblProductCollection.getLoquidation().equalsIgnoreCase("N")) {
                                    // PERFORM FUNDS TRANSFER HERE
                                }


                                LOG.info("\n EXITING THIS METHOD == branchOnlineDeposit(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                                return getResponseFormat(HttpStatus.OK, "Transaction Performed Successfully", tblTransHead);
                            } else {

                                LOG.info("\n EXITING THIS METHOD == branchOnlineDeposit(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                                return getResponseFormat(HttpStatus.OK, "Transaction Parked For Authorization", tblTransHead);
                            }


                        } else {
                            LOG.info("\n EXITING THIS METHOD == branchOnlineDeposit(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error On Auth Matrix Proc... \n " + callProcedureSubmitDoc.getStatusDescr(), callProcedureSubmitDoc.getStatusDescr());

                        }
                    } else {
                        LOG.info("\n EXITING THIS METHOD == branchOnlineDeposit(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Performing Check On Auth Matrix", "Error While Performing Check On Auth Matrix");
                    }

                } else {
                    LOG.info("\n EXITING THIS METHOD == branchOnlineDeposit(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Performing Transaction", "UserName/Passwords Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == branchOnlineDeposit(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == branchOnlineDeposit();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == branchOnlineDeposit(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == branchOnlineDeposit();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == branchOnlineDeposit(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/branchInvoiceInq", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> branchInvoiceInq(@Valid @RequestBody BranchInvoiceInqRequest branchInvoiceInqRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCollectionPostApi \n METHOD == branchInvoiceInq(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                BranchInvoiceInqResponse branchInvoiceInqResponse = jsCashFinService.branchInvoiceInq(branchInvoiceInqRequest);

                if (branchInvoiceInqResponse != null) {
                    LOG.info("\n EXITING THIS METHOD == branchInvoiceInq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Invoice Fetched", branchInvoiceInqResponse);

                } else {
                    LOG.info("\n EXITING THIS METHOD == branchInvoiceInq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Invoice Found", null);
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == branchInvoiceInq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == branchInvoiceInq();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == branchInvoiceInq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == branchInvoiceInq();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == branchInvoiceInq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/fetchLodgementChq", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> fetchLodgementChq(@Valid @RequestBody FetchLodgeLiquidationRequest fetchLodgeLiquidationRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCollectionPostApi \n METHOD == fetchLodgementChq(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                List<FetchLodgementResponse> fetchLodgementResponses = jsCashFinService.fetchLodgementChq(fetchLodgeLiquidationRequest, loggedUserDetail.getBranchId());

                if (fetchLodgementResponses != null && fetchLodgementResponses.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == fetchLodgementChq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Save Successfully", fetchLodgementResponses);

                } else {
                    LOG.info("\n EXITING THIS METHOD == fetchLodgementChq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == fetchLodgementChq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == fetchLodgementChq();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == fetchLodgementChq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == fetchLodgementChq();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == fetchLodgementChq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/fetchLiquidationChq", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> fetchLiquidationChq(@Valid @RequestBody FetchLodgeLiquidationRequest fetchLodgeLiquidationRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCollectionPostApi \n METHOD == fetchLiquidationChq(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                List<FetchLodgementResponse> fetchLodgementResponses = jsCashFinService.fetchLiquidationChq(fetchLodgeLiquidationRequest, loggedUserDetail.getBranchId());

                if (fetchLodgementResponses != null && fetchLodgementResponses.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == fetchLiquidationChq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Save SuccesFully", fetchLodgementResponses);

                } else {
                    LOG.info("\n EXITING THIS METHOD == fetchLiquidationChq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == fetchLiquidationChq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == fetchLiquidationChq();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == fetchLiquidationChq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == fetchLiquidationChq();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == fetchLiquidationChq(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/branch/authorizeTransaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> branchAuthorizeTransaction(@Valid @RequestBody BranchAuthorizeTransactionRequest branchAuthorizeTransactionRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCollectionPostApi \n METHOD == branchAuthorizeTransaction(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                ProcedureSubmitDocResponse branchAuthorizeResponse = jsCashFinService.callProcedureBranchAuthorizeTransaction(branchAuthorizeTransactionRequest, loggedUserDetail.getUserId());

                if (branchAuthorizeResponse != null) {
                    if (branchAuthorizeResponse.getStatus() == 1) {
                        LOG.info("\n EXITING THIS METHOD == branchAuthorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.OK, branchAuthorizeResponse.getStatusDescr(), branchAuthorizeResponse.getStatusDescr());
                    } else {
                        LOG.info("\n EXITING THIS METHOD == branchAuthorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error on Auth Matrix  " + branchAuthorizeResponse.getStatusDescr(), branchAuthorizeResponse.getStatusDescr());
                    }
                } else {
                    LOG.info("\n EXITING THIS METHOD == branchAuthorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Calling Auth procedure", "Error While Calling Auth procedure");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == branchAuthorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == branchAuthorizeTransaction();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == branchAuthorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCollectionPostApi \n METHOD == branchAuthorizeTransaction();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == branchAuthorizeTransaction(); OF CLASS = JsCashCollectionPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

}
