package org.ais.jcash.dto;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 2/7/2022
 * Time: 1:14 PM
 * Project : jcash
 */
public class BranchInvoiceInqResponse {


    HashMap<String, String> resultmap = new HashMap<>();

    private long fileDetailId;

    public HashMap<String, String> getResultmap() {
        return resultmap;
    }

    public void setResultmap(HashMap<String, String> resultmap) {
        this.resultmap = resultmap;
    }

    public long getFileDetailId() {
        return fileDetailId;
    }

    public void setFileDetailId(long fileDetailId) {
        this.fileDetailId = fileDetailId;
    }
}
