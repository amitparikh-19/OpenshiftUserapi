package com.kd.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class AgentDetails extends BaseAutoIdEntity<AgentDetails> {
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

	// if there is any mapped
	// agent/merchant id in WSP
	// remote system like IN prepaid
	// or ERP
	@Column
	private String wspremotesysid;

	// e.g. any region
	@Column
	private String superorg;

	// e.g. any business unit
	@Column
	private String org;

	// e.g. any business unit type
	@Column
	private String orgtype;

	// e.g. country in Canal Plus
	@Column
	private String suborg;

	// transaction currency
	@Column
	private String walletcurrency;

	// timezone id
	// e.g Asia/Kolkata
	@Column
	private String tzid;

	// timezone offset
	// e.g +5.30 for
	// Asia/Kolkata
	@Column
	private Float tzoffset;

	// latitude
	@Column
	private String latitude;

	// longitude
	@Column
	private String longitude;

	// Default Language Preference
	@Column
	private String deflangpref;

	// Temporary session language preference
	@Transient
	private String sessionlangpref;

	// notification preference
	// sms, email, both
	@Column
	private String notifypref;

	// e.g. state
	@Column
	private String loclevel1;

	// e.g. district
	@Column
	private String loclevel2;

	// e.g. subdistrict, tehsil, taluka, city
	@Column
	private String loclevel3;

	// zip code
	@Column
	private String zipcode;

	// postal address
	// building number, street, area etc.
	@Column
	private String address;

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
	 * @return wspremotesysid -  {@link String} if there is any mapped agent/merchant id in WSP remote system like IN prepaid or ERP
	 */
	public String getWspremotesysid() {
		return wspremotesysid;
	}

	/**
	 * 
	 * @param wspremotesysid {@link String} if there is any mapped agent/merchant id in WSP remote system like IN prepaid or ERP
	 */
	public void setWspremotesysid(String wspremotesysid) {
		this.wspremotesysid = wspremotesysid;
	}

	/**
	 * 
	 * @return superorg - {@link String} e.g. any region
	 */
	public String getSuperorg() {
		return superorg;
	}

	/**
	 * 
	 * @param superorg {@link String} e.g. any region
	 */
	public void setSuperorg(String superorg) {
		this.superorg = superorg;
	}

	/**
	 * 
	 * @return org - {@link String} e.g. any business unit
	 */
	public String getOrg() {
		return org;
	}

	/**
	 * 
	 * @param org {@link String} e.g. any business unit
	 */
	public void setOrg(String org) {
		this.org = org;
	}

	/**
	 * 
	 * @return orgtype - {@link String} e.g. any business unit type
	 */
	public String getOrgtype() {
		return orgtype;
	}

	/**
	 * 
	 * @param orgtype {@link String} e.g. any business unit type
	 */
	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}

	/**
	 * 
	 * @return suborg - {@link String} e.g. country in Canal Plus
	 */
	public String getSuborg() {
		return suborg;
	}

	/**
	 * 
	 * @param suborg {@link String} e.g. country in Canal Plus
	 */
	public void setSuborg(String suborg) {
		this.suborg = suborg;
	}

	/**
	 * 
	 * @return walletcurrency - {@link String} transaction currency
	 */
	public String getWalletcurrency() {
		return walletcurrency;
	}

	/**
	 * 
	 * @param walletcurrency {@link String} transaction currency
	 */
	public void setWalletcurrency(String walletcurrency) {
		this.walletcurrency = walletcurrency;
	}

	/**
	 * 
	 * @return tzid - {@link String} timezone id 
	 */
	public String getTzid() {
		return tzid;
	}

	/**
	 * 
	 * @param tzid {@link String} timezone id
	 */
	public void setTzid(String tzid) {
		this.tzid = tzid;
	}

	/**
	 * 
	 * @return tzoffset - {@link Float} timezone offset
	 */
	public Float getTzoffset() {
		return tzoffset;
	}

	/**
	 * 
	 * @param tzoffset {@link Float} timezone offset
	 */
	public void setTzoffset(Float tzoffset) {
		this.tzoffset = tzoffset;
	}

	/**
	 * 
	 * @return latitude - {@link String} latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * 
	 * @param latitude {@link String} latitude
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 
	 * @return longitude - {@link String} longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * 
	 * @param longitude {@link String} longitude
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * 
	 * @return deflangpref - {@link String} Default Language Preference
	 */
	public String getDeflangpref() {
		return deflangpref;
	}

	/**
	 * 
	 * @param deflangpref {@link String} Default Language Preference
	 */
	public void setDeflangpref(String deflangpref) {
		this.deflangpref = deflangpref;
	}

	/**
	 * 
	 * @return sessionlangpref - {@link String} Session Language Preference
	 */
	public String getSessionlangpref() {
		return sessionlangpref;
	}

	/**
	 * 
	 * @param sessionlangpref {@link String} Session Language Preference
	 */
	public void setSessionlangpref(String sessionlangpref) {
		this.sessionlangpref = sessionlangpref;
	}

	/**
	 * 
	 * @return notifypref - {@link String} Notify Preference
	 */
	public String getNotifypref() {
		return notifypref;
	}

	/**
	 * 
	 * @param notifypref {@link String} Notify Preference
	 */
	public void setNotifypref(String notifypref) {
		this.notifypref = notifypref;
	}

	/**
	 * 
	 * @return loclevel1 - {@link String} state
	 */
	public String getLoclevel1() {
		return loclevel1;
	}

	/**
	 * 
	 * @param loclevel1 {@link String} state
	 */
	public void setLoclevel1(String loclevel1) {
		this.loclevel1 = loclevel1;
	}

	/**
	 * 
	 * @return loclevel2 - {@link String} district
	 */
	public String getLoclevel2() {
		return loclevel2;
	}

	/**
	 * 
	 * @param loclevel2 {@link String} district
	 */
	public void setLoclevel2(String loclevel2) {
		this.loclevel2 = loclevel2;
	}

	/**
	 * 
	 * @return loclevel3 - {@link String} city
	 */
	public String getLoclevel3() {
		return loclevel3;
	}

	/**
	 * 
	 * @param loclevel3 {@link String} city
	 */
	public void setLoclevel3(String loclevel3) {
		this.loclevel3 = loclevel3;
	}

	/**
	 * 
	 * @return zipcode - {@link String} zip code
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * 
	 * @param zipcode {@link String} zip code
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * 
	 * @return address - {@link String} Postal Address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @param address {@link String} Postal Address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 
	 * @param AgentDetails {@link AgentDetails} 
	 */
	@Override
	public AgentDetails mergeUpdates(AgentDetails tobemerged) {

		this.wsp = (null != tobemerged.getWsp() && !("".equals(tobemerged.getWsp()))
				&& !(" ".equals(tobemerged.getWsp())) ? tobemerged.getUsername() : this.getUsername());
		this.username = (null != tobemerged.getUsername() && !("".equals(tobemerged.getUsername()))
				&& !(" ".equals(tobemerged.getUsername())) ? tobemerged.getUsername() : this.getUsername());
		this.usertype = (null != tobemerged.getUsertype() && !("".equals(tobemerged.getUsertype()))
				&& !(" ".equals(tobemerged.getUsertype())) ? tobemerged.getUsertype() : this.getUsertype());
		this.usersubtype = (null != tobemerged.getUsersubtype() && !("".equals(tobemerged.getUsersubtype()))
				&& !(" ".equals(tobemerged.getUsersubtype())) ? tobemerged.getUsersubtype() : this.getUsersubtype());
		this.wspremotesysid = (null != tobemerged.getWspremotesysid() && !("".equals(tobemerged.getWspremotesysid()))
				&& !(" ".equals(tobemerged.getWspremotesysid())) ? tobemerged.getWspremotesysid()
						: this.getWspremotesysid());
		this.superorg = (null != tobemerged.getSuperorg() ? tobemerged.getSuperorg() : this.getSuperorg());
		this.org = (null != tobemerged.getOrg() && !("".equals(tobemerged.getOrg()))
				&& !(" ".equals(tobemerged.getOrg())) ? tobemerged.getOrg() : this.getOrg());
		this.orgtype = (null != tobemerged.getOrgtype() && !("".equals(tobemerged.getOrgtype()))
				&& !(" ".equals(tobemerged.getOrgtype())) ? tobemerged.getOrgtype() : this.getOrgtype());
		this.suborg = (null != tobemerged.getSuborg() && !("".equals(tobemerged.getSuborg()))
				&& !(" ".equals(tobemerged.getSuborg())) ? tobemerged.getSuborg() : this.getSuborg());
		this.walletcurrency = (null != tobemerged.getWalletcurrency() && !("".equals(tobemerged.getWalletcurrency()))
				&& !(" ".equals(tobemerged.getWalletcurrency())) ? tobemerged.getWalletcurrency()
						: this.getWalletcurrency());
		this.tzid = (null != tobemerged.getTzid() && !("".equals(tobemerged.getTzid()))
				&& !(" ".equals(tobemerged.getTzid())) ? tobemerged.getTzid() : this.getTzid());
		this.tzoffset = (null != tobemerged.getTzoffset() ? tobemerged.getTzoffset() : this.getTzoffset());
		this.latitude = (null != tobemerged.getLatitude() ? tobemerged.getLatitude() : this.getLatitude());
		this.longitude = (null != tobemerged.getLongitude() ? tobemerged.getLongitude() : this.getLongitude());
		this.deflangpref = (null != tobemerged.getDeflangpref() && !("".equals(tobemerged.getDeflangpref()))
				&& !(" ".equals(tobemerged.getDeflangpref())) ? tobemerged.getDeflangpref() : this.getDeflangpref());
		this.sessionlangpref = (null != tobemerged.getSessionlangpref() && !("".equals(tobemerged.getSessionlangpref()))
				&& !(" ".equals(tobemerged.getSessionlangpref())) ? tobemerged.getSessionlangpref()
						: this.getSessionlangpref());
		this.notifypref = (null != tobemerged.getNotifypref() && !("".equals(tobemerged.getNotifypref()))
				&& !(" ".equals(tobemerged.getNotifypref())) ? tobemerged.getNotifypref() : this.getNotifypref());
		this.loclevel1 = (null != tobemerged.getLoclevel1() && !("".equals(tobemerged.getLoclevel1()))
				&& !(" ".equals(tobemerged.getLoclevel1())) ? tobemerged.getLoclevel1() : this.getLoclevel1());
		this.loclevel2 = (null != tobemerged.getLoclevel2() && !("".equals(tobemerged.getLoclevel2()))
				&& !(" ".equals(tobemerged.getLoclevel2())) ? tobemerged.getLoclevel2() : this.getLoclevel2());
		this.loclevel3 = (null != tobemerged.getLoclevel3() && !("".equals(tobemerged.getLoclevel3()))
				&& !(" ".equals(tobemerged.getLoclevel3())) ? tobemerged.getLoclevel3() : this.getLoclevel3());
		this.zipcode = (null != tobemerged.getZipcode() && !("".equals(tobemerged.getZipcode()))
				&& !(" ".equals(tobemerged.getZipcode())) ? tobemerged.getZipcode() : this.getZipcode());
		this.address = (null != tobemerged.getAddress() && !("".equals(tobemerged.getAddress()))
				&& !(" ".equals(tobemerged.getAddress())) ? tobemerged.getAddress() : this.getAddress());
		return this;
	}
}
