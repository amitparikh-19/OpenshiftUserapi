package com.kd.apps.exception;

/**
 * 
 * @author Myron Rogtao
 *
 */
public class InternalServerException extends ServerException {

	private static final long serialVersionUID = 1L;

	public InternalServerException(String errorKey, String message) {
		super(errorKey, message);
	}
}
