package org.ais.jcash.controller.cms;

import io.swagger.annotations.Api;
import org.ais.jcash.WsdlT24Api.service.T24MockSupport;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Api(value = "Balance Inquiry")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/balanceInquiry")
public class BalanceInquiryApi extends AbstractApi {

    @Autowired
    private T24MockSupport t24MockSupport;

    @GetMapping(value = "/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> inquire(@PathVariable String accountNumber, HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        Map<String, Object> data = t24MockSupport.mockBalanceInquiry(accountNumber);
        data.put("companyId", user.getCompanyId());
        return getResponseFormat(HttpStatus.OK,
                t24MockSupport.isMockEnabled() ? "Balance fetched (MOCK)" : "Balance fetched", data);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> inquirePost(@RequestBody Map<String, Object> body, HttpServletRequest request) {
        LoggedUserDetail user = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
        if (user == null) return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Logged User Found", null);
        String account = String.valueOf(body.getOrDefault("accountNumber", "1000000001"));
        Map<String, Object> data = t24MockSupport.mockBalanceInquiry(account);
        data.put("companyId", user.getCompanyId());
        return getResponseFormat(HttpStatus.OK, "Balance fetched (MOCK)", data);
    }
}
