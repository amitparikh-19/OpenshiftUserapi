package com.kd.apps.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class Wallet extends BaseAutoIdEntity<Wallet> {
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

	// balance amount in the wallet
	@Column(precision = 32, scale = 4)
	private BigDecimal balance;
	
	// currency of the balance amount
	// in the wallet
	@Column
	private String currency;

	// signed balance amount
	// to prevent any manual tampering
	// to the balance
	@Column
	private String signature;
	// END


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
	 * @return usertype - {@link String} type of user superadmin, merchant,customer
	 */
	public String getUsertype() {
		return usertype;
	}

	/**
	 * 
	 * @param usertype {@link String} type of user superadmin, merchant,customer
	 */
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	/**
	 * 
	 * @return usersubtype - {@link String} sub type of user superadmin, super agent, agent, retailer ,wsp, esp
	 */
	public String getUsersubtype() {
		return usersubtype;
	}

	/**
	 * 
	 * @param usersubtype {@link String} sub type of user superadmin, super agent, agent, retailer ,wsp, esp
	 */
	public void setUsersubtype(String usersubtype) {
		this.usersubtype = usersubtype;
	}

	/**
	 * 
	 * @return balance - {@link BigDecimal} user balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 
	 * @param balance {@link BigDecimal} user balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * 
	 * @return currency - {@link String} currency of balance in wallet
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 
	 * @param currency {@link String} currency of balance in wallet
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * 
	 * @return signature - {@link String} signature of wallet
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 
	 * @param signature {@link String} signature of wallet
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * 
	 * @param Wallet {@link Wallet}
	 */
	@Override
	@javax.persistence.Transient
	public Wallet mergeUpdates(Wallet tobemerged) {
		this.setWsp((null != tobemerged.getWsp() && !tobemerged.getWsp().trim().equals("")) ? tobemerged.getWsp()
				: this.getWsp());
		this.setUsername((null != tobemerged.getUsername() && !tobemerged.getUsername().trim().equals(""))
				? tobemerged.getUsername() : this.getUsername());
		this.setUsertype((null != tobemerged.getUsertype() && !tobemerged.getUsertype().trim().equals(""))
				? tobemerged.getUsertype() : this.getUsertype());
		this.setUsersubtype((null != tobemerged.getUsersubtype() && !tobemerged.getUsersubtype().trim().equals(""))
				? tobemerged.getUsersubtype() : this.getUsersubtype());
		this.setBalance((null != tobemerged.getBalance() ? tobemerged.getBalance() : this.getBalance()));
		this.setCurrency((null != tobemerged.getCurrency() && !tobemerged.getCurrency().trim().equals(""))
				? tobemerged.getCurrency() : this.getCurrency());
		this.setSignature((null != tobemerged.getSignature() && !tobemerged.getSignature().trim().equals(""))
				? tobemerged.getSignature() : this.getSignature());
		this.setId((null != tobemerged.getId() ? tobemerged.getId() : this.getId()));
		this.setCreated((null != tobemerged.getCreated() ? tobemerged.getCreated() : this.getCreated()));
		this.setCreatedstr((null != tobemerged.getCreatedstr() && !tobemerged.getCreatedstr().trim().equals(""))
				? tobemerged.getCreatedstr() : this.getCreatedstr());
		return this;
	}
}
