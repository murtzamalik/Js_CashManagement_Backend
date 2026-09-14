package org.ais.jcash.controller.payment;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashFinService;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.WsdlT24Api.dto.IftTitleFetchRequest;
import org.ais.jcash.WsdlT24Api.model.InternalFundsTransferResponse;
import org.ais.jcash.WsdlT24Api.model.InternalFundsTransferTitleFetchResponse;
import org.ais.jcash.WsdlT24Api.service.WsdlT24IServiceImpl;
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


@Api(value = "Cash Management Api Non Financial Post Api", description = "POST NON FIN API For Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashPaymentPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashPaymentPostApi.class);

    @Autowired
    private JsCashNonFinService jsCashNonFinService;

    @Autowired
    private JsCashFinService jsCashFinService;

    @Autowired
    WsdlT24IServiceImpl wsdlT24IService;


    @RequestMapping(value = "/initiateSingleTransaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> initiateSingleTransaction(@Valid @RequestBody InitiateSingleTransactionRequest initiateSingleTransactionRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashPaymentPostApi \n METHOD == initiateSingleTransaction(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                CustomizedLovAuthCompanyProduct customizedLovAuthCompanyProduct = jsCashNonFinService.getUserAuthProdutsNature(loggedUserDetail.getUserId(),
                        initiateSingleTransactionRequest.getProductId());

                if (customizedLovAuthCompanyProduct != null) {

                    TblTransHead tblTransHead = new TblTransHead();

                    TblCompany tblCompany = new TblCompany();
                    TblAccount tblAccount = new TblAccount();
                    TblProduct tblProduct = new TblProduct();
                    LkpPaymentMode lkpPaymentMode = new LkpPaymentMode();

                    tblCompany.setCompanyId(loggedUserDetail.getCompanyId());
                    tblAccount.setAccountId(initiateSingleTransactionRequest.getDebitAcctNoId());
                    tblProduct.setProductId(initiateSingleTransactionRequest.getProductId());


                    tblTransHead.setTblAccount2(tblAccount);
                    tblTransHead.setTblCompany(tblCompany);
                    tblTransHead.setTblProduct(tblProduct);


                    tblTransHead.setTransAmount(BigDecimal.valueOf(initiateSingleTransactionRequest.getTransferAmnt()));
                    tblTransHead.setSecurityDeviceCode(initiateSingleTransactionRequest.getSecurityDeviceCode());
                    tblTransHead.setCustomerReference(initiateSingleTransactionRequest.getCustRef());
                    tblTransHead.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                    tblTransHead.setBeneficiaryName(initiateSingleTransactionRequest.getBeneficiaryName());
                    tblTransHead.setBeneficiaryAddress(initiateSingleTransactionRequest.getBeneficiaryAddress());
                    tblTransHead.setBeneficiaryAccountNo(initiateSingleTransactionRequest.getBenActNo());
                    tblTransHead.setBeneficiaryEmail(initiateSingleTransactionRequest.getBenEmail());
                    tblTransHead.setPaymentModeId(initiateSingleTransactionRequest.getPayModeId() == null?null:BigDecimal.valueOf(initiateSingleTransactionRequest.getPayModeId()));
                    tblTransHead.setBeneficiaryBankId(initiateSingleTransactionRequest.getBenBankId() == null?null:BigDecimal.valueOf(initiateSingleTransactionRequest.getBenBankId()));
                    tblTransHead.setBeneficiaryAccountTitle(initiateSingleTransactionRequest.getAccountTitle());



                    tblTransHead = jsCashNonFinService.saveInitiateSinglrTransaction(tblTransHead);


                    if (customizedLovAuthCompanyProduct.getProductCode().equalsIgnoreCase("COC")) {
                        //////////////////// CASH OVER COUNTER TRANSACTUION //////////////////////////////




                        if (tblTransHead != null && tblTransHead.getTransHeadId() > 0) {


                            String xpin = generateRrnNumber();

                            TblCashOverCounter tblCashOverCounter = new TblCashOverCounter();

//                            tblCashOverCounter.setTransHeadId1(BigDecimal.valueOf(tblTransHead.getTransHeadId()));
                            tblCashOverCounter.setDocumentNo(initiateSingleTransactionRequest.getDocNo());
                            tblCashOverCounter.setDocumentType(initiateSingleTransactionRequest.getDocType());
                            tblCashOverCounter.setMobileNo(initiateSingleTransactionRequest.getMobileNo());
                            tblCashOverCounter.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                            tblCashOverCounter.setXpin(BigDecimal.valueOf(Long.parseLong(xpin)));

                            tblCashOverCounter = jsCashNonFinService.saveInitiateSingleTransaction(tblCashOverCounter);


                            if (tblCashOverCounter != null && tblCashOverCounter.getCashOverCounterId() > 0) {


                                ProcedureSubmitDocResponse callProcedureSubmitDoc = jsCashFinService.callProcedureSubmitDoc(tblTransHead.getTransHeadId(), loggedUserDetail.getUserId());
                                if (callProcedureSubmitDoc != null) {

                                    if (callProcedureSubmitDoc.getStatus() == 1) {
                                        if (callProcedureSubmitDoc.getAuthComplete().equalsIgnoreCase("Y")) {

                                            TblSmsMsgEmail tblSmsMsgEmail = new TblSmsMsgEmail();

                                            tblSmsMsgEmail.setText(xpin);
//                                            tblSmsMsgEmail.setUserId(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                                            tblSmsMsgEmail.setMessageType("S");
                                            tblSmsMsgEmail.setContactEmail(tblCashOverCounter.getMobileNo());
//                                            tblSmsMsgEmail.setTransHeadId(BigDecimal.valueOf(tblTransHead.getTransHeadId()));
                                            tblSmsMsgEmail.setSendFlag(new BigDecimal(0));
                                            tblSmsMsgEmail.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                                            tblSmsMsgEmail = jsCashNonFinService.saveInitiateSingleTransactions(tblSmsMsgEmail);

                                            LOG.info("\n EXITING THIS METHOD == branchOnlineDeposit(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                                            return getResponseFormat(HttpStatus.OK, "Transaction Performed Successfully", null);
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
                                LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = initiateSingleTransaction \n\n\n");
                                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Internal Error", null);
                            }


                        }  else if (customizedLovAuthCompanyProduct.getProductCode().equalsIgnoreCase("IFT")) {
                            //////////////////// INTERNAL FUNDS TRANSFER //////////////////////////////
                            ProcedureSubmitDocResponse callProcedureSubmitDoc = jsCashFinService.callProcedureSubmitDoc(tblTransHead.getTransHeadId(), loggedUserDetail.getUserId());
                            if (callProcedureSubmitDoc != null) {

                                if (callProcedureSubmitDoc.getStatus() == 1) {
                                    if (callProcedureSubmitDoc.getAuthComplete().equalsIgnoreCase("Y")) {

//                                        InternalFundsTransferResponse internalFundsTransferResponse=wsdlT24IService.internalFundsTranfer(initiateSingleTransactionRequest.getBenActNo(),String.valueOf(initiateSingleTransactionRequest.getDebitAcctNoId()),String.valueOf(initiateSingleTransactionRequest.getTransferAmnt()));
                                        InternalFundsTransferResponse internalFundsTransferResponse=wsdlT24IService.internalFundsTranfer("0000670111","0000486547","000000020000");

                                        LOG.info("\n EXITING THIS METHOD == branchOnlineDeposit(); OF CLASS = JsCashCollectionPostApi \n\n\n");
                                        return getResponseFormat(HttpStatus.OK, "Transaction Performed Successfully", null);
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



                        } else if (customizedLovAuthCompanyProduct.getProductCode().equalsIgnoreCase("IBFT")) {
                            //////////////////// INTER BANK FUNDS TRANSFER //////////////////////////////

                        } else {

                        }

                    } else {
                        LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = initiateSingleTransaction \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Internal Error", null);
                    }
                } else {
                    LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = initiateSingleTransaction \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Internal Error", null);
                }


            } else {
                LOG.info("\n EXITING THIS METHOD == checkOtp(); OF CLASS = initiateSingleTransaction \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email", "No User Found Against This Email");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashPaymentPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        }
        catch (Exception e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == initiateSingleTransaction();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = JsCashPaymentPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
        return null;
    }

    @RequestMapping(value = "/verifyCoCXpin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getDataAgainstXpin(@Valid @RequestBody VerifyXpinRequest verifyXpinRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashPaymentPostApi \n METHOD == getDataAgainstXpin(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));

            if (loggedUserDetail != null&& loggedUserDetail.getUserType().equalsIgnoreCase("B")) {

                List<XpinTransactionsResponse> tblCashOverCounter = jsCashNonFinService.getDataAgainstXpin(Long.valueOf(verifyXpinRequest.getXpin()));


                if (tblCashOverCounter != null && tblCashOverCounter.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getDataAgainstXpin(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblCashOverCounter);
                } else {

                    LOG.info("\n EXITING THIS METHOD == getDataAgainstXpin(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }

            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashPaymentPostApi(); OF CLASS = getDataAgainstXpin() \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "You Are Not Logged In/ Insufficient Rights", "No Record Found");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == getDataAgainstXpin();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getDataAgainstXpin(); OF CLASS = JsCashPaymentPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == getDataAgainstXpin();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getDataAgainstXpin(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/receiveCocCash", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> receiveCocCash(@Valid @RequestBody RecCocCashRequest recCocCashRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashPaymentPostApi \n METHOD == receiveCocCash(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblTransHead tblTransHead = new TblTransHead();
                TblCompany tblCompany = new TblCompany();
                TblProduct tblProduct = new TblProduct();
                LkpBranch lkpBranch = new LkpBranch();

                tblCompany.setCompanyId(recCocCashRequest.getCompanyId());
                tblProduct.setProductId(recCocCashRequest.getProductId());
                lkpBranch.setBranchId(loggedUserDetail.getBranchId());


                tblTransHead.setBeneficiaryName(recCocCashRequest.getBenName());
                tblTransHead.setBeneficiaryAddress(recCocCashRequest.getBenAddress());
                tblTransHead.setTransAmount(recCocCashRequest.getTransAmount());
                tblTransHead.setLkpBranch(lkpBranch);
                tblTransHead.setTblCompany(tblCompany);
                tblTransHead.setTblProduct(tblProduct);
                tblTransHead.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                if(recCocCashRequest.getPaymentMode().equalsIgnoreCase("T")){
                    tblTransHead.setBeneficiaryAccountNo(recCocCashRequest.getIftAccountNo() == null?null:recCocCashRequest.getIftAccountNo());
                    tblTransHead.setBeneficiaryAccountTitle(recCocCashRequest.getIftAccountTitle() == null?null:recCocCashRequest.getIftAccountTitle());
                }


                tblTransHead = jsCashNonFinService.saveRecCocCashTransHead(tblTransHead);

                if (tblTransHead != null) {

                    TblCashOverCounter tblCashOverCounter = jsCashNonFinService.updateRecCocCash(recCocCashRequest, loggedUserDetail,tblTransHead);


                    if (tblCashOverCounter != null && tblCashOverCounter.getCashOverCounterId() > 0) {
                        LOG.info("\n EXITING THIS METHOD == receiveCocCash(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblCashOverCounter);

                    } else {
                        LOG.info("\n EXITING THIS METHOD == receiveCocCash(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                    }

                } else {
                    LOG.info("\n EXITING THIS METHOD == receiveCocCash(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == receiveCocCash(); OF CLASS = initiateSingleTransaction \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email", "No User Found Against This Email");
            }
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == receiveCocCash();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == receiveCocCash(); OF CLASS = JsCashPaymentPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/verifyCOCXpin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getDataAgainstXpin(@Valid @RequestBody verifyCOCXpin verifyCOCXpin, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashPaymentPostApi \n METHOD == verifyCOCXpin(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<XpinTransactionsResponse> tblCashOverCounter = jsCashNonFinService.getDataAgainstXpin(Long.valueOf(verifyCOCXpin.getXpin()));


                if (tblCashOverCounter != null && tblCashOverCounter.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == verifyCOCXpin(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblCashOverCounter);
                } else {

                    LOG.info("\n EXITING THIS METHOD == verifyCOCXpin(); OF CLASS = JSCashGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }

            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashPaymentPostApi(); OF CLASS = verifyCOCXpin() \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == verifyCOCXpin();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == verifyCOCXpin(); OF CLASS = JsCashPaymentPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == verifyCOCXpin();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getDataAgainstXpin(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/initiateSingleTransactionTitleFetch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> initiateSingleTransactionTitleFetch(@Valid @RequestBody IftTitleFetchRequest iftTitleFetchRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashPaymentPostApi \n METHOD == initiateSingleTransactionTitleFetch(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                InternalFundsTransferTitleFetchResponse internalFundsTransferTitleFetchResponse=wsdlT24IService.IftTitleFetch(iftTitleFetchRequest.getAccNo());


                if (internalFundsTransferTitleFetchResponse != null && internalFundsTransferTitleFetchResponse.getResponseCode().equalsIgnoreCase("1")) {
                    LOG.info("\n EXITING THIS METHOD == initiateSingleTransactionTitleFetch(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Title Fetched Successfully...!", internalFundsTransferTitleFetchResponse);
                } else {

                    LOG.info("\n EXITING THIS METHOD == initiateSingleTransactionTitleFetch(); OF CLASS = JSCashGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Title Fetched", "No Title Fetched");

                }

            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashPaymentPostApi(); OF CLASS = initiateSingleTransactionTitleFetch() \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", "No Logged User Found");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == initiateSingleTransactionTitleFetch();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == initiateSingleTransactionTitleFetch(); OF CLASS = JsCashPaymentPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == initiateSingleTransactionTitleFetch();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == initiateSingleTransactionTitleFetch(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/recCocTransferIFT", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> recCocTransferIFT(@Valid @RequestBody IftTitleFetchRequest iftTitleFetchRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashPaymentPostApi \n METHOD == recCocTransferIFT(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                InternalFundsTransferTitleFetchResponse internalFundsTransferTitleFetchResponse=wsdlT24IService.IftTitleFetch(iftTitleFetchRequest.getAccNo());


                if (internalFundsTransferTitleFetchResponse != null && internalFundsTransferTitleFetchResponse.getResponseCode().equalsIgnoreCase("1")) {
                    LOG.info("\n EXITING THIS METHOD == recCocTransferIFT(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Title Fetched Successfully...!", internalFundsTransferTitleFetchResponse);
                } else {

                    LOG.info("\n EXITING THIS METHOD == recCocTransferIFT(); OF CLASS = JSCashGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Title Fetched", "No Title Fetched");

                }

            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashPaymentPostApi(); OF CLASS = recCocTransferIFT() \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", "No Logged User Found");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == recCocTransferIFT();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == recCocTransferIFT(); OF CLASS = JsCashPaymentPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == recCocTransferIFT();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == recCocTransferIFT(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/reviewTransaction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> reviewTransaction(@Valid @RequestBody ReviewTransactionRequest reviewTransactionRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashPaymentPostApi \n METHOD == reviewTransaction(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<ReviewTransactionResponse> reviewTransactionResponses = jsCashFinService.reviewFinancialTransactions(reviewTransactionRequest);


                if (reviewTransactionResponses != null && reviewTransactionResponses.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == reviewTransaction(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", reviewTransactionResponses);
                } else {

                    LOG.info("\n EXITING THIS METHOD == reviewTransaction(); OF CLASS = JSCashGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }

            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashPaymentPostApi(); OF CLASS = reviewTransaction() \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == reviewTransaction();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == reviewTransaction(); OF CLASS = JsCashPaymentPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == reviewTransaction();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getDataAgainstXpin(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/reviewTransactionDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> reviewTransactionDetail(@Valid @RequestBody ReviewTransactionDetailRequest reviewTransactionDetailRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashPaymentPostApi \n METHOD == reviewTransactionDetail(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                ReviewTransactionDetailResponse reviewTransactionDetailResponse = jsCashFinService.reviewFinancialTransactionsDetails(reviewTransactionDetailRequest);


                if (reviewTransactionDetailResponse != null) {
                    LOG.info("\n EXITING THIS METHOD == reviewTransactionDetail(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", reviewTransactionDetailResponse);
                } else {

                    LOG.info("\n EXITING THIS METHOD == reviewTransactionDetail(); OF CLASS = JSCashGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }

            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashPaymentPostApi(); OF CLASS = reviewTransactionDetail()) \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == verifyCOCXpin();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashPaymentPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == verifyCOCXpin();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getDataAgainstXpin(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

}



