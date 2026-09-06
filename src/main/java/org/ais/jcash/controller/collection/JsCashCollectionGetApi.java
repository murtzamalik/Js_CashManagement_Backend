package org.ais.jcash.controller.collection;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashFinService;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.dto.BranchAuthPendingTransaction;
import org.ais.jcash.dto.LoggedUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/10/2022
 * Time: 3:33 PM
 * Project : jcash
 */

@Api(value = "JS Cash Management Collection GET Api", description = "GET COllection API For JS Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/fin")
public class JsCashCollectionGetApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashCollectionGetApi.class);

    @Autowired
    private JsCashFinService jsCashFinService;

    @RequestMapping(value = "/branchAuth/fetchAllPendingAuthTransactions", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllModule(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCollectionGetApi \n METHOD == fetchAllPendingAuthTransactions(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<BranchAuthPendingTransaction> branchAuthPendingTransactions = jsCashFinService.getBranchPendingTransaction(loggedUserDetail.getUserId());

                if (branchAuthPendingTransactions != null && branchAuthPendingTransactions.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == fetchAllPendingAuthTransactions(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", branchAuthPendingTransactions);
                } else {
                    LOG.info("\n EXITING THIS METHOD == fetchAllPendingAuthTransactions(); OF CLASS = JSCashGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == fetchAllPendingAuthTransactions(); OF CLASS = getAllModule \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == saveCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCollectionGetApi \n METHOD == fetchAllPendingAuthTransactions();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == fetchAllPendingAuthTransactions(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

}
