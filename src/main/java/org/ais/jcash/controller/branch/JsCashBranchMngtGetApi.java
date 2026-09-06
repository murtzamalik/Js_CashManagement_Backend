package org.ais.jcash.controller.branch;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.LoggedUserDetail;
import org.ais.jcash.model.TblAuthMatrixHead;
import org.ais.jcash.model.TblUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@Api(value = "JS Cash Management Api GET Api", description = "GET API For JS Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashBranchMngtGetApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashBranchMngtGetApi.class);

    @Autowired
    JsCashNonFinService jsCashNonFinService;

    @RequestMapping(value = "/getAllBranchUsers", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllBranchUsers(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtGetApi \n METHOD == getAllBranchUsers(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblUser> tblUsers = jsCashNonFinService.getallBranchuser((long) 4);

                if (tblUsers != null && tblUsers.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getAllBranchUsers(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblUsers);
                } else {
                    LOG.info("\n EXITING THIS METHOD == getAllBranchUsers(); OF CLASS = JsCashBranchMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashBranchMngtGetApi(); OF CLASS = getAllBranchUsers(); \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == getAllBranchUsers();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAllBranchUsers(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashBranchMngtGetApi \n METHOD == getAllBranchUsers();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getAllBranchUsers(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getBranchAuthMatrix", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getBranchAuthMatrix( HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtGetApi \n METHOD == getBranchAuthMatrix(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblAuthMatrixHead> tblAuthMatrixHeads = jsCashNonFinService.getBranchAuthMatrix();

                if (tblAuthMatrixHeads != null && tblAuthMatrixHeads.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getBranchAuthMatrix() \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblAuthMatrixHeads);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == getBranchAuthMatrix(); OF CLASS = JsCashBranchMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashBranchMngtGetApi(); OF CLASS = getBranchAuthMatrix() \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashBranchMngtGetApi \n METHOD == getBranchAuthMatrix();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getBranchAuthMatrix(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashBranchMngtGetApi \n METHOD == getBranchAuthMatrix();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getBranchAuthMatrix(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getBranchAuthMatrixs/{authMatrixHeadId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getBranchAuthMatrixs(@PathVariable String authMatrixHeadId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashBranchMngtGetApi \n METHOD == getBranchAuthMatrix(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblAuthMatrixHead tblAuthMatrixHeads = jsCashNonFinService.getBranchAuthMatrixs(authMatrixHeadId);

                if (tblAuthMatrixHeads != null) {
                    LOG.info("\n EXITING THIS METHOD == getBranchAuthMatrix \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblAuthMatrixHeads);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == getBranchAuthMatrix(); OF CLASS = JsCashBranchMngtGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashBranchMngtGetApi(); OF CLASS = getBranchAuthMatrix() \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashBranchMngtGetApi \n METHOD == getBranchAuthMatrix();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getBranchAuthMatrix(); OF CLASS = JsCashBranchMngtGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashBranchMngtGetApi \n METHOD == getBranchAuthMatrix();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getBranchAuthMatrix(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }


}