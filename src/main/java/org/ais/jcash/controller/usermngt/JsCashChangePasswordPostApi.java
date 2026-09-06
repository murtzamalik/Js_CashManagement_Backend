package org.ais.jcash.controller.usermngt;

import io.swagger.annotations.Api;
import org.ais.jcash.Service.JsCashNonFinService;
import org.ais.jcash.controller.AbstractApi;
import org.ais.jcash.controller.company.JsCashCompanyPostApi;
import org.ais.jcash.dto.*;
import org.ais.jcash.model.LkpOtpType;
import org.ais.jcash.model.TblOtp;
import org.ais.jcash.model.TblUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/18/2022
 * Time: 11:27 AM
 * Project : jcash
 */


@Api(value = "JS Cash Management Api Non Financial Change Password Post Api", description = "POST NON FIN API Change Password For JS Cash Management")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/nonFin/changePassword")
public class JsCashChangePasswordPostApi extends AbstractApi {

    Logger LOG = LoggerFactory.getLogger(JsCashCompanyPostApi.class);

    @Autowired
    private JsCashNonFinService jsCashNonFinService;

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> changePassword(@Valid @RequestBody ChangePasswordRequest saveChangePasswordRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashChangePasswordPostApi \n METHOD == changePassword(); ");
        try {
            LoggedUserDetail loggedUserDetail = getLoggedUserDataFromHeaderToken(request.getHeader("Authorization"));
            if (loggedUserDetail != null) {

                TblUser tblUser = jsCashNonFinService.checkUserPassword(loggedUserDetail.getUserId(), saveChangePasswordRequest.getCurrPass());
                if (tblUser != null && tblUser.getUserId() > 0) {

                    int update = jsCashNonFinService.updateUserPassword(tblUser.getUserId(), saveChangePasswordRequest.getNewPass(), loggedUserDetail.getUserId());
                    if (update > 0) {

                        LOG.info("\n EXITING THIS METHOD == changePassword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.OK, "Password Changed Successfully.", tblUser);
                    } else {
                        LOG.info("\n EXITING THIS METHOD == changePassword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Changing Password", "Error While Changing Password");
                    }


                } else {
                    LOG.info("\n EXITING THIS METHOD == changePassword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Wrong Password", "");
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == changePassword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                return getResponseFormat(HttpStatus.UNAUTHORIZED, "Session Expired", "Session Expired");
            }

        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashChangePasswordPostApi \n METHOD == changePassword();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == changePassword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashChangePasswordPostApi \n METHOD == changePassword();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == changePassword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }
    }

    @RequestMapping(value = "/forgetPasswordVerification", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> forgetPasswordVerification(@Valid @RequestBody VerfiyEmailRequest saveVerfiyEmailRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashChangePasswordPostApi \n METHOD == forgetPasswordVerification(); ");
        try {

            TblUser tblUser = jsCashNonFinService.verifyUserEmail(saveVerfiyEmailRequest.getUserName(), saveVerfiyEmailRequest.getEmail());

            if (tblUser != null && tblUser.getUserId() > 0) {
                LkpOtpType lkpOtpType = jsCashNonFinService.getOtpType("FP");

                TblOtp tblOtp = new TblOtp();

                String otp = generateRrnNumber();

                tblOtp.setUserId(new BigDecimal(tblUser.getUserId()));
                tblOtp.setLkpOtpType(lkpOtpType);
                tblOtp.setCreateuser(new BigDecimal(tblUser.getUserId()));
                tblOtp.setOtpin(otp);


                tblOtp = jsCashNonFinService.saveOtp(tblOtp);
                if (tblOtp != null && tblOtp.getOtpId() > 0) {

                    List<String> toEmail = new ArrayList<>();
                    toEmail.add("sharjeelsatti360@gmail.com");
                    String subject = "Forget Password OTP";
                    String body = " You have requested Pasword Change, Your OTP is " + otp;

//                    sendEmail(toEmail, "", subject, body, "", "");

                    if (tblUser != null && tblUser.getUserId() > 0) {

                        LOG.info("\n EXITING THIS METHOD == forgetPasswordVerification(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email OTP IS " + otp, "No User Found Against This Email");
                    } else {
                        LOG.info("\n EXITING THIS METHOD == forgetPasswordVerification(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                        return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email", "No User Found Against This Email");
                    }
                } else {
                    LOG.info("\n EXITING THIS METHOD == forgetPasswordVerification(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No OTP Type Configured", null);
                }
            } else {
                LOG.info("\n EXITING THIS METHOD == forgetPasswordVerification(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email", "No User Found Against This Email");
            }


        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashChangePasswordPostApi \n METHOD == forgetPasswordVerification();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == forgetPasswordVerification(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashChangePasswordPostApi \n METHOD == forgetPasswordVerification();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == forgetPasswordVerification(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }

    @RequestMapping(value = "/verifyChangePasswordOtp", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> verifyChangePasswordOtp(@Valid @RequestBody verifyChangePasswordOtpRequest verifyChangePasswordOtpRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashChangePasswordPostApi \n METHOD == verifyChangePasswordOtp(); ");
        try {

            TblUser tblUser = jsCashNonFinService.verifyUserEmail(verifyChangePasswordOtpRequest.getUserName(), verifyChangePasswordOtpRequest.getEmail());

            if (tblUser != null && tblUser.getUserId() > 0) {
                LkpOtpType lkpOtpType = jsCashNonFinService.getOtpType("FP");
                int update = jsCashNonFinService.verifyOtp(verifyChangePasswordOtpRequest.getOtp(), tblUser.getUserId(), lkpOtpType.getOtpTypeId());
                if (update > 0) {

                    LOG.info("\n EXITING THIS METHOD == verifyChangePasswordOtp(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "OTP Verified.!!", tblUser);
                } else {
                    LOG.info("\n EXITING THIS METHOD == verifyChangePasswordOtp(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Changing Password", "Error While Changing Password");
                }


            } else {
                LOG.info("\n EXITING THIS METHOD == forgetPasswordVerification(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email", "No User Found Against This Email");
            }
        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashChangePasswordPostApi \n METHOD == verifyChangePasswordOtp();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == JsCashChangePasswordPostApi(); OF CLASS = JsCashNonFinPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashChangePasswordPostApi \n METHOD == verifyChangePasswordOtp();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == verifyChangePasswordOtp(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }


    }

    @RequestMapping(value = "/confirmPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> confirmPasword(@Valid @RequestBody ConformPasswordRequest confirmEmailRequest, HttpServletRequest request) {
        LOG.info("\n\n\nINSIDE \n CLASS == JsCashChangePasswordPostApi \n METHOD == confirmPasword(); ");
        try {

            TblUser tblUser = jsCashNonFinService.verifyUserEmail(confirmEmailRequest.getUserName(), confirmEmailRequest.getEmail());
            if (tblUser != null && tblUser.getUserId() > 0) {

                int update = jsCashNonFinService.updateUserPassword(tblUser.getUserId(), confirmEmailRequest.getNewPassword(), tblUser.getUserId());
                if (update > 0) {

                    LOG.info("\n EXITING THIS METHOD == confirmPasword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.OK, "Password Changed Successfully.", tblUser);
                } else {
                    LOG.info("\n EXITING THIS METHOD == confirmPasword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                    return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "Error While Changing Password", "Error While Changing Password");
                }


            } else {
                LOG.info("\n EXITING THIS METHOD == confirmPasword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
                return getResponseFormat(HttpStatus.METHOD_NOT_ALLOWED, "No User Found Against This Email", "No User Found Against This Email");
            }


        }catch (DataAccessException e) {
            LOG.error("\n CLASS == JsCashChangePasswordPostApi \n METHOD == confirmPasword();  ERROR ----- " + e.getStackTrace());
            LOG.info("\n EXITING THIS METHOD == confirmPasword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getCause().getCause().getLocalizedMessage(), null);
        } catch (Exception e) {
            LOG.error("\n CLASS == JsCashChangePasswordPostApi \n METHOD == confirmPasword();  ERROR ----- " + e.getLocalizedMessage());
            LOG.info("\n EXITING THIS METHOD == confirmPasword(); OF CLASS = JsCashChangePasswordPostApi \n\n\n");
            return getResponseFormat(HttpStatus.INTERNAL_SERVER_ERROR, "Critical Error ::" + e.getLocalizedMessage(), null);
        }

    }



}
