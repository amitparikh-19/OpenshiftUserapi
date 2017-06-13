package com.kd.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class RolePermission extends BaseAutoIdEntity<RolePermission> {
	private static final long serialVersionUID = 1L;

	// START
	@Column
	private String role;

	@Column
	private String permission;

	// END
	/**
	 * 
	 * @return role - {@link String} name of role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * 
	 * @param role {@link String} name of role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * 
	 * @return permission - {@link String} name of permission
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * 
	 * @param permission {@link String} name of permission
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * 
	 * @param RolePermission {@link RolePermission}
	 */
	@Override
	public RolePermission mergeUpdates(RolePermission tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}
}