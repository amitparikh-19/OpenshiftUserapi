package com.kd.apps.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Entity
public class WspProductSku extends BaseAutoIdEntity<WspProductSku> {
	private static final long serialVersionUID = 1L;

	// START
	// wallet service provider id or tenant id
	@Column
	private String wsp;

	// aggregator digital service provider id
	@Column
	private String adsp;

	// original digital service provider id
	@Column
	private String odsp;

	// product code
	@Column
	private String product;

	// sub product code
	@Column
	private String subproduct;

	// txntype
	@Column
	private String txntype;

	// flag if product has fixed price
	@Column
	private Boolean fixedprice;

	// min price if it's price range
	@Column(precision = 32, scale = 4)
	private BigDecimal minprice;

	// max price if it's price range
	@Column(precision = 32, scale = 4)
	private BigDecimal maxprice;

	// % margin for a wsp or tenant
	@Column(precision = 32, scale = 4)
	private BigDecimal wspmargin;

	// default % commission that will be paid to agents
	@Column(precision = 32, scale = 4)
	private BigDecimal commission;

	// default fixed commission that will be paid to agents
	@Column(precision = 32, scale = 4)
	private BigDecimal fixedcommission;

	// default % fee that can
	// be collected from source
	@Column(precision = 32, scale = 4)
	private BigDecimal fee;

	// default fixed fee that can
	// be collected from source
	@Column(precision = 32, scale = 4)
	private BigDecimal fixedfee;

	// default % tax
	@Column(precision = 32, scale = 4)
	private BigDecimal tax;

	// default fixed tax
	@Column(precision = 32, scale = 4)
	private BigDecimal fixedtax;

	// Currency Code
	@Column
	private String currency;

	// start datetime
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date startdatetime;

	// start datetime string
	@Transient
	private String startdatetimestr;

	// end datetime
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date enddatetime;

	// end datetime string
	@Transient
	private String enddatetimestr;

	@Version
	private Long version;
	// END

	@Override
	public WspProductSku mergeUpdates(WspProductSku tobemerged) {
		// TODO Auto-generated method stub
		return null;
	}
}
