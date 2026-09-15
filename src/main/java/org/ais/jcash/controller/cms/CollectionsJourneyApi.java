package org.ais.jcash.controller.cms;

import io.swagger.annotations.Api;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.workflow.service.CmsWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Api(value = "Collections Journey")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/collectionsJourney")
public class CollectionsJourneyApi extends AbstractApi {

    @Autowired
    private CmsWorkflowService workflowService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> get(HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        Map<String, Object> j = workflowService.store().getCollectionsJourney(user.getCompanyId());
        if (j == null) {
            j = seed(user.getCompanyId());
            workflowService.store().saveCollectionsJourney(user.getCompanyId(), j);
        }
        return getResponseFormat(HttpStatus.OK, "Collections journey", j);
    }

    @PostMapping(value = "/advance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> advance(HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        Map<String, Object> j = workflowService.store().getCollectionsJourney(user.getCompanyId());
        if (j == null) j = seed(user.getCompanyId());
        String stage = String.valueOf(j.get("stage"));
        if ("DEPOSITED".equals(stage)) {
            j.put("stage", "LODGED");
            j.put("lodgementRef", "LDG-" + System.currentTimeMillis() % 100000);
        } else if ("LODGED".equals(stage)) {
            j.put("stage", "LIQUIDATED");
            j.put("liquidationRef", "LIQ-" + System.currentTimeMillis() % 100000);
        } else {
            j = seed(user.getCompanyId());
        }
        j.put("updatedAt", new Date());
        workflowService.store().saveCollectionsJourney(user.getCompanyId(), j);
        return getResponseFormat(HttpStatus.OK, "Stage updated", j);
    }

    private Map<String, Object> seed(long companyId) {
        Map<String, Object> j = new LinkedHashMap<>();
        j.put("companyId", companyId);
        j.put("reference", "COL-DEMO-" + (companyId % 1000));
        j.put("stage", "DEPOSITED");
        j.put("amount", "250000");
        j.put("links", new String[]{"/onlinedeposit", "/lodegment", "/liquidation"});
        j.put("updatedAt", new Date());
        return j;
    }
}
