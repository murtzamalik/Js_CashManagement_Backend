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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Api(value = "Salary Pay")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/salaryPay")
public class SalaryPayApi extends AbstractApi {

    @Autowired
    private CmsWorkflowService workflowService;

    @GetMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> companies() {
        List<Map<String, String>> list = new ArrayList<>();
        list.add(co("PAYROLL", "Corporate Payroll"));
        list.add(co("VENDOR", "Vendor Salary Disbursement"));
        return getResponseFormat(HttpStatus.OK, "Salary companies", list);
    }

    @PostMapping(value = "/fetch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> fetch(@RequestBody Map<String, Object> body) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("employeeId", body.get("employeeId"));
        data.put("employeeName", "MOCK EMPLOYEE " + String.valueOf(body.getOrDefault("employeeId", "0000")).replaceAll(".*(\\d{4})$", "$1"));
        data.put("amount", "85000");
        data.put("period", java.time.YearMonth.now().toString());
        data.put("mockMode", true);
        return getResponseFormat(HttpStatus.OK, "Salary fetched (MOCK)", data);
    }

    @PostMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> pay(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        CmsParkPaymentRequest park = new CmsParkPaymentRequest();
        park.setProductCode("SALARY");
        park.setAmount(new BigDecimal(String.valueOf(body.getOrDefault("amount", "0"))));
        park.setCustRef(String.valueOf(body.getOrDefault("custRef", "SAL-" + System.currentTimeMillis())));
        park.setDebitAccount(String.valueOf(body.getOrDefault("fromAccount", "1000000001")));
        park.setBenTitle(String.valueOf(body.getOrDefault("employeeName", "EMPLOYEE")));
        park.setBenIban(String.valueOf(body.getOrDefault("employeeId", "EMP0001")));
        park.setPayloadJson(String.valueOf(body));
        CmsTxnDto txn = workflowService.park(user, park);
        Map<String, Object> data = txn.toMap();
        data.put("message", "Salary payment parked for authorization");
        data.put("notificationToast", data.get("nextApproverEmail"));
        return getResponseFormat(HttpStatus.OK, "Parked for authorization", data);
    }

    private Map<String, String> co(String code, String name) {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("code", code);
        m.put("name", name);
        return m;
    }
}
