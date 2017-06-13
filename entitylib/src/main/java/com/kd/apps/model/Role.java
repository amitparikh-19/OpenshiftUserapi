package com.kd.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class Role extends BaseAutoIdEntity<Role> {
	private static final long serialVersionUID = 1L;

	// START
	@Column
	private String name;

	// END

	/**
	 * 
	 * @return name - {@link String} name of role
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name {@link String} name of role
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @param Role {@link Role}
	 */
	@Override
	public Role mergeUpdates(Role tobemerged) {
		return null;
	}
}
