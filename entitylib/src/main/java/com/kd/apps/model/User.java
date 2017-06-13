package com.kd.apps.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kd.apps.dto.UserAuthority;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class User extends BaseEntity<User, String> implements UserDetails {
	private static final long serialVersionUID = 1L;

	// START
	// username of the user
	@Id
	private String username;

	// password
	@Column
	private String password;

	// transaction pin
	@Column
	private String tpin;

	// Full name of the
	// user
	@Column
	private String name;

	// email of the user
	@Column
	private String email;

	// mobile of the user
	@Column
	private String mobile;

	// brand
	@Column
	private String brand;

	// superadmin = superadmin
	// merchant = merchant
	// customer = customer
	@Column
	private String type;

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
	private String subtype;

	// sa = super admin
	// mo = merchant owner
	// ad = admin
	// om = operations manager
	// sm = sales manager
	// fm = finance manager
	// im = it manager
	// sp = sales personnel
	// cu = customer
	@Column
	private String jobtitle;

	// Merchant or Agent hierarchy level
	@Column
	private Integer level;

	// parent user
	// in case of
	// merchant hierarchy
	@Column
	private String parent;

	// root or top most parent user
	// in case of
	// merchant hierarchy
	@Column
	private String root;

	// current state
	// of the user
	@Column
	private String state;

	// is user account
	// active/enabled
	@Column
	private Boolean enabled = true;

	// is user account
	// not expired due to
	// deleted or inactive etc.
	@Column
	private Boolean accountNonExpired = true;

	// is user account
	// not locked
	@Column
	private Boolean accountNonLocked = true;

	// is password or tpin
	// not expired
	@Column
	private Boolean credentialsNonExpired = true;

	// milliseconds for the
	// jwt token to expire
	@Transient
	private Long expires;

	// setting new password
	// temp placeholder
	@Transient
	private String newpassword;

	// setting new tpin
	// temp placeholder
	@Transient
	private String newtpin;

	// channel used to authenticate
	// webapp, api, mobileapp, sms, ussd
	@Transient
	private String authchannel;

	@Transient
	private Set<UserAuthority> authorities;
	// END

	@Override
	@JsonIgnore
	@Transient
	public String getId() {
		return username;
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
	 * @return password - {@link String} password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password {@link String} password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return tpin - {@link String} transaction pin
	 */
	public String getTpin() {
		return tpin;
	}

	/**
	 * 
	 * @param tpin {@link String} transaction pin
	 */
	public void setTpin(String tpin) {
		this.tpin = tpin;
	}

	/**
	 * 
	 * @return name - {@link String} name of user
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name {@link String} name of user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return email - {@link String} email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email {@link String} email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return mobile - {@link String} mobile number
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 
	 * @param mobile {@link String} mobile number
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 
	 * @return brand - {@link String} brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * 
	 * @param brand {@link String} brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * 
	 * @return type - {@link String} type of user superadmin, merchant,customer
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type {@link String} type of user superadmin, merchant,customer
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return subtype - {@link String} sub type of user superadmin, super agent, agent, retailer ,wsp, esp
	 */
	public String getSubtype() {
		return subtype;
	}

	/**
	 * 
	 * @param subtype {@link String} sub type of user superadmin, super agent, agent, retailer ,wsp, esp
	 */
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	/**
	 * 
	 * @return jobtitle - {@link String} super admin,merchant owner,admin,operations manager, sales manager,finance manager
	 */
	public String getJobtitle() {
		return jobtitle;
	}

	/**
	 * 
	 * @param jobtitle {@link String} super admin,merchant owner,admin,operations manager, sales manager,finance manager
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	/**
	 * 
	 * @return level - {@link Integer} Merchant or Agent hierarchy level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 
	 * @param level {@link Integer} Merchant or Agent hierarchy level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 
	 * @return parent - {@link String} Parent User
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * 
	 * @param parent {@link String} Parent User
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}

	/**
	 * 
	 * @return root - {@link String} Root User
	 */
	public String getRoot() {
		return root;
	}

	/**
	 * 
	 * @param root {@link String} Root User
	 */
	public void setRoot(String root) {
		this.root = root;
	}

	/**
	 * 
	 * @return state - {@link String} Current state of user
	 */
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @param state {@link String} Current state of user
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 
	 * @return enabled - {@link Boolean} User is enabled or disabled
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * 
	 * @param enabled {@link Boolean} User is enabled or disabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 
	 * @return accountNonExpired - {@link Boolean}  is user account not expired due to deleted or inactive etc.
	 */
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * 
	 * @param accountNonExpired {@link Boolean} is user account not expired due to deleted or inactive etc.
	 */
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * 
	 * @return accountNonLocked - {@link Boolean}  is user account not locked
	 */
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * 
	 * @param accountNonLocked {@link Boolean} is user account not locked
	 */
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * 
	 * @return credentialsNonExpired - {@link Boolean}  is password or tpin not expired
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * 
	 * @param credentialsNonExpired {@link Boolean} is password or tpin not expired
	 */
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * 
	 * @return expires - {@link Long}  milliseconds for the jwt token to expire
	 */
	public Long getExpires() {
		return expires;
	}

	/**
	 * 
	 * @param expires {@link Long} milliseconds for the jwt token to expire
	 */
	public void setExpires(Long expires) {
		this.expires = expires;
	}

	/**
	 * 
	 * @return newpassword - {@link String}  setting new password temp placeholder
	 */
	public String getNewpassword() {
		return newpassword;
	}

	/**
	 * 
	 * @param newpassword {@link String} setting new password temp placeholder
	 */
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	/**
	 * 
	 * @return newtpin - {@link String} setting new tpin temp placeholder
	 */
	public String getNewtpin() {
		return newtpin;
	}

	/**
	 * 
	 * @param newtpin {@link String} setting new tpin temp placeholder
	 */
	public void setNewtpin(String newtpin) {
		this.newtpin = newtpin;
	}

	/**
	 * 
	 * @return authchannel - {@link String} channel used to authenticate webapp, api, mobileapp, sms, ussd
	 */
	public String getAuthchannel() {
		return authchannel;
	}

	/**
	 * 
	 * @param authchannel {@link String} channel used to authenticate webapp, api, mobileapp, sms, ussd
	 */
	public void setAuthchannel(String authchannel) {
		this.authchannel = authchannel;
	}
	
	/**
	 * 
	 * @return Set<UserAuthority> - {@link UserAuthority}
	 */
	@Override
	public Set<UserAuthority> getAuthorities() {
		return authorities;
	}
	
	/**
	 * 
	 * @param authorities {@link UserAuthority}
	 */
	public void setAuthorities(Set<UserAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * 
	 * @return enabled - {@link Boolean} User is enabled or disabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * 
	 * @return accountNonExpired - {@link Boolean}  is user account not expired due to deleted or inactive etc.
	 */
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * 
	 * @return accountNonLocked - {@link Boolean}  is user account not locked
	 */
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * 
	 * @return credentialsNonExpired - {@link Boolean}  is password or tpin not expired
	 */
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @param User {@link User}
	 */
	@Override
	@Transient
	public User mergeUpdates(User tobemerged) {
		this.setPassword((null != tobemerged.getPassword() && !tobemerged.getPassword().trim().equals(""))
				? tobemerged.getPassword() : this.getPassword());
		this.setTpin((null != tobemerged.getTpin() && !tobemerged.getTpin().trim().equals("")) ? tobemerged.getTpin()
				: this.getTpin());
		this.setName((null != tobemerged.getName() && !tobemerged.getName().trim().equals("")) ? tobemerged.getName()
				: this.getName());
		this.setEmail((null != tobemerged.getEmail() && !tobemerged.getEmail().trim().equals(""))
				? tobemerged.getEmail() : this.getEmail());
		this.setMobile((null != tobemerged.getMobile() && !tobemerged.getMobile().trim().equals(""))
				? tobemerged.getMobile() : this.getMobile());
		this.setBrand((null != tobemerged.getBrand() && !tobemerged.getBrand().trim().equals(""))
				? tobemerged.getBrand() : this.getBrand());
		this.setType((null != tobemerged.getType() && !tobemerged.getType().trim().equals("")) ? tobemerged.getType()
				: this.getType());
		this.setSubtype((null != tobemerged.getSubtype() && !tobemerged.getSubtype().trim().equals(""))
				? tobemerged.getSubtype() : this.getSubtype());
		this.setJobtitle((null != tobemerged.getJobtitle() && !tobemerged.getJobtitle().trim().equals(""))
				? tobemerged.getJobtitle() : this.getJobtitle());
		this.setLevel((null != tobemerged.getLevel() ? tobemerged.getLevel() : this.getLevel()));
		this.setParent((null != tobemerged.getParent() && !tobemerged.getParent().trim().equals(""))
				? tobemerged.getParent() : this.getParent());
		this.setRoot((null != tobemerged.getRoot() && !tobemerged.getRoot().trim().equals("")) ? tobemerged.getRoot()
				: this.getRoot());
		this.setState((null != tobemerged.getState() && !tobemerged.getState().trim().equals(""))
				? tobemerged.getState() : this.getState());
		this.setEnabled((null != tobemerged.getEnabled() ? tobemerged.getEnabled() : this.getEnabled()));
		this.setAccountNonExpired((null != tobemerged.getAccountNonExpired() ? tobemerged.getAccountNonExpired()
				: this.getAccountNonExpired()));
		this.setAccountNonLocked((null != tobemerged.getAccountNonLocked() ? tobemerged.getAccountNonLocked()
				: this.getAccountNonLocked()));
		this.setCredentialsNonExpired((null != tobemerged.getCredentialsNonExpired()
				? tobemerged.getCredentialsNonExpired() : this.getCredentialsNonExpired()));
		this.setExpires((null != tobemerged.getExpires() ? tobemerged.getExpires() : this.getExpires()));
		this.setNewpassword((null != tobemerged.getNewpassword() && !tobemerged.getNewpassword().trim().equals(""))
				? tobemerged.getNewpassword() : this.getNewpassword());
		this.setNewtpin((null != tobemerged.getNewtpin() && !tobemerged.getNewtpin().trim().equals(""))
				? tobemerged.getNewtpin() : this.getNewtpin());
		this.setAuthchannel((null != tobemerged.getAuthchannel() && !tobemerged.getAuthchannel().trim().equals(""))
				? tobemerged.getAuthchannel() : this.getAuthchannel());
		this.setAuthorities(
				(null != tobemerged.getAuthorities() ? tobemerged.getAuthorities() : this.getAuthorities()));

		return this;
	}
}
