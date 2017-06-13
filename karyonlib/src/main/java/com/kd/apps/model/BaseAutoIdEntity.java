package com.kd.apps.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Pradyumna Roy
 *
 * @param <E>
 */
@MappedSuperclass
public abstract class BaseAutoIdEntity<E> extends BaseEntity<E, Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}