package com.kd.apps;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kd.apps.dto.UserBal;

/**
 * 
 * @author Amit Parikh
 *
 */
@RestController
@RequestMapping("/data")
public class DemoInitCtrlr {

	@Autowired
	DbSeedDataSrvc dbSeedDataSrvc;

	@RequestMapping(value = "/cleanup", method = RequestMethod.GET)
	public boolean cleanup() {
		try {
			dbSeedDataSrvc.truncate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/inittopup", method = RequestMethod.GET)
	public List<UserBal> inittopup() {
		try {
			return dbSeedDataSrvc.inittopup();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/initdm", method = RequestMethod.GET)
	public List<UserBal> initdm() {
		try {
			return dbSeedDataSrvc.initdm();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public List<UserBal> balance() {
		try {
			return dbSeedDataSrvc.balance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}