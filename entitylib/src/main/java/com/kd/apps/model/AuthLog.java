package com.kd.apps.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class AuthLog extends BaseAutoIdEntity<AuthLog> {
	private static final long serialVersionUID = 1L;

	// START
	// wallet service provider id or tenant id
	@Column
	private String wsp;

	// User trying to do auth type transaction
	@Column
	private String username;

	// login, logout
	@Column
	private String action;

	// success, failure
	@Column
	private String status;

	// sms, ussd, web, mobileapp, api, webpos
	@Column
	private String channel;

	// auth req src ip address
	@Column
	private String ipaddress;

	// auth req src device like
	// browser name etc.
	@Column
	private String reqsrc;

	// date time of auth activity
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date authdatetime;

	// date time string of auth activity
	@Transient
	private String authdatetimestr;
	// END

	@Override
	public AuthLog mergeUpdates(AuthLog tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}
}
