package org.ais.jcash.controller.cms;

import io.swagger.annotations.Api;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.workflow.service.CmsWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Api(value = "CMS Reports")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/reports")
public class ReportsApi extends AbstractApi {

    @Autowired
    private CmsWorkflowService workflowService;

    @GetMapping(value = "/catalogue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> catalogue(HttpServletRequest request) {
        if (getLoggedUserDataFromHeaderToken(request.getHeader("Authorization")) == null) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        }
        List<Map<String, String>> cat = new ArrayList<>();
        cat.add(item("TXN_REGISTER", "Transaction register"));
        cat.add(item("TXN_STATUS", "Payment status"));
        cat.add(item("PENDING_AUTH", "Pending authorization"));
        cat.add(item("PAID_RELEASED", "Released / paid"));
        cat.add(item("FAILED_STOPPED", "Failed / stopped"));
        cat.add(item("SCHEDULED", "Scheduled payments"));
        cat.add(item("BENEFICIARY_LIST", "Beneficiary list"));
        cat.add(item("BILL_REGISTER", "Bill payment register"));
        cat.add(item("IBFT_REGISTER", "IBFT register"));
        cat.add(item("AUDIT_TRAIL", "Maker–checker audit trail"));
        cat.add(item("MATRIX_USAGE", "Auth matrix bands"));
        cat.add(item("BALANCE_SNAPSHOT", "Balance snapshot"));
        cat.add(item("COLLECTIONS_STATUS", "Collections journey status"));
        cat.add(item("SALARY_REGISTER", "Salary payments"));
        cat.add(item("TAX_REGISTER", "Tax payments"));
        return getResponseFormat(HttpStatus.OK, "Report catalogue", cat);
    }

    @PostMapping(value = "/run", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> run(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        String reportCode = body.get("reportCode") == null ? "TXN_REGISTER" : String.valueOf(body.get("reportCode"));
        String status = body.get("status") == null ? null : String.valueOf(body.get("status"));
        String product = body.get("productCode") == null ? null : String.valueOf(body.get("productCode"));
        if ("BALANCE_SNAPSHOT".equalsIgnoreCase(reportCode)) {
            List<Map<String, Object>> rows = new ArrayList<>();
            Map<String, Object> snap = new LinkedHashMap<>();
            snap.put("account", body.getOrDefault("account", "1000000001"));
            snap.put("availableBalance", "1250000.00");
            snap.put("ledgerBalance", "1254500.00");
            snap.put("currency", "PKR");
            snap.put("asOf", new java.util.Date());
            snap.put("mockMode", true);
            rows.add(snap);
            return getResponseFormat(HttpStatus.OK, "Report preview", rows);
        }
        List<Map<String, Object>> rows = workflowService.reportRows(user, reportCode, status, product);
        return getResponseFormat(HttpStatus.OK, "Report preview", rows);
    }

    @PostMapping(value = "/export", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]> export(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized".getBytes(StandardCharsets.UTF_8));
        }
        String reportCode = body.get("reportCode") == null ? "TXN_REGISTER" : String.valueOf(body.get("reportCode"));
        String status = body.get("status") == null ? null : String.valueOf(body.get("status"));
        String product = body.get("productCode") == null ? null : String.valueOf(body.get("productCode"));
        List<Map<String, Object>> rows;
        if ("BALANCE_SNAPSHOT".equalsIgnoreCase(reportCode)) {
            rows = new ArrayList<>();
            Map<String, Object> snap = new LinkedHashMap<>();
            snap.put("account", "1000000001");
            snap.put("availableBalance", "1250000.00");
            snap.put("ledgerBalance", "1254500.00");
            snap.put("currency", "PKR");
            rows.add(snap);
        } else {
            rows = workflowService.reportRows(user, reportCode, status, product);
        }
        String csv = workflowService.toCsv(rows);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + reportCode + ".csv");
        return new ResponseEntity<>(csv.getBytes(StandardCharsets.UTF_8), headers, HttpStatus.OK);
    }

    private Map<String, String> item(String code, String name) {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("code", code);
        m.put("name", name);
        return m;
    }
}
