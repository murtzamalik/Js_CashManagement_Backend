package org.ais.jcash.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

public class TblModuleRequest {


@NotEmpty (message = "Please enter module description")
    private String moduleDescr;


    public String getModuleDescr() {
        return moduleDescr;
    }

    public void setModuleDescr(String moduleDescr) {
        this.moduleDescr = moduleDescr;
    }
}
