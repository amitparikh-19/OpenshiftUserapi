package com.kd.apps.model;

import org.springframework.http.HttpStatus;

import com.kd.apps.Const;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public class RespBaseEntity extends BaseModel {
	// entity name
	private String entityname = "null";

	// Response Http Code for the result
	// of the ops on the entity
	private HttpStatus httpstatus = HttpStatus.OK;

	// Response Message key
	private String respmsgkey = Const.NO_MSG_KEY;

	// Response Message value
	private String respmsgval = Const.NO_VAL;

	public RespBaseEntity() {

	}

	public RespBaseEntity(String entityname) {
		this.entityname = entityname;
	}

	public String getEntityname() {
		return entityname;
	}

	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}

	public HttpStatus getHttpstatus() {
		return httpstatus;
	}

	public void setHttpstatus(HttpStatus httpstatus) {
		this.httpstatus = httpstatus;
	}

	public String getRespmsgkey() {
		return respmsgkey;
	}

	public void setRespmsgkey(String respmsgkey) {
		this.respmsgkey = respmsgkey;
	}

	public String getRespmsgval() {
		return respmsgval;
	}

	public void setRespmsgval(String respmsgval) {
		this.respmsgval = respmsgval;
	}
}