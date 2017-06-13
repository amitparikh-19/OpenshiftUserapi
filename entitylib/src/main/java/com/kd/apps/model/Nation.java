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
public class Nation extends BaseEntity<Nation, String> {
	private static final long serialVersionUID = 1L;

	// START

	// ISO3166-1-Alpha-2
	@Id
	private String id;

	// name of the country
	@Column
	private String name;

	// ISO3166-1-Alpha-3
	@Column
	private String iso3;

	// isd code
	@Column
	private String isd;

	// currency code
	@Column
	private String currencycode;

	// currency name
	@Column
	private String currencyname;

	// language codes comma separated
	@Column
	private String languages;

	// 2 letter continent code
	@Column
	private String continent;

	// capital city name
	@Column
	private String capital;

	// independence status
	@Column
	private String notes;
	// END

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public Nation mergeUpdates(Nation tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}
}
