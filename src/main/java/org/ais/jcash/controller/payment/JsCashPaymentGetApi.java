package org.ais.jcash.controller.payment;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.controller.JsCashNonFinGetApi;
import org.ais.jcash.dto.CompanyResponse;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.dto.LovResponse;
import org.ais.jcash.dto.RecCocCashRequest;
import org.ais.jcash.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@Api(value = "Cash Management Api Non Financial Get Api", description = "GET NON FIN API For Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashPaymentGetApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashNonFinGetApi.class);

    @Autowired
    private JsCashNonFinService jsCashNonFinService;


}
