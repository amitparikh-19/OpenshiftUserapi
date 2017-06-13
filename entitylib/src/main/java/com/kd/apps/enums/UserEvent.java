package com.kd.apps.enums;

/**
 * @author Myron  Rogtao
 * Constants representing the Events triggered to make transition in User FSM
 * representing User Life Cycle
 *
 */
public enum UserEvent {

	REJECT("reject"), REOPEN("reopen"), APPROVE("approve"), ACTIVATE("activate"), REACTIVATE("reactivate"), BLOCK(
			"block"), UNBLOCK("unblock"), PARTIALBLOCK("partialblock"), PARTIALUNBLOCK("partialunblock"), FULLBLOCK(
					"fullblock"), FULLUNBLOCK("fullunblock"), SELFBLOCK("selfblock"), SELFUNBLOCK(
							"selfunblock"), DELETE("delete"), SUSPEND("suspend"), CLOSE("close"), DEFAULT("getstates");

	private String event;

	UserEvent(String event) {
		this.event = event;
	}

	public String getEventValue() {
		return event;
	}

	public static UserEvent getEvent(String event) {
		UserEvent[] events = UserEvent.values();
		for (UserEvent uEvent : events) {
			if (uEvent.getEventValue().equals(event)) {
				return uEvent;
			}
		}
		return null;
	}
}
