package com.kd.apps.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class AgentInvoice extends BaseAutoIdEntity<AgentInvoice> {
	private static final long serialVersionUID = 1L;

	// START
	// wallet service provider id or tenant id
	@Column
	private String wsp;

	// username of wallet owner
	@Column
	private String username;

	// superadmin = superadmin
	// merchant = merchant
	// customer = customer
	@Column
	private String usertype;

	// superadmin = superadmin

	// Merchant Subtypes - mui/mscp and web terminal or web pos
	// superagent = super agent
	// agent = agent
	// subagent = sub agent
	// retailer = retailer
	// psp = payment service provider
	// wsp = wallet service provider
	// emp = merchant employee

	// Customer Subtypes - cui/cscp
	// customer = customer
	@Column
	private String usersubtype;

	// total amount of txn
	@Column(precision = 32, scale = 4)
	private BigDecimal totaltxnamount;

	// total fee amount
	@Column(precision = 32, scale = 4)
	private BigDecimal totalfee;

	// total tax amount
	@Column(precision = 32, scale = 4)
	private BigDecimal totaltaxamount;

	// total commission amount
	@Column(precision = 32, scale = 4)
	private BigDecimal totalcommission;

	// currency
	@Column
	private String currency;

	// due datetime of payment
	@Column
	private Date duedatetime;

	// due datetime of payment string
	@Transient
	private String duedatetimestr;

	// payment status
	@Column
	private String status;

	// actual datetime of payment
	@Column
	private Date paydatetime;

	// actual datetime of payment string
	@Transient
	private String paydatetimestr;

	// payment source
	// bank name etc.
	@Column
	private String paysrc;

	// payment txn id
	// or cheque num
	@Column
	private String payref;

	// free form comments
	@Column
	private String comments;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "agentinvoice", fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<AgentInvoiceItem> agentInvoiceItems;

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
	 * @return username - {@link String} username of wallet owner
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username {@link String} username of wallet owner
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return usertype - {@link String} usertype of wallet user admin, merchant,customer
	 */
	public String getUsertype() {
		return usertype;
	}

	/**
	 * 
	 * @param usertype {@link String} usertype of wallet user admin, merchant,customer
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	/**
	 * 
	 * @return usersubtype -  {@link String} usersubtype of wallet user superadmin, super agent,agent, sub agent,retailer,psp , wsp,emp
 	 *		,customer
	 */
	public String getUsersubtype() {
		return usersubtype;
	}

	/**
	 * 
	 * @param usersubtype {@link String} usersubtype of wallet user superadmin, super agent,agent, sub agent,retailer,psp , wsp,emp
 	 *		,customer
	 */
	public void setUsersubtype(String usersubtype) {
		this.usersubtype = usersubtype;
	}

	/**
	 * 
	 * @return totaltxnamount -  {@link BigDecimal} total amount of txn
	 */
	public BigDecimal getTotaltxnamount() {
		return totaltxnamount;
	}

	/**
	 * 
	 * @param totaltxnamount {@link BigDecimal} total amount of txn
	 */
	public void setTotaltxnamount(BigDecimal totaltxnamount) {
		this.totaltxnamount = totaltxnamount;
	}

	/**
	 * 
	 * @return totalfee -  {@link BigDecimal} total fee
	 */
	public BigDecimal getTotalfee() {
		return totalfee;
	}

	/**
	 * 
	 * @param totalfee {@link BigDecimal} total fee
	 */
	public void setTotalfee(BigDecimal totalfee) {
		this.totalfee = totalfee;
	}

	/**
	 * 
	 * @return totaltaxamount -  {@link BigDecimal} total tax amount
	 */
	public BigDecimal getTotaltaxamount() {
		return totaltaxamount;
	}

	/**
	 * 
	 * @param totaltaxamount {@link BigDecimal} total tax amount
	 */
	public void setTotaltaxamount(BigDecimal totaltaxamount) {
		this.totaltaxamount = totaltaxamount;
	}

	/**
	 * 
	 * @return totalcommission -  {@link BigDecimal} total commission
	 */
	public BigDecimal getTotalcommission() {
		return totalcommission;
	}

	/**
	 * 
	 * @param totalcommission {@link BigDecimal} total commission
	 */
	public void setTotalcommission(BigDecimal totalcommission) {
		this.totalcommission = totalcommission;
	}

	/**
	 * 
	 * @return currency -  {@link String} currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 
	 * @param currency {@link String} currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * 
	 * @return duedatetime -  {@link Date} due datetime of payment
	 */
	public Date getDuedatetime() {
		return duedatetime;
	}

	/**
	 * 
	 * @param duedatetime {@link Date} due datetime of payment
	 */
	public void setDuedatetime(Date duedatetime) {
		this.duedatetime = duedatetime;
	}

	/**
	 * 
	 * @return duedatetimestr -  {@link String} due datetime string of payment
	 */
	public String getDuedatetimestr() {
		return duedatetimestr;
	}

	/**
	 * 
	 * @param duedatetimestr {@link String} due datetime string of payment
	 */
	public void setDuedatetimestr(String duedatetimestr) {
		this.duedatetimestr = duedatetimestr;
	}

	/**
	 * 
	 * @return status -  {@link String} payment status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status {@link String} payment status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return paydatetime -  {@link Date} actual datetime of payment
	 */
	public Date getPaydatetime() {
		return paydatetime;
	}

	/**
	 * 
	 * @param paydatetime {@link Date} actual datetime of payment
	 */
	public void setPaydatetime(Date paydatetime) {
		this.paydatetime = paydatetime;
	}

	/**
	 * 
	 * @return paydatetimestr -  {@link String} actual datetime string of payment
	 */
	public String getPaydatetimestr() {
		return paydatetimestr;
	}

	/**
	 * 
	 * @param paydatetimestr {@link String} actual datetime string of payment
	 */
	public void setPaydatetimestr(String paydatetimestr) {
		this.paydatetimestr = paydatetimestr;
	}

	/**
	 * 
	 * @return paysrc - {@link String} payment source bank name etc
	 */
	public String getPaysrc() {
		return paysrc;
	}

	/**
	 * 
	 * @param paysrc {@link String} payment source bank name etc
	 */	 
	public void setPaysrc(String paysrc) {
		this.paysrc = paysrc;
	}

	/**
	 * 
	 * @return payref - {@link String} payment txn id or cheque num
	 */
	public String getPayref() {
		return payref;
	}
	
	/**
	 * 
	 * @param payref {@link String} payment txn id or cheque num
	 */	
	public void setPayref(String payref) {
		this.payref = payref;
	}

	/**
	 * 
	 * @return comments - {@link String} free form comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * 
	 * @param comments {@link String} free form comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * 
	 * @return Set<AgentInvoiceItem> - {@link AgentInvoiceItem} reference to AgentInvoiceItem
	 */
	public Set<AgentInvoiceItem> getAgentInvoiceItems() {
		return agentInvoiceItems;
	}

	/**
	 * 
	 * @param Set<AgentInvoiceItem> {@link AgentInvoiceItem} reference to AgentInvoiceItem
	 */
	public void setAgentInvoiceItems(Set<AgentInvoiceItem> agentInvoiceItems) {
		this.agentInvoiceItems = agentInvoiceItems;
	}

	/**
	 * 
	 * @param AgentInvoice {@link AgentInvoice} 
	 */
	@Override
	public AgentInvoice mergeUpdates(AgentInvoice tobemerged) {
		this.wsp = (null != tobemerged.getWsp() && !("".equals(tobemerged.getWsp()))
				&& !(" ".equals(tobemerged.getWsp())) ? tobemerged.getWsp() : this.getWsp());
		;
		this.username = (null != tobemerged.getUsername() && !("".equals(tobemerged.getUsername()))
				&& !(" ".equals(tobemerged.getUsername())) ? tobemerged.getUsername() : this.getUsername());
		;
		this.usertype = (null != tobemerged.getUsertype() && !("".equals(tobemerged.getUsertype()))
				&& !(" ".equals(tobemerged.getUsertype())) ? tobemerged.getUsertype() : this.getUsertype());
		;
		this.usersubtype = (null != tobemerged.getUsersubtype() && !("".equals(tobemerged.getUsersubtype()))
				&& !(" ".equals(tobemerged.getUsersubtype())) ? tobemerged.getUsersubtype() : this.getUsersubtype());
		this.totaltxnamount = (null != tobemerged.getTotaltxnamount() ? tobemerged.getTotaltxnamount()
				: this.getTotaltxnamount());
		this.totalfee = (null != tobemerged.getTotalfee() ? tobemerged.getTotalfee() : this.getTotalfee());
		this.totaltaxamount = (null != tobemerged.getTotaltaxamount() ? tobemerged.getTotaltaxamount()
				: this.getTotaltaxamount());
		this.totalcommission = (null != tobemerged.getTotalcommission() ? tobemerged.getTotalcommission()
				: this.getTotalcommission());
		this.currency = (null != tobemerged.getCurrency() && !("".equals(tobemerged.getCurrency()))
				&& !(" ".equals(tobemerged.getCurrency())) ? tobemerged.getCurrency() : this.getCurrency());
		;
		this.duedatetime = (null != tobemerged.getDuedatetime() ? tobemerged.getDuedatetime() : this.getDuedatetime());
		this.duedatetimestr = (null != tobemerged.getDuedatetimestr() && !("".equals(tobemerged.getDuedatetimestr()))
				&& !(" ".equals(tobemerged.getDuedatetimestr())) ? tobemerged.getDuedatetimestr()
						: this.getDuedatetimestr());
		;
		this.status = (null != tobemerged.getStatus() && !("".equals(tobemerged.getStatus()))
				&& !(" ".equals(tobemerged.getStatus())) ? tobemerged.getStatus() : this.getStatus());
		;
		this.paydatetime = (null != tobemerged.getPaydatetime() ? tobemerged.getPaydatetime() : this.getPaydatetime());
		this.paydatetimestr = (null != tobemerged.getPaydatetimestr() && !("".equals(tobemerged.getPaydatetimestr()))
				&& !(" ".equals(tobemerged.getPaydatetimestr())) ? tobemerged.getPaydatetimestr()
						: this.getPaydatetimestr());
		;
		this.paysrc = (null != tobemerged.getPaysrc() && !("".equals(tobemerged.getPaysrc()))
				&& !(" ".equals(tobemerged.getPaysrc())) ? tobemerged.getPaysrc() : this.getPaysrc());
		this.payref = (null != tobemerged.getPayref() && !("".equals(tobemerged.getPayref()))
				&& !(" ".equals(tobemerged.getPayref())) ? tobemerged.getPayref() : this.getPayref());
		;
		this.comments = (null != tobemerged.getComments() && !("".equals(tobemerged.getComments()))
				&& !(" ".equals(tobemerged.getComments())) ? tobemerged.getComments() : this.getComments());
		return this;
	}
}
