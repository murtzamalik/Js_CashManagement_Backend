package org.ais.jcash.WsdlT24Api.controller;




import org.ais.jcash.WsdlT24Api.dto.*;
import org.ais.jcash.WsdlT24Api.model.*;
import org.ais.jcash.WsdlT24Api.service.WsdlT24IServiceImpl;
import org.ais.jcash.WsdlT24Api.service.impl.WsdlT24Service;
import org.ais.jcash.dto.InternalFundsTransferTitleFetchResponses;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.model.TblAuthMatrixHead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
//@RequestMapping("/")
public class WsdlT24Controller
{
    @Autowired
    WsdlT24IServiceImpl wsdlT24IService;


    Logger LOG = LoggerFactory.getLogger(WsdlT24Controller.class);
    @RequestMapping(value = "/iftTitleFetch/{accountNumber}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getIftTitleFetch(@PathVariable String accountNumber , HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == WsdlT24Controller \n METHOD == getIftTitleFetch(); ");
        try {

            InternalFundsTransferTitleFetchResponse internalFundsTransferTitleFetchResponse=wsdlT24IService.IftTitleFetch(accountNumber);

            if (internalFundsTransferTitleFetchResponse != null) {
                LOG.info("\n EXITING THIS METHOD == getIftTitleFetch(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.OK, " Successfull Fetch Title", internalFundsTransferTitleFetchResponse);



            } else {
                LOG.info("\n EXITING THIS METHOD == getIftTitleFetch(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error", "Please insert correct credentials");
            }
        }
        catch (Exception e) {
            LOG.error("\n CLASS == WsdlT24Controller \n METHOD == getIftTitleFetch();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getIftTitleFetch(); OF CLASS = WsdlT24Controller \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/ibftTitleFetch/{accountNumber}/{toAccount}/{toBankIMD}/{amount}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getIbftitleFetch(@PathVariable String accountNumber,@PathVariable String toAccount,@PathVariable String toBankIMD,@PathVariable String amount) {
        LOG.info("\n\n\nINSIDE \n CLASS == WsdlT24Controller \n METHOD == getIbftitleFetch(); ");
        try {
            IBFTTitleFetchResponse ibftTitleFetchResponse=wsdlT24IService.IbftTitleFetch(accountNumber,toAccount,toBankIMD,amount);
            if (ibftTitleFetchResponse != null) {
                LOG.info("\n EXITING THIS METHOD == getIbftitleFetch(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.OK, " Successfull Fetch Title", ibftTitleFetchResponse);



            } else {
                LOG.info("\n EXITING THIS METHOD == getIbftitleFetch(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error", "Please insert correct credentials");
            }
        }
        catch (Exception e) {
            LOG.error("\n CLASS == WsdlT24Controller \n METHOD == getIbftitleFetch();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getIbftitleFetch(); OF CLASS = WsdlT24Controller \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/utilityBillInquiry", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getUtilityBillInquiry(@RequestBody UtltyBillInquiry utltyBillInquiry, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == WsdlT24Controller \n METHOD == utilityBillInquiry(); ");
        try {
            UtilityBillInquiryResponse utilitybillInquiryResponse =wsdlT24IService.utilitybillinquiry(utltyBillInquiry);
            if (utilitybillInquiryResponse != null) {
                LOG.info("\n EXITING THIS METHOD == utilityBillInquiry(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.OK, " Successfull Fetch Title", utilitybillInquiryResponse);



            } else {
                LOG.info("\n EXITING THIS METHOD == utilityBillInquiry(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error", "Please insert correct credentials");
            }
        }
        catch (Exception e) {
            LOG.error("\n CLASS == WsdlT24Controller \n METHOD == utilityBillInquiry();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == utilityBillInquiry(); OF CLASS = WsdlT24Controller \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        }

    }

//

    @RequestMapping(value = "/balanceinquiry/{accountNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getBalanceInquiry(@PathVariable String accountNumber) {
        LOG.info("\n\n\nINSIDE \n CLASS == WsdlT24Controller \n METHOD == getBalanceInquiry(); ");
        try {
            BalanceInquiryResponse balanceInquiryResponse=wsdlT24IService.balanceinquiry(accountNumber);
            if (balanceInquiryResponse != null) {
                LOG.info("\n EXITING THIS METHOD == getBalanceInquiry(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.OK, " Successfull Balance Inquiry", balanceInquiryResponse);



            } else {
                LOG.info("\n EXITING THIS METHOD == getBalanceInquiry(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error", "Please insert correct credentials");
            }
        }
        catch (Exception e) {
            LOG.error("\n CLASS == WsdlT24Controller \n METHOD == getBalanceInquiry();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getBalanceInquiry(); OF CLASS = WsdlT24Controller \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/billinquiry", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getBillInquiry(@RequestBody UtltyBillInquiry utltyBillInquiry, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == WsdlT24Controller \n METHOD == getBillInquiry(); ");
        try {
            UtilityBillInquiryResponse utilityBillInquiryResponse=wsdlT24IService.utilitybillinquiry(utltyBillInquiry);
            if (utilityBillInquiryResponse != null) {
                LOG.info("\n EXITING THIS METHOD == getBillInquiry(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.OK, " Successfull Balance Inquiry", utilityBillInquiryResponse);



            } else {
                LOG.info("\n EXITING THIS METHOD == getBillInquiry(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error", "Please insert correct credentials");
            }
        }
        catch (Exception e) {
            LOG.error("\n CLASS == WsdlT24Controller \n METHOD == getBillInquiry();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getBillInquiry(); OF CLASS = WsdlT24Controller \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/internalFundTransfer/{fromAccount}/{toAccount}/{amount}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> internalFundTransfer(@PathVariable String fromAccount,@PathVariable String toAccount,@PathVariable String amount, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == WsdlT24Controller \n METHOD == internalFundTransfer(); ");
        try {
            InternalFundsTransferResponse internalFundsTransferResponse=wsdlT24IService.internalFundsTranfer(fromAccount, toAccount, amount);
            if (internalFundsTransferResponse != null) {
                LOG.info("\n EXITING THIS METHOD == internalFundTransfer(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.OK, " Successfull Balance Inquiry", internalFundsTransferResponse);



            } else {
                LOG.info("\n EXITING THIS METHOD == internalFundTransfer(); OF CLASS = Controller \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error", "Please insert correct credentials");
            }
        }
        catch (Exception e) {
            LOG.error("\n CLASS == WsdlT24Controller \n METHOD == internalFundTransfer();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == internalFundTransfer(); OF CLASS = WsdlT24Controller \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        }

    }
    public ResponseEntity<HashMap<String, Object>> getResponseFormat(HttpStatus status, String message, Object data) {
        int responsestatus;
        if (status.equals(HttpStatus.OK) || status.value() == 200) {
            responsestatus = 1;
        } else {
            responsestatus = 0;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("responsecode", responsestatus);
        map.put("messages", message);
        map.put("data", data);
        return ResponseEntity.status(status).body(map);
    }
}
