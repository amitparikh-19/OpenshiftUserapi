package com.kd.apps.states;

import java.util.ArrayList;
import java.util.List;

import com.kd.apps.UserFsm;
import com.kd.apps.enums.UserFsmEntity;
import com.kd.apps.enums.UserState;
import com.kd.apps.model.UserFsmResp;
/**
 * 
 * @author Myron Rogtao
 *
 */
public class RejectedState implements State {

	UserFsm fsm;

	public RejectedState(UserFsm fsm) {
		this.fsm = fsm;
	}

	@Override
	public UserFsmResp reject() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp reopen() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
			fsm.setState(fsm.getRegisteredState());
			response.setValid(true);
		}
		return response;
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
			states.add(UserState.ACTIVE.getStateValue());
			states.add(UserState.BLOCKED.getStateValue());
			break;
		case MERCHANTENTITY:
			states.add(UserState.PARTIALBLOCKED.getStateValue());
			states.add(UserState.FULLBLOCKED.getStateValue());
			states.add(UserState.APPROVED.getStateValue());
			states.add(UserState.ACTIVE.getStateValue());
			states.add(UserState.CLOSED.getStateValue());
			states.add(UserState.REJECTED.getStateValue());
			states.add(UserState.SUSPENDED.getStateValue());
			break;
		}
		return states;
	}

}
