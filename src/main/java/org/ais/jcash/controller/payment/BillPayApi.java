package org.ais.jcash.controller.payment;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.billpay.BillPayCatalogueService;
import org.ais.jcash.WsdlT24Api.dto.UtltyBillInquiry;
import org.ais.jcash.WsdlT24Api.dto.XmlHeaderInput;
import org.ais.jcash.WsdlT24Api.model.UtilityBillInquiryRequest;
import org.ais.jcash.WsdlT24Api.model.UtilityBillInquiryResponse;
import org.ais.jcash.WsdlT24Api.service.T24MockSupport;
import org.ais.jcash.WsdlT24Api.service.WsdlT24IServiceImpl;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.dto.billpay.BillPayCategoryDto;
import org.ais.jcash.dto.billpay.BillPayCompanyDto;
import org.ais.jcash.dto.billpay.BillPayFetchRequest;
import org.ais.jcash.dto.billpay.BillPayPayRequest;
import org.ais.jcash.workflow.dto.CmsParkPaymentRequest;
import org.ais.jcash.workflow.dto.CmsTxnDto;
import org.ais.jcash.workflow.service.CmsWorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Api(value = "Bill Pay APIs", description = "Category → Company → Fetch → Pay")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/billPay")
public class BillPayApi extends AbstractApi {

    private static final Logger LOG = LoggerFactory.getLogger(BillPayApi.class);

    @Autowired
    private BillPayCatalogueService billPayCatalogueService;

    @Autowired
    private T24MockSupport t24MockSupport;

    @Autowired
    private WsdlT24IServiceImpl wsdlT24IService;

    @Autowired
    private CmsWorkflowService cmsWorkflowService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> categories() {
        List<BillPayCategoryDto> list = billPayCatalogueService.categories();
        return getResponseFormat(HttpStatus.OK, "Bill pay categories", list);
    }

    @RequestMapping(value = "/companies/{categoryCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> companies(@PathVariable String categoryCode) {
        List<BillPayCompanyDto> list = billPayCatalogueService.companies(categoryCode);
        if (list == null || list.isEmpty()) {
            return getResponseFormat(HttpStatus.OK, "No companies for category", null);
        }
        return getResponseFormat(HttpStatus.OK, "Bill pay companies", list);
    }

    @RequestMapping(value = "/fetchBill", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> fetchBill(@Valid @RequestBody BillPayFetchRequest request, HttpServletRequest httpRequest) {
        LOG.info("BillPay fetchBill company={} consumer={}", request.getCompanyCode(), request.getConsumerNumber());
        try {
            LoggedUserDetail user = getLoggedUserDataFromHeaderToken(httpRequest.getHeader("Authorization"));
            if (user == null) {
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
            }
            if (billPayCatalogueService.findCompany(request.getCompanyCode()) == null) {
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Unknown biller company", null);
            }

            UtilityBillInquiryResponse bill;
            if (t24MockSupport.isMockEnabled()) {
                bill = billPayCatalogueService.fetchBill(request);
            } else {
                UtltyBillInquiry inquiry = new UtltyBillInquiry();
                XmlHeaderInput header = new XmlHeaderInput();
                UtilityBillInquiryRequest body = new UtilityBillInquiryRequest();
                body.setFromAccount(request.getFromAccount());
                body.setUtilityCompanyCode(request.getCompanyCode());
                body.setUtilityConsumerNumber(request.getConsumerNumber());
                inquiry.setXmlHeaderInput(header);
                inquiry.setUtilityBillInquiryRequest(body);
                bill = wsdlT24IService.utilitybillinquiry(inquiry);
                if (bill == null && t24MockSupport.isMockFallbackOnError()) {
                    bill = billPayCatalogueService.fetchBill(request);
                }
            }

            if (bill == null || bill.getTotalAmountPayableWithinDueDate() == null) {
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Unable to fetch bill", null);
            }
            return getResponseFormat(HttpStatus.OK,
                    t24MockSupport.isMockEnabled() ? "Bill fetched (MOCK)" : "Bill fetched successfully",
                    billPayCatalogueService.enrichFetch(bill, request));
        } catch (Exception e) {
            LOG.error("fetchBill error: {}", e.getLocalizedMessage());
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }

    @RequestMapping(value = "/payBill", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> payBill(@Valid @RequestBody BillPayPayRequest request, HttpServletRequest httpRequest) {
        LOG.info("BillPay payBill company={} amount={}", request.getCompanyCode(), request.getAmount());
        try {
            LoggedUserDetail user = getLoggedUserDataFromHeaderToken(httpRequest.getHeader("Authorization"));
            if (user == null) {
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
            }
            if (request.getSecurityDeviceCode() == null || request.getSecurityDeviceCode().trim().length() < 7) {
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Security device code required", null);
            }
            // Park into CMS workflow instead of immediate pay
            CmsParkPaymentRequest park = new CmsParkPaymentRequest();
            park.setProductCode("BILLPAY");
            try {
                park.setAmount(new BigDecimal(String.valueOf(request.getAmount() == null ? "0" : request.getAmount())));
            } catch (Exception ex) {
                park.setAmount(BigDecimal.ZERO);
            }
            park.setCustRef(request.getCustRef() != null ? request.getCustRef() : "BILL-" + System.currentTimeMillis());
            park.setDebitAccount(request.getFromAccount());
            park.setBenTitle(request.getCompanyName() != null ? request.getCompanyName() : request.getCompanyCode());
            park.setBenIban(request.getConsumerNumber());
            park.setPayloadJson("{\"category\":\"" + request.getCategoryCode() + "\",\"company\":\"" + request.getCompanyCode() + "\"}");
            CmsTxnDto txn = cmsWorkflowService.park(user, park);
            Map<String, Object> result = new LinkedHashMap<>(txn.toMap());
            result.put("status", "PENDING_AUTH");
            result.put("message", "Bill payment parked for authorization (MOCK)");
            result.put("rrn", "P" + txn.getTxnId());
            result.put("companyName", request.getCompanyName());
            result.put("amount", request.getAmount());
            result.put("mockMode", true);
            result.put("notificationToast", "Notification sent to " + txn.getNextApproverEmail());
            return getResponseFormat(HttpStatus.OK, String.valueOf(result.get("message")), result);
        } catch (Exception e) {
            LOG.error("payBill error: {}", e.getLocalizedMessage());
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }
}
