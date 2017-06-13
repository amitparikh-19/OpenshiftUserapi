package com.kd.apps.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 
 * @author Pradyumna Roy
 *
 * @param <E>
 * @param <ID>
 */
@MappedSuperclass
public abstract class BaseEntity<E, ID> extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	public abstract ID getId();

	public abstract E mergeUpdates(E tobemerged);

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date created;

	// string representation of created date-time
	@Transient
	private String createdstr;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedstr() {
		return createdstr;
	}

	public void setCreatedstr(String createdstr) {
		this.createdstr = createdstr;
	}
}