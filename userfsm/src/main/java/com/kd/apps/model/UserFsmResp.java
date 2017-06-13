package com.kd.apps.model;

import java.util.List;

/**
 * @author Myron Rogtao
 * UserFsmResp Model represents the output of the FSM
 *
 */
public class UserFsmResp extends RespTransitionEntity<User> {

	private List<String> possibleStates;

	public List<String> getPossibleStates() {
		return possibleStates;
	}

	public void setPossibleStates(List<String> possibleStates) {
		this.possibleStates = possibleStates;
	}

	public static UserFsmResp getDefaultFailResp() {
		UserFsmResp resp = new UserFsmResp();
		resp.setValid(false);
		return resp;
	}
}
