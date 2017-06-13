
package com.kd.apps.dto;

import java.math.BigDecimal;
/**
 * 
 * @author Amit Parikh
 *
 */
public class TpAdapterReq {

	private String adapterName;
	private String subscriber;
	private String product;
	private BigDecimal amount;
	private String partnerref;

	/**
	 * 
	 * @return String
	 */
	public String getAdapterName() {
		return adapterName;
	}

	/**
	 * 
	 * @param adapterName String
	 */
	public void setAdapterName(String adapterName) {
		this.adapterName = adapterName;
	}

	/**
	 * 
	 * @return String
	 */
	public String getSubscriber() {
		return subscriber;
	}

	/**
	 * 
	 * @param value String
	 */
	public void setSubscriber(String value) {
		this.subscriber = value;
	}

	/**
	 * 
	 * @return String
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * 
	 * @param value String
	 */
	public void setProduct(String value) {
		this.product = value;
	}

	/**
	 * 
	 * @return String
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 
	 * @param value String
	 */
	public void setAmount(BigDecimal value) {
		this.amount = value;
	}

	/**
	 * 
	 * @return String
	 */
	public String getPartnerref() {
		return partnerref;
	}

	/**
	 * 
	 * @param value String
	 */
	public void setPartnerref(String value) {
		this.partnerref = value;
	}

}
