package com.kd.apps.model;

import com.kd.apps.enums.UserState;

/**
 * @author Myron Rogtao
 * UserFsmData Model represents the input data to the FSM
 *
 */
public class UserFsmData {

	private User user;

	UserState currentState;

	UserState preSuspensionState;

	UserState preBlockinState;

	public UserFsmData(User user) {
		this.user = user;
	}

	public UserState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(UserState currentState) {
		this.currentState = currentState;
	}

	public UserState getPreBlockinState() {
		return preBlockinState;
	}

	public void setPreBlockinState(UserState preBlockinState) {
		this.preBlockinState = preBlockinState;
	}

	public UserState getPreSuspensionState() {
		return preSuspensionState;
	}

	public void setPreSuspensionState(UserState preSuspensionState) {
		this.preSuspensionState = preSuspensionState;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
