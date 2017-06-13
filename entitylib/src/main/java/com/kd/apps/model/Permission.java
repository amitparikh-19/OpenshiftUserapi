package com.kd.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class Permission extends BaseAutoIdEntity<Permission> {
	private static final long serialVersionUID = 1L;

	// START
	@Column
	private String name;

	// END

	/**
	 * 
	 * @return permission - {@link String} name of permission
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param permission {@link String} name of permission
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param Permission {@link Permission}
	 */
	@Override
	public Permission mergeUpdates(Permission tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}
}
