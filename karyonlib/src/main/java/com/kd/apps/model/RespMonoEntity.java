package com.kd.apps.model;

/**
 * 
 * @author Pradyumna Roy
 *
 * @param <E>
 */
public class RespMonoEntity<E> extends RespBaseEntity {
	private E entity;

	public RespMonoEntity() {
		super();
	}

	public RespMonoEntity(String entityname) {
		super(entityname);
	}

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}
}
