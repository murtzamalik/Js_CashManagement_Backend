package org.ais.jcash.controller;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.dto.CompanyResponse;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 3:49 PM
 * Project : jcash
 */


@Api(value = "JS Cash Management Api Non Financial Get Api", description = "GET NON FIN API For JS Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashNonFinGetApi extends AbstractApi {
    Logger LOG = LoggerFactory.getLogger(JsCashNonFinGetApi.class);

    @Autowired
    private JsCashNonFinService jsCashNonFinService;









}
