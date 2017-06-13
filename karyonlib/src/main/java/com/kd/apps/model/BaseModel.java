package com.kd.apps.model;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public class BaseModel {
	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@Transient
	@JsonIgnore
	public Boolean checknull() {
		return false;
	}
}