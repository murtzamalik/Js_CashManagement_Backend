package org.ais.jcash.controller.company;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashFinService;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.controller.JsCashNonFinGetApi;
import org.ais.jcash.dto.BranchAuthPendingTransaction;
import org.ais.jcash.dto.CompanyPendingAuthTransaction;
import org.ais.jcash.dto.CompanyResponse;
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
import java.util.HashMap;
import java.util.List;

@Api(value = "JS Cash Management Api Non Financial Get Api", description = "GET NON FIN API For JS Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashCompanyGetApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashNonFinGetApi.class);

    @Autowired
    private JsCashNonFinService jsCashNonFinService;

    @Autowired
    private JsCashFinService jsCashFinService;

    @RequestMapping(value = "/getAllCompanyGroups", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllCompanyGroups(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == getAllCompanyGroups(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                List<TblCompanyGroup> tblCompanyGroups = jsCashNonFinService.getAllCompanyGroups();


                if (tblCompanyGroups != null && tblCompanyGroups.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getAllCompanyGroups(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblCompanyGroups);
                } else {

                    LOG.info("\n EXITING THIS METHOD == getAllCompanyGroups(); OF CLASS = JsCashCompanyGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashCompanyGetApi(); OF CLASS = getAllCompanyGroups \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getAllCompanyGroups();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAllCompanyGroups(); OF CLASS = JsCashCompanyGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getAllCompanyGroups();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getAllCompanyGroups(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getAllGroupCompanies/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllCompanyGroups(@PathVariable String groupId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == getAllGroupCompanies/{groupId}(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblCompany> tblCompanyProfiles = jsCashNonFinService.getAllGroupCompanies(groupId);


                if (tblCompanyProfiles != null && tblCompanyProfiles.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getAllCompanyGroups(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblCompanyProfiles);
                } else {

                    LOG.info("\n EXITING THIS METHOD == getAllCompanyGroups(); OF CLASS = JsCashCompanyGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }

            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashCompanyGetApi OF CLASS = getAllGroupCompanies/{groupId} \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getAllGroupCompanies();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAllGroupCompanies(); OF CLASS = JsCashCompanyGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getAllCompanyGroups();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getAllCompanyGroups(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getCompanyDetails/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllCompany(@PathVariable String companyId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == /getCompanyDetails/{companyId}(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblCompany tblCompany = jsCashNonFinService.getCompanyById(companyId);
                List<TblUser> tblUser = jsCashNonFinService.getAlluser(companyId);
                List<TblCompanyProduct> tblCompanyProducts = jsCashNonFinService.getAllproducts(companyId);
                List<TblAccount> tblAccounts = jsCashNonFinService.getAllaccounts(companyId);
                List<TblUserAccountProduct> tblUserAccountProducts = jsCashNonFinService.getUserAccountProduct(companyId);
                List<TblAuthMatrixHead> tblAuthMatrixHeads = jsCashNonFinService.getCompanyAuthMatrix(companyId);
                List<TblParserHead> tblParserHeads = jsCashNonFinService.getCompanyParser(companyId);

                CompanyResponse companyResponse = new CompanyResponse();

                companyResponse.setTblCompany(tblCompany);
                companyResponse.setCompanyAccounts(tblAccounts);
                companyResponse.setCompanyProducts(tblCompanyProducts);
                companyResponse.setTblUser(tblUser);
                companyResponse.setTblUserAccountProducts(tblUserAccountProducts);
                companyResponse.setTblAuthMatrixHeads(tblAuthMatrixHeads);
                companyResponse.setTblParserHeads(tblParserHeads);



                LOG.info("\n EXITING THIS METHOD == getCompanyDetails(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", companyResponse);

            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashCompanyGetApi OF CLASS = /getCompanyDetails/{companyId}} \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getCompanyDetails();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getCompanyDetails(); OF CLASS = " +
                    " \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getCompanyDetails();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getCompanyDetails(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getCompanyProduct/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllCompanyProduct(@PathVariable String companyId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == getCompanyProduct(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                List<TblCompanyProduct>  tblCompanyProduct = jsCashNonFinService.getAllCompanyProduct(companyId);



                if (tblCompanyProduct != null && tblCompanyProduct.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getCompanyProduct(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblCompanyProduct);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == getCompanyProduct(); OF CLASS = JsCashCompanyGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashCompanyGetApi; OF CLASS = /getCompanyProduct \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == getCompanyProduct();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getCompanyProduct(); OF CLASS = JsCashCompanyGetApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == JsCashCompanyGetApi();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getCompanyProduct(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getAccounts/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAccounts(@PathVariable String companyId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == getAccounts/{companyId}(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                List<TblAccount>  tblAccounts = jsCashNonFinService.getAllaccounts(companyId);



                if (tblAccounts != null && tblAccounts.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getAccounts(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblAccounts);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == getAccounts(); OF CLASS = JsCashCompanyGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashCompanyGetApi OF CLASS = /getAccounts \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == getAccounts();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAccounts(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getAccounts();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getAccounts(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getAuthMatrix/{authHeadId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAuthMatrix(@PathVariable String authHeadId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == getAuthMatrix/{companyId}(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblAuthMatrixHead  tblAuthMatrixHeads = jsCashNonFinService.getAuthMatrix(authHeadId);

                if (tblAuthMatrixHeads != null && tblAuthMatrixHeads.getTblAuthMatrixDetails().size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getAuthMatrix/{companyId}(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblAuthMatrixHeads);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == getAuthMatrix/{companyId}(); OF CLASS = JsCashCompanyGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashCompanyGetApi OF CLASS = getAuthMatrix(); \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == getAuthMatrix();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getAuthMatrix(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getAuthMatrix();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getAuthMatrix(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getCompanyAuthMatrix/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getCompanyAuthMatrix(@PathVariable String companyId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == getCompanyAuthMatrix/{companyId}(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblAuthMatrixHead> tblAuthMatrixHeads = jsCashNonFinService.getCompanyAuthMatrix(companyId);

                if (tblAuthMatrixHeads != null && tblAuthMatrixHeads.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getCompanyAuthMatrix/{companyId}(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblAuthMatrixHeads);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == getCompanyAuthMatrix/{companyId}(); OF CLASS = JsCashCompanyGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashNonFinGetApi(); OF CLASS = getCompanyAuthMatrix/{companyId} \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == getCompanyAuthMatrix();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == saveCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getCompanyAuthMatrix();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getCompanyAuthMatrix(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getCompanyParser/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getCompanyParser(@PathVariable String companyId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == getCompanyParser/{companyId}(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                List<TblParserHead> tblParserHeads = jsCashNonFinService.getCompanyParser(companyId);


                if (tblParserHeads != null && tblParserHeads.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getCompanyParser/{companyId}(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblParserHeads);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == getCompanyParser/{companyId}(); OF CLASS = JsCashCompanyGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }

            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashCompanyGetApi OF CLASS = getCompanyParser/{companyId} \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == getCompanyParser();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getCompanyParser(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getCompanyParser();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getCompanyParser(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getUser/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getUsers(@PathVariable String companyId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == /getUser/{companyId}(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                List<TblUser>  tblUsers = jsCashNonFinService.getAlluser(companyId);



                if (tblUsers != null && tblUsers.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == /getUser/{companyId}(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblUsers);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == /getUser/{companyId}(); OF CLASS = JsCashCompanyGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashCompanyGetApi OF CLASS = /getUser/{companyId} \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == getUser();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getUser(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == /getUser/{companyId}();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == /getUser/{companyId}(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getUserAccountProduct/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getUserAccountProduct(@PathVariable String companyId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == getUserAccountProduct/{companyId}(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {


                List<TblUserAccountProduct>  tblUserAccountProducts = jsCashNonFinService.getUserAccountProduct(companyId);



                if (tblUserAccountProducts != null && tblUserAccountProducts.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == getUserAccountProduct/{companyId}(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", tblUserAccountProducts);
                }  else {

                    LOG.info("\n EXITING THIS METHOD == getUserAccountProduct/{companyId}(); OF CLASS = JsCashCompanyGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");

                }
            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashCompanyGetApi OF CLASS = getUserAccountProduct/{companyId} \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == getUserAccountProduct();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == getUserAccountProduct(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getUserAccountProduct();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getUserAccountProduct(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/getCompanyProfile/{companyId}", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getCompanyProfile(@PathVariable String companyId, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCompanyGetApi \n METHOD == /getCompanyProfile/{companyId}(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblCompany tblCompany = jsCashNonFinService.getCompanyById(companyId);
               /* List<TblUser> tblUser = jsCashNonFinService.getAlluser(companyId);
                List<TblCompanyProduct> tblCompanyProducts = jsCashNonFinService.getAllproducts(companyId);
                List<TblAccount> tblAccounts = jsCashNonFinService.getAllaccounts(companyId);
                List<TblUserAccountProduct> tblUserAccountProducts = jsCashNonFinService.getUserAccountProduct(companyId);
                List<TblAuthMatrixHead> tblAuthMatrixHeads = jsCashNonFinService.getCompanyAuthMatrix(companyId);
                List<TblParserHead> tblParserHeads = jsCashNonFinService.getCompanyParser(companyId);*/

                CompanyResponse companyResponse = new CompanyResponse();

                companyResponse.setTblCompany(tblCompany);
               /* companyResponse.setCompanyAccounts(tblAccounts);
                companyResponse.setCompanyProducts(tblCompanyProducts);
                companyResponse.setTblUser(tblUser);
                companyResponse.setTblUserAccountProducts(tblUserAccountProducts);
                companyResponse.setTblAuthMatrixHeads(tblAuthMatrixHeads);
                companyResponse.setTblParserHeads(tblParserHeads);*/



                LOG.info("\n EXITING THIS METHOD == getCompanyProfile(); \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Found", tblCompany);

            } else {
                LOG.info("\n EXITING THIS METHOD == JsCashCompanyGetApi OF CLASS = /getCompanyProfile/{companyId}} \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
            }
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCompanyGetApi \n METHOD == getCompanyProfile();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == getCompanyProfile(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/companyAuth/fetchAllPendingAuthTransactions", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getAllModule(HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashCollectionGetApi \n METHOD == fetchAllPendingAuthTransactions(); ");

        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null && loggedUserDetail.getUserType().equalsIgnoreCase("O")) {

                List<CompanyPendingAuthTransaction> companyPendingAuthTransactions = jsCashFinService.getCompanyPendingTransaction(loggedUserDetail.getUserId());

                if (companyPendingAuthTransactions != null && companyPendingAuthTransactions.size() > 0) {
                    LOG.info("\n EXITING THIS METHOD == fetchAllPendingAuthTransactions(); \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Found", companyPendingAuthTransactions);
                } else {
                    LOG.info("\n EXITING THIS METHOD == fetchAllPendingAuthTransactions(); OF CLASS = JSCashGetApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No Record Found", "No Record Found");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == fetchAllPendingAuthTransactions(); OF CLASS = getAllModule \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == fetchAllPendingAuthTransactions();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == fetchAllPendingAuthTransactions(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashCollectionGetApi \n METHOD == fetchAllPendingAuthTransactions();  ERROR ----- " + e.getLocalizedMessage() + "\n\n" + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == fetchAllPendingAuthTransactions(); \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

}
