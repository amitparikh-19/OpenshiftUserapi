package com.kd.apps.enums;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Myron  Rogtao
 *
 */
public enum TxnType {

	FUNDIN, FUNDOUT, MERCHXFER, SALE, FEE, COMM, CASHIN, CASHOUT, PURCHASE, SEND, SELF;

	public static TxnType getTxnType(String type) {

		if (type != null) {
			return TxnType.valueOf(type.toUpperCase());
		}
		return null;
	}

	public static List<String> listTxnTypes() {

		List<String> txnTypes = new ArrayList<>();
		for (TxnType txnType : TxnType.values()) {
			txnTypes.add(txnType.toString());
		}
		return txnTypes;
	}

}
