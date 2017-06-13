package com.kd.apps.dto;

import java.math.BigDecimal;

import com.kd.apps.model.BaseModel;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public class UserBal extends BaseModel {

	private String username;

	private String name;

	private String email;

	private String mobile;

	private BigDecimal balance;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
