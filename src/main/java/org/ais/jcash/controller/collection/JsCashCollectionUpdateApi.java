package org.ais.jcash.controller.collection;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashFinService;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.*;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:01 PM
 * Project : jcash
 */


@Api(value = "JS Cash Management Collection Update Api", description = "UPDATE COllection API For JS Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/fin/collection")
public class JsCashCollectionUpdateApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashCollectionUpdateApi.class);

    @Autowired
    private JsCashFinService jsCashFinService;


    @RequestMapping(value = "/updateLodgment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateLodgment(@Valid @RequestBody UpdateLodgmentRequest updateLodgmentRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCollectionUpdateApi \n METHOD == updateLodgment(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            List<Integer> errors = new ArrayList<>();
            if (loggedUserDetail != null) {
                for (Integer transHeadId : updateLodgmentRequest.getTransHeadIds()) {
//                    int update = jsCashFinService.takeActionOnLodgment(transHeadId, updateLodgmentRequest.getLodgementReason(), updateLodgmentRequest.getLodgementStatus(), loggedUserDetail.getUserId());

//                    if (update < 1) {
//                        errors.add(transHeadId);
//                    }
                }
                if (errors.size() == 0) {
                    LOG.info("\n EXITING THIS METHOD == updateLodgment(); OF CLASS = JsCashCollectionUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Action Taken Successfully", null);
                } else {
                    LOG.info("\n EXITING THIS METHOD == updateLodgment(); OF CLASS = JsCashCollectionUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error Occured", errors);
                }


            } else {
                LOG.info("\n EXITING THIS METHOD == updateLodgment(); OF CLASS = JsCashCollectionUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error Occured", "");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashCollectionUpdateApi \n METHOD == updateLodgment();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateLodgment(); OF CLASS = JsCashCollectionUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCollectionUpdateApi \n METHOD == updateLodgment();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateLodgment(); OF CLASS = JsCashCollectionUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }

    @RequestMapping(value = "/updateLiquidation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateLiquidation(@Valid @RequestBody UpdateLodgmentRequest updateLodgmentRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCollectionUpdateApi \n METHOD == updateLiquidation(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            List<Integer> errors = new ArrayList<>();
            if (loggedUserDetail != null) {
                for (Integer transHeadId : updateLodgmentRequest.getTransHeadIds()) {
//                    int update = jsCashFinService.takeActionOnLiquidation(transHeadId, updateLodgmentRequest.getLiquidationReason(), updateLodgmentRequest.getLiquidationStatus(), loggedUserDetail.getUserId());
//
//                    if (update < 1) {
//                        errors.add(transHeadId);
//                    }
                }
                if (errors.size() == 0) {
                    LOG.info("\n EXITING THIS METHOD == updateLiquidation(); OF CLASS = JsCashCollectionUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Action Taken Successfully", null);
                } else {
                    LOG.info("\n EXITING THIS METHOD == updateLiquidation(); OF CLASS = JsCashCollectionUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error Occured", errors);
                }


            } else {
                LOG.info("\n EXITING THIS METHOD == updateLiquidation(); OF CLASS = JsCashCollectionUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error Occured", "");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashCollectionUpdateApi \n METHOD == updateLiquidation();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateLiquidation(); OF CLASS = JsCashCollectionUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCollectionUpdateApi \n METHOD == updateLiquidation();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateLiquidation(); OF CLASS = JsCashCollectionUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }

}
