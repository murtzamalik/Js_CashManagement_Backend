package org.ais.jcash.controller.cms;

import io.swagger.annotations.Api;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.workflow.dto.CmsParkPaymentRequest;
import org.ais.jcash.workflow.dto.CmsTxnDto;
import org.ais.jcash.workflow.service.CmsWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Api(value = "Pay Order / Cheque Stub")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/payOrder")
public class PayOrderApi extends AbstractApi {

    @Autowired
    private CmsWorkflowService workflowService;

    @PostMapping(value = "/submit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> submit(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        String instrumentType = String.valueOf(body.getOrDefault("instrumentType", "PO")).toUpperCase();
        CmsParkPaymentRequest park = new CmsParkPaymentRequest();
        park.setProductCode("CHQ".equals(instrumentType) ? "CHQ" : "PO");
        park.setAmount(new BigDecimal(String.valueOf(body.getOrDefault("amount", "0"))));
        park.setCustRef(String.valueOf(body.getOrDefault("custRef", "PO-" + System.currentTimeMillis())));
        park.setDebitAccount(String.valueOf(body.getOrDefault("fromAccount", "1000000001")));
        park.setBenTitle(String.valueOf(body.getOrDefault("payeeName", "PAYEE")));
        park.setBenIban(String.valueOf(body.getOrDefault("payeeRef", "")));
        park.setPayloadJson(String.valueOf(body));
        CmsTxnDto txn = workflowService.park(user, park);
        return getResponseFormat(HttpStatus.OK, "Pay order parked for authorization", txn.toMap());
    }
}
