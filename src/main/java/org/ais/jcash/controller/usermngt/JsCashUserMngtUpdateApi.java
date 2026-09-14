package org.ais.jcash.controller.usermngt;


import io.swagger.annotations.Api;
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
import java.util.HashMap;
import java.util.List;


@Api(value = "Cash Management Api Login Post Api", description = "POST LOGIN  API For Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashUserMngtUpdateApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashUserMngtUpdateApi.class);

    @Autowired
    JsCashNonFinService jsCashNonFinService;


    @RequestMapping(value = "/updateModule", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateModule(@Valid @RequestBody UpdateModuleReq updateModuleReq, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateModule() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblModule tblModule = jsCashNonFinService.updateModule(updateModuleReq,loggedUserDetail);

                if (tblModule != null && tblModule.getModuleId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateModule(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblModule);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateModule(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateModule() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateModule()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateModule() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateModule();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateModule() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateMenu", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateMenu(@Valid @RequestBody UpdateMenuRequest updateMenuRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateMenu() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblMenu tblMenu = jsCashNonFinService.updateMenu(updateMenuRequest,loggedUserDetail);

                if (tblMenu != null && tblMenu.getMenuId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateMenu() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblMenu);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateMenu() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateMenu() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateMenu()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateMenu() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateMenu();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateMenu() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateRole(@Valid @RequestBody UpdateRoleRequest updateRoleRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateRole() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblRole tblRole = jsCashNonFinService.updateRole(updateRoleRequest,loggedUserDetail);

                if (tblRole != null && tblRole.getRoleId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateRole() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblRole);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateRole() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateRole() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateRole()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateRole() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateRole();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateRole() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateParserHead", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateParserHead(@RequestBody UpdateParserHeadRequest updateParserHeadRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateParserHead() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblParserHead tblParserHead = jsCashNonFinService.updateParserHead(updateParserHeadRequest,loggedUserDetail);


                if (tblParserHead != null && tblParserHead.getParserHeadId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateParserHead() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblParserHead);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateParserHead() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some problem occurs", "Some problem occurs");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateParserHead() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateParserHead()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateParserHead() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateParserHead();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateParserHead() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateParserDetails", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateParserDetails(@RequestBody UpdateParserDetailsRequest updateParserDetailsRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateParserDetails()");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblParserDetail tblParserDetail = jsCashNonFinService.updateParserDetail(updateParserDetailsRequest,loggedUserDetail);


                if (tblParserDetail != null && tblParserDetail.getParserDetailId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateParserDetails() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblParserDetail);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateParserDetails() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some problem occurs", "Some problem occurs");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateParserDetails() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateParserDetails();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateParserDetails() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateParserDetails();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateParserDetails() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/approveCountries", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> approveCountries(@RequestBody ApproveCountriesRequest approveCountriesRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCountries()");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<LkpCountry> lkpCountry = jsCashNonFinService.approveCountries(approveCountriesRequest,loggedUserDetail);


                if (lkpCountry != null) {
                    LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpCountry);

                } else {
                    LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some problem occurs", "Some problem occurs");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCountries();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCountries();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/approveCountry", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> approveCountry(@Valid @RequestBody ApproveCountry approveCountry, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCountry() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpCountry lkpCountry = jsCashNonFinService.approveCountry(approveCountry,loggedUserDetail);

                if (lkpCountry != null && lkpCountry.getCountryId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == approveCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpCountry);

                } else {
                    LOG.info("\n EXITING THIS METHOD == approveCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == approveCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCountry()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == approveCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCountry();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == approveCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateCountry", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateCountry(@Valid @RequestBody UpdateCountryRequest updateCountryRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateCountry() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpCountry lkpCountry = jsCashNonFinService.updateCountry(updateCountryRequest,loggedUserDetail);

                if (lkpCountry != null && lkpCountry.getCountryId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateCountry(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpCountry);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateCountry(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateCountry()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateCountry();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/deleteCountry", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> deleteCountry(@Valid @RequestBody DeleteCountryRequest deleteCountryRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteCountryRequest() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpCountry lkpCountry = jsCashNonFinService.deleteCountry(deleteCountryRequest,loggedUserDetail);

                if (lkpCountry != null && lkpCountry.getCountryId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == deleteCountryRequest(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpCountry);

                } else {
                    LOG.info("\n EXITING THIS METHOD == deleteCountryRequest(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Couldn't delete, please try again", "Couldn't delete, please try again");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == deleteCountryRequest() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteCountryRequest()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == deleteCountryRequest() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteCountryRequest();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == deleteCountryRequest() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/deleteCountries", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> deleteCountries(@RequestBody DeleteCountriesRequest deleteCountriesRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCountries()");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<LkpCountry> lkpCountry = jsCashNonFinService.deleteCountries(deleteCountriesRequest,loggedUserDetail);


                if (lkpCountry != null) {
                    LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpCountry);

                } else {
                    LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some problem occurs", "Some problem occurs");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCountries();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCountries();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateProvince", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateProvince(@Valid @RequestBody UpdateProvinceRequest updateProvinceRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateProvince() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpProvince lkpProvince = jsCashNonFinService.updateProvince(updateProvinceRequest,loggedUserDetail);

                if (lkpProvince != null && lkpProvince.getProvinceId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateCountry(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpProvince);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateCountry(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateCountry()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateCountry();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateCountry() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/approveProvince", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> approveProvince(@Valid @RequestBody ApproveProvince approveProvince, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveProvince() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpProvince lkpProvince = jsCashNonFinService.approveProvince(approveProvince,loggedUserDetail);

                if (lkpProvince != null && lkpProvince.getProvinceId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == approveProvince() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpProvince);

                } else {
                    LOG.info("\n EXITING THIS METHOD == approveProvince() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Please try again", "Please try again");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == approveProvince() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveProvince()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == approveProvince() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveProvince();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == approveProvince() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/approveProvinces", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> approveProvinces(@RequestBody ApproveProvincesRequest approveProvincesRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveProvinces()");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<LkpProvince> lkpProvince = jsCashNonFinService.approveProvinces(approveProvincesRequest,loggedUserDetail);


                if (lkpProvince != null) {
                    LOG.info("\n EXITING THIS METHOD == ApproveProvincesRequest() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpProvince);

                } else {
                    LOG.info("\n EXITING THIS METHOD == approveProvinces() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some problem occurs", "Some problem occurs");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == approveProvinces() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveProvinces();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveProvinces();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == approveCountries() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/deleteProvince", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> deleteProvince(@Valid @RequestBody DeleteProvinceRequest deleteProvinceRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteProvinceRequest() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpProvince lkpProvince = jsCashNonFinService.deleteProvince(deleteProvinceRequest,loggedUserDetail);

                if (lkpProvince != null && lkpProvince.getProvinceId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == deleteProvinceRequest(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpProvince);

                } else {
                    LOG.info("\n EXITING THIS METHOD == deleteProvinceRequest(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Couldn't delete, please try again", "Couldn't delete, please try again");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == deleteProvinceRequest() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteProvinceRequest()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == deleteProvinceRequest() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteProvince();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == deleteProvince() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/deleteProvinces", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> deleteProvinces(@RequestBody DeleteProvincesRequest deleteProvincesRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteProvinces()");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<LkpProvince> lkpProvince = jsCashNonFinService.deleteProvinces(deleteProvincesRequest,loggedUserDetail);


                if (lkpProvince != null) {
                    LOG.info("\n EXITING THIS METHOD == deleteProvinces() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpProvince);

                } else {
                    LOG.info("\n EXITING THIS METHOD == deleteProvinces() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some problem occurs", "Some problem occurs");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == deleteProvinces() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteProvinces();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == deleteProvinces() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteProvinces();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == deleteProvinces() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateCity", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateCity(@Valid @RequestBody UpdateCityRequest updateCityRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateCity() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpCity lkpCity = jsCashNonFinService.updateCity(updateCityRequest,loggedUserDetail);

                if (lkpCity != null && lkpCity.getCityId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateCity(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpCity);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateCity(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateCity()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == updateCity();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/approveCity", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> approveCity(@Valid @RequestBody ApproveCityRequest approveCityRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCity() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpCity lkpCity = jsCashNonFinService.approveCity(approveCityRequest,loggedUserDetail);

                if (lkpCity != null && lkpCity.getCityId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == approveCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpCity);

                } else {
                    LOG.info("\n EXITING THIS METHOD == approveCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Please try again", "Please try again");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == approveCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCity()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == approveCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCity();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == approveCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/approveCities", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> approveCities(@RequestBody ApproveCitiesRequest approveCitiesRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCities()");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<LkpCity> lkpCity = jsCashNonFinService.approveCities(approveCitiesRequest,loggedUserDetail);


                if (lkpCity != null) {
                    LOG.info("\n EXITING THIS METHOD == approveCities() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpCity);

                } else {
                    LOG.info("\n EXITING THIS METHOD == approveCities() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some problem occurs", "Some problem occurs");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == approveCities() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCities();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == approveCities() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == approveCities();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == approveCities() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/deleteCity", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> deleteCity(@Valid @RequestBody DeleteCityRequest deleteCityRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteCity() ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpCity lkpCity = jsCashNonFinService.deleteCity(deleteCityRequest,loggedUserDetail);

                if (lkpCity != null && lkpCity.getCityId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == deleteCity(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpCity);

                } else {
                    LOG.info("\n EXITING THIS METHOD == deleteCity(); OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Couldn't delete, please try again", "Couldn't delete, please try again");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == deleteCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteCity()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == deleteCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteCity();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == deleteCity() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/deleteCities", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> deleteCities(@RequestBody DeleteCitiesRequest deleteCitiesRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteCities()");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                List<LkpCity> lkpCity = jsCashNonFinService.deleteCities(deleteCitiesRequest,loggedUserDetail);


                if (lkpCity != null) {
                    LOG.info("\n EXITING THIS METHOD == deleteCities() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", lkpCity);

                } else {
                    LOG.info("\n EXITING THIS METHOD == deleteCities() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some problem occurs", "Some problem occurs");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == deleteCities() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteCities();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == deleteCities() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtUpdateApi \n METHOD == deleteCities();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == deleteCities() OF CLASS = JsCashUserMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }
}
