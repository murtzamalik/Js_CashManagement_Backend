
package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/25/2022
 * Time: 3:58 PM
 * Project : jcash
 */
public class VerifyXpinRequest {

    @NotNull
    @NotEmpty
    private String xpin;

    public String getXpin() {
        return xpin;
    }

    public void setXpin(String xpin) {
        this.xpin = xpin;
    }
}
