package com.kd.apps.model;

import java.io.Serializable;

/**
 * 
 * @author Myron Rogtao
 *
 * @param <T>
 */
public class RespTransitionEntity<T extends Serializable> {

	private Boolean valid;
	private String currentState;
	private String nextState;
	private String event;
	private T entity;

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getNextState() {
		return nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}
}
