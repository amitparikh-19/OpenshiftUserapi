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
public class PartialBlockedState implements State {

	UserFsm fsm;

	public PartialBlockedState(UserFsm fsm) {
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
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
			if (fsm.getPreBlockingState() != null) {
				fsm.setState(fsm.getPreBlockingState());
				response.setValid(true);
			}
		}
		return response;

	}

	@Override
	public UserFsmResp fullBlock() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.MERCHANTENTITY) {
			fsm.setState(fsm.getPartiallyAndFullyBlockedState());
			response.setValid(true);
		} else if (fsm.getEntityType() == UserFsmEntity.CUSTOMER) {
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
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp activate() {
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp suspend() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
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
		return UserFsmResp.getDefaultFailResp();

	}

	@Override
	public UserFsmResp unBlock() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.CUSTOMER) {
			if (fsm.getPreBlockingState() != null) {
				fsm.setState(fsm.getPreBlockingState());
				response.setValid(true);
			}
		}
		return response;
	}

	@Override
	public UserFsmResp selfReqBlock() {
		UserFsmResp response = UserFsmResp.getDefaultFailResp();
		if (fsm.getEntityType() == UserFsmEntity.CUSTOMER) {
			fsm.setState(fsm.getSelfPartialBlockState());
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
		UserFsmResp response = new UserFsmResp();
		response.setPossibleStates(getPossibleStates());
		return response;
	}

	private List<String> getPossibleStates() {
		List<String> states = new ArrayList<>();

		switch (fsm.getEntityType()) {
		case CUSTOMER:
			states.add(UserState.ACTIVE.getStateValue());
			states.add(UserState.PREREGISTERED.getStateValue());
			states.add(UserState.FULLBLOCKED.getStateValue());
			states.add(UserState.SELFPARTIALBLOCKED.getStateValue());
			break;
		case MERCHANTACTOR:
			break;
		case MERCHANTENTITY:
			states.add(UserState.ACTIVE.getStateValue());
			states.add(UserState.REGISTERED.getStateValue());
			states.add(UserState.APPROVED.getStateValue());
			states.add(UserState.SUSPENDED.getStateValue());
			states.add(UserState.PARTIALFULLBLOCKED.getStateValue());
			break;
		}
		return states;
	}

}
