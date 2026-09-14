package org.ais.jcash.WsdlT24Api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ais.jcash.WsdlT24Api.dto.*;
import org.ais.jcash.WsdlT24Api.dto.ifttfresponse.Data;
import org.ais.jcash.WsdlT24Api.dto.ifttfresponse.Root;
import org.ais.jcash.WsdlT24Api.model.*;
import org.ais.jcash.WsdlT24Api.service.impl.WsdlT24Service;
import org.ais.jcash.controller.AbstractApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 3:46 PM
 * Project : jcash
 */


@Service
public class WsdlT24IServiceImpl extends AbstractApi implements WsdlT24Service {

    @Value("${jcash.t24.base-url:http://20.24.26.237:8080}")
    private String t24BaseUrl;

    private RestTemplate restTemplate;

    @Override
    public InternalFundsTransferTitleFetchResponse IftTitleFetch(String accountNumber) {
        String url=t24BaseUrl+"/internalfundtransfertitlefetch";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime lt = LocalDateTime.now();
        // Setting Transmission Date and Time
        String tdt = transmissionDateTime.format(lt);
        // Setting Stan
        String stan=generateRrnNumber();

        XmlHeaderInput xmlHeaderInput=new XmlHeaderInput();
        xmlHeaderInput.setMerchantID("0096");
        xmlHeaderInput.setTransmissionDateTime(tdt);
        xmlHeaderInput.setStan(stan);
        xmlHeaderInput.setCustomerIDType("ACCOUNT");
        xmlHeaderInput.setCustomerID(accountNumber);
        xmlHeaderInput.setPinBlockType("TIN");
        xmlHeaderInput.setPinBlock("1A28CB1C44D84A0B");
        xmlHeaderInput.setMerchantType("0096");
        xmlHeaderInput.setTransactionDescription("FTTF-CMS." + tdt + stan);
        xmlHeaderInput.setProcCode("66");
        xmlHeaderInput.setTransactionFee("0");
        xmlHeaderInput.setCardAcceptorNameLocation("JSBANK                   CMS          PK");
        xmlHeaderInput.setTargetHost("any");

        InternalFundsTransferTitleFetchRequest internalFundsTransferTitleFetchRequest=new InternalFundsTransferTitleFetchRequest();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        internalFundsTransferTitleFetchRequest.setAccountNumber(accountNumber);
        HashMap<String, Object> paramMap = new HashMap<>();

        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/json");
        paramMap.put("xmlHeaderlnput", xmlHeaderInput);
        paramMap.put("internalFundsTransferTitleFetchRequest", internalFundsTransferTitleFetchRequest);

        Root r = new Root();
        InternalFundsTransferTitleFetchResponse internalFundsTransferTitleFetchResponse=new InternalFundsTransferTitleFetchResponse();
        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            r = mapper.readValue(x, Root.class);
            if(r.getResponsecode()==1) {
                internalFundsTransferTitleFetchResponse.setAccountNumber(r.getData().getAccountNo());
                internalFundsTransferTitleFetchResponse.setAccountTitle(r.getData().getAccountTitle());
                internalFundsTransferTitleFetchResponse.setBranchName(r.getData().getBranchName());
                internalFundsTransferTitleFetchResponse.setResponseCode(String.valueOf(r.getResponsecode()));
            }
            else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return internalFundsTransferTitleFetchResponse;

    }



    @Override
    public IBFTTitleFetchResponse IbftTitleFetch(String accountNumber,String toAccount,String toBankIMD,String amount) {
        String url=t24BaseUrl+"/ibfttitlefetch";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime lt = LocalDateTime.now();
        // Setting Transmission Date and Time
        String tdt = transmissionDateTime.format(lt);
        // Setting Stan
        String stan=generateRrnNumber();
        XmlHeaderInput xmlHeaderInput=new XmlHeaderInput();
        xmlHeaderInput.setMerchantID("0098");
        xmlHeaderInput.setTransmissionDateTime(tdt);
        xmlHeaderInput.setStan(stan);
        xmlHeaderInput.setCustomerIDType("ACCOUNT");
        xmlHeaderInput.setCustomerID(accountNumber);
        xmlHeaderInput.setPinBlockType("TIN");
        xmlHeaderInput.setPinBlock("D9115529E922468E");
        xmlHeaderInput.setMerchantType("0098");
        xmlHeaderInput.setTransactionDescription("IBFT-MB." + tdt + stan);
        xmlHeaderInput.setProcCode("62");
        xmlHeaderInput.setTransactionFee("60");
        xmlHeaderInput.setCardAcceptorNameLocation("JSBANK                   CMS          PK");
        xmlHeaderInput.setTargetHost("any");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        IBFTTitleFetchRequest ibftTitleFetchRequest=new IBFTTitleFetchRequest();
        ibftTitleFetchRequest.setFromAccount(accountNumber);
        ibftTitleFetchRequest.setAmount(amount);
        ibftTitleFetchRequest.setToAccount(toAccount);
        ibftTitleFetchRequest.setToBankIMD(toBankIMD);
        HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/json");
        paramMap.put("xmlHeaderlnput", xmlHeaderInput);
        paramMap.put("ibftTitleFetchRequest", ibftTitleFetchRequest);

        org.ais.jcash.WsdlT24Api.dto.ibfttitlefetchresponse.Root r;
        IBFTTitleFetchResponse ibftTitleFetchResponse=new IBFTTitleFetchResponse();
        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            r = mapper.readValue(x, org.ais.jcash.WsdlT24Api.dto.ibfttitlefetchresponse.Root .class);
            if(r.getResponsecode()==1) {
                ibftTitleFetchResponse.setAmount(r.getData().getAmount());
                ibftTitleFetchResponse.setFromAccount(r.getData().getFromAccount());
                ibftTitleFetchResponse.setToAccount(r.getData().getToAccount());
                ibftTitleFetchResponse.setToAccountTitle(r.getData().getToAccountTitle());
                ibftTitleFetchResponse.setToBankIMD(r.getData().getToBankIMD());
                ibftTitleFetchResponse.setToBankName(r.getData().getToBankName());
                ibftTitleFetchResponse.setToBranchName(r.getData().getToBranchName());

            }
            else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ibftTitleFetchResponse;

    }

    @Override

    public BalanceInquiryResponse balanceinquiry(String accountNumber) {
        String url=t24BaseUrl+"/balanceinquiry";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime lt = LocalDateTime.now();
        // Setting Transmission Date and Time
        String tdt = transmissionDateTime.format(lt);
        // Setting Stan
        String stan=generateRrnNumber();
        XmlHeaderInput xmlHeaderInput=new XmlHeaderInput();
        xmlHeaderInput.setMerchantID("0096");
        xmlHeaderInput.setTransmissionDateTime(tdt);
        xmlHeaderInput.setStan(stan);
        xmlHeaderInput.setCustomerIDType("ACCOUNT");
        xmlHeaderInput.setCustomerID(accountNumber);
        xmlHeaderInput.setPinBlockType("TIN");
        xmlHeaderInput.setPinBlock("1A28CB1C44D84A0B");
        xmlHeaderInput.setMerchantType("0096");
        xmlHeaderInput.setTransactionDescription("BALINQ-MB." + tdt + stan);
        xmlHeaderInput.setProcCode("31");
        xmlHeaderInput.setTransactionFee("0");
        xmlHeaderInput.setCardAcceptorNameLocation("JSBANK                   CMS          PK");
        xmlHeaderInput.setTargetHost("any");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String, Object> paramMap = new HashMap<>();

        HashMap<String, Object> headerMap = new HashMap<>();
        BalanceInquiryRequest balanceInquiryRequest=new BalanceInquiryRequest();
        balanceInquiryRequest.setAccountNumber(accountNumber);
        headerMap.put("Content-Type","application/json");
        paramMap.put("xmlHeaderlnput", xmlHeaderInput);
        paramMap.put("balanceInquiryRequest", balanceInquiryRequest);

        org.ais.jcash.WsdlT24Api.dto.balanceinquiry.Root  r;
        BalanceInquiryResponse balanceInquiryResponse=new BalanceInquiryResponse();
        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            r = mapper.readValue(x, org.ais.jcash.WsdlT24Api.dto.balanceinquiry.Root  .class);
            if(r.getResponsecode()==1) {
                balanceInquiryResponse.setAccountCurrency(r.getData().getAccountCurrency());
                balanceInquiryResponse.setAccountNumber(r.getData().getAccountNo());
                balanceInquiryResponse.setAccountStatus(r.getData().getAccountStatus());
                balanceInquiryResponse.setAccountType(r.getData().getAccountType());
                balanceInquiryResponse.setLedgerBalance(r.getData().getLedgerBalance());
                balanceInquiryResponse.setWorkingBalance(r.getData().getWorkingBalance());

            }
            else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return balanceInquiryResponse;
    }

    @Override
    public UtilityBillInquiryResponse utilitybillinquiry(UtltyBillInquiry utltyBillInquiry) {
        String url=t24BaseUrl+"/billinquiry";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime lt = LocalDateTime.now();
        // Setting Transmission Date and Time
        String tdt = transmissionDateTime.format(lt);
        // Setting Stan
        String stan=generateRrnNumber();

        utltyBillInquiry.getXmlHeaderInput().setMerchantID("0098");
        utltyBillInquiry.getXmlHeaderInput().setTransmissionDateTime(tdt);
        utltyBillInquiry.getXmlHeaderInput().setStan(stan);
        utltyBillInquiry.getXmlHeaderInput().setCustomerIDType("ACCOUNT");
        utltyBillInquiry.getXmlHeaderInput().setPinBlockType("TIN");
        utltyBillInquiry.getXmlHeaderInput().setPinBlock("E0C50F53E650256F");
        utltyBillInquiry.getXmlHeaderInput().setMerchantType("0098");
        utltyBillInquiry.getXmlHeaderInput().setTransactionDescription("MOBINQ-MB." + tdt + stan);
        utltyBillInquiry.getXmlHeaderInput().setProcCode("72");
        utltyBillInquiry.getXmlHeaderInput().setTransactionFee("0");
        utltyBillInquiry.getXmlHeaderInput().setCardAcceptorNameLocation("JS BANK MOBILE BANKINGPK");
        utltyBillInquiry.getXmlHeaderInput().setTargetHost("any");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String, Object> paramMap = new HashMap<>();

        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/json");

        paramMap.put("xmlHeaderlnput", utltyBillInquiry.getXmlHeaderInput());
        paramMap.put("utilityBillInquiryRequest", utltyBillInquiry.getUtilityBillInquiryRequest());

        org.ais.jcash.WsdlT24Api.dto.utilitybillinquiry.Root  r;
        UtilityBillInquiryResponse utilityBillInquiryResponse=new UtilityBillInquiryResponse();
        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            r = mapper.readValue(x, org.ais.jcash.WsdlT24Api.dto.utilitybillinquiry.Root   .class);
            if(r.getResponsecode()==1) {
                utilityBillInquiryResponse.setBillingMonth(r.getData().getBillingMonth());
                utilityBillInquiryResponse.setBillStatus(r.getData().getBillStatus());
                utilityBillInquiryResponse.setFromAccount(r.getData().getFromAccount());
                utilityBillInquiryResponse.setNetCED(r.getData().getNetCED());

                utilityBillInquiryResponse.setPaymentDueDate(r.getData().getPaymentDueDate());
                utilityBillInquiryResponse.setSubscriberNameOrConnectionType(r.getData().getSubcriberNameorConnectionType());
                utilityBillInquiryResponse.setTotalAmountPayableAfterDueDate(r.getData().getTotalAmountPayableAfterDueDate());
                utilityBillInquiryResponse.setTotalAmountPayableWithinDueDate(r.getData().getTotalAmountPayableWithinDueDate());
                utilityBillInquiryResponse.setUtilityCompanyCode(r.getData().getUtilityCompanyCode());
                utilityBillInquiryResponse.setUtilityConsumerNumber(r.getData().getUtilityConsumerNumber());
                utilityBillInquiryResponse.setNetWithholdingTax(r.getData().getNetWithHoldingTax());


            }
            else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return utilityBillInquiryResponse;
    }

    @Override
    public InternalFundsTransferResponse internalFundsTranfer(String fromAccount,String toAccount, String amount) {
        String url=t24BaseUrl+"/internalfundtransferpayment";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime lt = LocalDateTime.now();
        // Setting Transmission Date and Time
        String tdt = transmissionDateTime.format(lt);
        // Setting Stan
        String stan=generateRrnNumber();
        XmlHeaderInput xmlHeaderInput=new XmlHeaderInput();
        xmlHeaderInput.setMerchantID("0098");
        xmlHeaderInput.setTransmissionDateTime(tdt);
        xmlHeaderInput.setStan(stan);
        xmlHeaderInput.setCustomerIDType("ACCOUNT");
        xmlHeaderInput.setCustomerID(fromAccount);
        xmlHeaderInput.setPinBlockType("TIN");
        xmlHeaderInput.setPinBlock("4FBE4EB8E340F942");
        xmlHeaderInput.setMerchantType("0098");
        xmlHeaderInput.setTransactionDescription("FT-MB." + tdt + stan);
        xmlHeaderInput.setProcCode("52");
        xmlHeaderInput.setTransactionFee("0");
        xmlHeaderInput.setCardAcceptorNameLocation("JS BANK MOBILE BANKINGPK");
        xmlHeaderInput.setTargetHost("any");

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String, Object> paramMap = new HashMap<>();

        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/json");
        InternalFundsTransferRequest internalFundsTransferRequest=new InternalFundsTransferRequest();
        internalFundsTransferRequest.setFromAccount(fromAccount);
        internalFundsTransferRequest.setToAccount(toAccount);
        internalFundsTransferRequest.setAmount(amount);
        InternalFundsTransferTitleFetchRequest internalFundsTransferTitleFetchRequest=new InternalFundsTransferTitleFetchRequest();
        internalFundsTransferTitleFetchRequest.setAccountNumber(fromAccount);
        paramMap.put("xmlHeaderlnput", xmlHeaderInput);
        paramMap.put("internalFundsTransferRequest", internalFundsTransferRequest);
        paramMap.put("internalFundsTransferTitleFetchRequest", internalFundsTransferTitleFetchRequest);

        org.ais.jcash.WsdlT24Api.dto.internalfundstransferpayment.Root   r;
        InternalFundsTransferResponse internalFundsTransferResponse=new InternalFundsTransferResponse();
        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            r = mapper.readValue(x,  org.ais.jcash.WsdlT24Api.dto.internalfundstransferpayment.Root   .class);
            if(r.getResponsecode()==1) {
                internalFundsTransferResponse.setFromAccount(r.getData().getFromAccount());
                internalFundsTransferResponse.setToAccount(r.getData().getToAccount());
                internalFundsTransferResponse.setAmount(r.getData().getAmount());
            }
            else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return internalFundsTransferResponse;
    }

    //Generate random Number
    //
    public String generateRrnNumber() {
        int length = Integer.valueOf(6);
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }
}