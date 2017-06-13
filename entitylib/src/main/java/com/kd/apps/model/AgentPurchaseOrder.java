package com.kd.apps.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class AgentPurchaseOrder extends BaseAutoIdEntity<AgentPurchaseOrder> {
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

	// po amount
	@Column(precision = 32, scale = 4)
	private BigDecimal amount;

	// if any fee amount
	@Column(precision = 32, scale = 4)
	private BigDecimal fee;

	// if any tax amount
	@Column(precision = 32, scale = 4)
	private BigDecimal taxamount;

	// if any fee amount
	@Column(precision = 32, scale = 4)
	private BigDecimal commission;

	// currency
	@Column
	private String currency;

	// payment source
	// bank name etc.
	@Column
	private String paysrc;

	// payment txn id
	// or cheque num
	@Column
	private String payref;

	// purchase order datetime
	@Column
	private Date podatetime;

	// purchase order datetime string
	@Transient
	private String podatetimestr;

	// po credit status
	@Column
	private String status;

	// po credit datetime
	@Column
	private Date pocreditdatetime;

	// po credit datetime string
	@Transient
	private String pocreditdatetimestr;

	// free form comments
	@Column
	private String comments;

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
	 * @return amount -  {@link BigDecimal} amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 
	 * @param amount {@link BigDecimal} amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 
	 * @return fee -  {@link BigDecimal} fee
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * 
	 * @param fee {@link BigDecimal} fee
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	/**
	 * 
	 * @return taxamount -  {@link BigDecimal} tax amount
	 */
	public BigDecimal getTaxamount() {
		return taxamount;
	}

	/**
	 * 
	 * @param taxamount {@link BigDecimal} tax amount
	 */
	public void setTaxamount(BigDecimal taxamount) {
		this.taxamount = taxamount;
	}

	/**
	 * 
	 * @return commission -  {@link BigDecimal} commission
	 */
	public BigDecimal getCommission() {
		return commission;
	}

	/**
	 * 
	 * @param commission {@link BigDecimal} commission
	 */
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
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
	 * @return podatetime -  {@link Date} purchase order datetime
	 */
	public Date getPodatetime() {
		return podatetime;
	}

	/**
	 * 
	 * @param podatetime {@link Date} purchase order datetime
	 */
	public void setPodatetime(Date podatetime) {
		this.podatetime = podatetime;
	}

	/**
	 * 
	 * @return podatetimestr -  {@link String} purchase order datetime string
	 */
	public String getPodatetimestr() {
		return podatetimestr;
	}

	/**
	 * 
	 * @param podatetimestr {@link String} purchase order datetime string
	 */
	public void setPodatetimestr(String podatetimestr) {
		this.podatetimestr = podatetimestr;
	}

	/**
	 * 
	 * @return status -  {@link String} purchase order credit status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status {@link String} purchase order credit status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return pocreditdatetime -  {@link Date} purchase order credit datetime
	 */
	public Date getPocreditdatetime() {
		return pocreditdatetime;
	}

	/**
	 * 
	 * @param pocreditdatetime {@link Date} purchase order credit datetime
	 */
	public void setPocreditdatetime(Date pocreditdatetime) {
		this.pocreditdatetime = pocreditdatetime;
	}

	/**
	 * 
	 * @return pocreditdatetimestr -  {@link String} purchase order credit datetime string
	 */
	public String getPocreditdatetimestr() {
		return pocreditdatetimestr;
	}

	/**
	 * 
	 * @param pocreditdatetimestr {@link String} purchase order credit datetime string
	 */
	public void setPocreditdatetimestr(String pocreditdatetimestr) {
		this.pocreditdatetimestr = pocreditdatetimestr;
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
	 * @param AgentPurchaseOrder {@link AgentPurchaseOrder} 
	 */
	@Override
	public AgentPurchaseOrder mergeUpdates(AgentPurchaseOrder tobemerged) {

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
		;
		this.amount = (null != tobemerged.getAmount() ? tobemerged.getAmount() : this.getAmount());
		;
		this.fee = (null != tobemerged.getFee() ? tobemerged.getFee() : this.getFee());
		;
		this.taxamount = (null != tobemerged.getTaxamount() ? tobemerged.getTaxamount() : this.getTaxamount());
		;
		this.commission = (null != tobemerged.getCommission() ? tobemerged.getCommission() : this.getCommission());
		;
		this.currency = (null != tobemerged.getCurrency() && !("".equals(tobemerged.getCurrency()))
				&& !(" ".equals(tobemerged.getCurrency())) ? tobemerged.getCurrency() : this.getCurrency());
		;
		this.paysrc = (null != tobemerged.getPaysrc() && !("".equals(tobemerged.getPaysrc()))
				&& !(" ".equals(tobemerged.getPaysrc())) ? tobemerged.getPaysrc() : this.getPaysrc());
		;
		this.payref = (null != tobemerged.getPayref() && !("".equals(tobemerged.getPayref()))
				&& !(" ".equals(tobemerged.getPayref())) ? tobemerged.getPayref() : this.getPayref());
		;
		this.podatetime = (null != tobemerged.getPodatetime() ? tobemerged.getPodatetime() : this.getPodatetime());
		;
		this.podatetimestr = (null != tobemerged.getPodatetimestr() && !("".equals(tobemerged.getPodatetimestr()))
				&& !(" ".equals(tobemerged.getPodatetimestr())) ? tobemerged.getPodatetimestr()
						: this.getPodatetimestr());
		;
		this.status = (null != tobemerged.getStatus() && !("".equals(tobemerged.getStatus()))
				&& !(" ".equals(tobemerged.getStatus())) ? tobemerged.getStatus() : this.getStatus());
		;
		this.pocreditdatetime = (null != tobemerged.getPocreditdatetime() ? tobemerged.getPocreditdatetime()
				: this.getPocreditdatetime());
		;
		this.pocreditdatetimestr = (null != tobemerged.getPocreditdatetimestr()
				&& !("".equals(tobemerged.getPocreditdatetimestr()))
				&& !(" ".equals(tobemerged.getPocreditdatetimestr())) ? tobemerged.getPocreditdatetimestr()
						: this.getPocreditdatetimestr());
		;
		this.comments = (null != tobemerged.getComments() && !("".equals(tobemerged.getComments()))
				&& !(" ".equals(tobemerged.getComments())) ? tobemerged.getComments() : this.getComments());
		;
		return this;
	}

}
