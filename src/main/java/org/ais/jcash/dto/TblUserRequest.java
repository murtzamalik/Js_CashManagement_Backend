package org.ais.jcash.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class TblUserRequest {

    @NotEmpty (message = "Please enter Contact Number")
    @NotNull
    private String contactNo;
    @NotEmpty (message = "Please select department")
    @NotNull
    private String department;
    @NotEmpty (message = "Please enter designation")
    @NotNull
    private String designation;
    @NotEmpty (message = "Please enter E-Mail")
    @NotNull
    private String email;
    @NotEmpty (message = "Please enter Employee Number")
    @NotNull
    private String employeeNo;
    @NotEmpty (message = "Please enter Password")
    @NotNull
    private String password;

    private Date profileExpiry;

    @NotEmpty (message = "Please enter User Code")
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

    private long companyId;

    private long roleId;

    private String allowEntry;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getAllowEntry() { return allowEntry; }

    public void setAllowEntry(String allowEntry) { this.allowEntry = allowEntry; }
}