package com.kd.apps.srvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kd.apps.enums.UserState;

/**
 * 
 * @author Pradyumna Roy
 * 
 */
@Service
public class MerchantStateTransition extends StateTransition<UserState> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kd.apps.srvc.StateTransition#listPossibleStatesForTransition()
	 */
	@Override
	public List<String> listPossibleStatesForTransition() {
		switch (this.getCurrentState()) {
		case ACTIVE:
			setPossibleStates(statesFromActive());
			break;
		case BLOCKED:
			setPossibleStates(statesFromBlocked());
			break;
		}
		return getPossibleStates();
	}

	private List<String> statesFromActive() {
		List<String> states = new ArrayList<>();
		states.add(UserState.CLOSED.getStateValue());
		states.add(UserState.BLOCKED.getStateValue());
		return states;
	}

	private List<String> statesFromBlocked() {
		List<String> states = new ArrayList<>();
		states.add(UserState.REGISTERED.getStateValue());
		states.add(UserState.ACTIVE.getStateValue());
		return states;
	}
}
