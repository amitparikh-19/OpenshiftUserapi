package com.kd.apps;

import com.kd.apps.enums.UserEvent;
import com.kd.apps.model.UserFsmData;
import com.kd.apps.model.UserFsmResp;

/**
 * 
 * @author Myron Rogtao
 *
 */
public interface UserFsmTrigger {

	UserFsmResp initAndTrigger(UserFsmData data, UserEvent event);
}
