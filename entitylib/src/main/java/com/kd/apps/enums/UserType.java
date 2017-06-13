package com.kd.apps.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Myron  Rogtao
 * Constant values representing Merchant Types
 * 
 * Types : SuperAdmin | Merchant | Customer | Wsp
 *
 */
public enum UserType {

	SUPERADMIN("SuperAdmin", false), MERCHANT("Merchant", true), CUSTOMER("Customer", true), WSP("Wsp", true);

	private String userType;

	private boolean chargable;

	UserType(String userType, boolean chargable) {
		this.userType = userType;
		this.chargable = chargable;
	}

	public String getType() {
		return userType;
	}

	public static UserType getUserType(String userType) {

		UserType[] types = UserType.values();
		for (UserType uType : types) {
			if (uType.getType().equals(userType)) {
				return uType;
			}
		}
		return null;
	}

	public boolean isChargeable() {
		return chargable;
	}

	public static List<String> getTypeList(boolean chargeable) {
		List<String> list = new ArrayList<String>();
		UserType[] types = UserType.values();
		for (UserType uType : types) {
			if (uType.isChargeable() && chargeable) {
				list.add(uType.getType());
			}
		}
		return list;
	}
}
