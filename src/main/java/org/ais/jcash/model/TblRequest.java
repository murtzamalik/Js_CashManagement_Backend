package org.ais.jcash.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TBL_REQUEST database table.
 * 
 */
@Entity
@Table(name="TBL_REQUEST")
@NamedQuery(name="TblRequest.findAll", query="SELECT t FROM TblRequest t")
public class TblRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TBL_REQUEST_REQUESTID_GENERATOR", sequenceName="TBL_REQUEST_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TBL_REQUEST_REQUESTID_GENERATOR")
	@Column(name="REQUEST_ID")
	private long requestId;

	private String accountnumber;

	private String amount;

	private String apiendpoint;

	private String bankcode;

	private String beneficiaryname;

	private String branchid;

	private String cardacceptornamelocation;

	private String charges;

	private String chequepoddnumber;

	private String chequepoddtype;

	private String city;

	private String clearingdate;

	private String clearingstatus;

	private String clearingtype;

	@Temporal(TemporalType.DATE)
	private Date createdate;

	private BigDecimal createuser;

	private String currency;

	private String customerid;

	private String customeridtype;

	private String depositslipno;

	private String effectdate;

	private String fromaccount;

	private String fromamount;

	private String fromdate;

	private String instrumentdate;

	private String instrumentmode;

	private String instrumentno;

	private String instrumentnostartseries;

	private String instrumenttype;

	@Lob
	@Column(name="JSON_REQUEST")
	private String jsonRequest;

	private String merchantid;

	private String merchanttype;

	private String messagetype;

	private String narration;

	private String noofleaves;

	private String onaccountof;

	private String pinblock;

	private String pinblocktype;

	private String proccode;

	private String reason;

	private String referencebankcode;

	private String referencebranchcode;

	private String referencebranchname;

	private String reqno;

	private String reserved1;

	private String reserved2;

	private String returncode;

	private String stan;

	private String status;

	private String t24transactionid;

	private String targethost;

	private String telleraccount;

	private String toaccount;

	private String toaccountbranch;

	private String toaccounttitle;

	private String toamount;

	private String tobankimd;

	private String tobankname;

	private String todate;

	private String transactioncode;

	private String transactiondescription;

	private String transactionfee;

	private String transactionno;

	private String transmissiondatetime;

	private String transtype;

	private String updatecheques;

	private String utilitycompanycode;

	private String utilityconsumernumber;

	private String voucherno;

	//bi-directional many-to-one association to TblResponse
	@OneToMany(mappedBy="tblRequest")
	private List<TblResponse> tblResponses;

	//bi-directional many-to-one association to TblTransHead
	@OneToMany(mappedBy="tblRequest")
	private List<TblTransHead> tblTransHeads;

	public TblRequest() {
	}

	public long getRequestId() {
		return this.requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public String getAccountnumber() {
		return this.accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getApiendpoint() {
		return this.apiendpoint;
	}

	public void setApiendpoint(String apiendpoint) {
		this.apiendpoint = apiendpoint;
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

	public String getBranchid() {
		return this.branchid;
	}

	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}

	public String getCardacceptornamelocation() {
		return this.cardacceptornamelocation;
	}

	public void setCardacceptornamelocation(String cardacceptornamelocation) {
		this.cardacceptornamelocation = cardacceptornamelocation;
	}

	public String getCharges() {
		return this.charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	public String getChequepoddnumber() {
		return this.chequepoddnumber;
	}

	public void setChequepoddnumber(String chequepoddnumber) {
		this.chequepoddnumber = chequepoddnumber;
	}

	public String getChequepoddtype() {
		return this.chequepoddtype;
	}

	public void setChequepoddtype(String chequepoddtype) {
		this.chequepoddtype = chequepoddtype;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClearingdate() {
		return this.clearingdate;
	}

	public void setClearingdate(String clearingdate) {
		this.clearingdate = clearingdate;
	}

	public String getClearingstatus() {
		return this.clearingstatus;
	}

	public void setClearingstatus(String clearingstatus) {
		this.clearingstatus = clearingstatus;
	}

	public String getClearingtype() {
		return this.clearingtype;
	}

	public void setClearingtype(String clearingtype) {
		this.clearingtype = clearingtype;
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

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomeridtype() {
		return this.customeridtype;
	}

	public void setCustomeridtype(String customeridtype) {
		this.customeridtype = customeridtype;
	}

	public String getDepositslipno() {
		return this.depositslipno;
	}

	public void setDepositslipno(String depositslipno) {
		this.depositslipno = depositslipno;
	}

	public String getEffectdate() {
		return this.effectdate;
	}

	public void setEffectdate(String effectdate) {
		this.effectdate = effectdate;
	}

	public String getFromaccount() {
		return this.fromaccount;
	}

	public void setFromaccount(String fromaccount) {
		this.fromaccount = fromaccount;
	}

	public String getFromamount() {
		return this.fromamount;
	}

	public void setFromamount(String fromamount) {
		this.fromamount = fromamount;
	}

	public String getFromdate() {
		return this.fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getInstrumentdate() {
		return this.instrumentdate;
	}

	public void setInstrumentdate(String instrumentdate) {
		this.instrumentdate = instrumentdate;
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

	public String getJsonRequest() {
		return this.jsonRequest;
	}

	public void setJsonRequest(String jsonRequest) {
		this.jsonRequest = jsonRequest;
	}

	public String getMerchantid() {
		return this.merchantid;
	}

	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}

	public String getMerchanttype() {
		return this.merchanttype;
	}

	public void setMerchanttype(String merchanttype) {
		this.merchanttype = merchanttype;
	}

	public String getMessagetype() {
		return this.messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	public String getNarration() {
		return this.narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getNoofleaves() {
		return this.noofleaves;
	}

	public void setNoofleaves(String noofleaves) {
		this.noofleaves = noofleaves;
	}

	public String getOnaccountof() {
		return this.onaccountof;
	}

	public void setOnaccountof(String onaccountof) {
		this.onaccountof = onaccountof;
	}

	public String getPinblock() {
		return this.pinblock;
	}

	public void setPinblock(String pinblock) {
		this.pinblock = pinblock;
	}

	public String getPinblocktype() {
		return this.pinblocktype;
	}

	public void setPinblocktype(String pinblocktype) {
		this.pinblocktype = pinblocktype;
	}

	public String getProccode() {
		return this.proccode;
	}

	public void setProccode(String proccode) {
		this.proccode = proccode;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReferencebankcode() {
		return this.referencebankcode;
	}

	public void setReferencebankcode(String referencebankcode) {
		this.referencebankcode = referencebankcode;
	}

	public String getReferencebranchcode() {
		return this.referencebranchcode;
	}

	public void setReferencebranchcode(String referencebranchcode) {
		this.referencebranchcode = referencebranchcode;
	}

	public String getReferencebranchname() {
		return this.referencebranchname;
	}

	public void setReferencebranchname(String referencebranchname) {
		this.referencebranchname = referencebranchname;
	}

	public String getReqno() {
		return this.reqno;
	}

	public void setReqno(String reqno) {
		this.reqno = reqno;
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

	public String getReturncode() {
		return this.returncode;
	}

	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}

	public String getStan() {
		return this.stan;
	}

	public void setStan(String stan) {
		this.stan = stan;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getT24transactionid() {
		return this.t24transactionid;
	}

	public void setT24transactionid(String t24transactionid) {
		this.t24transactionid = t24transactionid;
	}

	public String getTargethost() {
		return this.targethost;
	}

	public void setTargethost(String targethost) {
		this.targethost = targethost;
	}

	public String getTelleraccount() {
		return this.telleraccount;
	}

	public void setTelleraccount(String telleraccount) {
		this.telleraccount = telleraccount;
	}

	public String getToaccount() {
		return this.toaccount;
	}

	public void setToaccount(String toaccount) {
		this.toaccount = toaccount;
	}

	public String getToaccountbranch() {
		return this.toaccountbranch;
	}

	public void setToaccountbranch(String toaccountbranch) {
		this.toaccountbranch = toaccountbranch;
	}

	public String getToaccounttitle() {
		return this.toaccounttitle;
	}

	public void setToaccounttitle(String toaccounttitle) {
		this.toaccounttitle = toaccounttitle;
	}

	public String getToamount() {
		return this.toamount;
	}

	public void setToamount(String toamount) {
		this.toamount = toamount;
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

	public String getTodate() {
		return this.todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public String getTransactioncode() {
		return this.transactioncode;
	}

	public void setTransactioncode(String transactioncode) {
		this.transactioncode = transactioncode;
	}

	public String getTransactiondescription() {
		return this.transactiondescription;
	}

	public void setTransactiondescription(String transactiondescription) {
		this.transactiondescription = transactiondescription;
	}

	public String getTransactionfee() {
		return this.transactionfee;
	}

	public void setTransactionfee(String transactionfee) {
		this.transactionfee = transactionfee;
	}

	public String getTransactionno() {
		return this.transactionno;
	}

	public void setTransactionno(String transactionno) {
		this.transactionno = transactionno;
	}

	public String getTransmissiondatetime() {
		return this.transmissiondatetime;
	}

	public void setTransmissiondatetime(String transmissiondatetime) {
		this.transmissiondatetime = transmissiondatetime;
	}

	public String getTranstype() {
		return this.transtype;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
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

	public String getVoucherno() {
		return this.voucherno;
	}

	public void setVoucherno(String voucherno) {
		this.voucherno = voucherno;
	}

	public List<TblResponse> getTblResponses() {
		return this.tblResponses;
	}

	public void setTblResponses(List<TblResponse> tblResponses) {
		this.tblResponses = tblResponses;
	}

	public TblResponse addTblRespons(TblResponse tblRespons) {
		getTblResponses().add(tblRespons);
		tblRespons.setTblRequest(this);

		return tblRespons;
	}

	public TblResponse removeTblRespons(TblResponse tblRespons) {
		getTblResponses().remove(tblRespons);
		tblRespons.setTblRequest(null);

		return tblRespons;
	}

	public List<TblTransHead> getTblTransHeads() {
		return this.tblTransHeads;
	}

	public void setTblTransHeads(List<TblTransHead> tblTransHeads) {
		this.tblTransHeads = tblTransHeads;
	}

	public TblTransHead addTblTransHead(TblTransHead tblTransHead) {
		getTblTransHeads().add(tblTransHead);
		tblTransHead.setTblRequest(this);

		return tblTransHead;
	}

	public TblTransHead removeTblTransHead(TblTransHead tblTransHead) {
		getTblTransHeads().remove(tblTransHead);
		tblTransHead.setTblRequest(null);

		return tblTransHead;
	}

}