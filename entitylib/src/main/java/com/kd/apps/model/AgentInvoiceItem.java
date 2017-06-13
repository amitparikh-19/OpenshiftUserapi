package com.kd.apps.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class AgentInvoiceItem extends BaseAutoIdEntity<AgentInvoiceItem> {
	private static final long serialVersionUID = 1L;

	// START
	// Agent Invoice Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agentinvoice")
	private AgentInvoice agentinvoice;

	// product code
	@Column
	private String product;

	// sub product code
	@Column
	private String subproduct;

	// txn count
	@Column
	private Long txncount;

	// total amount of txn
	@Column(precision = 32, scale = 4)
	private BigDecimal totaltxnamount;

	// total fee amount
	@Column(precision = 32, scale = 4)
	private BigDecimal totalfee;

	// total tax amount
	@Column(precision = 32, scale = 4)
	private BigDecimal totaltaxamount;

	// total commission amount
	@Column(precision = 32, scale = 4)
	private BigDecimal totalcommission;

	// currency
	@Column
	private String currency;

	/**
	 * 
	 * @return agentinvoice - {@link AgentInvoice} AgentInvoice id
	 */
	public AgentInvoice getAgentinvoice() {
		return agentinvoice;
	}

	/**
	 * 
	 * @param agentinvoice {@link AgentInvoice} AgentInvoice id
	 */
	public void setAgentinvoice(AgentInvoice agentinvoice) {
		this.agentinvoice = agentinvoice;
	}

	/**
	 * 
	 * @return product - {@link String} product code
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * 
	 * @param product {@link String} product code
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * 
	 * @return subproduct - {@link String} sub product code
	 */
	public String getSubproduct() {
		return subproduct;
	}

	/**
	 * 
	 * @param subproduct {@link String} sub product code
	 */
	public void setSubproduct(String subproduct) {
		this.subproduct = subproduct;
	}

	/**
	 * 
	 * @return txncount - {@link Long} transaction count
	 */
	public Long getTxncount() {
		return txncount;
	}

	/**
	 * 
	 * @param txncount {@link Long} transaction count
	 */
	public void setTxncount(Long txncount) {
		this.txncount = txncount;
	}

	/**
	 * 
	 * @return totaltxnamount - {@link BigDecimal} total transaction count
	 */
	public BigDecimal getTotaltxnamount() {
		return totaltxnamount;
	}

	/**
	 * 
	 * @param totaltxnamount {@link BigDecimal} total transaction count
	 */
	public void setTotaltxnamount(BigDecimal totaltxnamount) {
		this.totaltxnamount = totaltxnamount;
	}

	/**
	 * 
	 * @return totalfee - {@link BigDecimal} total fee
	 */
	public BigDecimal getTotalfee() {
		return totalfee;
	}

	/**
	 * 
	 * @param totalfee {@link BigDecimal} total fee
	 */
	public void setTotalfee(BigDecimal totalfee) {
		this.totalfee = totalfee;
	}

	/**
	 * 
	 * @return totaltaxamount - {@link BigDecimal} total tax amount
	 */
	public BigDecimal getTotaltaxamount() {
		return totaltaxamount;
	}

	/**
	 * 
	 * @param totaltaxamount {@link BigDecimal} total tax amount
	 */
	public void setTotaltaxamount(BigDecimal totaltaxamount) {
		this.totaltaxamount = totaltaxamount;
	}

	/**
	 * 
	 * @return totalcommission - {@link BigDecimal} total commission
	 */
	public BigDecimal getTotalcommission() {
		return totalcommission;
	}

	/**
	 * 
	 * @param totalcommission {@link BigDecimal} total commission
	 */
	public void setTotalcommission(BigDecimal totalcommission) {
		this.totalcommission = totalcommission;
	}

	/**
	 * 
	 * @return currency - {@link String} currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 
	 * @param currency {@link String} currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * 
	 * @param AgentInvoiceItem {@link AgentInvoiceItem} 
	 */
	@Override
	public AgentInvoiceItem mergeUpdates(AgentInvoiceItem tobemerged) {
		this.agentinvoice = (null != tobemerged.getAgentinvoice() ? tobemerged.getAgentinvoice()
				: this.getAgentinvoice());
		;
		this.product = (null != tobemerged.getProduct() && !("".equals(tobemerged.getProduct()))
				&& !(" ".equals(tobemerged.getProduct())) ? tobemerged.getProduct() : this.getProduct());
		this.subproduct = (null != tobemerged.getSubproduct() && !("".equals(tobemerged.getSubproduct()))
				&& !(" ".equals(tobemerged.getSubproduct())) ? tobemerged.getSubproduct() : this.getSubproduct());
		this.txncount = (null != tobemerged.getTxncount() && !("".equals(tobemerged.getTxncount()))
				&& !(" ".equals(tobemerged.getTxncount())) ? tobemerged.getTxncount() : this.getTxncount());
		this.totaltxnamount = (null != tobemerged.getTotaltxnamount() ? tobemerged.getTotaltxnamount()
				: this.getTotaltxnamount());
		this.totalfee = (null != tobemerged.getTotalfee() ? tobemerged.getTotalfee() : this.getTotalfee());
		this.totaltaxamount = (null != tobemerged.getTotaltaxamount() ? tobemerged.getTotaltaxamount()
				: this.getTotaltaxamount());
		this.totalcommission = (null != tobemerged.getTotalcommission() ? tobemerged.getTotalcommission()
				: this.getTotalcommission());
		this.currency = (null != tobemerged.getCurrency() && !("".equals(tobemerged.getCurrency()))
				&& !(" ".equals(tobemerged.getCurrency())) ? tobemerged.getCurrency() : this.getCurrency());

		return this;

	}
}
