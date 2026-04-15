package org.ais.jcash.controller.usermngt;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Api(value = "Cash Management GET Api", description = "GET API For Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashUserMngtGetApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashLoginPostApi.class);

    @Autowired
    private JsCashNonFinService jsCashNonFinService;


    @RequestMapping(value = "/getAllModule", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllModule(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getAllModule(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<TblModule> tblModules;

                tblModules = jsCashNonFinService.getAllModule();

                if (tblModules != null && tblModules.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getAllModule(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblModules);
                } else {
                    LOG.info("\n EXITING THIS METHOD == getAllModule(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashUserMngtGetApi(); OF CLASS = getAllModule \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllModule();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAllModule(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllModule();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getAllModule(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getAllMenuByModuleId/{moduleId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllMenuById(@PathVariable long moduleId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getAllMenuByModuleId(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblMenu> tblMenus = jsCashNonFinService.getAllMenuByModuleId(moduleId);

                if (tblMenus != null && tblMenus.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getAllMenuByModuleId(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblMenus);
                } else {
                    LOG.info("\n EXITING THIS METHOD == getAllMenuByModuleId(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashUserMngtGetApi(); OF CLASS = getAllMenuByModuleId() \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllMenuByModuleId();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAllMenuByModuleId(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllMenuByModuleId();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getStackTrace().toString());
            LOG.info("\n EXITING THIS METHOD == getAllMenuByModuleId(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getAllRole", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllRole(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getAllRole(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblRole> tblRoles = jsCashNonFinService.getAllRolebyStatus("Y");
                List<TblMenu> tblMenus = new ArrayList<>();

                if (tblRoles != null && tblRoles.size() > 0) {

                    LOG.info("\n EXITING THIS METHOD == getAllRole(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblRoles);
                } else {

                    LOG.info("\n EXITING THIS METHOD == accountLogin(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == getAllMenuByModuleId(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllRole();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAllRole(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllRole();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getAllRole(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getMenuByRole/{roleId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getMenuByRole(@PathVariable String roleId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getMenuByRole(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblRoleRight> tblRoleRights = jsCashNonFinService.getMenuByRoleId(roleId);
                List<TblMenu> tblMenus = new ArrayList<>();

                if (tblRoleRights != null && tblRoleRights.size() > 0) {

                    for (TblRoleRight tblRoleRight : tblRoleRights) {
                        tblMenus.add(tblRoleRight.getTblMenu());
                    }
                    LOG.info("\n EXITING THIS METHOD == getMenuByRole(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblMenus);

                } else {

                    LOG.info("\n EXITING THIS METHOD == getMenuByRole(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashUserMngtGetApi; OF CLASS = getMenuByRole() \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getMenuByRole();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getMenuByRole(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getMenuByRole();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getMenuByRole(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getRoleRights/{roleId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getRoleRights(@PathVariable String roleId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getRoleRights(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblRoleRight> tblRoleRights = jsCashNonFinService.getAllRoleRightsbyRoleId(roleId);

                if (tblRoleRights != null && tblRoleRights.size() > 0) {

                    LOG.info("\n EXITING THIS METHOD == getRoleRights(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblRoleRights);

                } else {

                    LOG.info("\n EXITING THIS METHOD == getRoleRights(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashUserMngtGetApi(); OF CLASS = getRoleRights \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == getRoleRights();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getRoleRights(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getRoleRights();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getRoleRights(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllProduct( HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getAllProduct(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblProduct> tblProducts = jsCashNonFinService.getAllProduct();

                if (tblProducts != null && tblProducts.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getAllProduct(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblProducts);
                } else {
                    LOG.info("\n EXITING THIS METHOD == getAllProduct(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashUserMngtGetApi(); OF CLASS = getAllProduct(); \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllProduct();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAllProduct(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllProduct();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getAllProduct(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getAllParsers", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllParsers(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getAllParsers(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblParserHead>  tblParserHeads = jsCashNonFinService.getAllParsers();

                if (tblParserHeads != null) {
                    LOG.info("\n EXITING THIS METHOD == getAllParsers(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblParserHeads);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == getAllParsers(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashUserMngtGetApi(); OF CLASS = getAllParsers(); \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllParsers();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAllParsers(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllParsers();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getAllParsers(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getParser/{parserHeadId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getParser(@PathVariable String parserHeadId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getParser(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblParserHead tblParserHead = jsCashNonFinService.getParserById(parserHeadId);

                if (tblParserHead != null) {
                    LOG.info("\n EXITING THIS METHOD == getParser(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblParserHead);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == getParser(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashUserMngtGetApi; OF CLASS = getParser(); \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getParser();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getParser(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getParser();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getParser(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }


    @RequestMapping(value = "/getAllCountries", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllCountries(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getAllCountries(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<LkpCountry> lkpCountries;

                lkpCountries = jsCashNonFinService.getAllCountries();

                if (lkpCountries != null && lkpCountries.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == lkpCountries(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", lkpCountries);
                } else {
                    LOG.info("\n EXITING THIS METHOD == lkpCountries(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashUserMngtGetApi(); OF CLASS = lkpCountries \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == lkpCountries();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == lkpCountries(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == lkpCountries();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lkpCountries(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getAllProvinces", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllProvinces(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getAllCountries(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<LkpProvince> lkpProvinces;

                lkpProvinces = jsCashNonFinService.getAllProvinces();

                if (lkpProvinces != null && lkpProvinces.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == lkpCountries(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", lkpProvinces);
                } else {
                    LOG.info("\n EXITING THIS METHOD == lkpProvinces(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashUserMngtGetApi(); OF CLASS = lkpProvinces \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == lkpProvinces();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == lkpProvinces(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == lkpProvinces();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == lkpProvinces(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getAllCities", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllCities(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtGetApi \n METHOD == getAllCities(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<LkpCity> lkpCities;

                lkpCities = jsCashNonFinService.getAllCities();

                if (lkpCities != null && lkpCities.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getAllCities(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", lkpCities);
                } else {
                    LOG.info("\n EXITING THIS METHOD == getAllCities(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashUserMngtGetApi(); OF CLASS = getAllCities \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllCities();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAllCities(); OF CLASS = JsCashUserMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtGetApi \n METHOD == getAllCities();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getAllCities(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }






}
