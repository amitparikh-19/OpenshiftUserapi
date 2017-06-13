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
public class AgentCommercial extends BaseAutoIdEntity<AgentCommercial> {
	private static final long serialVersionUID = 1L;

	// START
	// wallet service provider id or tenant id
	@Column
	private String wsp;

	// original digital service provider id
	@Column
	private String odsp;

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

	// product code
	@Column
	private String product;

	// sub product code
	@Column
	private String subproduct;

	// flag if product has fixed price
	@Column
	private Boolean fixedprice;

	// min price if it's price range
	@Column(precision = 32, scale = 4)
	private BigDecimal minprice;

	// max price if it's price range
	@Column(precision = 32, scale = 4)
	private BigDecimal maxprice;

	// default % commission that will be paid to agents
	@Column(precision = 32, scale = 4)
	private BigDecimal commission;

	// default fixed commission that will be paid to agents
	@Column(precision = 32, scale = 4)
	private BigDecimal fixedcommission;

	// default % fee that can
	// be collected from source
	@Column(precision = 32, scale = 4)
	private BigDecimal fee;

	// default fixed fee that can
	// be collected from source
	@Column(precision = 32, scale = 4)
	private BigDecimal fixedfee;

	// default % tax
	@Column(precision = 32, scale = 4)
	private BigDecimal tax;

	// default fixed tax
	@Column(precision = 32, scale = 4)
	private BigDecimal fixedtax;

	// Currency Code
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
	 * @return wsp - {@link String} wallet service provider id or tenant id
	 *           
	 */
	public String getWsp() {
		return wsp;
	}

	/**
	 * 
	 * @param wsp {@link String} wallet service provider id or tenant id
	 *      
	 */
	public void setWsp(String wsp) {
		this.wsp = wsp;
	}

	/**
	 * 
	 * @return odsp - {@link String}  original digital service provider id
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
	 * @return product - {@link String} product code
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
	 * @return subproduct - {@link String} sub product code
	 */
	public String getSubproduct() {
		return subproduct;
	}

	/**
	 * 
	 * @param subproduct {@link String} sub product code
	 */
	public void setSubproduct(String subproduct) {
		this.subproduct = subproduct;
	}
	
	/**
	 * 
	 * @return fixedprice -  {@link Boolean} flag if product has fixed price
	 */
	public Boolean getFixedprice() {
		return fixedprice;
	}

	/**
	 * 
	 * @param fixedprice {@link Boolean} flag if product has fixed price
	 */
	public void setFixedprice(Boolean fixedprice) {
		this.fixedprice = fixedprice;
	}

	/**
	 * 
	 * @return minprice - {@link BigDecimal} min price if it's price range
	 */
	public BigDecimal getMinprice() {
		return minprice;
	}

	/**
	 * 
	 * @param minprice {@link BigDecimal} min price if it's price range
	 */
	public void setMinprice(BigDecimal minprice) {
		this.minprice = minprice;
	}

	/**
	 * 
	 * @return maxprice - {@link BigDecimal} max price if it's price range
	 */
	public BigDecimal getMaxprice() {
		return maxprice;
	}

	/**
	 * 
	 * @param maxprice {@link BigDecimal} max price if it's price range
	 */
	public void setMaxprice(BigDecimal maxprice) {
		this.maxprice = maxprice;
	}

	/**
	 * 
	 * @return commission - {@link BigDecimal} default % commission that will be paid to agents
	 */
	public BigDecimal getCommission() {
		return commission;
	}

	/**
	 * 
	 * @param commission {@link BigDecimal} default % commission that will be paid to agents
	 */
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	/**
	 * 
	 * @return fixedcommission - {@link BigDecimal} default fixed commission that will be paid to agents
	 */
	public BigDecimal getFixedcommission() {
		return fixedcommission;
	}

	/**
	 * 
	 * @param fixedcommission {@link BigDecimal} default fixed commission that will be paid to agents
	 */
	public void setFixedcommission(BigDecimal fixedcommission) {
		this.fixedcommission = fixedcommission;
	}

	/**
	 * 
	 * @return fee - {@link BigDecimal} default % fee that can be collected from source
	 */
	public BigDecimal getFee() {
		return fee;
	}
	
	/**
	 * 
	 * @param fee {@link BigDecimal} default % fee that can be collected from source
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	/**
	 * 
	 * @return fee - {@link BigDecimal} default fixed fee that can be collected from source
	 */
	public BigDecimal getFixedfee() {
		return fixedfee;
	}

	/**
	 * 
	 * @param fee {@link BigDecimal} default fixed fee that can be collected from source
	 */
	public void setFixedfee(BigDecimal fixedfee) {
		this.fixedfee = fixedfee;
	}

	/**
	 * 
	 * @return tax - {@link BigDecimal} default % tax
	 */
	public BigDecimal getTax() {
		return tax;
	}

	/**
	 * 
	 * @param tax {@link BigDecimal} default % tax
	 */
	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	/**
	 * 
	 * @return tax - {@link BigDecimal} default fixed tax
	 */
	public BigDecimal getFixedtax() {
		return fixedtax;
	}

	/**
	 * 
	 * @param tax {@link BigDecimal} default fixed tax
	 */
	public void setFixedtax(BigDecimal fixedtax) {
		this.fixedtax = fixedtax;
	}

	/**
	 * 
	 * @return currency - {@link String} currency code
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 
	 * @param currency {@link String} currency code
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
	 * @param AgentCommercial {@link AgentCommercial} 
	 */
	@Override
	public AgentCommercial mergeUpdates(AgentCommercial tobemerged) {
		this.wsp = (null != tobemerged.getWsp() && !("".equals(tobemerged.getWsp()))
				&& !(" ".equals(tobemerged.getWsp())) ? tobemerged.getUsername() : this.getUsername());
		this.odsp = (null != tobemerged.getOdsp() && !("".equals(tobemerged.getOdsp()))
				&& !(" ".equals(tobemerged.getOdsp())) ? tobemerged.getOdsp() : this.getOdsp());
		this.username = (null != tobemerged.getUsername() && !("".equals(tobemerged.getUsername()))
				&& !(" ".equals(tobemerged.getUsername())) ? tobemerged.getUsername() : this.getUsername());
		this.usertype = (null != tobemerged.getUsertype() && !("".equals(tobemerged.getUsertype()))
				&& !(" ".equals(tobemerged.getUsertype())) ? tobemerged.getUsertype() : this.getUsertype());
		this.usersubtype = (null != tobemerged.getUsersubtype() && !("".equals(tobemerged.getUsersubtype()))
				&& !(" ".equals(tobemerged.getUsersubtype())) ? tobemerged.getUsersubtype() : this.getUsersubtype());
		this.currency = (null != tobemerged.getCurrency() && !("".equals(tobemerged.getCurrency()))
				&& !(" ".equals(tobemerged.getCurrency())) ? tobemerged.getCurrency() : this.getCurrency());
		this.product = (null != tobemerged.getProduct() && !("".equals(tobemerged.getProduct()))
				&& !(" ".equals(tobemerged.getProduct())) ? tobemerged.getProduct() : this.getProduct());
		this.subproduct = (null != tobemerged.getSubproduct() && !("".equals(tobemerged.getSubproduct()))
				&& !(" ".equals(tobemerged.getSubproduct())) ? tobemerged.getSubproduct() : this.getSubproduct());
		this.enddatetimestr = (null != tobemerged.getEnddatetimestr() ? tobemerged.getEnddatetimestr()
				: this.getEnddatetimestr());
		this.enddatetimestr = (null != tobemerged.getEnddatetimestr() && !("".equals(tobemerged.getEnddatetimestr()))
				&& !(" ".equals(tobemerged.getEnddatetimestr())) ? tobemerged.getEnddatetimestr()
						: this.getEnddatetimestr());
		this.startdatetimestr = (null != tobemerged.getStartdatetimestr()
				&& !("".equals(tobemerged.getStartdatetimestr())) && !(" ".equals(tobemerged.getStartdatetimestr()))
						? tobemerged.getStartdatetimestr() : this.getStartdatetimestr());
		this.fixedprice = (null != tobemerged.getFixedprice() ? tobemerged.getFixedprice() : this.getFixedprice());
		this.fixedcommission = (null != tobemerged.getFixedcommission() ? tobemerged.getFixedcommission()
				: this.getFixedcommission());
		this.fixedfee = (null != tobemerged.getFixedfee() ? tobemerged.getFixedfee() : this.getFixedfee());
		this.fixedtax = (null != tobemerged.getFixedtax() ? tobemerged.getFixedtax() : this.getFixedtax());
		this.minprice = (null != tobemerged.getMinprice() ? tobemerged.getMinprice() : this.getMinprice());
		this.maxprice = (null != tobemerged.getMaxprice() ? tobemerged.getMaxprice() : this.getMaxprice());
		this.fee = (null != tobemerged.getFee() ? tobemerged.getFee() : this.getFee());
		this.commission = (null != tobemerged.getCommission() ? tobemerged.getCommission() : this.getCommission());
		this.tax = (null != tobemerged.getTax() ? tobemerged.getTax() : this.getTax());
		this.startdatetime = (null != tobemerged.getStartdatetime() ? tobemerged.getStartdatetime()
				: this.getStartdatetime());
		this.enddatetime = (null != tobemerged.getEnddatetime() ? tobemerged.getEnddatetime() : this.getEnddatetime());
		this.version = (null != tobemerged.getVersion() ? tobemerged.getVersion() : this.getVersion());
		return this;
	}
}
