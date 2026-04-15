package org.ais.jcash.controller;


import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashLovService;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.dto.*;
import org.ais.jcash.model.*;
import org.ais.jcash.util.JWTSecurity;
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
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:01 PM
 * Project : jcash
 */


@Api(value = "Cash Management Api Lov Get Api", description = "Get Lov Api For Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/lov")
public class LovGetApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(LovGetApi.class);

    @Autowired
    private JsCashLovService jsCashLovService;


    @RequestMapping(value = "/lovRoleRights", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovRoleRights(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovRoleRights(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovRoleRights();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovRoleRights(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovRoleRights(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovRoleRights();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovRoleRights(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovMenu", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovMenu(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovMenu(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovMenu();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovMenu(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovMenu(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovMenu();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovMenu(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovArea", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovArea(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovArea(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovArea();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovArea(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovArea(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovArea();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovArea(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovProduct", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovProduct(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovProduct(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovProduct();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovProduct(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovProduct(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovProduct();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovProduct(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovModule", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovModule(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovModule(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovModule();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovModule(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovModule(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovModule();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovModule(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovRole", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovRole(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovRole(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovRole();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovRole(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovRole(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovRole();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovRole(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovCompanyUsers/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovCompanyUsers(@PathVariable String companyId,HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovCompanyUsers(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovCompanyUsers(Long.valueOf(companyId));

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovCompanyUsers(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovCompanyUsers(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovCompanyUsers();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovCompanyUsers(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovCompanyProducts/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovCompanyProducts(@PathVariable String companyId,HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovCompanyProducts(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovCompanyProducts(Long.valueOf(companyId));

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovCompanyProducts(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovCompanyProducts(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovCompanyProducts();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovCompanyProducts(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovCompanyAcounts/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovCompanyAcounts(@PathVariable String companyId,HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovCompanyAcounts(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovCompanyAcounts(Long.valueOf(companyId));

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovCompanyAcounts(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovCompanyAcounts(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovCompanyAcounts();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovCompanyAcounts(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovBank", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovBank(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovBank(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovBank();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovModule(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovModule(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovModule();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovModule(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovBaseLocation", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovBaseLocation(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovBaseLocation(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovBaseLocation();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovBaseLocation(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovBaseLocation(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovBaseLocation();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovBaseLocation(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovBranch", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovBranch(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovBranch(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovBranch();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovBranch(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovBranch(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovBranch();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovBranch(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovCity", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovCity(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovCity(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovCity();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovCity(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovCity(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovCity();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovCity(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovCountry", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovCountry(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovCountry(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovCountry();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovCountry(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovCountry(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovCountry();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovCountry(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovRegion", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovRegion(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovRegion(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovRegion();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovRegion(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovRegion(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovRegion();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovRegion(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovUserType", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovUserType(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovUserType(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovUserType();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovUserType(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovUserType(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovUserType();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovUserType(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovUserLevel", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovUserLevel(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovUserLevel(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovUserLevel();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovUserLevel(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovUserLevel(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovUserLevel();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovUserLevel(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovCompanyParserLink/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovCompanyParserLink(@PathVariable String companyId,HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovCompanyParserLink(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovCompanyParserLink(companyId);

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovCompanyParserLink(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovCompanyParserLink(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovPraserLink();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovPraserLink(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovCompanyPraser/{productId}/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovCompanyPraser(@PathVariable String productId,@PathVariable String companyId,HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovCompanyPraser(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovCompanyPraser(companyId,productId);

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovCompanyPraser(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovCompanyPraser(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovCompanyPraser();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovCompanyPraser(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovAuthCompanyProduct/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovAuthProduct(@PathVariable String companyId,HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovAuthProduct(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovAuthProduct(Long.valueOf(companyId));

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovAuthProuduct(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovAuthProuduct(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovAuthProduct();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovAuthProduct(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovAuthCompany", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovAuthCompany(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovAuthCompany(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovAuthCompany();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovAuthCompany(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovAuthCompany(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovAuthCompany();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovAuthCompany(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovProductCollection", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovProductCollection(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovProductCollection(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovProductCollection();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovProductCollection(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovProductCollection(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovProductCollection();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovProductCollection(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovCompany", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovCompany(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovAuthCompany(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovCompany();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovCompany(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovAuthCompany(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovAuthCompany();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovAuthCompany(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovAuthCompanyAcounts/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovAuthCompanyAcounts(@PathVariable String companyId,HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovCompanyAcounts(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovAuthCompanyAcounts(Long.valueOf(companyId));

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovCompanyAcounts(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovCompanyAcounts(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovCompanyAcounts();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovCompanyAcounts(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovAuthCompanyProducts", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovAuthCompanyProduct(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashLovGetApi \n METHOD == lovAuthCompanyProduct(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if(loggedUserDetail != null) {
                List<CustomizedLovAuthCompanyProduct> lovResponses = jsCashLovService.lovAuthCompanyProduct(loggedUserDetail.getUserId());

                if (lovResponses != null && lovResponses.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == lovAuthCompanyProduct(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
                } else {
                    LOG.info("\n EXITING THIS METHOD == lovAuthCompanyProduct(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "No Record Found", null);
                }
            }else {
                LOG.info("\n EXITING THIS METHOD == checkOtp(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email", "No User Found Against This Email");
            }



        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLovGetApi \n METHOD == lovAuthCompany();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovAuthCompany(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/lovPaymentMode", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> lovPaymentMode(HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == LovGetApi \n METHOD == lovPaymentMode(); ");

            List<LovResponse> lovResponses = jsCashLovService.lovPaymentMode();

            if(lovResponses != null && lovResponses.size() > 0){
                LOG.info("\n EXITING THIS METHOD == lovPaymentMode(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", lovResponses);
            }else{
                LOG.info("\n EXITING THIS METHOD == lovPaymentMode(); \n\n\n");
                return getResponseFormat(HttpStatus.OK,  "No Record Found",null);
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == LovGetApi \n METHOD == lovPaymentMode();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lovPaymentMode(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

}
