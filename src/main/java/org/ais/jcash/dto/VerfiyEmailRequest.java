package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/18/2022
 * Time: 10:57 AM
 * Project : jcash
 */
public class VerfiyEmailRequest {
    @NotEmpty(message = "Please enter User Name")
    @NotNull
    private String userName;
    @NotEmpty (message = "Please enter E-Mail")
    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
