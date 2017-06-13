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
public class SelfReqBlockState implements State {

	UserFsm fsm;

	public SelfReqBlockState(UserFsm fsm) {
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
		if (fsm.getEntityType() == UserFsmEntity.CUSTOMER) {
			fsm.setPreBlockingState(this);
			fsm.setState(fsm.getSelfPartialBlockState());
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
		if (fsm.getEntityType() == UserFsmEntity.CUSTOMER) {
			fsm.setPreBlockingState(this);
			fsm.setState(fsm.getSelfFullBlockState());
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
		if (fsm.getEntityType() == UserFsmEntity.CUSTOMER) {
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
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.CUSTOMER) {
			fsm.setState(fsm.getPreBlockingState());
			response.setValid(true);
		}
		return response;
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
			states.add(UserState.SELFFULLBLOCKED.getStateValue());
			states.add(UserState.SELFPARTIALBLOCKED.getStateValue());
			states.add(UserState.ACTIVE.getStateValue());
			states.add(UserState.CLOSED.getStateValue());
			states.add(UserState.PREREGISTERED.getStateValue());
			break;
		case MERCHANTACTOR:
			break;
		case MERCHANTENTITY:
			break;
		}
		return states;
	}

}
