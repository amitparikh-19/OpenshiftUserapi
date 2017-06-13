package com.kd.apps.exception;

/**
 * 
 * @author Myron Rogtao
 *
 */
public class BizException extends ServerException {

	private static final long serialVersionUID = 1L;

	public BizException(String errorKey, String message) {
		super(errorKey, message);
	}
}
