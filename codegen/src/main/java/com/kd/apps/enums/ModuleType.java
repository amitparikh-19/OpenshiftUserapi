package com.kd.apps.enums;
/**
 * 
 * @author Myron Rogtao
 *
 */
public enum ModuleType {

	API("api"), SRVC("comp"), UI("ui"), TEST("test");

	private String value;

	ModuleType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
