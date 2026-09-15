package org.ais.jcash.controller.cms;

import io.swagger.annotations.Api;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.workflow.CmsTxnStatus;
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

@Api(value = "RTGS Stub")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/rtgs")
public class RtgsApi extends AbstractApi {

    @Autowired
    private CmsWorkflowService workflowService;

    @PostMapping(value = "/submit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> submit(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        BigDecimal amount = new BigDecimal(String.valueOf(body.getOrDefault("amount", "0")));
        CmsParkPaymentRequest park = new CmsParkPaymentRequest();
        park.setProductCode("RTGS");
        park.setAmount(amount);
        park.setCustRef(String.valueOf(body.getOrDefault("custRef", "RTGS-" + System.currentTimeMillis())));
        park.setDebitAccount(String.valueOf(body.getOrDefault("fromAccount", "1000000001")));
        park.setBenBankName(String.valueOf(body.getOrDefault("bankName", "RTGS BANK")));
        park.setBenIban(String.valueOf(body.getOrDefault("iban", "")));
        park.setBenTitle(String.valueOf(body.getOrDefault("accountTitle", "")));
        park.setPayloadJson(String.valueOf(body.getOrDefault("purpose", "")));
        CmsTxnDto txn = workflowService.park(user, park);
        // RTGS special statuses
        if (amount.compareTo(new BigDecimal("100000")) > 0) {
            txn.setStatus(CmsTxnStatus.AWAITING_SIGNATURE);
        } else {
            txn.setStatus(CmsTxnStatus.INSTRUCTION_QUEUED);
        }
        txn.setApprovalsRequired(amount.compareTo(new BigDecimal("100000")) > 0 ? 2 : 1);
        workflowService.store().saveTxn(txn);
        workflowService.store().addAudit(txn.getTxnId(), txn.getCompanyId(), CmsTxnStatus.PENDING_AUTH, txn.getStatus(),
                "RTGS_SUBMIT", user.getUserId(), "RTGS instruction staged");
        return getResponseFormat(HttpStatus.OK, "RTGS instruction accepted", txn.toMap());
    }
}
