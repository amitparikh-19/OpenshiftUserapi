package com.kd.apps.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kd.apps.Const;
import com.kd.apps.model.ExceptionResp;

/**
 * 
 * @author Myron Rogtao
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { BizException.class })
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	protected ResponseEntity<Object> handleBizException(BizException ex, WebRequest request) {

		// Response Body
		ExceptionResp msg = new ExceptionResp();
		msg.setErrorKey(ex.getErrorKey());
		msg.setErrorMessage(ex.getErrorMsg());

		// Response Header
		HttpHeaders headers = new HttpHeaders();
		headers.add(Const.HTTP_HEADER_RESP_MSG_KEY, ex.getErrorKey());
		headers.add(Const.HTTP_HEADER_RESP_MSG_VAL, ex.getErrorMsg());

		return handleExceptionInternal(ex, msg, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}

	@ExceptionHandler(value = { InternalServerException.class })
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseEntity<Object> handleInternalServerException(InternalServerException ex, WebRequest request) {

		// Response Body
		ExceptionResp msg = new ExceptionResp();
		msg.setErrorKey(ex.getErrorKey());
		msg.setErrorMessage(ex.getErrorMsg());

		// Response Header
		HttpHeaders headers = new HttpHeaders();
		headers.add(Const.HTTP_HEADER_RESP_MSG_KEY, ex.getErrorKey());
		headers.add(Const.HTTP_HEADER_RESP_MSG_VAL, ex.getErrorMsg());

		return handleExceptionInternal(ex, msg, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
}
