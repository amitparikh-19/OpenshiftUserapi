package com.kd.apps.enums;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Myron  Rogtao
 *
 */
public enum ChargeType {

	FEE, COMMISSION;

	public static List<String> listChargeTypes() {
		List<String> chargeTypes = new ArrayList<>();
		for (ChargeType charge : ChargeType.values()) {
			chargeTypes.add(charge.toString());
		}
		return chargeTypes;
	}
}
