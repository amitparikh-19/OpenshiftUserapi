package com.kd.apps;

/**
 * 
 * RespKey enum holds constants to represent the Response Message Keys with
 * default value. Each new Response Key entry will have the Date specified as an
 * Attribute of the RespKey enum.
 * 
 * @author Myron Rogtao
 * 
 */
public enum RespKey {

	CREATE_DUPLICATE_KEY("basesrvc.create.srvrko.dupkey", Const.DUPLICATE_ID_VAL, "02-Feb-2017"), CREATE_OK(
			"basesrvc.create.ok.success", Const.CREATE_SUCCESS_VAL, "02-Feb-2017"), CREATE_CONSTRAINT_VIOLATION(
					"basesrvc.create.srvrko.constraint_violation", Const.CONSTRAINT_VIOLATION_VAL,
					"02-Feb-2017"), CREATE_JPA_EX("basesrvc.create.srvrko.jpa_system_exception",
							Const.JPA_SYS_ERROR_VAL, "02-Feb-2017"), CREATE_DB_CONN_BAD(
									"basesrvc.create.srvrko.db_conn_unavailable", Const.DB_CONN_BAD_VAL,
									"02-Feb-2017"), CREATE_SYS_ERR("basesrvc.create.srvrko.system_error",
											Const.SYS_ERR_VAL, "02-Feb-2017"),

	READ_OK("basesrvc.read.ok.success", Const.READ_SUCCESS_VAL, "02-Feb-2017"), READ_NO_RECORD(
			"basesrvc.read.srvrko.norecord", Const.ENTITY_NOT_FOUND_VAL,
			"02-Feb-2017"), READ_JPA_EX("basesrvc.read.srvrko.norecord", Const.ENTITY_NOT_FOUND_VAL,
					"02-Feb-2017"), READ_DB_CONN_BAD("basesrvc.read.srvrko.db_conn_unavailable", Const.DB_CONN_BAD_VAL,
							"02-Feb-2017"), READ_SYS_ERR("basesrvc.read.srvrko.system_error", Const.SYS_ERR_VAL,
									"02-Feb-2017"),

	READALL_OK("basesrvc.readall.ok.success", Const.READ_SUCCESS_VAL, "02-Feb-2017"), READALL_NO_RECORD(
			"basesrvc.readall.srvrko.norecord", Const.ENTITY_NOT_FOUND_VAL,
			"02-Feb-2017"), READALL_JPA_EX("basesrvc.readall.srvrko.norecord", Const.ENTITY_NOT_FOUND_VAL,
					"02-Feb-2017"), READALL_DB_CONN_BAD("basesrvc.readall.srvrko.db_conn_unavailable",
							Const.DB_CONN_BAD_VAL, "02-Feb-2017"), READALL_SYS_ERR(
									"basesrvc.readall.srvrko.system_error", Const.SYS_ERR_VAL, "02-Feb-2017"),

	READ_PGBL_OK("basesrvc.readallpageable.ok.success", Const.READ_SUCCESS_VAL, "02-Feb-2017"), READ_PGBL_NO_RECORD(
			"basesrvc.readallpageable.srvrko.norecord", Const.ENTITY_NOT_FOUND_VAL,
			"02-Feb-2017"), READ_PGBL_JPA_EX("basesrvc.readallpageable.srvrko.norecord", Const.ENTITY_NOT_FOUND_VAL,
					"02-Feb-2017"), READ_PGBL_DB_CONN_BAD("basesrvc.readallpageable.srvrko.db_conn_unavailable",
							Const.DB_CONN_BAD_VAL, "02-Feb-2017"), READ_PGBL_SYS_ERR(
									"basesrvc.readallpageable.srvrko.system_error", Const.SYS_ERR_VAL, "02-Feb-2017"),

	UPDATE_OK("basesrvc.update.ok.success", Const.UPDATE_SUCCESS_VAL, "02-Feb-2017"), UPDATE_NO_RECORD(
			"basesrvc.update.srvrko.norecord", Const.ENTITY_NOT_FOUND_VAL,
			"02-Feb-2017"), UPDATE_JPA_EX("basesrvc.update.srvrko.jpa_system_exception", Const.JPA_SYS_ERROR_VAL,
					"02-Feb-2017"), UPDATE_DB_CONN_BAD("basesrvc.update.srvrko.db_conn_unavailable",
							Const.DB_CONN_BAD_VAL, "02-Feb-2017"), UPDATE_SYS_ERR("basesrvc.update.srvrko.system_error",
									Const.SYS_ERR_VAL, "02-Feb-2017"),

	DELETE_OK("basesrvc.delete.ok.success", Const.DELETE_SUCCESS_VAL, "02-Feb-2017"), DELETE_NO_RECORD(
			"basesrvc.delete.srvrko.norecord", Const.ENTITY_NOT_FOUND_VAL,
			"02-Feb-2017"), DELETE_JPA_EX("basesrvc.delete.srvrko.jpa_system_exception", Const.JPA_SYS_ERROR_VAL,
					"02-Feb-2017"), DELETE_DB_CONN_BAD("basesrvc.delete.srvrko.db_conn_unavailable",
							Const.DB_CONN_BAD_VAL, "02-Feb-2017"), DELETE_SYS_ERR("basesrvc.delete.srvrko.system_error",
									Const.SYS_ERR_VAL, "02-Feb-2017");

	private String key;
	private String defaultMsg;
	private String creationDate;

	RespKey(String key, String defaultMsg, String creationDate) {
		this.key = key;
		this.defaultMsg = defaultMsg;
		this.creationDate = creationDate;
	}

	public String getKey() {
		return key;
	}

	public String getMsg() {
		return defaultMsg;
	}

	public String creationDate() {
		return creationDate;
	}
}
