package com.kd.apps;

import org.springframework.stereotype.Component;

/**
 * Global constants file
 * 
 * @author Pradyumna Roy
 *
 */
@Component
public class Const {

	public static final String HTTP_HEADER_RESP_MSG_KEY = "RESP-MSG-KEY";
	public static final String HTTP_HEADER_RESP_MSG_VAL = "RESP-MSG-VAL";

	public static final String INFO = "info";
	public static final String OK = "ok";
	public static final String REDIR = "redir";
	public static final String CLNTKO = "clnt.ko";
	public static final String SRVRKO = "srvr.ko";
	public static final String NO_MSG_KEY = "NOMSGKEY";
	public static final String NO_VAL = "NOVAL";

	public static final String DEFAULT_SUPERADMIN = "sysadmin";
	public static final String DEFAULT_PASSWORD = "su3spense";

	public static final String CREATE_SUCCESS_VAL = "creation successful";
	public static final String READ_SUCCESS_VAL = "read success";
	public static final String UPDATE_SUCCESS_VAL = "updated successfully";
	public static final String DELETE_SUCCESS_VAL = "deleted successfully";

	public static final String ENTITY_NOT_FOUND_VAL = "entity not found";
	public static final String DUPLICATE_ID_VAL = "duplicate primary key";
	public static final String CONSTRAINT_VIOLATION_VAL = "constraint violation";
	public static final String JPA_SYS_ERROR_VAL = "jpa system exception";
	public static final String DB_CONN_BAD_VAL = "db connection unavailable";
	public static final String SYS_ERR_VAL = "system error";

	// Constant that defines the Format for date column search/filter and the
	// reg-ex for the date format
	public static final String FILTER_DATE_FORMAT = "dd-MM-yyyy";
	public static final String FILTER_DATE_REGEX = "\\d{2}-\\d{2}-\\d{4}";
}
