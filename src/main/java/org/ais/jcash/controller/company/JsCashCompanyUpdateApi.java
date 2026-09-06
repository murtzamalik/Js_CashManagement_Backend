
package org.ais.jcash.controller.company;

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
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:01 PM
 * Project : jcash
 */


@Api(value = "JS Cash Management Api Non Financial Update Api", description = "Update NON FIN API For JS Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin")
public class JsCashCompanyUpdateApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashCompanyUpdateApi.class);

    @Autowired
    private JsCashNonFinService jsCashNonFinService;


    @RequestMapping(value = "/updateCompanyGroup", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> UpdateCompanyGroup(@Valid @RequestBody UpdateCompanyGroupRequest updateCompanyGroupRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashNonFinUpdateApi \n METHOD == UpdateCompanyGroup(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

            TblCompanyGroup tblCompanyGroup = jsCashNonFinService.updateComapnyGroup(updateCompanyGroupRequest,loggedUserDetail);

            if (tblCompanyGroup != null && tblCompanyGroup.getCompanyGroupId() > 0) {
                LOG.info("\n EXITING THIS METHOD == UpdateCompanyGroup(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblCompanyGroup);

            } else {
                LOG.info("\n EXITING THIS METHOD == UpdateCompanyGroup(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
            }
            } else {
                LOG.info("\n EXITING THIS METHOD == UpdateCompanyGroup(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == updateCompanyGroup();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCompanyGroup(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashNonFinUpdateApi \n METHOD == UpdateCompanyGroup();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == UpdateCompanyGroup(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateCompany", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateCompany(@Valid @RequestBody UpdateCompanyRequest updateCompanyRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompany(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
            TblCompany tblCompany = jsCashNonFinService.updateCompany(updateCompanyRequest,loggedUserDetail);

            if (tblCompany != null && tblCompany.getCompanyId() > 0) {
                LOG.info("\n EXITING THIS METHOD == updateCompany(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblCompany);

            } else {
                LOG.info("\n EXITING THIS METHOD == updateCompany(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
            }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateCompany(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == updateCompany();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCompany(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompany();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateCompany(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateCompanyProducts", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateCompanyProducts(@RequestBody UpdateCompanyProductsRequest updateCompanyProductsRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyProducts(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
            TblCompanyProduct tblCompanyProduct = jsCashNonFinService.updateCompanyProduct(updateCompanyProductsRequest,loggedUserDetail);


            if (tblCompanyProduct != null && tblCompanyProduct.getCompanyProductId() > 0) {
                LOG.info("\n EXITING THIS METHOD == updateCompanyProducts(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblCompanyProduct);

            } else {
                LOG.info("\n EXITING THIS METHOD == UpdateCompanyAccounts(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
            }
            } else {
                LOG.info("\n EXITING THIS METHOD == UpdateCompanyAccounts(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == updateCompanyProducts();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCompanyProducts(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashNonFinUpdateApi \n METHOD == UpdateCompanyAccounts();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateCompanyProducts(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateCompanyAccounts", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateCompanyAccounts(@RequestBody UpdateAccountRequest updateAccountRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyAccounts(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblAccount tblAccount = jsCashNonFinService.updateCompanyAccounts(updateAccountRequest,loggedUserDetail);


                if (tblAccount != null && tblAccount.getAccountId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateCompanyAccounts(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblAccount);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateCompanyAccounts(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateCompanyAccounts(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == updateCompanyAccounts();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCompanyAccounts(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyAccounts();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateCompanyAccounts(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }
    
    @RequestMapping(value = "/updateCompanyUserAccountsProduct", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateCompanyUserAccountsProduct(@RequestBody UpdateUserAccountProductRequest updateUserAccountProductRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyUserAccountsProduct(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblUserAccountProduct tblUserAccountProduct = jsCashNonFinService.updateCompanyUserAccountsProduct(updateUserAccountProductRequest,loggedUserDetail);


                if (tblUserAccountProduct != null && tblUserAccountProduct.getUserAccountProductId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateCompanyUserAccountsProduct(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblUserAccountProduct);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateCompanyUserAccountsProduct(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateCompanyUserAccountsProduct(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == updateCompanyUserAccountsProduct();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCompanyUserAccountsProduct(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyUserAccountsProduct();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateCompanyUserAccountsProduct(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateCompanyUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateCompanyUser(@RequestBody UpdateUserRequest updateUserRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyUser(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblUser tblUser = jsCashNonFinService.updateCompanyUser(updateUserRequest,loggedUserDetail);


                if (tblUser != null && tblUser.getUserId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateCompanyUser(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblUser);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateCompanyUser(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateCompanyUser(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == updateCompanyUser();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCompanyUser(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyUser();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateCompanyUser(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateCompanyAuthMatrix", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateCompanyAuthMatrix(@RequestBody UpdateAuthMatrixHead updateAuthMatrixHead, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyAuthMatrix(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblAuthMatrixHead tblAuthMatrixHead = jsCashNonFinService.updateCompanyAuthMatrix(updateAuthMatrixHead,loggedUserDetail);


                if (tblAuthMatrixHead != null && tblAuthMatrixHead.getAuthMatrixHeadId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateCompanyAuthMatrix(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblAuthMatrixHead);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateCompanyAuthMatrix(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateCompanyAuthMatrix(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == updateCompanyAuthMatrix();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCompanyAuthMatrix(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyAuthMatrix();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateCompanyAuthMatrix(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/updateCompanyAuthMatrixDetail", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> updateCompanyAuthMatrixDetail(@RequestBody UpdateAuthMatrixDetailRequest updateAuthMatrixDetailRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyAuthMatrixDetail(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblAuthMatrixDetail tblAuthMatrixDetail = jsCashNonFinService.updateCompanyAuthMatrixDetail(updateAuthMatrixDetailRequest,loggedUserDetail);


                if (tblAuthMatrixDetail != null && tblAuthMatrixDetail.getAuthMatrixDetailId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == updateCompanyAuthMatrixDetail(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblAuthMatrixDetail);

                } else {
                    LOG.info("\n EXITING THIS METHOD == updateCompanyAuthMatrixDetail(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == updateCompanyAuthMatrixDetail(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == updateCompanyAuthMatrixDetail();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == updateCompanyAuthMatrixDetail(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashNonFinUpdateApi \n METHOD == updateCompanyAuthMatrixDetail();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == updateCompanyAuthMatrixDetail(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/authorizeCompanyAuthMatrix", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> authorizeCompanyAuthMatrix(@RequestBody UpdateCompanyAuthMatrixRequest updateCompanyAuthMatrixRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashNonFinUpdateApi \n METHOD == authorizeCompanyAuthMatrix(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {
                TblAuthMatrixHead tblAuthMatrixHead = jsCashNonFinService.updateAuthMatrixRequest(updateCompanyAuthMatrixRequest,loggedUserDetail);


                if (tblAuthMatrixHead != null && tblAuthMatrixHead.getAuthMatrixHeadId() > 0) {
                    LOG.info("\n EXITING THIS METHOD == authorizeCompanyAuthMatrix(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Record Updated Successfully", tblAuthMatrixHead);

                } else {
                    LOG.info("\n EXITING THIS METHOD == authorizeCompanyAuthMatrix(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Some Problem Occured", "Some Problem Occured");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == authorizeCompanyAuthMatrix(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashNonFinPostApi \n METHOD == authorizeCompanyAuthMatrix();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == authorizeCompanyAuthMatrix(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashNonFinUpdateApi \n METHOD == authorizeCompanyAuthMatrix();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == authorizeCompanyAuthMatrix(); OF CLASS = JsCashNonFinUpdateApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }
}
