package org.ais.jcash.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UpdateUserRequest {

    @NotEmpty(message = "Please enter Contact Number")
    @NotNull
    private String contactNo;
    @NotEmpty (message = "Please enter department")
    @NotNull
    private String department;
    @NotEmpty (message = "Please enter designation")
    @NotNull
    private String designation;
    @NotEmpty (message = "Please enter E Mail")
    @NotNull
    private String email;
    @NotEmpty (message = "Please enter Employee Number")
    @NotNull
    private String employeeNo;

    private Date profileExpiry;

    @NotEmpty (message = "Please enter User")
    @NotNull
    private String userCode;
    @NotEmpty (message = "Please enter User Group")
    @NotNull
    private String userGroup;
    @NotEmpty (message = "Please enter User Name")
    @NotNull
    private String userName;

    private long userTypeId;

    private long baseLocation;

    private long userId;

    private long roleId;

    @NotEmpty (message = "Please enter Password")
    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Date getProfileExpiry() {
        return profileExpiry;
    }

    public void setProfileExpiry(Date profileExpiry) {
        this.profileExpiry = profileExpiry;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public long getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(long baseLocation) {
        this.baseLocation = baseLocation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
