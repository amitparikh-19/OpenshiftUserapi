package com.kd.apps.srvc;

import java.util.List;

import org.apache.commons.logging.Log;

import com.kd.apps.KLog;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public abstract class StateTransition<E> {

	protected final KLog logger = new KLog(this.getClass());

	private E previousState;

	private E currentState;

	private E requestedState;

	private List<String> possibleStates;

	public StateTransition() {

	}

	/**
	 * Use this constructor for finding all possible states for transition
	 * 
	 * @param currentState
	 */
	public StateTransition(E currentState) {
		super();
		this.currentState = currentState;
	}

	/**
	 * Use this constructor for finding all possible states for transition which
	 * are dependent on the state prior to the current state i.e. previous state
	 * 
	 * @param previousState
	 * @param currentState
	 */
	public StateTransition(E previousState, E currentState) {
		super();
		this.previousState = previousState;
		this.currentState = currentState;
	}

	/**
	 * Use this constructor to validate requested state transition previous
	 * state can be null if not required
	 * 
	 * @param previousState
	 * @param currentState
	 * @param requestedState
	 */
	public StateTransition(E previousState, E currentState, E requestedState) {
		super();
		this.previousState = previousState;
		this.currentState = currentState;
		this.requestedState = requestedState;
	}

	public Log getLogger() {
		return logger;
	}

	public E getPreviousState() {
		return previousState;
	}

	public void setPreviousState(E previousState) {
		this.previousState = previousState;
	}

	public E getCurrentState() {
		return currentState;
	}

	public void setCurrentState(E currentState) {
		this.currentState = currentState;
	}

	public E getRequestedState() {
		return requestedState;
	}

	public void setRequestedState(E requestedState) {
		this.requestedState = requestedState;
	}

	public List<String> getPossibleStates() {
		return possibleStates;
	}

	public void setPossibleStates(List<String> possibleStates) {
		this.possibleStates = possibleStates;
	}

	public abstract List<String> listPossibleStatesForTransition();

	public boolean isRequestedStateValid() {
		return this.listPossibleStatesForTransition().contains(this.requestedState);
	}
}
