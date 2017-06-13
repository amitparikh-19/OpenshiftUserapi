package com.kd.apps.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class UserStateLog extends BaseAutoIdEntity<UserStateLog> {
	private static final long serialVersionUID = 1L;

	// START
	// wallet service provider id or tenant id
	@Column
	private String wsp;

	// primary key or ID
	// of the entity
	@Column
	private String username;

	@Column
	private String state;

	// date and time of the operation
	// or change
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date opdatetime;
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
	 * @return  opdatetime - {@link Date} date and time of the operation or change
	 */
	public Date getOpdatetime() {
		return opdatetime;
	}

	/**
	 * 
	 * @param opdatetime {@link Date} date and time of the operation or change
	 */
	public void setOpdatetime(Date opdatetime) {
		this.opdatetime = opdatetime;
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
	 * @param UserStateLog {@link UserStateLog}
	 */
	@Override
	public UserStateLog mergeUpdates(UserStateLog tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}

}
