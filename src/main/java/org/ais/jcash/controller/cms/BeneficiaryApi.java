package org.ais.jcash.controller.cms;

import io.swagger.annotations.Api;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.workflow.service.CmsWorkflowService;
import org.ais.jcash.workflow.service.InMemoryCmsStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Api(value = "CMS Beneficiaries")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/beneficiaries")
public class BeneficiaryApi extends AbstractApi {

    @Autowired
    private CmsWorkflowService workflowService;

    private InMemoryCmsStore store() {
        return workflowService.store();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> list(HttpServletRequest request) {
        LoggedUserDetail user = user(request);
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        return getResponseFormat(HttpStatus.OK, "Beneficiaries", store().findBeneficiaries(user.getCompanyId()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> create(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        LoggedUserDetail user = user(request);
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        String iban = normalize(String.valueOf(body.getOrDefault("iban", "")));
        if (iban.length() < 15) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Valid IBAN required", null);
        Map<String, Object> existing = store().findBeneficiaryByIban(user.getCompanyId(), iban);
        if (existing != null) return getResponseFormat(HttpStatus.OK, "Already exists", existing);
        Map<String, Object> b = new LinkedHashMap<>();
        b.put("beneficiaryId", store().nextBeneId());
        b.put("companyId", user.getCompanyId());
        b.put("bankName", body.get("bankName"));
        b.put("bankImd", body.get("bankImd"));
        b.put("iban", iban);
        b.put("accountTitle", body.get("accountTitle"));
        b.put("phone", body.get("phone"));
        b.put("locked", "N");
        b.put("active", "Y");
        b.put("createUser", user.getUserId());
        b.put("createDate", new Date());
        store().saveBeneficiary(b);
        return getResponseFormat(HttpStatus.OK, "Beneficiary saved", b);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> update(@PathVariable long id, @RequestBody Map<String, Object> body, HttpServletRequest request) {
        LoggedUserDetail user = user(request);
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        Map<String, Object> b = store().findBeneficiary(id);
        if (b == null || ((Number) b.get("companyId")).longValue() != user.getCompanyId()) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Not found", null);
        }
        if ("Y".equalsIgnoreCase(String.valueOf(b.get("locked")))) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Beneficiary is locked company-wide", null);
        }
        if (body.containsKey("bankName")) b.put("bankName", body.get("bankName"));
        if (body.containsKey("accountTitle")) b.put("accountTitle", body.get("accountTitle"));
        if (body.containsKey("phone")) b.put("phone", body.get("phone"));
        if (body.containsKey("iban")) b.put("iban", normalize(String.valueOf(body.get("iban"))));
        b.put("lastUpdateDate", new Date());
        store().saveBeneficiary(b);
        return getResponseFormat(HttpStatus.OK, "Updated", b);
    }

    @PostMapping(value = "/{id}/unlock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> unlock(@PathVariable long id, HttpServletRequest request) {
        LoggedUserDetail user = user(request);
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        Map<String, Object> b = store().findBeneficiary(id);
        if (b == null || ((Number) b.get("companyId")).longValue() != user.getCompanyId()) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Not found", null);
        }
        b.put("locked", "N");
        b.put("lastUpdateDate", new Date());
        store().saveBeneficiary(b);
        return getResponseFormat(HttpStatus.OK, "Unlocked", b);
    }

    private LoggedUserDetail user(HttpServletRequest request) {
        return getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
    }

    private String normalize(String iban) {
        return iban == null ? "" : iban.replaceAll("\\s+", "").toUpperCase(Locale.ROOT);
    }
}
