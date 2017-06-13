package com.kd.apps.srvc;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kd.apps.Const;
import com.kd.apps.UserFsmTrigger;
import com.kd.apps.UserRespKey;
import com.kd.apps.Utils;
import com.kd.apps.enums.UserEvent;
import com.kd.apps.enums.UserState;
import com.kd.apps.enums.UserSubType;
import com.kd.apps.enums.UserType;
import com.kd.apps.exception.BizException;
import com.kd.apps.exception.ServerException;
import com.kd.apps.model.RespDeletedEntity;
import com.kd.apps.model.RespMonoEntity;
import com.kd.apps.model.User;
import com.kd.apps.model.UserFsmData;
import com.kd.apps.model.UserFsmResp;
import com.kd.apps.model.UserStateLog;
import com.kd.apps.repo.UserRepo;
import com.kd.apps.repo.UserStateLogRepo;

/**
 * 
 * @author Pradyumna Roy
 * 
 */
@Service
public class UserSrvc extends BaseSrvc<User, String> {

	private UserRepo userRepo;

	@Autowired
	UserFsmTrigger userFsm;

	@Autowired
	UserStateLogRepo userStateRepo;

	@Autowired
	Utils util;

	@Autowired
	public UserSrvc(UserRepo userRepo) {
		super(userRepo, "User");
		this.userRepo = userRepo;
	}

	// User Id Generation :
	public String generateNewUserId() {
		// random 10-digit number
		boolean existing = false;
		String id = null;
		do {
			long number = ThreadLocalRandom.current().nextLong(1000000000L, 9999999999L);
			id = Long.toString(number);
			existing = userRepo.exists(id);
		} while (existing);
		// no existing user with this id
		return id;
	}

	/**
	 * Method determines state transition info based on the current user state
	 * and triggered event.
	 * 
	 * @param userId
	 *            : User Id Information
	 * @param event
	 *            : Event to be triggered
	 * @return UserFsmResp object as response as a part of ResponseEntity
	 * @throws BizException
	 *             when invalid Event or user details are supplied
	 */
	public ResponseEntity<UserFsmResp> getStateInfo(String userId, String event) throws BizException {

		User user = userRepo.findOne(userId);
		if (user != null) {
			UserFsmData userForFSM = prepareUserData(user);
			UserEvent uEvent = UserEvent.getEvent(event);
			if (uEvent != null) {
				if (userForFSM.getCurrentState() != null) {
					UserFsmResp response = userFsm.initAndTrigger(userForFSM, uEvent);

					// Temporarily persist state change info
					/*
					 * if(response.getValid()!=null &&
					 * response.getValid().booleanValue()){ User updatedUser =
					 * response.getEntity(); userRepo.save(updatedUser);
					 * 
					 * UserStateLog stateLog = new UserStateLog();
					 * stateLog.setOpdatetime(new Date());
					 * stateLog.setUsername(updatedUser.getUsername());
					 * stateLog.setState(updatedUser.getState());
					 * userStateRepo.save(stateLog); }
					 */
					return new ResponseEntity<UserFsmResp>(response, HttpStatus.OK);
				}
				throw new BizException(UserRespKey.INVALID_STATE.getKey(), UserRespKey.INVALID_STATE.getMsg());
			}
			throw new BizException(UserRespKey.INVALID_EVENT.getKey(), UserRespKey.INVALID_EVENT.getMsg());
		}
		throw new BizException(UserRespKey.USER_NOT_FOUND.getKey(), UserRespKey.USER_NOT_FOUND.getMsg());
	}

	/**
	 * Method prepares User Fsm data from the current object
	 * 
	 * @param user
	 *            : User object
	 * @return UserFsmData as input to the FSM
	 */
	private UserFsmData prepareUserData(User user) {
		UserFsmData userForFSM = new UserFsmData(user);
		userForFSM.setCurrentState(UserState.getState(user.getState()));

		UserStateLog preBlocking = userStateRepo
				.findTopByStateInAndUsernameOrderByOpdatetimeDesc(UserState.getPreBlockingStates(), user.getUsername());
		UserStateLog preSuspend = userStateRepo.findTopByStateInAndUsernameOrderByOpdatetimeDesc(
				UserState.getPreSuspensionStates(), user.getUsername());

		if (preBlocking != null) {
			userForFSM.setPreBlockinState(UserState.getState(preBlocking.getState()));
		}
		if (preSuspend != null) {
			userForFSM.setPreSuspensionState(UserState.getState(preSuspend.getState()));
		}
		return userForFSM;
	}

	/**
	 * Method creates user based on user information. If user is of type
	 * Merchant or Customer, then a wallet is also created for the user.
	 * 
	 * @param inputentity
	 *            : User information
	 * @param principal
	 *            : Security principal
	 * @return Created user entity as ResponseEntity
	 * @throws ServerException
	 */
	@Transactional
	public ResponseEntity<User> createUser(User inputentity, Principal principal) throws ServerException {

		// Validate existence of Mandate Fields
		if (validateUserInfo(inputentity)) {
			inputentity.setUsername(generateNewUserId());
			inputentity.setPassword(new BCryptPasswordEncoder().encode(inputentity.getPassword()));

			// Create User
			RespMonoEntity<User> respMonoEntity = create(
					principal != null ? principal.getName() : Const.DEFAULT_SUPERADMIN, inputentity);
			HttpHeaders headers = util.getRespHeader(respMonoEntity.getRespmsgkey(), respMonoEntity.getRespmsgval());
			return new ResponseEntity<User>(respMonoEntity.getEntity(), headers, respMonoEntity.getHttpstatus());
		}
		throw new BizException(UserRespKey.INVALID_DATA.getKey(), UserRespKey.INVALID_DATA.getMsg());
	}

	/**
	 * Method to validate incoming user data during user creation.
	 * 
	 * @param user
	 *            : Input user data.
	 * @return true if validations results are successful else false
	 */
	private boolean validateUserInfo(User user) {

		// Validate Mandate Fields.
		// TODO validate sub-type when hierarchy is included
		if (user == null || util.isNullOrEmpty(user.getPassword()) || util.isNullOrEmpty(user.getType())) {
			return false;
		}

		// Validate user type
		// TODO validate sub-type when hierarchy is included
		if (UserType.getUserType(user.getType()) == null
				|| (!util.isNullOrEmpty(user.getSubtype()) && UserSubType.getUserSubType(user.getSubtype()) == null)) {
			return false;
		}
		return true;
	}

	/**
	 * Method retrieves User information based on he Username
	 * 
	 * @param username
	 *            : Id of the User
	 * @return User object
	 */
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	/**
	 * Method retrieves User information based on he Name
	 * 
	 * @param name
	 *            : name of the User
	 * @return List of Users based on name
	 */
	public List<User> findByName(String name) {
		return userRepo.findByName(name);
	}

	public User getWspUser() {
		String wspType = UserType.WSP.getType();
		return userRepo.findTopByType(wspType);
	}

	/**
	 * Method retrieves Users based on type specified
	 * 
	 * @return List of Users based on type
	 */
	public ResponseEntity<List<User>> getUsersBasedOnType(String type) {
		if (type != null) {
			String[] types = type.split(",");
			Collection<String> typeCollection = new ArrayList<>(Arrays.asList(types));
			List<User> userList = userRepo.findByTypeIn(typeCollection);
			HttpHeaders headers = util.getRespHeader(UserRespKey.USER_LIST_SUCCESS.getKey(),
					UserRespKey.USER_LIST_SUCCESS.getMsg());
			return new ResponseEntity<List<User>>(userList, headers, HttpStatus.OK);
		}
		HttpHeaders headers = util.getRespHeader(UserRespKey.USER_LIST_SUCCESS.getKey(),
				UserRespKey.USER_LIST_SUCCESS.getMsg());
		return new ResponseEntity<List<User>>(null, headers, HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<User>> search(Specification<User> spec) {
		List<User> userList = userRepo.findAll(spec);
		HttpHeaders headers = util.getRespHeader(UserRespKey.USER_LIST_SUCCESS.getKey(),
				UserRespKey.USER_LIST_SUCCESS.getMsg());
		return new ResponseEntity<List<User>>(userList, headers, HttpStatus.OK);
	}

	public ResponseEntity<List<String>> getUserTypes(boolean chargeable) {
		List<String> userTypeList = UserType.getTypeList(chargeable);
		HttpHeaders headers = util.getRespHeader(UserRespKey.TYPE_LIST_SUCCESS.getKey(),
				UserRespKey.TYPE_LIST_SUCCESS.getMsg());
		return new ResponseEntity<List<String>>(userTypeList, headers, HttpStatus.OK);
	}

	@Transactional(rollbackFor={ServerException.class})
	public ResponseEntity<RespDeletedEntity> deleteEntity(String username,Principal principal) throws ServerException {
		
		RespDeletedEntity respDeletedEntity = delete(principal==null?Const.DEFAULT_SUPERADMIN:principal.getName(), username);

		HttpHeaders headers = util.getRespHeader(respDeletedEntity.getRespmsgkey(),respDeletedEntity.getRespmsgval());
		ResponseEntity<RespDeletedEntity> responseEntity = new ResponseEntity<RespDeletedEntity>(respDeletedEntity, headers,
				respDeletedEntity.getHttpstatus());
		return responseEntity;
	}
}
