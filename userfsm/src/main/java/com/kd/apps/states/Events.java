package com.kd.apps.states;

import com.kd.apps.model.UserFsmResp;
/**
 * 
 * @author Myron Rogtao
 *
 */
public interface Events {

	public UserFsmResp reject();

	public UserFsmResp reopen();

	public UserFsmResp approve();

	public UserFsmResp reActivate();

	public UserFsmResp block();

	public UserFsmResp unBlock();

	public UserFsmResp partialBlock();

	public UserFsmResp partialUnblock();

	public UserFsmResp fullBlock();

	public UserFsmResp fullUnblock();

	public UserFsmResp selfReqBlock();

	public UserFsmResp selfReqUnblock();

	public UserFsmResp delete();

	public UserFsmResp activate();

	public UserFsmResp suspend();

	public UserFsmResp close();

	public UserFsmResp getStates();
}
