package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/18/2022
 * Time: 10:04 AM
 * Project : jcash
 */
public class ChangePasswordRequest {

    @NotEmpty (message = "Current Password field cannot be empty")
    @NotNull
    private String currPass;

    @NotEmpty (message = "New Password field cannot be empty")
    @NotNull
    private String newPass;


    public String getCurrPass() {
        return currPass;
    }

    public void setCurrPass(String currPass) {
        this.currPass = currPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
