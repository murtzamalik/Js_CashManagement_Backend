package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/19/2022
 * Time: 4:08 PM
 * Project : jcash
 */
public class verifyChangePasswordOtpRequest {


    private String otp;
    @NotEmpty(message = "Please Enter User Name")
    @NotNull
    private  String userName;
    @NotEmpty (message = "Please enter E-Mail")
    @NotNull
    private String email;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
