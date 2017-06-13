package com.kd.apps.enums;
/**
 * 
 * @author Myron  Rogtao
 *
 */
public enum Product {

	TOPUP("Topup"), DMONEY("DMoney");

	private String product;

	Product(String product) {
		this.product = product;
	}

	public String getProductType() {
		return product;
	}
}
