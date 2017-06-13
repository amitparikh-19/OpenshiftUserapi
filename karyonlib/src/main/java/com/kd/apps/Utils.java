package com.kd.apps;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * Utility methods for logging and datetime
 * 
 * @author Pradyumna Roy
 *
 */
@Component
public class Utils {

	// private static final String RESPKEYDLMTR = ".";
	// public static final String LOGDLMTR = "|";
	public final String apiname;

	public BigDecimal HUNDRED = new BigDecimal("100");

	/**
	 * @param apinamestr
	 *            Name of the API
	 */
	@Autowired
	public Utils(@Value("${api.name}") String apinamestr) {
		apiname = apinamestr;
	}

	/**
	 * @return apiname - Name of the API
	 */
	public String getApiname() {
		return apiname;
	}

	/**
	 * @param loggedinuser
	 *            - Currently logged in user
	 * @return json string - {"apiname":"$apiname",
	 *         "loggedinuser":"$loggedinuser"}
	 */
	public String logid(String loggedinuser) {
		String logid = "";

		logid = "{" + "\"" + "apiname" + "\":\"" + apiname + "\"," + "\"" + "loggedinuser" + "\":\"" + loggedinuser
				+ "\"}";

		return logid;
	}

	/**
	 * @param stage
	 *            input or processing or output stages of a method execution
	 * @return json string - {"stage":"input"}
	 */
	public String logstage(String stage) {
		return getjson("stage", stage);
	}

	/**
	 * @param method
	 *            name of the method
	 * @return json string - {"method":"$methodname"}
	 */
	public String logmethod(String method) {
		return getjson("method", method);
	}

	/**
	 * @param entityname
	 *            name of the entity
	 * @return json string - {"entity":"$entityname"}
	 */
	public String logentity(String entityname) {
		return getjson("entity", entityname);
	}

	/**
	 * @param prop
	 *            name of the property
	 * @param value
	 *            value of the property
	 * @return json string - {"prop":"$value"}
	 */
	public String getjson(String prop, Object value) {
		String jsonstring = "";
		if (value instanceof String) {
			jsonstring = "\"" + prop + "\":\"" + value.toString() + "\"";
		} else {
			jsonstring = "\"" + prop + "\":" + value.toString();
		}
		return "{" + jsonstring + "}";
	}

	/**
	 * @param pageable
	 *            pagination data
	 * @return json string
	 */
	public String getpaginationstr(Pageable pageable) {
		String paginationstr = "{}";
		if (null != pageable) {
			String sortstr = "[]";
			Iterator<Order> orders = pageable.getSort().iterator();
			if (null != orders) {
				Order order = null;
				sortstr = "[";
				while (orders.hasNext()) {
					order = orders.next();
					sortstr = sortstr + "{" + "\"" + "property" + "\":\"" + order.getProperty() + "\"," + "\""
							+ "direction" + "\":\"" + order.getDirection().toString() + "\"" + "},";
				}
				sortstr = sortstr.substring(0, sortstr.length() - 2);
				sortstr = sortstr + "]";
			}
			paginationstr = "{" + "\"" + "pageNumber" + "\":" + pageable.getPageNumber() + "," + "\"" + "offset" + "\":"
					+ pageable.getOffset() + "," + "\"" + "pageSize" + ":\"" + pageable.getPageSize() + "," + "\""
					+ "sort" + "\":\"" + sortstr + "\"" + "}";
		}
		return paginationstr;
	}

	/*
	 * public String getRespmsgkey(String ctrlr, String methodname, HttpStatus
	 * httpstatus, String respmsgid) { String apiresult = Const.OK; if
	 * (httpstatus.is2xxSuccessful()) { apiresult = Const.OK; } else if
	 * (httpstatus.is5xxServerError()) { apiresult = Const.SRVRKO; } else if
	 * (httpstatus.is4xxClientError()) { apiresult = Const.CLNTKO; } else if
	 * (httpstatus.is1xxInformational()) { apiresult = Const.INFO; } else if
	 * (httpstatus.is3xxRedirection()) { apiresult = Const.REDIR; } return ctrlr
	 * + RESPKEYDLMTR + methodname + RESPKEYDLMTR + apiresult + RESPKEYDLMTR +
	 * respmsgid; }
	 */

	/**
	 * Will be deprecated soon
	 * 
	 * @return txnid string
	 */
	public synchronized String generateTxnID() {
		return String.valueOf(new Date().getTime());
	}

	/**
	 * Method checks for null or empty String
	 * 
	 * @param str
	 *            Input String
	 * @return true if null or empty, else false
	 */
	public boolean isNullOrEmpty(String str) {
		return (str == null || str.trim().equals("")) ? true : false;
	}

	/**
	 * Method prepares Http Header to be sent as a part of the response
	 * 
	 * @param key
	 *            Message Key
	 * @param value
	 *            Message value
	 * @return HttpHeaders
	 */
	public HttpHeaders getRespHeader(String key, String value) {
		HttpHeaders header = new HttpHeaders();
		header.add(Const.HTTP_HEADER_RESP_MSG_KEY, key);
		header.add(Const.HTTP_HEADER_RESP_MSG_VAL, value);
		return header;
	}

	/**
	 * 
	 * @param percent
	 *            percentage
	 * @param percentOfAmount
	 *            amount on which percentage is to be calculated
	 * @return amount in BigDecimal as percentage of another amount
	 */
	public synchronized BigDecimal getAmountFromPercent(BigDecimal percent, BigDecimal percentOfAmount) {
		BigDecimal result = (percent.multiply(percentOfAmount)).divide(HUNDRED, 4, RoundingMode.HALF_UP);
		return result;
	}

	/**
	 * 
	 * @param actualCharge
	 *            amount to be compared with min amount and max amount
	 * @param minCharge
	 *            min amount
	 * @param maxCharge
	 *            max amount
	 * @return actualCharge if minCharge&lt;actualCharge&lt;maxCharge, or
	 *         minCharge if actualCharge&lt;minCharge, or maxCharge if
	 *         maxCharge&gt;actualCharge
	 */
	public synchronized BigDecimal getChargeAmt(BigDecimal actualCharge, BigDecimal minCharge, BigDecimal maxCharge) {
		if (maxCharge != null && actualCharge.compareTo(maxCharge) > 0) {
			return maxCharge;
		} else if (minCharge != null && actualCharge.compareTo(minCharge) > 0) {
			return actualCharge;
		} else {
			return minCharge;
		}
	}

	/**
	 * 
	 * @param strDate
	 *            - date in string
	 * @param dateFormat
	 *            - format of the date
	 * @return date object parsed from given date string and date format
	 */
	public static Date parseDate(String strDate, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			return format.parse(strDate);
		} catch (ParseException e) {
			// Log Error
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Round up the date to set the time to 23:59:59:999
	 * 
	 * @param date
	 *            Date to be rounded up
	 * @return rounded up date
	 */
	public static Date roundUp(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	/**
	 * Round down the date to set the time to 0:0:0:0
	 * 
	 * @param date
	 *            Date to be rounded down
	 * @return rounded down date
	 */
	public static Date roundDown(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
}
