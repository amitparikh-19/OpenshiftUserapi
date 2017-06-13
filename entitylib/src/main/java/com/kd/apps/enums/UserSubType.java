package com.kd.apps.enums;

/**
 * @author Myron  Rogtao
 * Constant values representing Merchant Sub Types
 *
 */
public enum UserSubType {

	// More to be added. Added those applicable for User FSM
	WSP("Wsp"), SUPERAGENT("SuperAgent"), AGENT("Agent"), BRANCH("Branch"), CUSTOMER("Customer"), SUPERADMIN(
			"SuperAdmin");

	private String userSubType;

	UserSubType(String userSubType) {
		this.userSubType = userSubType;
	}

	public String getSubType() {
		return userSubType;
	}

	public static UserSubType getUserSubType(String userSubType) {

		UserSubType[] types = UserSubType.values();
		for (UserSubType uType : types) {
			if (uType.getSubType().equals(userSubType)) {
				return uType;
			}
		}
		return null;
	}
}
