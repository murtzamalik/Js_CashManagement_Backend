package org.ais.jcash.controller.branch;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.*;
import org.ais.jcash.model.TblAuthMatrixDetail;
import org.ais.jcash.model.TblAuthMatrixHead;
import org.ais.jcash.model.TblUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Api(value = "JS Cash Management Api Login Post Api", description = "POST LOGIN  API For JS Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashBranchMngtUpdateApi extends AbstractApi {


    Logger LOG = LoggerFactory.getLogger(JsCashBranchMngtUpdateApi.class);

    @Autowired
    JsCashNonFinService jsCashNonFinService;

    @RequestMapping(value = "/updateBranchUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateBranchUser(@RequestBody UpdateBranchUserRequest updateBranchUserRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateBranchUser(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblUser tblUser = jsCashNonFinService.updateBranchUser(updateBranchUserRequest,loggedUserDetail);


                if (tblUser != null && tblUser.getUserId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateBranchUser(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblUser);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateBranchUser(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Updated", "No Record Updated");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateBranchUser(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateBranchUser();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateBranchUser(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateBranchUser();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateBranchUser(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateBranchAuthorizationHead", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateBranchAuthorizationHead(@RequestBody UpdateBranchAuthMatrixHeadRequest updateBranchAuthMatrixHeadRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateBranchAuthorizationHead(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblAuthMatrixHead tblAuthMatrixHead = jsCashNonFinService.updateBranchAuthMatrixHead(updateBranchAuthMatrixHeadRequest,loggedUserDetail);


                if (tblAuthMatrixHead != null && tblAuthMatrixHead.getAuthMatrixHeadId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateBranchAuthorizationHead(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblAuthMatrixHead);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateBranchAuthorizationHead(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Updated", "No Record Updated");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateBranchAuthorizationHead(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateBranchAuthorizationHead();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateBranchAuthorizationHead(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateBranchAuthorizationHead();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateBranchAuthorizationHead(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateBranchAutorizationDetail", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateBranchAutorizationDetail(@RequestBody UpdateBranchAuthMatrixDetailRequest updateBranchAuthMatrixDetailRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateBranchAutorizationDetail(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblAuthMatrixDetail tblAuthMatrixDetail = jsCashNonFinService.updateBranchAuthMatrixDetails(updateBranchAuthMatrixDetailRequest,loggedUserDetail);


                if (tblAuthMatrixDetail != null && tblAuthMatrixDetail.getAuthMatrixDetailId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateBranchAutorizationDetail(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblAuthMatrixDetail);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateBranchAutorizationDetail(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Updated", "No Record Updated");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateBranchAutorizationDetail(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateBranchAutorizationDetail();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateBranchAutorizationDetail(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateBranchAutorizationDetail();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateBranchAutorizationDetail(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }


    @RequestMapping(value = "/updateAuthorizeBranchUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateAuthorizeBranchUser(@RequestBody UpdateAuthorizationBranchUserRequest updateAuthorizationBranchUserRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateAuthorizeBranchUser(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblUser tblUser = jsCashNonFinService.updateAuthorizeBranchUser(updateAuthorizationBranchUserRequest,loggedUserDetail);


                if (tblUser != null && tblUser.getUserId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateAuthorizeBranchUser(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblUser);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateBranchUser(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Updated", "No Record Updated");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateAuthorizeBranchUser(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateAuthorizeBranchUser();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateAuthorizeBranchUser(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashBranchMngtUpdateApi \n METHOD == updateAuthorizeBranchUser();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateAuthorizeBranchUser(); OF CLASS = JsCashBranchMngtUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }


}
