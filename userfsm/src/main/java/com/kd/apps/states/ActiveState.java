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
public class ActiveState implements State {

	UserFsm fsm;

	public ActiveState(UserFsm fsm) {
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
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		response.setValid(false);
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY || fsm.getEntityType() == UserFsmEntity.CUSTOMER) {
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
		response.setValid(false);
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY || fsm.getEntityType() == UserFsmEntity.CUSTOMER) {
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
		response.setValid(false);
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTACTOR) {
			fsm.setState(fsm.getClosedState());
			response.setValid(true);
		}
		return response;
	}

	@Override
	public UserFsmResp activate() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp suspend() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		response.setValid(false);
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
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
		response.setValid(false);
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
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		response.setValid(false);
		if (fsm.getEntityType() == UserFsmEntity.CUSTOMER) {
			fsm.setState(fsm.getSelfBlockState());
			response.setValid(true);
		}
		return response;

	}

	@Override
	public UserFsmResp selfReqUnblock() {
		return UserFsmResp.getDefaultFailResp();
	}

	@Override
	public UserFsmResp getStates() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		response.setPossibleStates(getPossibleStates());
		return response;
	}

	private List<String> getPossibleStates() {
		List<String> states = new ArrayList<>();

		switch (fsm.getEntityType()) {
		case CUSTOMER:
			states.add(UserState.PARTIALBLOCKED.getStateValue());
			states.add(UserState.FULLBLOCKED.getStateValue());
			states.add(UserState.SELFBLOCKED.getStateValue());
			break;
		case MERCHANTACTOR:
			states.add(UserState.CLOSED.getStateValue());
			states.add(UserState.BLOCKED.getStateValue());
			break;
		case MERCHANTENTITY:
			states.add(UserState.SUSPENDED.getStateValue());
			states.add(UserState.FULLBLOCKED.getStateValue());
			states.add(UserState.PARTIALBLOCKED.getStateValue());
			break;
		}
		return states;
	}

}
