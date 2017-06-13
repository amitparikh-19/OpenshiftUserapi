package com.kd.apps.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class Charge extends BaseAutoIdEntity<Charge> {
	private static final long serialVersionUID = 1L;

	// fee is always from customer to wsp
	// commission is always from wsp to merchant

	// wallet service provider id or tenant id
	@Column
	private String wsp;

	// source type like fee, comm
	@Column
	private String chargetype;

	// // fundin, fundout
	// cashin, cashout
	// transfer, reverse
	// refund, sale/purchase
	// loyalty, bonus
	@Column
	private String txntype;

	// source type like merchant, customer, wsp etc.
	@Column
	private String chargedtotype;

	// Paer of the charge. Source/ Destination
	@Column
	private String payer;

	// source type like merchant, customer, wsp etc.
	@Column
	private String beneficiarytype;

	@Column
	private BigDecimal fixedcharge;

	@Column
	private BigDecimal mincharge;

	@Column
	private BigDecimal maxcharge;

	@Column
	private Float percentcharge;

	// date time for the start of the charge
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column
	private Date startdatetime;

	// date time string for the start of the charge
	@Transient
	private String startdatetimestr;

	// date time for the end of the charge
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column
	private Date enddatetime;

	// date time string for the end of the charge
	@Transient
	private String enddatetimestr;

	// freeform comment
	@Column
	private String comment;
	// END

	/**
	 * 
	 * @return  wsp - {@link String} wallet service provider id or tenant id
	 */	
	public String getWsp() {
		return wsp;
	}

	/**
	 * 
	 * @param wsp {@link String} wallet service provider id or tenant id
	 */
	public void setWsp(String wsp) {
		this.wsp = wsp;
	}

	/**
	 * 
	 * @return  chargetype - {@link String} source type like fee, comm
	 */	
	public String getChargetype() {
		return chargetype;
	}

	/**
	 * 
	 * @param chargetype {@link String} source type like fee, comm
	 */	
	public void setChargetype(String chargetype) {
		this.chargetype = chargetype;
	}

	/**
	 * 
	 * @return  payer - {@link String} Payer of the charge. Source/ Destination
	 */	
	public String getPayer() {
		return payer;
	}

	/**
	 * 
	 * @param payer {@link String} Payer of the charge. Source/ Destination
	 */	
	public void setPayer(String payer) {
		this.payer = payer;
	}

	/**
	 * 
	 * @return  txntype - {@link String} fundin, fundout,cashin, cashout,transfer, reverse,refund, sale/purchase,loyalty, bonus
	 */	
	public String getTxntype() {
		return txntype;
	}

	/**
	 * 
	 * @param txntype {@link String} fundin, fundout,cashin, cashout,transfer, reverse,refund, sale/purchase,loyalty, bonus
	 */	
	public void setTxntype(String txntype) {
		this.txntype = txntype;
	}

	/**
	 * 
	 * @return chargedtotype - {@link String} source type like merchant, customer, wsp etc.
	 */
	public String getChargedtotype() {
		return chargedtotype;
	}

	/**
	 * 
	 * @param chargedtotype {@link String} source type like merchant, customer, wsp etc.
	 */	
	public void setChargedtotype(String chargedtotype) {
		this.chargedtotype = chargedtotype;
	}

	/**
	 * 
	 * @return beneficiarytype - {@link String} beneficiary type like merchant, customer, wsp etc
	 */
	public String getBeneficiarytype() {
		return beneficiarytype;
	}

	/**
	 * 
	 * @param beneficiarytype {@link String} beneficiary type like merchant, customer, wsp etc
	 */	
	public void setBeneficiarytype(String beneficiarytype) {
		this.beneficiarytype = beneficiarytype;
	}

	/**
	 * 
	 * @return fixedcharge - {@link BigDecimal} fix charge
	 */
	public BigDecimal getFixedcharge() {
		return fixedcharge;
	}

	/**
	 * 
	 * @param fixedcharge {@link BigDecimal} fix charge
	 */	
	public void setFixedcharge(BigDecimal fixedcharge) {
		this.fixedcharge = fixedcharge;
	}

	/**
	 * 
	 * @return mincharge - {@link BigDecimal} minimum charge
	 */
	public BigDecimal getMincharge() {
		return mincharge;
	}

	/**
	 * 
	 * @param mincharge {@link BigDecimal} minimum charge
	 */	
	public void setMincharge(BigDecimal mincharge) {
		this.mincharge = mincharge;
	}
	
	/**
	 * 
	 * @return maxcharge - {@link BigDecimal} maximum charge
	 */
	public BigDecimal getMaxcharge() {
		return maxcharge;
	}

	/**
	 * 
	 * @param maxcharge {@link BigDecimal} maximum charge
	 */
	public void setMaxcharge(BigDecimal maxcharge) {
		this.maxcharge = maxcharge;
	}

	/**
	 * 
	 * @return percentcharge - {@link Float} percentage charge
	 */
	public Float getPercentcharge() {
		return percentcharge;
	}

	/**
	 * 
	 * @param percentcharge {@link Float} percentage charge
	 */
	public void setPercentcharge(Float percentcharge) {
		this.percentcharge = percentcharge;
	}

	/**
	 * 
	 * @return startdatetime - {@link Date} date time for the start of the charge
	 */
	public Date getStartdatetime() {
		return startdatetime;
	}

	/**
	 * 
	 * @param startdatetime {@link Date} date time for the start of the charge
	 */
	public void setStartdatetime(Date startdatetime) {
		this.startdatetime = startdatetime;
	}

	/**
	 * 
	 * @return startdatetimestr - {@link String} string date time for the start of the charge
	 */
	public String getStartdatetimestr() {
		return startdatetimestr;
	}

	/**
	 * 
	 * @param startdatetimestr {@link String} string date time for the start of the charge
	 */
	public void setStartdatetimestr(String startdatetimestr) {
		this.startdatetimestr = startdatetimestr;
	}

	/**
	 * 
	 * @return enddatetime - {@link Date} date time for the end of the charge
	 */
	public Date getEnddatetime() {
		return enddatetime;
	}

	/**
	 * 
	 * @param enddatetime {@link Date} date time for the end of the charge
	 */
	public void setEnddatetime(Date enddatetime) {
		this.enddatetime = enddatetime;
	}

	/**
	 * 
	 * @return enddatetimestr - {@link String} string date time for the end of the charge
	 */
	public String getEnddatetimestr() {
		return enddatetimestr;
	}

	/**
	 * 
	 * @param enddatetimestr {@link String} string date time for the end of the charge
	 */
	public void setEnddatetimestr(String enddatetimestr) {
		this.enddatetimestr = enddatetimestr;
	}

	/**
	 * 
	 * @return comment - {@link String} free text comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * 
	 * @param comment {@link String} free text comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 
	 * @param Charge {@link Charge} 
	 */
	@Override
	@javax.persistence.Transient
	public Charge mergeUpdates(Charge tobemerged) {
		this.setWsp((null != tobemerged.getWsp() && !tobemerged.getWsp().trim().equals("")) ? tobemerged.getWsp()
				: this.getWsp());
		this.setChargetype((null != tobemerged.getChargetype() && !tobemerged.getChargetype().trim().equals(""))
				? tobemerged.getChargetype() : this.getChargetype());
		this.setTxntype((null != tobemerged.getTxntype() && !tobemerged.getTxntype().trim().equals(""))
				? tobemerged.getTxntype() : this.getTxntype());
		this.setChargedtotype(
				(null != tobemerged.getChargedtotype() && !tobemerged.getChargedtotype().trim().equals(""))
						? tobemerged.getChargedtotype() : this.getChargedtotype());
		this.setPayer((null != tobemerged.getPayer() && !tobemerged.getPayer().trim().equals(""))
				? tobemerged.getPayer() : this.getPayer());
		this.setBeneficiarytype(
				(null != tobemerged.getBeneficiarytype() && !tobemerged.getBeneficiarytype().trim().equals(""))
						? tobemerged.getBeneficiarytype() : this.getBeneficiarytype());
		this.setFixedcharge(
				(null != tobemerged.getFixedcharge() ? tobemerged.getFixedcharge() : this.getFixedcharge()));
		this.setMincharge((null != tobemerged.getMincharge() ? tobemerged.getMincharge() : this.getMincharge()));
		this.setMaxcharge((null != tobemerged.getMaxcharge() ? tobemerged.getMaxcharge() : this.getMaxcharge()));
		this.setPercentcharge(
				(null != tobemerged.getPercentcharge() ? tobemerged.getPercentcharge() : this.getPercentcharge()));
		this.setStartdatetime(
				(null != tobemerged.getStartdatetime() ? tobemerged.getStartdatetime() : this.getStartdatetime()));
		this.setStartdatetimestr(
				(null != tobemerged.getStartdatetimestr() && !tobemerged.getStartdatetimestr().trim().equals(""))
						? tobemerged.getStartdatetimestr() : this.getStartdatetimestr());
		this.setEnddatetime(
				(null != tobemerged.getEnddatetime() ? tobemerged.getEnddatetime() : this.getEnddatetime()));
		this.setEnddatetimestr(
				(null != tobemerged.getEnddatetimestr() && !tobemerged.getEnddatetimestr().trim().equals(""))
						? tobemerged.getEnddatetimestr() : this.getEnddatetimestr());
		this.setComment((null != tobemerged.getComment() && !tobemerged.getComment().trim().equals(""))
				? tobemerged.getComment() : this.getComment());
		this.setId((null != tobemerged.getId() ? tobemerged.getId() : this.getId()));
		this.setCreated((null != tobemerged.getCreated() ? tobemerged.getCreated() : this.getCreated()));
		this.setCreatedstr((null != tobemerged.getCreatedstr() && !tobemerged.getCreatedstr().trim().equals(""))
				? tobemerged.getCreatedstr() : this.getCreatedstr());

		return this;
	}
}
