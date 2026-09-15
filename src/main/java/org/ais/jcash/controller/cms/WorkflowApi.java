package org.ais.jcash.controller.cms;

import io.swagger.annotations.Api;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.workflow.dto.CmsParkPaymentRequest;
import org.ais.jcash.workflow.dto.CmsTxnDto;
import org.ais.jcash.workflow.dto.WorkflowActionRequest;
import org.ais.jcash.workflow.service.CmsWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value = "CMS Workflow")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/workflow")
public class WorkflowApi extends AbstractApi {

    @Autowired
    private CmsWorkflowService workflowService;

    @PostMapping(value = "/park", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> park(@RequestBody CmsParkPaymentRequest body, HttpServletRequest request) {
        try {
            LoggedUserDetail user = requireUser(request);
            CmsTxnDto txn = workflowService.park(user, body);
            return getResponseFormat(HttpStatus.OK, "Parked for authorization", enrich(txn));
        } catch (Exception e) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage(), null);
        }
    }

    @PostMapping(value = "/approve", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> approve(@RequestBody WorkflowActionRequest body, HttpServletRequest request) {
        try {
            LoggedUserDetail user = requireUser(request);
            CmsTxnDto txn = workflowService.approve(user, body);
            return getResponseFormat(HttpStatus.OK, "Approval recorded", enrich(txn));
        } catch (Exception e) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage(), null);
        }
    }

    @PostMapping(value = "/release", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> release(@RequestBody WorkflowActionRequest body, HttpServletRequest request) {
        try {
            LoggedUserDetail user = requireUser(request);
            CmsTxnDto txn = workflowService.release(user, body);
            return getResponseFormat(HttpStatus.OK, "Release processed", enrich(txn));
        } catch (Exception e) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage(), null);
        }
    }

    @PostMapping(value = "/stop", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> stop(@RequestBody WorkflowActionRequest body, HttpServletRequest request) {
        try {
            LoggedUserDetail user = requireUser(request);
            CmsTxnDto txn = workflowService.stop(user, body);
            return getResponseFormat(HttpStatus.OK, "Payment stopped", enrich(txn));
        } catch (Exception e) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage(), null);
        }
    }

    @PostMapping(value = "/processScheduled", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> processScheduled(HttpServletRequest request) {
        try {
            LoggedUserDetail user = requireUser(request);
            List<CmsTxnDto> paid = workflowService.processDueScheduled(user);
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("processedCount", paid.size());
            data.put("rows", paid.stream().map(this::enrich).collect(Collectors.toList()));
            return getResponseFormat(HttpStatus.OK, "Scheduler run complete", data);
        } catch (Exception e) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage(), null);
        }
    }

    @GetMapping(value = "/queues", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> queues(HttpServletRequest request) {
        try {
            LoggedUserDetail user = requireUser(request);
            return getResponseFormat(HttpStatus.OK, "Queues", workflowService.queues(user));
        } catch (Exception e) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage(), null);
        }
    }

    @GetMapping(value = "/{txnId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> get(@PathVariable long txnId, HttpServletRequest request) {
        try {
            LoggedUserDetail user = requireUser(request);
            return getResponseFormat(HttpStatus.OK, "Transaction", enrich(workflowService.get(user, txnId)));
        } catch (Exception e) {
            return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage(), null);
        }
    }

    private LoggedUserDetail requireUser(HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) throw new IllegalStateException("No Logged User Found");
        return user;
    }

    private Map<String, Object> enrich(CmsTxnDto txn) {
        Map<String, Object> m = txn.toMap();
        m.put("notificationToast", "Notification sent to " + txn.getNextApproverEmail() + " / " + txn.getNextApproverMobile());
        return m;
    }
}
