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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Api(value = "Tax Pay")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/taxPay")
public class TaxPayApi extends AbstractApi {

    @Autowired
    private CmsWorkflowService workflowService;

    @GetMapping(value = "/authorities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> authorities() {
        List<Map<String, String>> list = new ArrayList<>();
        list.add(item("FBR", "FBR — Federal Board of Revenue"));
        list.add(item("SRB", "SRB — Sindh Revenue Board"));
        list.add(item("PRA", "PRA — Punjab Revenue Authority"));
        return getResponseFormat(HttpStatus.OK, "Tax authorities", list);
    }

    @PostMapping(value = "/fetch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> fetch(@RequestBody Map<String, Object> body) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("psid", body.get("psid"));
        data.put("authority", body.get("authority"));
        data.put("taxpayerName", "MOCK TAXPAYER");
        data.put("amount", "45200");
        data.put("taxPeriod", java.time.YearMonth.now().toString());
        data.put("mockMode", true);
        return getResponseFormat(HttpStatus.OK, "Tax liability fetched (MOCK)", data);
    }

    @PostMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> pay(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        CmsParkPaymentRequest park = new CmsParkPaymentRequest();
        park.setProductCode("TAX");
        park.setAmount(new BigDecimal(String.valueOf(body.getOrDefault("amount", "0"))));
        park.setCustRef(String.valueOf(body.getOrDefault("custRef", "TAX-" + System.currentTimeMillis())));
        park.setDebitAccount(String.valueOf(body.getOrDefault("fromAccount", "1000000001")));
        park.setBenTitle(String.valueOf(body.getOrDefault("authority", "FBR")));
        park.setBenIban(String.valueOf(body.getOrDefault("psid", "PSID0001")));
        park.setPayloadJson(String.valueOf(body));
        CmsTxnDto txn = workflowService.park(user, park);
        return getResponseFormat(HttpStatus.OK, "Parked for authorization", txn.toMap());
    }

    private Map<String, String> item(String code, String name) {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("code", code);
        m.put("name", name);
        return m;
    }
}
