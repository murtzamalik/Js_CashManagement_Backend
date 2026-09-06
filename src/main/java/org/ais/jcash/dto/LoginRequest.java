package org.ais.jcash.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 12/21/2021
 * Time: 4:07 PM
 * Project : jcash
 */


public class LoginRequest {

    @NotEmpty(message = "User Name Is Cannot Be Empty")
    @NotNull(message = "User Name Cannot Be Null")
    private String userName;

    @NotEmpty(message = "Password Cannot Be Empty")
    @NotNull(message = "Password Cannot Be Null")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
