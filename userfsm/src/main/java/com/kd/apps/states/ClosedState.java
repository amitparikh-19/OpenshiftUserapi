package com.kd.apps.states;

import java.util.ArrayList;
import java.util.List;

import com.kd.apps.UserFsm;
import com.kd.apps.model.UserFsmResp;
/**
 * 
 * @author Myron Rogtao
 *
 */
public class ClosedState implements State {

	UserFsm fsm;

	public ClosedState(UserFsm fsm) {
		this.fsm = fsm;
	}

	@Override
	public UserFsmResp reject() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp reopen() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp approve() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp reActivate() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp partialBlock() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp partialUnblock() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp fullBlock() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp fullUnblock() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp delete() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp activate() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp suspend() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp close() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp block() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp unBlock() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp selfReqBlock() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp selfReqUnblock() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp getStates() {
		UserFsmResp response = new UserFsmResp();
		response.setPossibleStates(getPossibleStates());
		return response;
	}

	private List<String> getPossibleStates() {
		List<String> states = new ArrayList<>();

		switch (fsm.getEntityType()) {
		case CUSTOMER:
			break;
		case MERCHANTACTOR:
			break;
		case MERCHANTENTITY:
			break;
		}
		return states;
	}

}
