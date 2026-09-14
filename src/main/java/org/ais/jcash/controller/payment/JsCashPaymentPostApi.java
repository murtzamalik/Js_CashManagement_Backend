package org.ais.jcash.controller.payment;

import io.swagger.annotations.Api;
import org.ais.jcash.Repo.LkpBankRepo;
import org.ais.jcash.Service.JsCashFinService;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.WsdlT24Api.dto.IftTitleFetchRequest;
import org.ais.jcash.WsdlT24Api.model.IBFTTitleFetchResponse;
import org.ais.jcash.WsdlT24Api.model.InternalFundsTransferResponse;
import org.ais.jcash.WsdlT24Api.model.InternalFundsTransferTitleFetchResponse;
import org.ais.jcash.WsdlT24Api.service.T24MockSupport;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private LkpBankRepo lkpBankRepo;

    @Autowired
    private T24MockSupport t24MockSupport;


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
                    if (initiateSingleTransactionRequest.getBenBankId() != null && initiateSingleTransactionRequest.getBenBankId() < 9000L) {
                        tblTransHead.setBeneficiaryBankId(BigDecimal.valueOf(initiateSingleTransactionRequest.getBenBankId()));
                    } else if (initiateSingleTransactionRequest.getBenBankId() != null) {
                        // Mock bank ids (9000+) — skip FK to LKP_BANK
                        tblTransHead.setBeneficiaryBankId(null);
                    } else {
                        tblTransHead.setBeneficiaryBankId(null);
                    }
                    tblTransHead.setBeneficiaryAccountTitle(initiateSingleTransactionRequest.getAccountTitle());



                    // Persist beneficiary mobile on address for IBFT (no dedicated phone column on trans head)
                    if (initiateSingleTransactionRequest.getMobileNo() != null && !initiateSingleTransactionRequest.getMobileNo().trim().isEmpty()) {
                        tblTransHead.setBeneficiaryAddress(initiateSingleTransactionRequest.getMobileNo().trim());
                    }

                    tblTransHead = jsCashNonFinService.saveInitiateSinglrTransaction(tblTransHead);

                    if (tblTransHead == null || tblTransHead.getTransHeadId() <= 0) {
                        LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = initiateSingleTransaction \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Internal Error", null);
                    }

                    String productCode = customizedLovAuthCompanyProduct.getProductCode();

                    if (productCode != null && productCode.equalsIgnoreCase("COC")) {
                        //////////////////// CASH OVER COUNTER TRANSACTUION //////////////////////////////
                            String xpin = generateRrnNumber();

                            TblCashOverCounter tblCashOverCounter = new TblCashOverCounter();

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
                                            tblSmsMsgEmail.setMessageType("S");
                                            tblSmsMsgEmail.setContactEmail(tblCashOverCounter.getMobileNo());
                                            tblSmsMsgEmail.setSendFlag(new BigDecimal(0));
                                            tblSmsMsgEmail.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                                            tblSmsMsgEmail = jsCashNonFinService.saveInitiateSingleTransactions(tblSmsMsgEmail);

                                            LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                                            return getResponseFormat(HttpStatus.OK, "Transaction Performed Successfully", null);
                                        } else {

                                            LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                                            return getResponseFormat(HttpStatus.OK, "Transaction Parked For Authorization", tblTransHead);
                                        }


                                    } else {
                                        LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error On Auth Matrix Proc... \n " + callProcedureSubmitDoc.getStatusDescr(), callProcedureSubmitDoc.getStatusDescr());

                                    }
                                } else {
                                    LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Performing Check On Auth Matrix", "Error While Performing Check On Auth Matrix");
                                }


                            } else {
                                LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = initiateSingleTransaction \n\n\n");
                                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Internal Error", null);
                            }


                    } else if (productCode != null && productCode.equalsIgnoreCase("IFT")) {
                            //////////////////// INTERNAL FUNDS TRANSFER //////////////////////////////
                            ProcedureSubmitDocResponse callProcedureSubmitDoc = jsCashFinService.callProcedureSubmitDoc(tblTransHead.getTransHeadId(), loggedUserDetail.getUserId());
                            if (callProcedureSubmitDoc != null) {

                                if (callProcedureSubmitDoc.getStatus() == 1) {
                                    if (callProcedureSubmitDoc.getAuthComplete().equalsIgnoreCase("Y")) {

                                        InternalFundsTransferResponse internalFundsTransferResponse=wsdlT24IService.internalFundsTranfer("0000670111","0000486547","000000020000");

                                        LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                                        return getResponseFormat(HttpStatus.OK, "Transaction Performed Successfully", null);
                                    } else {

                                        LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                                        return getResponseFormat(HttpStatus.OK, "Transaction Parked For Authorization", tblTransHead);
                                    }


                                } else {
                                    LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error On Auth Matrix Proc... \n " + callProcedureSubmitDoc.getStatusDescr(), callProcedureSubmitDoc.getStatusDescr());

                                }
                            } else {
                                LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = JsCashPaymentPostApi \n\n\n");
                                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Performing Check On Auth Matrix", "Error While Performing Check On Auth Matrix");
                            }



                    } else if (productCode != null && productCode.equalsIgnoreCase("IBFT")) {
                            //////////////////// INTER BANK FUNDS TRANSFER //////////////////////////////
                            ProcedureSubmitDocResponse callProcedureSubmitDoc = jsCashFinService.callProcedureSubmitDoc(tblTransHead.getTransHeadId(), loggedUserDetail.getUserId());
                            if (callProcedureSubmitDoc != null) {
                                if (callProcedureSubmitDoc.getStatus() == 1) {
                                    if (callProcedureSubmitDoc.getAuthComplete().equalsIgnoreCase("Y")) {
                                        String fromAcc = String.valueOf(initiateSingleTransactionRequest.getDebitAcctNoId());
                                        String toAcc = initiateSingleTransactionRequest.getBenActNo();
                                        String amount = String.valueOf(initiateSingleTransactionRequest.getTransferAmnt());
                                        String imd = initiateSingleTransactionRequest.getToBankIMD();
                                        if ((imd == null || imd.trim().isEmpty()) && initiateSingleTransactionRequest.getBenBankId() != null) {
                                            Optional<LkpBank> bank = lkpBankRepo.findById(initiateSingleTransactionRequest.getBenBankId());
                                            if (bank.isPresent() && bank.get().getBankImd() != null) {
                                                imd = bank.get().getBankImd();
                                            }
                                        }
                                        if (imd == null || imd.trim().isEmpty()) {
                                            imd = "000000";
                                        }
                                        IBFTTitleFetchResponse ibftPay = wsdlT24IService.IbftPayment(fromAcc, toAcc, imd, amount);
                                        LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); IBFT auth complete mock/live pay={} \n\n\n", ibftPay != null);
                                        return getResponseFormat(HttpStatus.OK, "IBFT Transaction Performed Successfully", ibftPay);
                                    } else {
                                        LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); IBFT parked \n\n\n");
                                        return getResponseFormat(HttpStatus.OK, "IBFT Transaction Parked For Authorization", tblTransHead);
                                    }
                                } else {
                                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error On Auth Matrix Proc... \n " + callProcedureSubmitDoc.getStatusDescr(), callProcedureSubmitDoc.getStatusDescr());
                                }
                            } else {
                                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Performing Check On Auth Matrix", "Error While Performing Check On Auth Matrix");
                            }

                    } else {
                        LOG.info("\n EXITING THIS METHOD == initiateSingleTransaction(); OF CLASS = initiateSingleTransaction \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Unsupported product for single transaction: " + productCode, null);
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

    @RequestMapping(value = "/t24MockStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> t24MockStatus(HttpServletRequest request) {
        return getResponseFormat(HttpStatus.OK, "T24 integration status", t24MockSupport.status());
    }

    @RequestMapping(value = "/initiateIbftTitleFetch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> initiateIbftTitleFetch(@Valid @RequestBody IbftTitleFetchPaymentRequest requestBody, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashPaymentPostApi \n METHOD == initiateIbftTitleFetch(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail == null) {
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", "No Logged User Found");
            }

            IBFTTitleFetchResponse ibftTitleFetchResponse = wsdlT24IService.IbftTitleFetch(
                    requestBody.getFromAccount(),
                    requestBody.getToAccount(),
                    requestBody.getToBankIMD(),
                    requestBody.getAmount()
            );

            if (ibftTitleFetchResponse != null && ibftTitleFetchResponse.getToAccountTitle() != null) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("fromAccount", ibftTitleFetchResponse.getFromAccount());
                data.put("toAccount", ibftTitleFetchResponse.getToAccount());
                data.put("toBankIMD", ibftTitleFetchResponse.getToBankIMD());
                data.put("amount", ibftTitleFetchResponse.getAmount());
                data.put("toAccountTitle", ibftTitleFetchResponse.getToAccountTitle());
                data.put("toBankName", ibftTitleFetchResponse.getToBankName());
                data.put("toBranchName", ibftTitleFetchResponse.getToBranchName());
                data.put("mockMode", t24MockSupport.isMockEnabled());
                return getResponseFormat(HttpStatus.OK, t24MockSupport.isMockEnabled()
                        ? "IBFT Title Fetched (MOCK)"
                        : "IBFT Title Fetched Successfully", data);
            }
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No IBFT Title Fetched", "No IBFT Title Fetched");
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == initiateIbftTitleFetch();  ERROR ----- " + e.getLocalizedMessage());
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }

    @RequestMapping(value = "/initiateBulkIbft", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> initiateBulkIbft(@Valid @RequestBody BulkIbftRequest bulkIbftRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashPaymentPostApi \n METHOD == initiateBulkIbft(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail == null) {
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", "No Logged User Found");
            }

            CustomizedLovAuthCompanyProduct product = jsCashNonFinService.getUserAuthProdutsNature(
                    loggedUserDetail.getUserId(), bulkIbftRequest.getProductId());
            if (product == null || product.getProductCode() == null || !product.getProductCode().equalsIgnoreCase("IBFT")) {
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Selected product is not an IBFT product", null);
            }

            List<HashMap<String, Object>> results = new ArrayList<>();
            int successCount = 0;
            int failCount = 0;
            int rowIndex = 0;

            for (BulkIbftRowRequest row : bulkIbftRequest.getRows()) {
                rowIndex++;
                HashMap<String, Object> rowResult = new HashMap<>();
                rowResult.put("row", rowIndex);
                rowResult.put("iban", row.getIban());

                try {
                    if (row.getIban() == null || row.getIban().trim().isEmpty()) {
                        rowResult.put("status", "FAILED");
                        rowResult.put("message", "IBAN is required");
                        failCount++;
                        results.add(rowResult);
                        continue;
                    }
                    if (row.getTransferAmnt() <= 0) {
                        rowResult.put("status", "FAILED");
                        rowResult.put("message", "Amount must be greater than zero");
                        failCount++;
                        results.add(rowResult);
                        continue;
                    }

                    Long bankId = row.getBenBankId();
                    if (bankId == null && row.getBankName() != null && !row.getBankName().trim().isEmpty()) {
                        Optional<LkpBank> bank = lkpBankRepo.findFirstByBankNameIgnoreCase(row.getBankName().trim());
                        if (bank.isPresent()) {
                            bankId = bank.get().getBankId();
                        }
                    }
                    // Mock bank catalogue uses ids >= 9000 (no LKP_BANK row)
                    boolean mockBank = bankId != null && bankId >= 9000L;
                    if (bankId == null && (row.getBankName() == null || row.getBankName().trim().isEmpty())) {
                        rowResult.put("status", "FAILED");
                        rowResult.put("message", "Bank not found");
                        failCount++;
                        results.add(rowResult);
                        continue;
                    }
                    if (bankId == null && t24MockSupport.isMockEnabled()) {
                        mockBank = true;
                    }
                    if (bankId == null && !t24MockSupport.isMockEnabled()) {
                        rowResult.put("status", "FAILED");
                        rowResult.put("message", "Bank not found");
                        failCount++;
                        results.add(rowResult);
                        continue;
                    }

                    TblTransHead tblTransHead = new TblTransHead();
                    TblCompany tblCompany = new TblCompany();
                    TblAccount tblAccount = new TblAccount();
                    TblProduct tblProduct = new TblProduct();

                    tblCompany.setCompanyId(loggedUserDetail.getCompanyId());
                    tblAccount.setAccountId(bulkIbftRequest.getDebitAcctNoId());
                    tblProduct.setProductId(bulkIbftRequest.getProductId());

                    tblTransHead.setTblAccount2(tblAccount);
                    tblTransHead.setTblCompany(tblCompany);
                    tblTransHead.setTblProduct(tblProduct);
                    tblTransHead.setTransAmount(BigDecimal.valueOf(row.getTransferAmnt()));
                    tblTransHead.setSecurityDeviceCode(bulkIbftRequest.getSecurityDeviceCode());
                    String custRef = row.getCustRef();
                    if (custRef == null || custRef.trim().isEmpty()) {
                        custRef = (bulkIbftRequest.getBatchRef() == null ? "IBFT-BULK" : bulkIbftRequest.getBatchRef()) + "-" + rowIndex;
                    }
                    tblTransHead.setCustomerReference(custRef);
                    tblTransHead.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));
                    tblTransHead.setBeneficiaryAccountNo(row.getIban().trim().replaceAll("\\s+", "").toUpperCase());
                    if (!mockBank && bankId != null) {
                        tblTransHead.setBeneficiaryBankId(BigDecimal.valueOf(bankId));
                    }
                    tblTransHead.setBeneficiaryAccountTitle(row.getAccountTitle());
                    tblTransHead.setBeneficiaryName(row.getAccountTitle());
                    if (row.getMobileNo() != null) {
                        tblTransHead.setBeneficiaryAddress(row.getMobileNo().trim());
                    }

                    tblTransHead = jsCashNonFinService.saveInitiateSinglrTransaction(tblTransHead);
                    if (tblTransHead == null || tblTransHead.getTransHeadId() <= 0) {
                        rowResult.put("status", "FAILED");
                        rowResult.put("message", "Failed to save transaction");
                        failCount++;
                        results.add(rowResult);
                        continue;
                    }

                    ProcedureSubmitDocResponse submitDoc = jsCashFinService.callProcedureSubmitDoc(
                            tblTransHead.getTransHeadId(), loggedUserDetail.getUserId());
                    if (submitDoc != null && submitDoc.getStatus() == 1) {
                        rowResult.put("status", "SUCCESS");
                        rowResult.put("transHeadId", tblTransHead.getTransHeadId());
                        rowResult.put("message", "Y".equalsIgnoreCase(submitDoc.getAuthComplete())
                                ? "IBFT processed"
                                : "Parked for authorization");
                        successCount++;
                    } else {
                        rowResult.put("status", "FAILED");
                        rowResult.put("message", submitDoc == null ? "Auth matrix error" : submitDoc.getStatusDescr());
                        failCount++;
                    }
                    results.add(rowResult);
                } catch (Exception rowEx) {
                    rowResult.put("status", "FAILED");
                    rowResult.put("message", rowEx.getLocalizedMessage());
                    failCount++;
                    results.add(rowResult);
                }
            }

            HashMap<String, Object> summary = new HashMap<>();
            summary.put("successCount", successCount);
            summary.put("failCount", failCount);
            summary.put("rows", results);

            return getResponseFormat(HttpStatus.OK,
                    "Bulk IBFT completed. Success: " + successCount + ", Failed: " + failCount,
                    summary);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashPaymentPostApi \n METHOD == initiateBulkIbft();  ERROR ----- " + e.getLocalizedMessage());
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



