package org.ais.jcash.WsdlT24Api.model;

public class InternalFundsTransferTitleFetchResponse {


    private String accountNumber;

    private String accountTitle;

    private String branchName;

    private String responseCode;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getAccountTitle() {
            return accountTitle;
        }

        public void setAccountTitle(String accountTitle) {
            this.accountTitle = accountTitle;
        }

        public String getBranchName() {
            return branchName;
        }

        public void setBranchName(String branchName) {
            this.branchName = branchName;
        }
    }

