package com.kd.apps.model;

import java.util.Iterator;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Pradyumna Roy
 *
 * @param <E>
 */
public class RespMultiEntity<E> extends RespBaseEntity {

	@JsonIgnore
	private Iterable<E> entities;

	private Long count;

	public RespMultiEntity() {
		super();
	}

	public RespMultiEntity(String entityname) {
		super(entityname);
	}

	public Iterable<E> getEntities() {
		return entities;
	}

	public void setEntities(Iterable<E> entities) {
		this.entities = entities;
	}

	public Long getCount() {
		count = 0L;
		if (null != entities) {
			Iterator<E> iterator = entities.iterator();
			while (iterator.hasNext()) {
				count++;
				iterator.next();
			}
		}
		return count;
	}
}
