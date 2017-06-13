package com.kd.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class UserRole extends BaseAutoIdEntity<UserRole> {
	private static final long serialVersionUID = 1L;

	// START
	@Column
	private String username;

	@Column
	private String role;

	// END

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
	 * @return role - {@link String} role of user
	 */
	public String getRole() {
		return role;
	}

	/**
	 * 
	 * @param role {@link String} role of user
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * 
	 * @param UserRole {@link UserRole}
	 */
	@Override
	public UserRole mergeUpdates(UserRole tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}
}