package com.kd.apps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kd.apps.dto.UserBal;
import com.kd.apps.enums.UserState;
import com.kd.apps.enums.UserType;
import com.kd.apps.exception.ServerException;
import com.kd.apps.model.Charge;
import com.kd.apps.model.RespMonoEntity;
import com.kd.apps.model.User;
import com.kd.apps.srvc.UserSrvc;
/**
 * 
 * @author Amit Parikh
 *
 */
@Service
public class DbSeedDataSrvc {

	@Autowired
	UserSrvc usersrvc;

	private EntityManager entityManager;

	@Autowired
	public DbSeedDataSrvc(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public void truncate() throws Exception {
		List<String> tableNames = new ArrayList<>();
		tableNames.add("User");
		tableNames.add("Wallet");
		tableNames.add("WalTxn");
		tableNames.add("WalTxnLog");
		tableNames.add("Charge");
		tableNames.forEach(tableName -> entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate());
		recreateuser(Const.DEFAULT_SUPERADMIN, Const.DEFAULT_SUPERADMIN, UserType.SUPERADMIN.getType(), "sa@ot.com", 0,
				"9876543210", UserState.ACTIVE.getStateValue(), null);
	}

	@Transactional
	public List<UserBal> inittopup() throws Exception {
		// cleanup the db first
		truncate();
		// create Merchant for digital topup

		User merch1 = null;
		List<User> userList = null;
		List<Charge> chargeList = null;
		if ((userList = usersrvc.findByName("merch1")).size() > 0) {
			merch1 = userList.get(0);
		} else {
			merch1 = recreateuser(null, "merch1", UserType.MERCHANT.getType(), "merch1@ot.com", 1, "9876543211",
					UserState.ACTIVE.getStateValue(), null);
		}
		if (usersrvc.findByName("merch2").size() == 0) {
			recreateuser(null, "merch2", UserType.MERCHANT.getType(), "merch2@ot.com", 2, "9876543212",
					UserState.ACTIVE.getStateValue(), merch1.getUsername());
		}
		if (usersrvc.findByName("Vodafone").size() == 0) {
			recreateuser("vodafone", "Vodafone", UserType.WSP.getType(), "wsp@ot.com", 0, "0000000000",
					UserState.ACTIVE.getStateValue(), null);
		}
		return balance();
	}

	@Transactional
	public List<UserBal> initdm() throws Exception {
		// cleanup the db and init topup() first
		inittopup();
		// create Customer for digital money
		if (usersrvc.findByName("cust1").size() == 0) {
			recreateuser(null, "cust1", UserType.CUSTOMER.getType(), "cust1@ot.com", 0, "9876543213",
					UserState.ACTIVE.getStateValue(), null);
		}
		if (usersrvc.findByName("cust2").size() == 0) {
			recreateuser(null, "cust2", UserType.CUSTOMER.getType(), "cust2@ot.com", 0, "9876543214",
					UserState.ACTIVE.getStateValue(), null);
		}
		return balance();
	}

	public List<UserBal> balance() throws Exception {
		List<UserBal> userBalList = new ArrayList<UserBal>();

		HashMap<String, User> userHm = new HashMap<String, User>();
		Iterator<User> userIterator = usersrvc.readAll("demo").getEntities().iterator();
		User user;
		while (userIterator.hasNext()) {
			user = userIterator.next();
			userHm.put(user.getUsername(), user);
		}
		return userBalList;
	}

	// Method will be used to create user based on name ,type and email
	public User recreateuser(String username, String name, String type, String email, int level, String mobile,
			String state, String parent) throws ServerException {
		User user = new User();
		user.setUsername(username == null ? usersrvc.generateNewUserId() : username);
		user.setPassword(new BCryptPasswordEncoder().encode(Const.DEFAULT_PASSWORD));
		user.setName(name);
		user.setType(type);
		user.setEmail(email);
		user.setLevel(level);
		user.setMobile(mobile);
		user.setState(state);

		if (user.getUsername().equals(Const.DEFAULT_SUPERADMIN))
			user.setJobtitle(Const.DEFAULT_SUPERADMIN);
		else
			user.setJobtitle("User");

		if (null != parent) {
			user.setParent(parent);
		}
		RespMonoEntity<User> respMonoEntity = usersrvc.create(Const.DEFAULT_SUPERADMIN, user);

		return respMonoEntity.getEntity();
	}
}
