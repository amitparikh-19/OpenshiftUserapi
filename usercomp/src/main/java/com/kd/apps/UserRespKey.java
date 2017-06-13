package com.kd.apps;

/**
 * @author Myron Rogtao
 * RespKey enum holds constants to represent the Response Message Keys with
 * default value. Each new Response Key entry will have the Date specified as an
 * Attribute of the RespKey enum.
 *
 */
public enum UserRespKey {

	TRANSITION_VALID("usersrvc.valid.transition", UserConst.TRANSITION_VALID_VAL, "01-Mar-2017"), INVALID_EVENT(
			"usersrvc.invalid.event", UserConst.INVALID_EVENT_VAL,
			"01-Mar-2017"), USER_NOT_FOUND("usersrvc.invalid.user", UserConst.USER_NOT_FOUND_VAL,
					"01-Mar-2017"), INVALID_TRANSITION("usersrvc.invalid.transition", UserConst.INVALID_TRANSITION_VAL,
							"01-Mar-2017"), INVALID_STATE("usersrvc.invalid.state", UserConst.INVALID_STATE_VAL,
									"01-Mar-2017"), INVALID_DATA("usersrvc.inpitdata.invalid",
											UserConst.INVALID_USER_DATA, "01-Mar-2017"), USER_LIST_SUCCESS(
													"usersrvc.wsplist.success", UserConst.USER_LIST_SUCCESS,
													"01-Mar-2017"), USER_LIST_FAIL("usersrvc.wsplist.fail",
															UserConst.USER_LIST_FAIL, "21-Mar-2017"), TYPE_LIST_SUCCESS(
																	"usersrvc.usertypelist.success",
																	UserConst.TYPE_LIST_SUCCESS, "21-Mar-2017");

	private String key;
	private String defaultMsg;
	private String creationDate;

	UserRespKey(String key, String defaultMsg, String creationDate) {
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
