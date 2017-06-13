package com.kd.apps.enums;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Myron  Rogtao
 *
 */
public enum ChargePayer {

	SRC, DEST, WSP;

	public static List<String> listPayers() {
		List<String> payers = new ArrayList<>();
		for (ChargePayer payer : ChargePayer.values()) {
			payers.add(payer.toString());
		}
		return payers;
	}

}
