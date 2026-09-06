package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 1/24/2022
 * Time: 2:36 PM
 * Project : jcash
 */
public class UpdateUserAccountProductRequest {

    private long userAccountProductId;

    private String isActive;

    private long accountId;

    private long userId;

    private long productId;

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getUserAccountProductId() {
        return userAccountProductId;
    }

    public void setUserAccountProductId(long userAccountProductId) {
        this.userAccountProductId = userAccountProductId;
    }
}
