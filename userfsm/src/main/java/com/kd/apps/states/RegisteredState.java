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
public class RegisteredState implements State {

	UserFsm fsm;

	public RegisteredState(UserFsm fsm) {
		this.fsm = fsm;
	}

	@Override
	public UserFsmResp reject() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
			fsm.setState(fsm.getRejectedState());
			response.setValid(true);
		}
		return response;

	}

	@Override
	public UserFsmResp reopen() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp approve() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
			if (fsm.getUserWrapper().getUser().getSubtype().equals("Branch")) {
				fsm.setState(fsm.getActiveState());
				response.setValid(true);
			} else {
				fsm.setState(fsm.getApprovedState());
				response.setValid(true);
			}
		}
		return response;

	}

	@Override
	public UserFsmResp reActivate() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp partialBlock() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
			fsm.setPreBlockingState(this);
			fsm.setState(fsm.getPartiallyBlockedState());
			response.setValid(true);
		}
		return response;

	}

	@Override
	public UserFsmResp partialUnblock() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp fullBlock() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
			fsm.setPreBlockingState(this);
			fsm.setState(fsm.getFullyBlockedState());
			response.setValid(true);
		}
		return response;

	}

	@Override
	public UserFsmResp fullUnblock() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp delete() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		fsm.setState(fsm.getClosedState());
		response.setValid(true);
		return response;
	}

	@Override
	public UserFsmResp activate() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTACTOR) {
			fsm.setState(fsm.getActiveState());
			response.setValid(true);
		}
		return response;

	}

	@Override
	public UserFsmResp suspend() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTACTOR) {
			fsm.setPreSuspensionState(this);
			fsm.setState(fsm.getSuspendedState());
			response.setValid(true);
		}
		return response;
	}

	@Override
	public UserFsmResp close() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp block() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTACTOR) {
			fsm.setPreBlockingState(this);
			fsm.setState(fsm.getBlockedState());
			response.setValid(true);
		}
		return response;

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
