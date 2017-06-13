package com.kd.apps.model;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public class SearchCriteria {

	private String key;
	private String operation;
	private Object value1;
	private Object value2;

	public SearchCriteria(String key, String operation, Object value1) {
		super();
		this.key = key;
		this.operation = operation;
		this.value1 = value1;
	}

	public SearchCriteria(String key, String operation, Object value1, Object value2) {
		super();
		this.key = key;
		this.operation = operation;
		this.value1 = value1;
		this.value2 = value2;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Object getValue1() {
		return value1;
	}

	public void setValue1(Object value1) {
		this.value1 = value1;
	}

	public Object getValue2() {
		return value2;
	}

	public void setValue2(Object value2) {
		this.value2 = value2;
	}

}
