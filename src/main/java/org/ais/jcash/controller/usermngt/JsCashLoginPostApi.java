package org.ais.jcash.controller.usermngt;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.dto.LoginRequest;
import org.ais.jcash.dto.LoginResponse;
import org.ais.jcash.dto.Menu;
import org.ais.jcash.model.*;
import org.ais.jcash.util.AESencryption;
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
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/18/2022
 * Time: 11:54 AM
 * Project : jcash
 */


@Api(value = "Cash Management Api Login Post Api", description = "POST LOGIN  API For Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class JsCashLoginPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashLoginPostApi.class);

    @Autowired
    private JsCashNonFinService jsCashNonFinService;


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> accountLogin(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLoginPostApi \n METHOD == accountLogin(); ");

            LoginResponse loginResponse = new LoginResponse();
            TblUser tblUser = jsCashNonFinService.checkUserLogin(loginRequest.getUserName(), loginRequest.getPassword());
            if (tblUser != null && tblUser.getUserId() > 0) {

                ////// GET ALL MENU AGIANST ROLE  //////////////////////////////////////////////////
                List<TblUserRole> tblUserRole = jsCashNonFinService.getRoleAgainstUser(tblUser.getUserId());
                if (tblUserRole != null ) {


                    Menu menu = null;
                    List<Menu> menus = new ArrayList<>();
                    List<TblModule> tblModules = jsCashNonFinService.getRoleWiseModule(tblUserRole);
                    if (tblModules != null && tblModules.size() > 0) {

                        for (TblModule tblModule : tblModules) {
                            List<Menu> childMenus = new ArrayList<>();
                            Menu childMenu = null;
                            List<TblRoleRight> tblPages = jsCashNonFinService.getRoleAndModuelWisePages(tblModule.getModuleId(), tblUserRole);
                            if (tblPages != null && tblPages.size() > 0) {
                                for (TblRoleRight tblPage : tblPages) {

//                                    if (tblPage.getMcStatus().equals("Y")) {
                                    childMenu = new Menu();
                                    childMenu.setLabel(tblPage.getTblMenu().getMenuDescription());
                                    childMenu.setTo(tblPage.getTblMenu().getMenuPath());
                                    childMenu.setSelectAllowed(tblPage.getSelectAllowed());
                                    childMenu.setUpdateAllowed(tblPage.getUpdateAllowed());
                                    childMenu.setAuthorizeAllowed(tblPage.getAuthorizeAllowed());
                                    childMenu.setHideYn(tblPage.getHideYn());
                                    childMenu.setInsertAllowed(tblPage.getInsertAllowed());
                                    childMenu.setIcon(tblPage.getTblMenu().getIconPath());

                                    childMenus.add(childMenu);
//                                    }
                                }

                            }


                            menu = new Menu();
                            menu.setLabel(tblModule.getModuleDescr());
                            menu.setItems(childMenus);
                            menu.setIcon("pi-angle-right");

                            menus.add(menu);
                        }
                    }

                    TblUserLoginHistory tblUserLoginHistory = new TblUserLoginHistory();

//                    tblUserLoginHistory.setUserId(BigDecimal.valueOf(tblUser.getUserId()));
                    tblUser.setUserId(Long.valueOf(tblUser.getUserId()));
                    tblUserLoginHistory.setTblUser(tblUser);
                    tblUserLoginHistory.setLoginDate(new Date());
                    tblUserLoginHistory.setIpAddress(getClientIpAddress(request));
                    tblUserLoginHistory.setHost("127.0.0.1");

                    tblUserLoginHistory = jsCashNonFinService.saveUserLoginHistory(tblUserLoginHistory);

                    JWTSecurity jwtSecurity = new JWTSecurity();
                    Map<String, Object> claims = new HashMap<>();
                    AESencryption aeSencryption = new AESencryption();

                    claims.put("userId", String.valueOf(tblUser.getUserId()));
                    claims.put("loginId", String.valueOf(tblUserLoginHistory.getUserLoginHistoryId()));
                    if(tblUser.getLkpUserType().getUserTypeId() == 4){
                        claims.put("userTypeId", "B");
                        claims.put("branchId", String.valueOf(tblUser.getLkpBranch().getBranchId()));
                    }else{
                        claims.put("userTypeId", "O");
                        claims.put("companyId", String.valueOf(tblUser.getTblCompany().getCompanyId()));
                    }

                    String jwt = jwtSecurity.createJWTWithClaims("JS_cash_User", claims);



                    loginResponse.setLogin(true);
                    loginResponse.setMenu(menus);
                    loginResponse.setTblUser(tblUser);

                    loginResponse.setSessionTimeOut(""+100*60000);
                    loginResponse.setToken("Bearer " + aeSencryption.encrypt(jwt));

                    LOG.info("\n EXITING THIS METHOD == accountLogin(); OF CLASS = JsCashLoginPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Login successful", loginResponse);

                } else {
                    LOG.info("\n EXITING THIS METHOD == accountLogin(); OF CLASS = JsCashLoginPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No roles attached to this user", "No roles attached to this user");
                }

                ////////////////////////////////////////////////////////////////


            } else {
                LOG.info("\n EXITING THIS METHOD == accountLogin(); OF CLASS = JsCashLoginPostApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
            }


        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashLoginPostApi \n METHOD == accountLogin();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == accountLogin(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLoginPostApi \n METHOD == accountLogin();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == accountLogin(); OF CLASS = JsCashLoginPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> logout(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            LOG.info("\n\n\nINSIDE \n CLASS == JsCashLoginPostApi \n METHOD == logout(); ");
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblUserLoginHistory tblUserLoginHistory = jsCashNonFinService.logoutUser(loggedUserDetail.getUserId());
                if (tblUserLoginHistory != null) {
                    LOG.info("\n EXITING THIS METHOD == logout(); OF CLASS = JsCashLoginPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Logged Out", null);
                } else {
                    LOG.info("\n EXITING THIS METHOD == logout(); OF CLASS = JsCashLoginPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Logging off", null);
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == logout(); OF CLASS = JsCashLoginPostApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "UserName/Password Invalid", "UserName/Password Invalid");
            }


        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashLoginPostApi \n METHOD == logout();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == logout(); OF CLASS = JsCashLoginPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashLoginPostApi \n METHOD == logout();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == logout(); OF CLASS = JsCashLoginPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }
}
