package com.kd.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class TxnType extends BaseEntity<TxnType, String> {
	private static final long serialVersionUID = 1L;

	// START
	// txntype name
	// fundin, fundout
	// cashin, cashout
	// commission, fee
	// transfer, reverse
	// refund, sale/purchase
	// loyalty, bonus
	@Id
	private String id;

	// description of the txn type
	@Column
	private String description;
	// END

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public TxnType mergeUpdates(TxnType tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}
}
