package com.kd.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class WalTxnLog extends BaseAutoIdEntity<WalTxnLog> {
	private static final long serialVersionUID = 1L;

	// START
	// sequence of the txn state
	@Column
	private Integer seqid;

	// txn id from waltxn entity
	@Column
	private String txnid;

	// freeform comment on the state of txn
	@Column
	private String comment;

	// placeholder for reference id from 3rd party like
	// payment provider, original digital service provider
	@Column
	private String ref;

	// different states of the txn
	@Column
	private String state;

	// END
	/**
	 * 
	 * @return seqid -{@link Integer} sequence of the txn state
	 */
	public Integer getSeqid() {
		return seqid;
	}

	/**
	 * 
	 * @param seqid {@link Integer} sequence of the txn state
	 */
	public void setSeqid(Integer seqid) {
		this.seqid = seqid;
	}

	/**
	 * 
	 * @return txnid -{@link String} Transaction Id
	 */
	public String getTxnid() {
		return txnid;
	}

	/**
	 * 
	 * @param txnid {@link String} Transaction Id
	 */
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}

	/**
	 * 
	 * @return comment - {@link String} Free Form comment for transaction
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * 
	 * @param comment {@link String} Free Form comment for transaction
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 
	 * @return ref - {@link String} placeholder for reference id from 3rd party like
	 */
	public String getRef() {
		return ref;
	}

	/**
	 * 
	 * @param ref {@link String} placeholder for reference id from 3rd party like
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}

	/**
	 * 
	 * @return state - {@link String} different states of the txn
	 */
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @param state {@link String} different states of the txn
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 
	 * @param WalTxnLog {@link WalTxnLog}
	 */
	@Override
	public WalTxnLog mergeUpdates(WalTxnLog tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}

}
