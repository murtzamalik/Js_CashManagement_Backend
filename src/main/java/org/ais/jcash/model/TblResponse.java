package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the TBL_RESPONSE database table.
 * 
 */
@Entity
@Table(name="TBL_RESPONSE")
@NamedQuery(name="TblResponse.findAll", query="SELECT t FROM TblResponse t")
public class TblResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_RESPONSE_RESPONSEID_GENERATOR", sequenceName="TBL_RESPONSE_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_RESPONSE_RESPONSEID_GENERATOR")
	@Column(name="RESPONSE_ID")
	private long responseId;

	private String accountcurrency;

	private String accountnumber;

	private String accountstatus;

	private String accounttitle;

	private String accounttransactionblock;

	private String accounttype;

	private String amount;

	private String amountafterduedate;

	private String amountwithinduedate;

	private String bankcode;

	private String beneficiaryname;

	private String billingmonth;

	private String billstatus;

	private String branchcode;

	private String branchname;

	private String city;

	private String closingbalance;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String fromaccount;

	private String instrumentblocks;

	private String instrumentmode;

	private String instrumentno;

	private String instrumentnostartseries;

	private String instrumenttype;

	@Lob
	@Column(name="JSON_RESPONSE")
	private String jsonResponse;

	private String ledgerbalance;

	private String noofleaves;

	private String nooftxns;

	private String paymentduedate;

	private String reason;

	private String reserved1;

	private String reserved2;

	private String responsecode;

	private String responsedescription;

	private String statementpath;

	private String status;

	private String subscribername;

	private String t24transactionid;

	private String toaccount;

	private String toaccounttitle;

	private String tobankimd;

	private String tobankname;

	private String tobranchname;

	private String updatecheques;

	private String utilitycompanycode;

	private String utilityconsumernumber;

	private String workingbalance;

	//bi-directional many-to-one association to TblRequest
	@ManyToOne
	@JoinColumn(name="REQUEST_ID")
	private TblRequest tblRequest;

	public TblResponse() {
	}

	public long getResponseId() {
		return this.responseId;
	}

	public void setResponseId(long responseId) {
		this.responseId = responseId;
	}

	public String getAccountcurrency() {
		return this.accountcurrency;
	}

	public void setAccountcurrency(String accountcurrency) {
		this.accountcurrency = accountcurrency;
	}

	public String getAccountnumber() {
		return this.accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAccountstatus() {
		return this.accountstatus;
	}

	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}

	public String getAccounttitle() {
		return this.accounttitle;
	}

	public void setAccounttitle(String accounttitle) {
		this.accounttitle = accounttitle;
	}

	public String getAccounttransactionblock() {
		return this.accounttransactionblock;
	}

	public void setAccounttransactionblock(String accounttransactionblock) {
		this.accounttransactionblock = accounttransactionblock;
	}

	public String getAccounttype() {
		return this.accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountafterduedate() {
		return this.amountafterduedate;
	}

	public void setAmountafterduedate(String amountafterduedate) {
		this.amountafterduedate = amountafterduedate;
	}

	public String getAmountwithinduedate() {
		return this.amountwithinduedate;
	}

	public void setAmountwithinduedate(String amountwithinduedate) {
		this.amountwithinduedate = amountwithinduedate;
	}

	public String getBankcode() {
		return this.bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	public String getBeneficiaryname() {
		return this.beneficiaryname;
	}

	public void setBeneficiaryname(String beneficiaryname) {
		this.beneficiaryname = beneficiaryname;
	}

	public String getBillingmonth() {
		return this.billingmonth;
	}

	public void setBillingmonth(String billingmonth) {
		this.billingmonth = billingmonth;
	}

	public String getBillstatus() {
		return this.billstatus;
	}

	public void setBillstatus(String billstatus) {
		this.billstatus = billstatus;
	}

	public String getBranchcode() {
		return this.branchcode;
	}

	public void setBranchcode(String branchcode) {
		this.branchcode = branchcode;
	}

	public String getBranchname() {
		return this.branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClosingbalance() {
		return this.closingbalance;
	}

	public void setClosingbalance(String closingbalance) {
		this.closingbalance = closingbalance;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public BigDecimal getCreateuser() {
		return this.createuser;
	}

	public void setCreateuser(BigDecimal createuser) {
		this.createuser = createuser;
	}

	public String getFromaccount() {
		return this.fromaccount;
	}

	public void setFromaccount(String fromaccount) {
		this.fromaccount = fromaccount;
	}

	public String getInstrumentblocks() {
		return this.instrumentblocks;
	}

	public void setInstrumentblocks(String instrumentblocks) {
		this.instrumentblocks = instrumentblocks;
	}

	public String getInstrumentmode() {
		return this.instrumentmode;
	}

	public void setInstrumentmode(String instrumentmode) {
		this.instrumentmode = instrumentmode;
	}

	public String getInstrumentno() {
		return this.instrumentno;
	}

	public void setInstrumentno(String instrumentno) {
		this.instrumentno = instrumentno;
	}

	public String getInstrumentnostartseries() {
		return this.instrumentnostartseries;
	}

	public void setInstrumentnostartseries(String instrumentnostartseries) {
		this.instrumentnostartseries = instrumentnostartseries;
	}

	public String getInstrumenttype() {
		return this.instrumenttype;
	}

	public void setInstrumenttype(String instrumenttype) {
		this.instrumenttype = instrumenttype;
	}

	public String getJsonResponse() {
		return this.jsonResponse;
	}

	public void setJsonResponse(String jsonResponse) {
		this.jsonResponse = jsonResponse;
	}

	public String getLedgerbalance() {
		return this.ledgerbalance;
	}

	public void setLedgerbalance(String ledgerbalance) {
		this.ledgerbalance = ledgerbalance;
	}

	public String getNoofleaves() {
		return this.noofleaves;
	}

	public void setNoofleaves(String noofleaves) {
		this.noofleaves = noofleaves;
	}

	public String getNooftxns() {
		return this.nooftxns;
	}

	public void setNooftxns(String nooftxns) {
		this.nooftxns = nooftxns;
	}

	public String getPaymentduedate() {
		return this.paymentduedate;
	}

	public void setPaymentduedate(String paymentduedate) {
		this.paymentduedate = paymentduedate;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReserved1() {
		return this.reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return this.reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getResponsecode() {
		return this.responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public String getResponsedescription() {
		return this.responsedescription;
	}

	public void setResponsedescription(String responsedescription) {
		this.responsedescription = responsedescription;
	}

	public String getStatementpath() {
		return this.statementpath;
	}

	public void setStatementpath(String statementpath) {
		this.statementpath = statementpath;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubscribername() {
		return this.subscribername;
	}

	public void setSubscribername(String subscribername) {
		this.subscribername = subscribername;
	}

	public String getT24transactionid() {
		return this.t24transactionid;
	}

	public void setT24transactionid(String t24transactionid) {
		this.t24transactionid = t24transactionid;
	}

	public String getToaccount() {
		return this.toaccount;
	}

	public void setToaccount(String toaccount) {
		this.toaccount = toaccount;
	}

	public String getToaccounttitle() {
		return this.toaccounttitle;
	}

	public void setToaccounttitle(String toaccounttitle) {
		this.toaccounttitle = toaccounttitle;
	}

	public String getTobankimd() {
		return this.tobankimd;
	}

	public void setTobankimd(String tobankimd) {
		this.tobankimd = tobankimd;
	}

	public String getTobankname() {
		return this.tobankname;
	}

	public void setTobankname(String tobankname) {
		this.tobankname = tobankname;
	}

	public String getTobranchname() {
		return this.tobranchname;
	}

	public void setTobranchname(String tobranchname) {
		this.tobranchname = tobranchname;
	}

	public String getUpdatecheques() {
		return this.updatecheques;
	}

	public void setUpdatecheques(String updatecheques) {
		this.updatecheques = updatecheques;
	}

	public String getUtilitycompanycode() {
		return this.utilitycompanycode;
	}

	public void setUtilitycompanycode(String utilitycompanycode) {
		this.utilitycompanycode = utilitycompanycode;
	}

	public String getUtilityconsumernumber() {
		return this.utilityconsumernumber;
	}

	public void setUtilityconsumernumber(String utilityconsumernumber) {
		this.utilityconsumernumber = utilityconsumernumber;
	}

	public String getWorkingbalance() {
		return this.workingbalance;
	}

	public void setWorkingbalance(String workingbalance) {
		this.workingbalance = workingbalance;
	}

	public TblRequest getTblRequest() {
		return this.tblRequest;
	}

	public void setTblRequest(TblRequest tblRequest) {
		this.tblRequest = tblRequest;
	}

}