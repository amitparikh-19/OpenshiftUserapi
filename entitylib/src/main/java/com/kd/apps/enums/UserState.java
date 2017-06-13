package com.kd.apps.enums;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Myron  Rogtao
 * Constant values representing the States of User Life Cycle
 * 
 * User : Merchant Entity | Merchant Actor | Customer
 * 
 */
public enum UserState {

	PREREGISTERED("preregistered"), REGISTERED("registered"), REJECTED("rejected"), APPROVED("approved"), ACTIVE(
			"active"), PARTIALBLOCKED("partialblocked"), FULLBLOCKED("fullblocked"), PARTIALFULLBLOCKED(
					"partialfullblocked"), BLOCKED("blocked"), SELFBLOCKED("selfblocked"), SELFFULLBLOCKED(
							"selffullblocked"), SELFPARTIALBLOCKED(
									"selfpartialblocked"), SUSPENDED("suspended"), CLOSED("closed");

	private String state;

	UserState(String state) {
		this.state = state;
	}

	public String getStateValue() {
		return state;
	}

	public static UserState getState(String state) {

		UserState[] userStates = UserState.values();
		for (UserState userState : userStates) {
			if (userState.getStateValue().equals(state)) {
				return userState;
			}
		}
		return null;
	}

	/**
	 * Method returns states in which a user can be prior to any Blocking State
	 * 
	 * @return List of Pre-Blocking States
	 */
	public static Collection<String> getPreBlockingStates() {
		Collection<String> preBlockingStates = new ArrayList<>();
		preBlockingStates.add(PREREGISTERED.getStateValue());
		preBlockingStates.add(REGISTERED.getStateValue());
		preBlockingStates.add(APPROVED.getStateValue());
		preBlockingStates.add(ACTIVE.getStateValue());
		return preBlockingStates;
	}

	/**
	 * Method returns states in which a user can be prior to Suspension State
	 * 
	 * @return List of Pre-Suspension states
	 */
	public static Collection<String> getPreSuspensionStates() {
		Collection<String> preSuspensionStates = new ArrayList<>();
		preSuspensionStates.add(PARTIALBLOCKED.getStateValue());
		preSuspensionStates.add(PARTIALFULLBLOCKED.getStateValue());
		preSuspensionStates.add(FULLBLOCKED.getStateValue());
		preSuspensionStates.add(REGISTERED.getStateValue());
		preSuspensionStates.add(APPROVED.getStateValue());
		preSuspensionStates.add(ACTIVE.getStateValue());
		return preSuspensionStates;
	}
}
