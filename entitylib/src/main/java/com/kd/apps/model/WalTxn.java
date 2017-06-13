package com.kd.apps.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class WalTxn extends BaseEntity<WalTxn, String> implements Cloneable {
	private static final long serialVersionUID = 1L;

	// Constant defining external source
	public static final String EXT_SRC = "EXT_SRC";

	// Constant defining external destination
	public static final String EXT_DEST = "EXT_DEST";

	// START
	@Id
	private String txnid;

	// parent txn id if any
	@Column
	private String parenttxnid;

	// wallet service provider id or tenant id
	@Column
	private String wsp;

	// wallet service provider user id
	@Column
	private String rootid;

	// aggregator digital service provider id
	@Column
	private String adsp;

	// original digital service provider id
	@Column
	private String odsp;

	// sms, ussd, web, mobileapp, api, webpos
	@Column
	private String channel;

	// txn src ip address
	@Column
	private String ipaddress;

	// txn src device like
	// browser name etc.
	@Column
	private String reqsrc;

	// fundin, fundout
	// cashin, cashout
	// commission, fee
	// transfer, reverse
	// refund, sale/purchase
	// loyalty, bonus
	@Column
	private String txntype;

	// products like topup, databundle, billpay,
	// emoney
	@Column
	private String product;

	// source type like merchant, customer etc.
	@Column
	private String srctype;

	// source wallet owner id
	@Column
	private String srcid;

	// Fee payer : source / Destination
	@Column
	private String feepayer;

	// Comm payer : source / Destination
	@Column
	private String commpayer;

	// transaction originating source
	// wallet owner id
	// it will be used for
	// multimode txn
	@Column
	private String txnorigsrc;

	// parent of source wallet owner
	@Column
	private String parentsrcid;

	// root of source wallet owner
	@Column
	private String rootsrcid;

	// source wallet owner device id
	// from which txn originated
	@Column
	private String srcdeviceid;

	// end user or beneficiary - original digital service provider
	// subscriber id like prepaid mobile num or dth card num
	@Column
	private String odspsubsid;

	// end user or beneficiary - original digital service provider
	// subscriber account number if any
	@Column
	private String odspsubsaccnt;

	// destination type like merchant, customer etc.
	@Column
	private String desttype;

	// destination wallet owner id
	@Column
	private String destid;

	// parent of destination wallet owner
	@Column
	private String parentdestid;

	// root of destination wallet owner
	@Column
	private String rootdestid;

	// amount of txn
	@Column(precision = 32, scale = 4)
	private BigDecimal amount;

	// fee on txn
	@Column(precision = 32, scale = 4)
	private BigDecimal fee;

	// agent commission on txn
	@Column(precision = 32, scale = 4)
	private BigDecimal acommission;

	// tax on txn - to be removed
	@Column(precision = 32, scale = 4)
	private BigDecimal tax;

	// source wallet balance
	@Column(precision = 32, scale = 4)
	private BigDecimal srcwalbal;

	// destination wallet balance
	@Column(precision = 32, scale = 4)
	private BigDecimal destwalbal;

	// original digital service provider
	// subscriber balance if available
	@Column(precision = 32, scale = 4)
	private BigDecimal odspsubsbal;

	// original digital service provider
	// merchant (wallet service provider/tenant)
	// balance if available
	@Column(precision = 32, scale = 4)
	private BigDecimal odspwspbal;

	// currency of txn
	@Column
	private String currency;

	// ***********
	// converted amount
	@Column(precision = 32, scale = 4)
	private BigDecimal camount;

	// wallet service provider or tenant
	// commission on txn (if any)
	@Column(precision = 32, scale = 4)
	private BigDecimal wspcommission;

	// original or inventory
	// conversion rate
	@Column(precision = 32, scale = 4)
	private BigDecimal ocrate;

	// currency of converted amount
	@Column(precision = 32, scale = 4)
	private String ccurrency;
	// ***********

	// final state of txn
	@Column
	private String status;

	// date time of final state of txn
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date txndatetime;

	// date time string of final state of txn
	@Transient
	private String txndatetimestr;

	// date time of final state of parent txn
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date ptxndatetime;

	// date time string of final state of parent txn
	@Transient
	private String ptxndatetimestr;

	// payment src like
	// credit card, bank
	// e.g. PP:VISA:IN:xxxx
	// for payment done through
	// India based VISA card
	// using Paypal payment
	// gateway. xxxx is last 4
	// digit of the card
	@Column
	private String paysrc;

	// aggregator payment reference id if any
	@Column
	private String apayref;

	// original payment reference id if any
	@Column
	private String opayref;

	// txnid if any from
	// agent or merchant
	@Column
	private String agenttxnid;

	// aggregator digital service provider reference id if any
	@Column
	private String adspref;

	// original digital service provider reference id if any
	@Column
	private String odspref;

	// freeform comment on the txn
	@Column
	private String comment;
	// END

	/**
	 * @return txnid {@link String} Auto generated Transaction id
	 */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return txnid;
	}

	/**
	 * @return txnid {@link String} Auto generated Transaction id
	 */
	public String getTxnid() {
		return txnid;
	}

	/**
	 * 
	 * @param txnid {@link String} Auto generated Transaction id
	 */
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}

	/**
	 * @return parenttxnid {@link String} Parent Transaction id
	 */
	public String getParenttxnid() {
		return parenttxnid;
	}

	/**
	 * 
	 * @param parenttxnid {@link String} Parent Transaction id
	 */
	public void setParenttxnid(String parenttxnid) {
		this.parenttxnid = parenttxnid;
	}

	/**
	 * 
	 * @return  wsp - {@link String} wallet service provider id or tenant id
	 */
	public String getWsp() {
		return wsp;
	}

	/**
	 * 
	 * @param wsp {@link String} wallet service provider id or tenant id
	 */
	public void setWsp(String wsp) {
		this.wsp = wsp;
	}

	/**
	 * 
	 * @return  rootid - {@link String} wallet service provider user id
	 */
	public String getRootid() {
		return rootid;
	}

	/**
	 * 
	 * @param rootid {@link String} wallet service provider user id
	 */
	public void setRootid(String rootid) {
		this.rootid = rootid;
	}

	/**
	 * 
	 * @return  adsp - {@link String} aggregator service provider user id
	 */
	public String getAdsp() {
		return adsp;
	}

	/**
	 * 
	 * @param adsp {@link String} aggregator service provider user id
	 */
	public void setAdsp(String adsp) {
		this.adsp = adsp;
	}

	/**
	 * 
	 * @return  odsp - {@link String} original digital service provider id
	 */	
	public String getOdsp() {
		return odsp;
	}

	/**
	 * 
	 * @param odsp {@link String} original digital service provider id
	 */
	public void setOdsp(String odsp) {
		this.odsp = odsp;
	}

	/**
	 * 
	 * @return  channel - {@link String} sms, ussd, web, mobileapp, api, webpos
	 */	
	public String getChannel() {
		return channel;
	}

	/**
	 * 
	 * @param channel {@link String} sms, ussd, web, mobileapp, api, webpos
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * 
	 * @return  ipaddress - {@link String} ip address of transaction originated
	 */	
	public String getIpaddress() {
		return ipaddress;
	}

	/**
	 * 
	 * @param ipaddress {@link String} ip address of transaction originated
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	/**
	 * 
	 * @return  reqsrc - {@link String} txn src device like browser name etc.
	 */	
	public String getReqsrc() {
		return reqsrc;
	}

	/**
	 * 
	 * @param reqsrc {@link String} txn src device like browser name etc.
	 */
	public void setReqsrc(String reqsrc) {
		this.reqsrc = reqsrc;
	}

	/**
	 * 
	 * @return  txntype - {@link String} transaction type
	 */
	public String getTxntype() {
		return txntype;
	}

	/**
	 * 
	 * @param txntype {@link String} transaction type
	 */
	public void setTxntype(String txntype) {
		this.txntype = txntype;
	}

	/**
	 * 
	 * @return  product - {@link String} product code
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * 
	 * @param product {@link String} product code
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * 
	 * @return  srctype - {@link String} source type like merchant, customer etc.
	 */
	public String getSrctype() {
		return srctype;
	}

	/**
	 * 
	 * @param srctype {@link String} source type like merchant, customer etc.
	 */
	public void setSrctype(String srctype) {
		this.srctype = srctype;
	}

	/**
	 * 
	 * @return  feepayer - {@link String} fee payer Source / Destination
	 */
	public String getFeepayer() {
		return feepayer;
	}

	/**
	 * 
	 * @param feepayer {@link String} fee payer Source / Destination
	 */
	public void setFeepayer(String feepayer) {
		this.feepayer = feepayer;
	}

	/**
	 * 
	 * @return  commpayer - {@link String} commission payer Source / Destination
	 */
	public String getCommpayer() {
		return commpayer;
	}

	/**
	 * 
	 * @param commpayer {@link String} commission payer Source / Destination
	 */
	public void setCommpayer(String commpayer) {
		this.commpayer = commpayer;
	}

	/**
	 * 
	 * @return  srcid - {@link String} Source Id
	 */
	public String getSrcid() {
		return srcid;
	}

	/**
	 * 
	 * @param srcid {@link String} Source Id
	 */
	public void setSrcid(String srcid) {
		this.srcid = srcid;
	}

	/**
	 * 
	 * @return  txnorigsrc - {@link String} transaction originating source user id
	 */
	public String getTxnorigsrc() {
		return txnorigsrc;
	}

	/**
	 * 
	 * @param txnorigsrc {@link String} transaction originating source user id
	 */
	public void setTxnorigsrc(String txnorigsrc) {
		this.txnorigsrc = txnorigsrc;
	}

	/**
	 * 
	 * @return  parentsrcid - {@link String} Source Parent Id
	 */
	public String getParentsrcid() {
		return parentsrcid;
	}

	/**
	 * 
	 * @param parentsrcid {@link String} Source Parent Id
	 */
	public void setParentsrcid(String parentsrcid) {
		this.parentsrcid = parentsrcid;
	}

	/**
	 * 
	 * @return  rootsrcid - {@link String} Source Root Id
	 */
	public String getRootsrcid() {
		return rootsrcid;
	}

	/**
	 * 
	 * @param rootsrcid {@link String} Source Root Id
	 */
	public void setRootsrcid(String rootsrcid) {
		this.rootsrcid = rootsrcid;
	}

	/**
	 * 
	 * @return  srcdeviceid - {@link String} Source Device Id
	 */
	public String getSrcdeviceid() {
		return srcdeviceid;
	}

	/**
	 * 
	 * @param srcdeviceid {@link String} Source Device Id
	 */
	public void setSrcdeviceid(String srcdeviceid) {
		this.srcdeviceid = srcdeviceid;
	}

	/**
	 * 
	 * @return  odspsubsid - {@link String} original digital service provider subscriber id
	 */
	public String getOdspsubsid() {
		return odspsubsid;
	}

	/**
	 * 
	 * @param odspsubsid {@link String} original digital service provider subscriber id
	 */
	public void setOdspsubsid(String odspsubsid) {
		this.odspsubsid = odspsubsid;
	}

	/**
	 * 
	 * @return  odspsubsaccnt - {@link String} original digital service provider subscriber account number
	 */
	public String getOdspsubsaccnt() {
		return odspsubsaccnt;
	}
	
	/**
	 * 
	 * @param odspsubsaccnt {@link String} original digital service provider subscriber account number
	 */
	public void setOdspsubsaccnt(String odspsubsaccnt) {
		this.odspsubsaccnt = odspsubsaccnt;
	}

	/**
	 * 
	 * @return  desttype - {@link String} Destination type
	 */
	public String getDesttype() {
		return desttype;
	}

	/**
	 * 
	 * @param desttype {@link String} Destination type
	 */
	public void setDesttype(String desttype) {
		this.desttype = desttype;
	}

	/**
	 * 
	 * @return  destid - {@link String} Destination User id
	 */
	public String getDestid() {
		return destid;
	}

	/**
	 * 
	 * @param destid {@link String} Destination User id
	 */
	public void setDestid(String destid) {
		this.destid = destid;
	}

	/**
	 * 
	 * @return  parentdestid - {@link String} Parent Destination User id
	 */
	public String getParentdestid() {
		return parentdestid;
	}

	/**
	 * 
	 * @param parentdestid {@link String} Parent Destination User id
	 */
	public void setParentdestid(String parentdestid) {
		this.parentdestid = parentdestid;
	}

	/**
	 * 
	 * @return  rootdestid - {@link String} Root Destination User id
	 */
	public String getRootdestid() {
		return rootdestid;
	}

	/**
	 * 
	 * @param rootdestid {@link String} Root Destination User id
	 */
	public void setRootdestid(String rootdestid) {
		this.rootdestid = rootdestid;
	}

	/**
	 * 
	 * @return  amount - {@link BigDecimal} Amount of transaction
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 
	 * @param amount {@link BigDecimal} Amount of transaction
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 
	 * @return  fee - {@link BigDecimal} Fee of transaction
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * 
	 * @param fee {@link BigDecimal} Fee of transaction
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	/**
	 * 
	 * @return  acommission - {@link BigDecimal} Agent Commission of transaction
	 */
	public BigDecimal getAcommission() {
		return acommission;
	}
	
	/**
	 * 
	 * @param acommission {@link BigDecimal} Agent Commission of transaction
	 */
	public void setAcommission(BigDecimal acommission) {
		this.acommission = acommission;
	}

	/**
	 * 
	 * @return  tax - {@link BigDecimal} Tax of transaction
	 */
	public BigDecimal getTax() {
		return tax;
	}

	/**
	 * 
	 * @param tax {@link BigDecimal} Tax of transaction
	 */
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	/**
	 * 
	 * @return  srcwalbal - {@link BigDecimal} Source Wallet balance
	 */
	public BigDecimal getSrcwalbal() {
		return srcwalbal;
	}

	/**
	 * 
	 * @param srcwalbal {@link BigDecimal} Source Wallet balance
	 */
	public void setSrcwalbal(BigDecimal srcwalbal) {
		this.srcwalbal = srcwalbal;
	}

	/**
	 * 
	 * @return  destwalbal - {@link BigDecimal} Destination Wallet balance
	 */
	public BigDecimal getDestwalbal() {
		return destwalbal;
	}

	/**
	 * 
	 * @param destwalbal {@link BigDecimal} Destination Wallet balance
	 */
	public void setDestwalbal(BigDecimal destwalbal) {
		this.destwalbal = destwalbal;
	}

	/**
	 * 
	 * @return  odspsubsbal - {@link BigDecimal} Original Service provider subscriber balance
	 */
	public BigDecimal getOdspsubsbal() {
		return odspsubsbal;
	}

	/**
	 * 
	 * @param odspsubsbal {@link BigDecimal} Original Service provider subscriber balance
	 */
	public void setOdspsubsbal(BigDecimal odspsubsbal) {
		this.odspsubsbal = odspsubsbal;
	}

	/**
	 * 
	 * @return  odspwspbal - {@link BigDecimal} Original Service provider wsp balance
	 */
	public BigDecimal getOdspwspbal() {
		return odspwspbal;
	}

	/**
	 * 
	 * @param odspwspbal {@link BigDecimal} Original Service provider wsp balance
	 */
	public void setOdspwspbal(BigDecimal odspwspbal) {
		this.odspwspbal = odspwspbal;
	}

	/**
	 * 
	 * @return  currency - {@link String} Currency of transaction
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 
	 * @param currency {@link String} Currency of transaction
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * 
	 * @return  camount - {@link BigDecimal} Commission Amount of transaction
	 */
	public BigDecimal getCamount() {
		return camount;
	}

	/**
	 * 
	 * @param camount {@link BigDecimal} Commission Amount of transaction
	 */
	public void setCamount(BigDecimal camount) {
		this.camount = camount;
	}

	/**
	 * 
	 * @return  wspcommission - {@link BigDecimal} WSP commission
	 */
	public BigDecimal getWspcommission() {
		return wspcommission;
	}

	/**
	 * 
	 * @param wspcommission {@link BigDecimal} WSP commission
	 */
	public void setWspcommission(BigDecimal wspcommission) {
		this.wspcommission = wspcommission;
	}

	/**
	 * 
	 * @return  wspcommission - {@link BigDecimal} original or inventory conversion rate
	 */
	public BigDecimal getOcrate() {
		return ocrate;
	}

	/**
	 * 
	 * @param wspcommission {@link BigDecimal} original or inventory conversion rate
	 */
	public void setOcrate(BigDecimal ocrate) {
		this.ocrate = ocrate;
	}

	/**
	 * 
	 * @return  ccurrency - {@link BigDecimal} Commission currency
	 */
	public String getCcurrency() {
		return ccurrency;
	}

	/**
	 * 
	 * @param ccurrency {@link BigDecimal} Commission currency
	 */
	public void setCcurrency(String ccurrency) {
		this.ccurrency = ccurrency;
	}

	/**
	 * 
	 * @return  status - {@link String} Transaction status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status {@link String} Transaction status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return  txndatetime - {@link Date} Transaction Date time
	 */
	public Date getTxndatetime() {
		return txndatetime;
	}

	/**
	 * 
	 * @param txndatetime {@link Date} Transaction Date time
	 */
	public void setTxndatetime(Date txndatetime) {
		this.txndatetime = txndatetime;
	}

	/**
	 * 
	 * @return  txndatetimestr - {@link String} Transaction date time string
	 */
	public String getTxndatetimestr() {
		return txndatetimestr;
	}

	/**
	 * 
	 * @param txndatetimestr {@link String} Transaction Date time string
	 */
	public void setTxndatetimestr(String txndatetimestr) {
		this.txndatetimestr = txndatetimestr;
	}

	/**
	 * 
	 * @return  ptxndatetime - {@link Date} Parent Transaction date time
	 */
	public Date getPtxndatetime() {
		return ptxndatetime;
	}

	/**
	 * 
	 * @param ptxndatetime {@link Date} Parent Transaction date time
	 */
	public void setPtxndatetime(Date ptxndatetime) {
		this.ptxndatetime = ptxndatetime;
	}

	/**
	 * 
	 * @return  ptxndatetimestr - {@link String} Parent Transaction date time string
	 */
	public String getPtxndatetimestr() {
		return ptxndatetimestr;
	}

	/**
	 * 
	 * @param ptxndatetimestr {@link String} Parent Transaction date time string
	 */
	public void setPtxndatetimestr(String ptxndatetimestr) {
		this.ptxndatetimestr = ptxndatetimestr;
	}

	/**
	 * 
	 * @return  paysrc - {@link String} Payment src
	 */
	public String getPaysrc() {
		return paysrc;
	}

	/**
	 * 
	 * @param paysrc {@link String} Payment src
	 */
	public void setPaysrc(String paysrc) {
		this.paysrc = paysrc;
	}

	/**
	 * 
	 * @return  apayref - {@link String} Aggregator Payment Reference
	 */
	public String getApayref() {
		return apayref;
	}

	/**
	 * 
	 * @param apayref {@link String} Aggregator Payment Reference
	 */
	public void setApayref(String apayref) {
		this.apayref = apayref;
	}

	/**
	 * 
	 * @return  opayref - {@link String} Original Payment Reference
	 */
	public String getOpayref() {
		return opayref;
	}

	/**
	 * 
	 * @param opayref {@link String} Original Payment Reference
	 */
	public void setOpayref(String opayref) {
		this.opayref = opayref;
	}

	/**
	 * 
	 * @return  agenttxnid - {@link String} Agent Transaction Id
	 */
	public String getAgenttxnid() {
		return agenttxnid;
	}

	/**
	 * 
	 * @param agenttxnid {@link String} Agent Transaction Id
	 */
	public void setAgenttxnid(String agenttxnid) {
		this.agenttxnid = agenttxnid;
	}

	/**
	 * 
	 * @return  adspref - {@link String} Aggregator digital service provider reference id
	 */
	public String getAdspref() {
		return adspref;
	}

	/**
	 * 
	 * @param adspref {@link String} Aggregator digital service provider reference id
	 */
	public void setAdspref(String adspref) {
		this.adspref = adspref;
	}

	/**
	 * 
	 * @return  odspref - {@link String} Original digital service provider reference id
	 */
	public String getOdspref() {
		return odspref;
	}

	/**
	 * 
	 * @param odspref {@link String} Original digital service provider reference id
	 */
	public void setOdspref(String odspref) {
		this.odspref = odspref;
	}

	/**
	 * 
	 * @return  comment - {@link String} free text comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * 
	 * @param comment {@link String} free text comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 
	 * @param WalTxn {@link WalTxn}
	 */
	@Override
	@javax.persistence.Transient
	public WalTxn mergeUpdates(WalTxn tobemerged) {
		this.setTxnid((null != tobemerged.getTxnid() && !tobemerged.getTxnid().trim().equals(""))
				? tobemerged.getTxnid() : this.getTxnid());
		this.setParenttxnid((null != tobemerged.getParenttxnid() && !tobemerged.getParenttxnid().trim().equals(""))
				? tobemerged.getParenttxnid() : this.getParenttxnid());
		this.setWsp((null != tobemerged.getWsp() && !tobemerged.getWsp().trim().equals("")) ? tobemerged.getWsp()
				: this.getWsp());
		this.setRootid((null != tobemerged.getRootid() && !tobemerged.getRootid().trim().equals(""))
				? tobemerged.getRootid() : this.getRootid());
		this.setAdsp((null != tobemerged.getAdsp() && !tobemerged.getAdsp().trim().equals("")) ? tobemerged.getAdsp()
				: this.getAdsp());
		this.setOdsp((null != tobemerged.getOdsp() && !tobemerged.getOdsp().trim().equals("")) ? tobemerged.getOdsp()
				: this.getOdsp());
		this.setChannel((null != tobemerged.getChannel() && !tobemerged.getChannel().trim().equals(""))
				? tobemerged.getChannel() : this.getChannel());
		this.setIpaddress((null != tobemerged.getIpaddress() && !tobemerged.getIpaddress().trim().equals(""))
				? tobemerged.getIpaddress() : this.getIpaddress());
		this.setReqsrc((null != tobemerged.getReqsrc() && !tobemerged.getReqsrc().trim().equals(""))
				? tobemerged.getReqsrc() : this.getReqsrc());
		this.setTxntype((null != tobemerged.getTxntype() && !tobemerged.getTxntype().trim().equals(""))
				? tobemerged.getTxntype() : this.getTxntype());
		this.setProduct((null != tobemerged.getProduct() && !tobemerged.getProduct().trim().equals(""))
				? tobemerged.getProduct() : this.getProduct());
		this.setSrctype((null != tobemerged.getSrctype() && !tobemerged.getSrctype().trim().equals(""))
				? tobemerged.getSrctype() : this.getSrctype());
		this.setSrcid((null != tobemerged.getSrcid() && !tobemerged.getSrcid().trim().equals(""))
				? tobemerged.getSrcid() : this.getSrcid());
		this.setFeepayer((null != tobemerged.getFeepayer() && !tobemerged.getFeepayer().trim().equals(""))
				? tobemerged.getFeepayer() : this.getFeepayer());
		this.setCommpayer((null != tobemerged.getCommpayer() && !tobemerged.getCommpayer().trim().equals(""))
				? tobemerged.getCommpayer() : this.getCommpayer());
		this.setTxnorigsrc((null != tobemerged.getTxnorigsrc() && !tobemerged.getTxnorigsrc().trim().equals(""))
				? tobemerged.getTxnorigsrc() : this.getTxnorigsrc());
		this.setParentsrcid((null != tobemerged.getParentsrcid() && !tobemerged.getParentsrcid().trim().equals(""))
				? tobemerged.getParentsrcid() : this.getParentsrcid());
		this.setRootsrcid((null != tobemerged.getRootsrcid() && !tobemerged.getRootsrcid().trim().equals(""))
				? tobemerged.getRootsrcid() : this.getRootsrcid());
		this.setSrcdeviceid((null != tobemerged.getSrcdeviceid() && !tobemerged.getSrcdeviceid().trim().equals(""))
				? tobemerged.getSrcdeviceid() : this.getSrcdeviceid());
		this.setOdspsubsid((null != tobemerged.getOdspsubsid() && !tobemerged.getOdspsubsid().trim().equals(""))
				? tobemerged.getOdspsubsid() : this.getOdspsubsid());
		this.setOdspsubsaccnt(
				(null != tobemerged.getOdspsubsaccnt() && !tobemerged.getOdspsubsaccnt().trim().equals(""))
						? tobemerged.getOdspsubsaccnt() : this.getOdspsubsaccnt());
		this.setDesttype((null != tobemerged.getDesttype() && !tobemerged.getDesttype().trim().equals(""))
				? tobemerged.getDesttype() : this.getDesttype());
		this.setDestid((null != tobemerged.getDestid() && !tobemerged.getDestid().trim().equals(""))
				? tobemerged.getDestid() : this.getDestid());
		this.setParentdestid((null != tobemerged.getParentdestid() && !tobemerged.getParentdestid().trim().equals(""))
				? tobemerged.getParentdestid() : this.getParentdestid());
		this.setRootdestid((null != tobemerged.getRootdestid() && !tobemerged.getRootdestid().trim().equals(""))
				? tobemerged.getRootdestid() : this.getRootdestid());
		this.setAmount((null != tobemerged.getAmount() ? tobemerged.getAmount() : this.getAmount()));
		this.setFee((null != tobemerged.getFee() ? tobemerged.getFee() : this.getFee()));
		this.setAcommission(
				(null != tobemerged.getAcommission() ? tobemerged.getAcommission() : this.getAcommission()));
		this.setTax((null != tobemerged.getTax() ? tobemerged.getTax() : this.getTax()));
		this.setSrcwalbal((null != tobemerged.getSrcwalbal() ? tobemerged.getSrcwalbal() : this.getSrcwalbal()));
		this.setDestwalbal((null != tobemerged.getDestwalbal() ? tobemerged.getDestwalbal() : this.getDestwalbal()));
		this.setOdspsubsbal(
				(null != tobemerged.getOdspsubsbal() ? tobemerged.getOdspsubsbal() : this.getOdspsubsbal()));
		this.setOdspwspbal((null != tobemerged.getOdspwspbal() ? tobemerged.getOdspwspbal() : this.getOdspwspbal()));
		this.setCurrency((null != tobemerged.getCurrency() && !tobemerged.getCurrency().trim().equals(""))
				? tobemerged.getCurrency() : this.getCurrency());
		this.setCamount((null != tobemerged.getCamount() ? tobemerged.getCamount() : this.getCamount()));
		this.setWspcommission(
				(null != tobemerged.getWspcommission() ? tobemerged.getWspcommission() : this.getWspcommission()));
		this.setOcrate((null != tobemerged.getOcrate() ? tobemerged.getOcrate() : this.getOcrate()));
		this.setCcurrency((null != tobemerged.getCcurrency() && !tobemerged.getCcurrency().trim().equals(""))
				? tobemerged.getCcurrency() : this.getCcurrency());
		this.setStatus((null != tobemerged.getStatus() && !tobemerged.getStatus().trim().equals(""))
				? tobemerged.getStatus() : this.getStatus());
		this.setTxndatetime(
				(null != tobemerged.getTxndatetime() ? tobemerged.getTxndatetime() : this.getTxndatetime()));
		this.setTxndatetimestr(
				(null != tobemerged.getTxndatetimestr() && !tobemerged.getTxndatetimestr().trim().equals(""))
						? tobemerged.getTxndatetimestr() : this.getTxndatetimestr());
		this.setPtxndatetime(
				(null != tobemerged.getPtxndatetime() ? tobemerged.getPtxndatetime() : this.getPtxndatetime()));
		this.setPtxndatetimestr(
				(null != tobemerged.getPtxndatetimestr() && !tobemerged.getPtxndatetimestr().trim().equals(""))
						? tobemerged.getPtxndatetimestr() : this.getPtxndatetimestr());
		this.setPaysrc((null != tobemerged.getPaysrc() && !tobemerged.getPaysrc().trim().equals(""))
				? tobemerged.getPaysrc() : this.getPaysrc());
		this.setApayref((null != tobemerged.getApayref() && !tobemerged.getApayref().trim().equals(""))
				? tobemerged.getApayref() : this.getApayref());
		this.setOpayref((null != tobemerged.getOpayref() && !tobemerged.getOpayref().trim().equals(""))
				? tobemerged.getOpayref() : this.getOpayref());
		this.setAgenttxnid((null != tobemerged.getAgenttxnid() && !tobemerged.getAgenttxnid().trim().equals(""))
				? tobemerged.getAgenttxnid() : this.getAgenttxnid());
		this.setAdspref((null != tobemerged.getAdspref() && !tobemerged.getAdspref().trim().equals(""))
				? tobemerged.getAdspref() : this.getAdspref());
		this.setOdspref((null != tobemerged.getOdspref() && !tobemerged.getOdspref().trim().equals(""))
				? tobemerged.getOdspref() : this.getOdspref());
		this.setComment((null != tobemerged.getComment() && !tobemerged.getComment().trim().equals(""))
				? tobemerged.getComment() : this.getComment());
		this.setCreated((null != tobemerged.getCreated() ? tobemerged.getCreated() : this.getCreated()));
		this.setCreatedstr((null != tobemerged.getCreatedstr() && !tobemerged.getCreatedstr().trim().equals(""))
				? tobemerged.getCreatedstr() : this.getCreatedstr());

		return this;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
