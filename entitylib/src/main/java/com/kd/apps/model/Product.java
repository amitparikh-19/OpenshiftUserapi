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
public class Product extends BaseEntity<Product, String> {
	private static final long serialVersionUID = 1L;

	// START
	// name of the product
	// etopup, evoucher, databundle, billpay, emoney
	@Id
	private String id;

	// description of the product
	@Column
	private String description;
	// END

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public Product mergeUpdates(Product tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}
}
