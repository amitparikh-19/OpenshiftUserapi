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
public class SuspendedState implements State {

	UserFsm fsm;

	public SuspendedState(UserFsm fsm) {
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
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
			fsm.setState(fsm.getSuspendedState());
			response.setValid(true);
		}
		return response;
	}

	@Override
	public UserFsmResp reActivate() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
			fsm.setState(fsm.getPreSuspensionState());
			response.setValid(true);
		}
		return response;
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
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
			fsm.setState(fsm.getClosedState());
			response.setValid(true);
		}
		return response;
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
			states.add(UserState.REGISTERED.getStateValue());
			states.add(UserState.APPROVED.getStateValue());
			states.add(UserState.ACTIVE.getStateValue());
			states.add(UserState.PARTIALBLOCKED.getStateValue());
			states.add(UserState.PARTIALFULLBLOCKED.getStateValue());
			states.add(UserState.FULLBLOCKED.getStateValue());
			states.add(UserState.CLOSED.getStateValue());
			break;
		}
		return states;
	}
}
