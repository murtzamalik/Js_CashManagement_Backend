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
import java.math.BigDecimal;
import java.util.HashMap;


@Api(value = "Cash Management Api Login Post Api", description = "POST LOGIN  API For Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashUserMngtPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashUserMngtPostApi.class);

    @Autowired
    JsCashNonFinService jsCashNonFinService;

    @RequestMapping(value = "/saveModule", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveModule(@Valid @RequestBody TblModuleRequest saveModuleReq, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtPostApi \n METHOD == saveModule(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblModule tblModule = new TblModule();

                tblModule.setModuleDescr(saveModuleReq.getModuleDescr());
                tblModule.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                tblModule = jsCashNonFinService.saveModule(tblModule);

                if (tblModule != null && tblModule.getModuleId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveModule(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblModule);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveModule(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveModule(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveModule();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveModule(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveModule();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveModule(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveMenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveMenu(@Valid @RequestBody TblMenuRequest saveMenuRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtPostApi \n METHOD == saveMenu(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblModule tblModule = new TblModule();
                TblMenu tblMenu = new TblMenu();

                tblMenu.setIconName(saveMenuRequest.getIconName());
                tblMenu.setIconPath(saveMenuRequest.getIconPath());
                tblMenu.setMenuCode(saveMenuRequest.getMenuCode());
                tblMenu.setMenuDescription(saveMenuRequest.getMenuDescription());
                tblMenu.setMenuPath(saveMenuRequest.getMenuPath());
                tblMenu.setMenuType(saveMenuRequest.getMenuType());
                tblMenu.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));


                tblModule.setModuleId(Long.valueOf(saveMenuRequest.getModuleId()));

                tblMenu.setTblModule(tblModule);

                tblMenu = jsCashNonFinService.saveMenu(tblMenu);

                if (tblMenu != null && tblMenu.getMenuId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == accountLogin(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblMenu);

                } else {
                    LOG.info("\n EXITING THIS METHOD == accountLogin(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveMenu(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveMenu();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveMenu(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveMenu();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveMenu(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveRole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveRole(@Valid @RequestBody TblRoleRequest saveRoleRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtPostApi \n METHOD == saveRole() ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblRole tblRole = new TblRole();

                tblRole.setRoleDescr(saveRoleRequest.getRoleDescr());
                tblRole.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));


                tblRole = jsCashNonFinService.saveRole(tblRole);

                if (tblRole != null && tblRole.getRoleId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveRole() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblRole);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveRole(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveRole() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveRole()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveRole() OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveRole()  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveRole() OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveRoleRights", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveRoleRights(@Valid @RequestBody TblRoleRightRequest saveRoleRightRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtPostApi \n METHOD == saveRoleRights() ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblRoleRight tblRoleRights = new TblRoleRight();

                tblRoleRights.setSelectAllowed(saveRoleRightRequest.getSelectAllowed());
                tblRoleRights.setUpdateAllowed(saveRoleRightRequest.getUpdateAllowed());
                tblRoleRights.setAuthorizeAllowed(saveRoleRightRequest.getAuthorizeAllowed());
                tblRoleRights.setHideYn(saveRoleRightRequest.getHideYn());
                tblRoleRights.setInsertAllowed(saveRoleRightRequest.getInsertAllowed());
                tblRoleRights.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));

                TblRole tblRole = new TblRole();
                TblMenu tblMenu = new TblMenu();

                tblRole.setRoleId(saveRoleRightRequest.getRoleId());
                tblMenu.setMenuId(saveRoleRightRequest.getMenuId());

                tblRoleRights.setTblRole(tblRole);
                tblRoleRights.setTblMenu(tblMenu);


                tblRoleRights = jsCashNonFinService.saveRoleRights(tblRoleRights);

                if (tblRoleRights != null && tblRoleRights.getRoleRightsId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveRole() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblRoleRights);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveRoleRights() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveRoleRights() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveRoleRights()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveRoleRights() OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveRole();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveRoleRights() OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveParserHead", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveParserHead(@Valid @RequestBody TblParserHeadRequest tblParserHeadRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtPostApi \n METHOD == saveParserHead() ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblParserHead tblParserHead = new TblParserHead();

                tblParserHead.setCode(tblParserHeadRequest.getCode());
                tblParserHead.setDescription(tblParserHeadRequest.getDescription());
                tblParserHead.setIsActive(tblParserHeadRequest.getIsActive());
                TblProduct tblProduct = new TblProduct();
                tblProduct.setProductId(tblParserHeadRequest.getProductId());
                tblParserHead.setTblProduct(tblProduct);
                tblParserHead.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));


                tblParserHead = jsCashNonFinService.saveParserhead(tblParserHead);

                if (tblParserHead != null && tblParserHead.getParserHeadId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveParserHead() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblParserHead);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveParserHead() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveParserHead() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveParserHead()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveParserHead() OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveParserHead()  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveParserHead() OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveParserDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveParserDeatils(@Valid @RequestBody TblParserDetailRequest tblParserDetailRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtPostApi \n METHOD == saveParserDetails() ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblParserDetail tblParserDetail = new TblParserDetail();

                tblParserDetail.setColumnName(tblParserDetailRequest.getColumnName());
                tblParserDetail.setColumnType(tblParserDetailRequest.getColumnType());
                tblParserDetail.setFixedLength(tblParserDetailRequest.getFixedLength());
                tblParserDetail.setMaxLength(tblParserDetailRequest.getMaxLength());
                tblParserDetail.setMinLength(tblParserDetailRequest.getMinLength());
                tblParserDetail.setIsMandatory(tblParserDetailRequest.getIsMandatory());
                tblParserDetail.setIsSearchable(tblParserDetailRequest.getIsSearchable());
                tblParserDetail.setCreateuser(new BigDecimal(loggedUserDetail.getUserId()));
                tblParserDetail.setSequence(tblParserDetailRequest.getSequence());

                TblParserHead tblParserHead = new TblParserHead();
                tblParserHead.setParserHeadId(tblParserDetailRequest.getParserHeadId());

                tblParserDetail.setTblParserHead(tblParserHead);

                tblParserDetail = jsCashNonFinService.saveParserDetails(tblParserDetail);

                if (tblParserDetail != null && tblParserDetail.getParserDetailId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveParserDetails() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", tblParserDetail);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveParserDetails() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveParserDetails() OF CLASS = JsCashUserMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveParserDetails()  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveParserDetails() OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveParserDetails();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveParserDetails() OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveCountry", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveCountry(@Valid @RequestBody LkpCountryRequest lkpCountryRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashUserMngtPostApi \n METHOD == saveCountry(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpCountry lkpCountry = new LkpCountry();


                lkpCountry.setCountryCode(String.valueOf(lkpCountryRequest.getCountryCode()));
                lkpCountry.setCountryName(lkpCountryRequest.getCountryName());
                lkpCountry.setIsActive(lkpCountryRequest.getIsActive());
                lkpCountry.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));


                lkpCountry = jsCashNonFinService.saveCountry(lkpCountry);

                if (lkpCountry != null && lkpCountry.getCountryId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveCountry(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", lkpCountry);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveCountry(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveCountry(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveCountry();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCountry(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveCountry();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveCountry(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveProvince", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> saveProvince(@Valid @RequestBody AddProvinceRequest addProvinceRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtPostApi \n METHOD == saveProvince(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpProvince lkpProvince = new LkpProvince();

                lkpProvince.setProvinceCode(String.valueOf(addProvinceRequest.getProvinceCode()));
                lkpProvince.setProvinceName(lkpProvince.getProvinceName());
                lkpProvince.setIsActive(lkpProvince.getIsActive());
                lkpProvince.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                LkpCountry lkpCountry = new LkpCountry();
                lkpCountry.setCountryId(addProvinceRequest.getCountryId());
                lkpProvince.setLkpCountry(lkpCountry);

                lkpProvince = jsCashNonFinService.saveProvince(lkpProvince);

                if (lkpProvince != null && lkpProvince.getProvinceId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == saveProvince(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", lkpProvince);

                } else {
                    LOG.info("\n EXITING THIS METHOD == saveProvince(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Saved", "No Record Saved");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == saveProvince(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == saveProvince();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == saveProvince(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/saveCity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> addCity(@Valid @RequestBody AddCityRequest addCityRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtPostApi \n METHOD == addCity(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                LkpCity lkpCity = new LkpCity();

                lkpCity.setCityCode(String.valueOf(lkpCity.getCityCode()));
                lkpCity.setCityName(lkpCity.getCityName());
                lkpCity.setIsActive(lkpCity.getIsActive());
                lkpCity.setCreateuser(BigDecimal.valueOf(loggedUserDetail.getUserId()));

                LkpProvince lkpProvince = new LkpProvince();
                lkpProvince.setProvinceId(addCityRequest.getProvinceId());
                lkpCity.setLkpProvince(lkpProvince);

                lkpCity = jsCashNonFinService.saveCity(lkpCity);

                if (lkpCity != null && lkpCity.getCityId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == addCity(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Saved Successfully", lkpProvince);

                } else {
                    LOG.info("\n EXITING THIS METHOD == addCity(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Saved", "No Record Saved");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == addCity(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashUserMngtPostApi \n METHOD == addCity();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == addCity(); OF CLASS = JsCashUserMngtPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }
}
