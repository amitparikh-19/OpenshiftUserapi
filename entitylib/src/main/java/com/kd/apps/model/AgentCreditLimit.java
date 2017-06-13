package com.kd.apps.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class AgentCreditLimit extends BaseAutoIdEntity<AgentCreditLimit> {
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

	// maximum credit limit
	@Column(precision = 32, scale = 4)
	private BigDecimal maxcreditlimit;

	// invoice limit after which
	// agent should be notified
	// invoiced
	// of diminishing creditline
	@Column(precision = 32, scale = 4)
	private BigDecimal invoicelimit;

	// currency
	@Column
	private String currency;

	// start datetime
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date startdatetime;

	// start datetime string
	@Transient
	private String startdatetimestr;

	// end datetime
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date enddatetime;

	// end datetime string
	@Transient
	private String enddatetimestr;

	@Version
	private Long version;

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
	 * @return maxcreditlimit - {@link BigDecimal} maximum credit limit
	 */
	public BigDecimal getMaxcreditlimit() {
		return maxcreditlimit;
	}

	/**
	 * 
	 * @param maxcreditlimit {@link BigDecimal} maximum credit limit
	 */
	public void setMaxcreditlimit(BigDecimal maxcreditlimit) {
		this.maxcreditlimit = maxcreditlimit;
	}

	/**
	 * 
	 * @return invoicelimit - {@link BigDecimal} invoice limit after which agent should be notified invoiced of diminishing creditline
	 */
	public BigDecimal getInvoicelimit() {
		return invoicelimit;
	}

	/**
	 * 
	 * @param invoicelimit {@link BigDecimal} invoice limit after which agent should be notified invoiced of diminishing creditline
	 */
	public void setInvoicelimit(BigDecimal invoicelimit) {
		this.invoicelimit = invoicelimit;
	}

	/**
	 * 
	 * @return currency - {@link String} currency
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
	 * @return startdatetime - {@link Date} start datetime
	 */
	public Date getStartdatetime() {
		return startdatetime;
	}

	/**
	 * 
	 * @param startdatetime {@link Date} start datetime
	 */
	public void setStartdatetime(Date startdatetime) {
		this.startdatetime = startdatetime;
	}

	/**
	 * 
	 * @return startdatetimestr - {@link String} start datetime string
	 */
	public String getStartdatetimestr() {
		return startdatetimestr;
	}

	/**
	 * 
	 * @param startdatetimestr {@link String} start datetime string
	 */
	public void setStartdatetimestr(String startdatetimestr) {
		this.startdatetimestr = startdatetimestr;
	}

	/**
	 * 
	 * @return enddatetime - {@link Date} end datetime
	 */
	public Date getEnddatetime() {
		return enddatetime;
	}

	/**
	 * 
	 * @param enddatetime {@link Date} end datetime
	 */
	public void setEnddatetime(Date enddatetime) {
		this.enddatetime = enddatetime;
	}

	/**
	 * 
	 * @return enddatetimestr - {@link String} end datetime string
	 */
	public String getEnddatetimestr() {
		return enddatetimestr;
	}

	/**
	 * 
	 * @param enddatetimestr {@link String} end datetime string
	 */
	public void setEnddatetimestr(String enddatetimestr) {
		this.enddatetimestr = enddatetimestr;
	}

	/**
	 * 
	 * @return version - {@link Long} version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * 
	 * @param version {@link Long} version
	 */
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * 
	 * @param AgentCreditLimit {@link AgentCreditLimit} 
	 */
	@Override
	public AgentCreditLimit mergeUpdates(AgentCreditLimit tobemerged) {
		this.wsp = (null != tobemerged.getWsp() && !("".equals(tobemerged.getWsp()))
				&& !(" ".equals(tobemerged.getWsp())) ? tobemerged.getUsername() : this.getUsername());
		this.username = (null != tobemerged.getUsername() && !("".equals(tobemerged.getUsername()))
				&& !(" ".equals(tobemerged.getUsername())) ? tobemerged.getUsername() : this.getUsername());
		this.usertype = (null != tobemerged.getUsertype() && !("".equals(tobemerged.getUsertype()))
				&& !(" ".equals(tobemerged.getUsertype())) ? tobemerged.getUsertype() : this.getUsertype());
		this.usersubtype = (null != tobemerged.getUsersubtype() && !("".equals(tobemerged.getUsersubtype()))
				&& !(" ".equals(tobemerged.getUsersubtype())) ? tobemerged.getUsersubtype() : this.getUsersubtype());
		this.currency = (null != tobemerged.getCurrency() && !("".equals(tobemerged.getCurrency()))
				&& !(" ".equals(tobemerged.getCurrency())) ? tobemerged.getCurrency() : this.getCurrency());
		this.enddatetimestr = (null != tobemerged.getEnddatetimestr() ? tobemerged.getEnddatetimestr()
				: this.getEnddatetimestr());
		this.enddatetimestr = (null != tobemerged.getEnddatetimestr() && !("".equals(tobemerged.getEnddatetimestr()))
				&& !(" ".equals(tobemerged.getEnddatetimestr())) ? tobemerged.getEnddatetimestr()
						: this.getEnddatetimestr());
		this.startdatetimestr = (null != tobemerged.getStartdatetimestr()
				&& !("".equals(tobemerged.getStartdatetimestr())) && !(" ".equals(tobemerged.getStartdatetimestr()))
						? tobemerged.getStartdatetimestr() : this.getStartdatetimestr());
		this.startdatetime = (null != tobemerged.getStartdatetime() ? tobemerged.getStartdatetime()
				: this.getStartdatetime());
		this.enddatetime = (null != tobemerged.getEnddatetime() ? tobemerged.getEnddatetime() : this.getEnddatetime());
		this.version = (null != tobemerged.getVersion() ? tobemerged.getVersion() : this.getVersion());
		this.maxcreditlimit = (null != tobemerged.getMaxcreditlimit() ? tobemerged.getMaxcreditlimit()
				: this.getMaxcreditlimit());
		this.invoicelimit = (null != tobemerged.getInvoicelimit() ? tobemerged.getInvoicelimit()
				: this.getInvoicelimit());
		return this;
	}
}
