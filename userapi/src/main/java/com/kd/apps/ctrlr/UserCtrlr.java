package com.kd.apps.ctrlr;

import java.security.Principal;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kd.apps.exception.BizException;
import com.kd.apps.exception.ServerException;
import com.kd.apps.model.RespDeletedEntity;
import com.kd.apps.model.User;
import com.kd.apps.model.UserFsmResp;
import com.kd.apps.spec.SpecificationsBuilder;
import com.kd.apps.srvc.UserSrvc;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@RestController
@RequestMapping("/users")
public class UserCtrlr extends BaseAbstractCtrlr<User, String> {

	private UserSrvc userSrvc;

	@Autowired
	public UserCtrlr(UserSrvc userSrvc) {
		super(userSrvc, "users");
		this.userSrvc = userSrvc;
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> create(@RequestBody User inputentity, Principal principal) throws ServerException {
		return userSrvc.createUser(inputentity, principal);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<User> read(@PathVariable(value = "id") String id, Principal principal)
			throws ServerException {
		return readEntity(id, principal);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> readAll(Principal principal) throws ServerException {
		return readAllEntities(principal);
	}

	@Override
	@RequestMapping(value = "/pageable", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> readAllPageable(@PathParam("firstresult") Integer firstresult,
			@PathParam("maxresult") Integer maxresult, @PathParam("sortdir") String sortdir,
			@PathParam("sortfield") String sortfield, Principal principal) throws ServerException {
		return readAllEntitiesPageable(firstresult, maxresult, sortdir, sortfield, principal);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> update(@PathVariable(value = "id") String id, @RequestBody User tobemerged,
			Principal principal) throws ServerException {
		return updateEntity(id, tobemerged, principal);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<RespDeletedEntity> delete(@PathVariable(value = "id") String id, Principal principal)
			throws ServerException {
		return userSrvc.deleteEntity(id, principal);
	}

	/**
	 * Rest Handler to verify user state change.
	 * @param id
	 * 			: ID of the user
	 * @param event
	 * 			  : Life cycle event to be triggered
	 * @param principal
	 * 				  : Security Object
	 * @return UserFsmResp holding state machine transition response
	 * @throws BizException
	 */
	@RequestMapping(value = "/{id}/state/{event}", method = RequestMethod.GET)
	public ResponseEntity<UserFsmResp> changeStatePost(@NotNull @PathVariable(required = true) String id,
			@NotNull @PathVariable(required = true) String event, Principal principal) throws BizException {
		return userSrvc.getStateInfo(id, event);
	}

	@RequestMapping(value = "/userlist/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getuserListByType(@PathVariable(value = "type") String type, Principal principal)
			throws BizException {
		return userSrvc.getUsersBasedOnType(type);
	}

	@RequestMapping(value = "/usertypes/chargeable", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getuserTypes(Principal principal) throws BizException {
		boolean chargeable = true;
		return userSrvc.getUserTypes(chargeable);
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public ResponseEntity<List<User>> search(@RequestParam("params") String params) {
		SpecificationsBuilder<User> builder = new SpecificationsBuilder<User>();
		Specification<User> spec = builder.buildSpecifications(params);
		return userSrvc.search(spec);
	}

	@RequestMapping(value = "/pagesearch", method = RequestMethod.GET)
	public ResponseEntity<Iterable<User>> readAllPageable(@PathParam("firstresult") Integer firstresult,
			@PathParam("maxresult") Integer maxresult, @PathParam("sortdir") String sortdir,
			@PathParam("sortfield") String sortfield, @RequestParam(value = "params", required = false) String params,
			Principal principal) throws ServerException {
		SpecificationsBuilder<User> builder = new SpecificationsBuilder<User>();
		Specification<User> spec = builder.buildSpecifications(params);
		return readAllEntitiesPageSearchable(firstresult, maxresult, sortdir, sortfield, spec, principal);
	}
}