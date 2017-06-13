package com.kd.apps;

import org.springframework.stereotype.Component;

import com.kd.apps.enums.UserEvent;
import com.kd.apps.enums.UserFsmEntity;
import com.kd.apps.enums.UserState;
import com.kd.apps.enums.UserSubType;
import com.kd.apps.enums.UserType;
import com.kd.apps.model.User;
import com.kd.apps.model.UserFsmData;
import com.kd.apps.model.UserFsmResp;
import com.kd.apps.states.ActiveState;
import com.kd.apps.states.ApprovedState;
import com.kd.apps.states.BlockedState;
import com.kd.apps.states.ClosedState;
import com.kd.apps.states.FullBlockedState;
import com.kd.apps.states.PartialBlockedState;
import com.kd.apps.states.PartialFullBlockedState;
import com.kd.apps.states.PreRegisteredState;
import com.kd.apps.states.RegisteredState;
import com.kd.apps.states.RejectedState;
import com.kd.apps.states.SelfReqBlockState;
import com.kd.apps.states.SelfReqFullBlockState;
import com.kd.apps.states.SelfReqPartialBlockState;
import com.kd.apps.states.State;
import com.kd.apps.states.SuspendedState;

/**
 * @author Myron Rogtao
 * UserFsm class is the Finite State Machine representing User Life Cycle
 *
 */
@Component
public final class UserFsm implements UserFsmTrigger {

	// States of the FSM
	private State registeredState;
	private State suspendedState;
	private State rejectedState;
	private State partialBlockedState;
	private State fullBlockedState;
	private State partialFullBlockedState;
	private State closedState;
	private State approvedState;
	private State activeState;
	private State blockedState;
	private State selfBlockState;
	private State selfFullBlockState;
	private State selfPatrtialBlockState;
	private State preRegState;

	// Internal states maintained for FSM operation
	private State currentState;
	private State preBlockingState;
	private State preSuspensionState;

	// Entity Type for which the FSM is built.
	private UserFsmEntity entityType;

	// Input data to the FSM
	private UserFsmData userWrapper;

	private UserFsm() {
	}

	private UserFsm(UserFsmData userWrapper, UserEvent uEvent) {

		this.registeredState = (State) new RegisteredState(this);
		this.rejectedState = (State) new RejectedState(this);
		this.suspendedState = (State) new SuspendedState(this);
		this.partialBlockedState = (State) new PartialBlockedState(this);
		this.fullBlockedState = (State) new FullBlockedState(this);
		this.partialFullBlockedState = (State) new PartialFullBlockedState(this);
		this.closedState = (State) new ClosedState(this);
		this.approvedState = (State) new ApprovedState(this);
		this.activeState = (State) new ActiveState(this);
		this.blockedState = (State) new BlockedState(this);
		this.selfBlockState = (State) new SelfReqBlockState(this);
		this.selfFullBlockState = (State) new SelfReqFullBlockState(this);
		this.selfPatrtialBlockState = (State) new SelfReqPartialBlockState(this);
		this.preRegState = (State) new PreRegisteredState(this);

		this.userWrapper = userWrapper;
		this.currentState = getUserState(userWrapper.getCurrentState());
		this.entityType = getFsmEntityType(userWrapper.getUser().getType(), userWrapper.getUser().getSubtype());
		this.preBlockingState = getUserState(userWrapper.getPreBlockinState());
		this.preSuspensionState = getUserState(userWrapper.getPreSuspensionState());

	}
	
	/**
	 * Method prepares FSM input data and triggers user event. Output of the
	 * operation is UserFsmResp object representing the transition outcome.
	 * @param data
	 *            : User data as input for the FSM
	 * @param event
	 *            : user event to be triggered
	 * @return
	 */
	@Override
	public UserFsmResp initAndTrigger(UserFsmData data, UserEvent event) {
		UserFsm fsm = new UserFsm(data, event);
		return fsm.triggerEvent(event);
	}

	public void setState(State state) {
		this.currentState = state;
	}

	public State getState() {
		return this.currentState;
	}

	private UserFsmResp reject() {
		return this.currentState.reject();
	}

	private UserFsmResp reopen() {
		return this.currentState.reopen();
	}

	private UserFsmResp approve() {
		return this.currentState.approve();
	}

	private UserFsmResp reActivateAndApprove() {
		return this.currentState.reActivate();
	}

	private UserFsmResp partialBlock() {
		return this.currentState.partialBlock();
	}

	private UserFsmResp partialUnblock() {
		return this.currentState.partialUnblock();
	}

	private UserFsmResp fullBlock() {
		return this.currentState.fullBlock();
	}

	private UserFsmResp block() {
		return this.currentState.block();
	}

	private UserFsmResp unBlock() {
		return this.currentState.unBlock();
	}

	private UserFsmResp fullUnblock() {
		return this.currentState.fullUnblock();
	}

	private UserFsmResp delete() {
		return this.currentState.delete();
	}

	private UserFsmResp activate() {
		return this.currentState.activate();
	}

	private UserFsmResp suspend() {
		return this.currentState.suspend();
	}

	private UserFsmResp close() {
		return this.currentState.close();
	}

	private UserFsmResp selfBlock() {
		return this.currentState.selfReqBlock();
	}

	private UserFsmResp selfUnblock() {
		return this.currentState.selfReqUnblock();
	}

	private UserFsmResp getStates() {
		return this.currentState.getStates();
	}

	public State getRegisteredState() {
		return this.registeredState;
	}

	public State getRejectedState() {
		return this.rejectedState;
	}

	public State getSuspendedState() {
		return this.suspendedState;
	}

	public State getPartiallyBlockedState() {
		return this.partialBlockedState;
	}

	public State getFullyBlockedState() {
		return this.fullBlockedState;
	}

	public State getPartiallyAndFullyBlockedState() {
		return this.partialFullBlockedState;
	}

	public State getClosedState() {
		return this.closedState;
	}

	public State getApprovedState() {
		return this.approvedState;
	}

	public State getActiveState() {
		return this.activeState;
	}

	public State getBlockedState() {
		return this.blockedState;
	}

	public UserFsmData getUserWrapper() {
		return this.userWrapper;
	}

	public void setPreBlockingState(State preBlockingState) {
		this.preBlockingState = preBlockingState;
	}

	public State getPreBlockingState() {
		return this.preBlockingState;
	}

	public void setPreSuspensionState(State preSuspensionState) {
		this.preSuspensionState = preSuspensionState;
	}

	public State getPreSuspensionState() {
		return this.preSuspensionState;
	}

	public UserFsmEntity getEntityType() {
		return this.entityType;
	}

	public State getSelfBlockState() {
		return this.selfBlockState;
	}

	public State getSelfFullBlockState() {
		return this.selfFullBlockState;
	}

	public State getSelfPartialBlockState() {
		return this.selfPatrtialBlockState;
	}

	public State getPreRegisteredState() {
		return this.preRegState;
	}

	/**
	 * Method triggers event on the current state based on the user event
	 * 
	 * @param uEvent
	 *            : user event to be triggered
	 * @return FSM response object. If valid transition, user state is updated
	 *         and available as a part of response
	 */
	private UserFsmResp triggerEvent(UserEvent uEvent) {

		String currentState = getStringRep(getState());
		UserFsmResp response = this.trigger(uEvent);
		if (response != null) {
			response.setCurrentState(currentState);
			response.setEvent(uEvent.getEventValue());
			response.setValid(response.getValid());
			if (response.getValid() != null && response.getValid().booleanValue()) {
				response.setNextState(getStringRep(getState()));
				User userToUpdate = getUserWrapper().getUser();
				userToUpdate.setState(response.getNextState());
				response.setEntity(userToUpdate);
			}
		}
		return response;
	}

	/**
	 * Method determines the FSM entity type based on input data
	 * 
	 * @param type
	 *            : user type
	 * @param subtype
	 *            : user sub type
	 * @return UserFsmEntity for which the FSM needs to operate
	 */
	private static UserFsmEntity getFsmEntityType(String type, String subtype) {
		UserType userType = UserType.getUserType(type);
		UserSubType userSubType = UserSubType.getUserSubType(subtype);

		if (userType != null && userSubType != null) {
			switch (userType) {
			case CUSTOMER:
				return UserFsmEntity.CUSTOMER;
			case MERCHANT:
				switch (userSubType) {
				case SUPERAGENT:
				case BRANCH:
					return UserFsmEntity.MERCHANTENTITY;
				default:
					return UserFsmEntity.MERCHANTACTOR;
				}
			default:
				return null;
			}
		}
		return null;
	}

	/**
	 * Method returns string representation of the user state based on the
	 * current FSM state
	 * 
	 * @param state
	 *            : current FSM state
	 * @return String representation of the current state
	 */
	private static String getStringRep(State state) {
		if (state != null) {
			if (state instanceof RegisteredState) {
				return UserState.REGISTERED.getStateValue();
			}
			if (state instanceof RejectedState) {
				return UserState.REJECTED.getStateValue();
			}
			if (state instanceof SuspendedState) {
				return UserState.SUSPENDED.getStateValue();
			}
			if (state instanceof PartialBlockedState) {
				return UserState.PARTIALBLOCKED.getStateValue();
			}
			if (state instanceof FullBlockedState) {
				return UserState.FULLBLOCKED.getStateValue();
			}
			if (state instanceof PartialFullBlockedState) {
				return UserState.PARTIALFULLBLOCKED.getStateValue();
			}
			if (state instanceof ClosedState) {
				return UserState.CLOSED.getStateValue();
			}
			if (state instanceof ApprovedState) {
				return UserState.APPROVED.getStateValue();
			}
			if (state instanceof ActiveState) {
				return UserState.ACTIVE.getStateValue();
			}
			if (state instanceof BlockedState) {
				return UserState.BLOCKED.getStateValue();
			}
			if (state instanceof SelfReqBlockState) {
				return UserState.SELFBLOCKED.getStateValue();
			}
			if (state instanceof SelfReqFullBlockState) {
				return UserState.SELFFULLBLOCKED.getStateValue();
			}
			if (state instanceof SelfReqPartialBlockState) {
				return UserState.SELFPARTIALBLOCKED.getStateValue();
			}
			if (state instanceof PreRegisteredState) {
				return UserState.PREREGISTERED.getStateValue();
			}
		}
		return null;
	}

	/**
	 * Method triggers event on the current state of the FSM
	 * 
	 * @param uEvent
	 *            : Event to be triggered
	 * @return FSM response object
	 */
	private UserFsmResp trigger(UserEvent uEvent) {

		switch (uEvent) {
		case ACTIVATE:
			return this.activate();
		case APPROVE:
			return this.approve();
		case BLOCK:
			return this.block();
		case CLOSE:
			return this.close();
		case DELETE:
			return this.delete();
		case FULLBLOCK:
			return this.fullBlock();
		case FULLUNBLOCK:
			return this.fullUnblock();
		case PARTIALBLOCK:
			return this.partialBlock();
		case PARTIALUNBLOCK:
			return this.partialUnblock();
		case REACTIVATE:
			return this.reActivateAndApprove();
		case REJECT:
			return this.reject();
		case REOPEN:
			return this.reopen();
		case SELFBLOCK:
			return this.selfBlock();
		case SELFUNBLOCK:
			return this.selfUnblock();
		case SUSPEND:
			return this.suspend();
		case UNBLOCK:
			return this.unBlock();
		case DEFAULT:
			return this.getStates();
		}
		return null;
	}

	/**
	 * Method returns current State of the FSM based on the user state
	 * 
	 * @param userstate
	 *            : current state of the user
	 * @return State holding appropriate FSM state. Return null if invalid state
	 */
	private State getUserState(UserState userstate) {

		if (userstate != null) {
			switch (userstate) {
			case ACTIVE:
				return this.getActiveState();
			case APPROVED:
				return this.getApprovedState();
			case BLOCKED:
				return this.getBlockedState();
			case CLOSED:
				return this.getClosedState();
			case FULLBLOCKED:
				return this.getFullyBlockedState();
			case PARTIALBLOCKED:
				return this.getPartiallyBlockedState();
			case PARTIALFULLBLOCKED:
				return this.getPartiallyAndFullyBlockedState();
			case PREREGISTERED:
				return this.getPreRegisteredState();
			case REGISTERED:
				return this.getRegisteredState();
			case REJECTED:
				return this.getRejectedState();
			case SELFBLOCKED:
				return this.getSelfBlockState();
			case SELFFULLBLOCKED:
				return this.getSelfFullBlockState();
			case SELFPARTIALBLOCKED:
				return this.getSelfPartialBlockState();
			case SUSPENDED:
				return this.getSuspendedState();

			}
		}
		return null;
	}
}
