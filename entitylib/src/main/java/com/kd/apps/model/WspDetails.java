package com.kd.apps.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class WspDetails extends BaseAutoIdEntity<WspDetails> {
	private static final long serialVersionUID = 1L;

	// START
	// wallet service provider id or tenant id
	@Column
	private String wsp;

	// language codes separated by comma
	@Column
	private String langcodes;

	// Default Language Preference
	@Column
	private String deflangpref;

	// base url for Wallet Service Provider
	@Column
	private String baseurl;
	// END

	@Override
	public WspDetails mergeUpdates(WspDetails tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}
}
