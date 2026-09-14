package org.ais.jcash.WsdlT24Api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ais.jcash.WsdlT24Api.dto.*;
import org.ais.jcash.WsdlT24Api.dto.ifttfresponse.Root;
import org.ais.jcash.WsdlT24Api.model.*;
import org.ais.jcash.WsdlT24Api.service.impl.WsdlT24Service;
import org.ais.jcash.controller.AbstractApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

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

    private static final Logger LOG = LoggerFactory.getLogger(WsdlT24IServiceImpl.class);

    @Value("${jcash.t24.base-url:http://20.24.26.237:8080}")
    private String t24BaseUrl;

    @Autowired
    private T24MockSupport t24MockSupport;

    @Override
    public InternalFundsTransferTitleFetchResponse IftTitleFetch(String accountNumber) {
        if (t24MockSupport.isMockEnabled()) {
            LOG.info("T24 MOCK mode — IftTitleFetch for {}", accountNumber);
            return t24MockSupport.mockIftTitleFetch(accountNumber);
        }

        String url = t24BaseUrl + "/internalfundtransfertitlefetch";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime lt = LocalDateTime.now();
        String tdt = transmissionDateTime.format(lt);
        String stan = generateRrnNumber();

        XmlHeaderInput xmlHeaderInput = new XmlHeaderInput();
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

        InternalFundsTransferTitleFetchRequest internalFundsTransferTitleFetchRequest = new InternalFundsTransferTitleFetchRequest();
        internalFundsTransferTitleFetchRequest.setAccountNumber(accountNumber);
        HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        paramMap.put("xmlHeaderlnput", xmlHeaderInput);
        paramMap.put("internalFundsTransferTitleFetchRequest", internalFundsTransferTitleFetchRequest);

        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            Root r = mapper.readValue(x, Root.class);
            if (r.getResponsecode() == 1) {
                InternalFundsTransferTitleFetchResponse response = new InternalFundsTransferTitleFetchResponse();
                response.setAccountNumber(r.getData().getAccountNo());
                response.setAccountTitle(r.getData().getAccountTitle());
                response.setBranchName(r.getData().getBranchName());
                response.setResponseCode(String.valueOf(r.getResponsecode()));
                return response;
            }
        } catch (Exception e) {
            LOG.warn("IftTitleFetch live call failed: {}", e.getMessage());
        }
        if (t24MockSupport.isMockFallbackOnError()) {
            LOG.info("T24 MOCK fallback — IftTitleFetch for {}", accountNumber);
            return t24MockSupport.mockIftTitleFetch(accountNumber);
        }
        return null;
    }

    @Override
    public IBFTTitleFetchResponse IbftTitleFetch(String accountNumber, String toAccount, String toBankIMD, String amount) {
        if (t24MockSupport.isMockEnabled()) {
            LOG.info("T24 MOCK mode — IbftTitleFetch toAccount={}", toAccount);
            return t24MockSupport.mockIbftTitleFetch(accountNumber, toAccount, toBankIMD, amount);
        }

        String url = t24BaseUrl + "/ibfttitlefetch";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime lt = LocalDateTime.now();
        String tdt = transmissionDateTime.format(lt);
        String stan = generateRrnNumber();
        XmlHeaderInput xmlHeaderInput = new XmlHeaderInput();
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

        IBFTTitleFetchRequest ibftTitleFetchRequest = new IBFTTitleFetchRequest();
        ibftTitleFetchRequest.setFromAccount(accountNumber);
        ibftTitleFetchRequest.setAmount(amount);
        ibftTitleFetchRequest.setToAccount(toAccount);
        ibftTitleFetchRequest.setToBankIMD(toBankIMD);
        HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        paramMap.put("xmlHeaderlnput", xmlHeaderInput);
        paramMap.put("ibftTitleFetchRequest", ibftTitleFetchRequest);

        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            org.ais.jcash.WsdlT24Api.dto.ibfttitlefetchresponse.Root r =
                    mapper.readValue(x, org.ais.jcash.WsdlT24Api.dto.ibfttitlefetchresponse.Root.class);
            if (r.getResponsecode() == 1) {
                IBFTTitleFetchResponse ibftTitleFetchResponse = new IBFTTitleFetchResponse();
                ibftTitleFetchResponse.setAmount(r.getData().getAmount());
                ibftTitleFetchResponse.setFromAccount(r.getData().getFromAccount());
                ibftTitleFetchResponse.setToAccount(r.getData().getToAccount());
                ibftTitleFetchResponse.setToAccountTitle(r.getData().getToAccountTitle());
                ibftTitleFetchResponse.setToBankIMD(r.getData().getToBankIMD());
                ibftTitleFetchResponse.setToBankName(r.getData().getToBankName());
                ibftTitleFetchResponse.setToBranchName(r.getData().getToBranchName());
                return ibftTitleFetchResponse;
            }
        } catch (Exception e) {
            LOG.warn("IbftTitleFetch live call failed: {}", e.getMessage());
        }
        if (t24MockSupport.isMockFallbackOnError()) {
            LOG.info("T24 MOCK fallback — IbftTitleFetch toAccount={}", toAccount);
            return t24MockSupport.mockIbftTitleFetch(accountNumber, toAccount, toBankIMD, amount);
        }
        return null;
    }

    @Override
    public IBFTTitleFetchResponse IbftPayment(String fromAccount, String toAccount, String toBankIMD, String amount) {
        if (t24MockSupport.isMockEnabled()) {
            LOG.info("T24 MOCK mode — IbftPayment toAccount={}", toAccount);
            return t24MockSupport.mockIbftTitleFetch(fromAccount, toAccount, toBankIMD, amount);
        }

        String url = t24BaseUrl + "/ibftpayment";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String tdt = transmissionDateTime.format(LocalDateTime.now());
        String stan = generateRrnNumber();

        XmlHeaderInput xmlHeaderInput = new XmlHeaderInput();
        xmlHeaderInput.setMerchantID("0098");
        xmlHeaderInput.setTransmissionDateTime(tdt);
        xmlHeaderInput.setStan(stan);
        xmlHeaderInput.setCustomerIDType("ACCOUNT");
        xmlHeaderInput.setCustomerID(fromAccount);
        xmlHeaderInput.setPinBlockType("TIN");
        xmlHeaderInput.setPinBlock("D9115529E922468E");
        xmlHeaderInput.setMerchantType("0098");
        xmlHeaderInput.setTransactionDescription("IBFTPAY-MB." + tdt + stan);
        xmlHeaderInput.setProcCode("40");
        xmlHeaderInput.setTransactionFee("60");
        xmlHeaderInput.setCardAcceptorNameLocation("JSBANK                   CMS          PK");
        xmlHeaderInput.setTargetHost("any");

        IBFTTitleFetchRequest req = new IBFTTitleFetchRequest();
        req.setFromAccount(fromAccount);
        req.setToAccount(toAccount);
        req.setToBankIMD(toBankIMD);
        req.setAmount(amount);

        HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        paramMap.put("xmlHeaderlnput", xmlHeaderInput);
        paramMap.put("ibftPaymentRequest", req);

        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            org.ais.jcash.WsdlT24Api.dto.ibfttitlefetchresponse.Root r =
                    mapper.readValue(x, org.ais.jcash.WsdlT24Api.dto.ibfttitlefetchresponse.Root.class);
            if (r.getResponsecode() == 1) {
                IBFTTitleFetchResponse response = new IBFTTitleFetchResponse();
                response.setFromAccount(r.getData().getFromAccount());
                response.setToAccount(r.getData().getToAccount());
                response.setAmount(r.getData().getAmount());
                response.setToBankIMD(r.getData().getToBankIMD());
                response.setToAccountTitle(r.getData().getToAccountTitle());
                response.setToBankName(r.getData().getToBankName());
                response.setToBranchName(r.getData().getToBranchName());
                return response;
            }
        } catch (Exception e) {
            LOG.warn("IbftPayment live call failed: {}", e.getMessage());
        }
        if (t24MockSupport.isMockFallbackOnError()) {
            return t24MockSupport.mockIbftTitleFetch(fromAccount, toAccount, toBankIMD, amount);
        }
        return null;
    }

    @Override
    public BalanceInquiryResponse balanceinquiry(String accountNumber) {
        if (t24MockSupport.isMockEnabled()) {
            BalanceInquiryResponse mock = new BalanceInquiryResponse();
            mock.setAccountNumber(accountNumber);
            mock.setAccountStatus("ACTIVE");
            mock.setAccountType("CURRENT");
            mock.setLedgerBalance("100000.00");
            mock.setWorkingBalance("99500.00");
            mock.setAccountCurrency("PKR");
            return mock;
        }

        String url = t24BaseUrl + "/balanceinquiry";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime lt = LocalDateTime.now();
        String tdt = transmissionDateTime.format(lt);
        String stan = generateRrnNumber();
        XmlHeaderInput xmlHeaderInput = new XmlHeaderInput();
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

        HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> headerMap = new HashMap<>();
        BalanceInquiryRequest balanceInquiryRequest = new BalanceInquiryRequest();
        balanceInquiryRequest.setAccountNumber(accountNumber);
        headerMap.put("Content-Type", "application/json");
        paramMap.put("xmlHeaderlnput", xmlHeaderInput);
        paramMap.put("balanceInquiryRequest", balanceInquiryRequest);

        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            org.ais.jcash.WsdlT24Api.dto.balanceinquiry.Root r =
                    mapper.readValue(x, org.ais.jcash.WsdlT24Api.dto.balanceinquiry.Root.class);
            if (r.getResponsecode() == 1) {
                BalanceInquiryResponse balanceInquiryResponse = new BalanceInquiryResponse();
                balanceInquiryResponse.setAccountCurrency(r.getData().getAccountCurrency());
                balanceInquiryResponse.setAccountNumber(r.getData().getAccountNo());
                balanceInquiryResponse.setAccountStatus(r.getData().getAccountStatus());
                balanceInquiryResponse.setAccountType(r.getData().getAccountType());
                balanceInquiryResponse.setLedgerBalance(r.getData().getLedgerBalance());
                balanceInquiryResponse.setWorkingBalance(r.getData().getWorkingBalance());
                return balanceInquiryResponse;
            }
        } catch (Exception e) {
            LOG.warn("balanceinquiry live call failed: {}", e.getMessage());
        }
        if (t24MockSupport.isMockFallbackOnError()) {
            BalanceInquiryResponse mock = new BalanceInquiryResponse();
            mock.setAccountNumber(accountNumber);
            mock.setAccountStatus("ACTIVE");
            mock.setAccountType("CURRENT");
            mock.setLedgerBalance("100000.00");
            mock.setWorkingBalance("99500.00");
            mock.setAccountCurrency("PKR");
            return mock;
        }
        return null;
    }

    @Override
    public UtilityBillInquiryResponse utilitybillinquiry(UtltyBillInquiry utltyBillInquiry) {
        String url = t24BaseUrl + "/billinquiry";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime lt = LocalDateTime.now();
        String tdt = transmissionDateTime.format(lt);
        String stan = generateRrnNumber();

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

        HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        paramMap.put("xmlHeaderlnput", utltyBillInquiry.getXmlHeaderInput());
        paramMap.put("utilityBillInquiryRequest", utltyBillInquiry.getUtilityBillInquiryRequest());

        UtilityBillInquiryResponse utilityBillInquiryResponse = new UtilityBillInquiryResponse();
        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            org.ais.jcash.WsdlT24Api.dto.utilitybillinquiry.Root r =
                    mapper.readValue(x, org.ais.jcash.WsdlT24Api.dto.utilitybillinquiry.Root.class);
            if (r.getResponsecode() == 1) {
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
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return utilityBillInquiryResponse;
    }

    @Override
    public InternalFundsTransferResponse internalFundsTranfer(String fromAccount, String toAccount, String amount) {
        if (t24MockSupport.isMockEnabled()) {
            LOG.info("T24 MOCK mode — internalFundsTranfer");
            return t24MockSupport.mockInternalFundsTransfer(fromAccount, toAccount, amount);
        }

        String url = t24BaseUrl + "/internalfundtransferpayment";
        DateTimeFormatter transmissionDateTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime lt = LocalDateTime.now();
        String tdt = transmissionDateTime.format(lt);
        String stan = generateRrnNumber();
        XmlHeaderInput xmlHeaderInput = new XmlHeaderInput();
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

        HashMap<String, Object> paramMap = new HashMap<>();
        HashMap<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        InternalFundsTransferRequest internalFundsTransferRequest = new InternalFundsTransferRequest();
        internalFundsTransferRequest.setFromAccount(fromAccount);
        internalFundsTransferRequest.setToAccount(toAccount);
        internalFundsTransferRequest.setAmount(amount);
        InternalFundsTransferTitleFetchRequest internalFundsTransferTitleFetchRequest = new InternalFundsTransferTitleFetchRequest();
        internalFundsTransferTitleFetchRequest.setAccountNumber(fromAccount);
        paramMap.put("xmlHeaderlnput", xmlHeaderInput);
        paramMap.put("internalFundsTransferRequest", internalFundsTransferRequest);
        paramMap.put("internalFundsTransferTitleFetchRequest", internalFundsTransferTitleFetchRequest);

        String x = getResponseFromPostAPI(headerMap, paramMap, url);
        try {
            ObjectMapper mapper = new ObjectMapper();
            org.ais.jcash.WsdlT24Api.dto.internalfundstransferpayment.Root r =
                    mapper.readValue(x, org.ais.jcash.WsdlT24Api.dto.internalfundstransferpayment.Root.class);
            if (r.getResponsecode() == 1) {
                InternalFundsTransferResponse internalFundsTransferResponse = new InternalFundsTransferResponse();
                internalFundsTransferResponse.setFromAccount(r.getData().getFromAccount());
                internalFundsTransferResponse.setToAccount(r.getData().getToAccount());
                internalFundsTransferResponse.setAmount(r.getData().getAmount());
                return internalFundsTransferResponse;
            }
        } catch (Exception e) {
            LOG.warn("internalFundsTranfer live call failed: {}", e.getMessage());
        }
        if (t24MockSupport.isMockFallbackOnError()) {
            return t24MockSupport.mockInternalFundsTransfer(fromAccount, toAccount, amount);
        }
        return null;
    }

    public String generateRrnNumber() {
        int length = 6;
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }
}
