package com.kd.apps.exception;

/**
 * 
 * @author Myron Rogtao
 *
 */
public class ServerException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMsg;
	private String errorKey;

	public ServerException(String errorKey, String message) {
		this.errorKey = errorKey;
		this.errorMsg = message;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
