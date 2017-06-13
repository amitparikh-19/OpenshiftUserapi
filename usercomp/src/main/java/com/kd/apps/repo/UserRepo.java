package com.kd.apps.repo;

import java.util.Collection;
import java.util.List;

import com.kd.apps.model.User;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public interface UserRepo extends BaseRepo<User, String> {

	User findByUsernameOrEmailOrMobile(String username, String email, String mobile);

	User findTopByType(String type);

	User findByUsername(String username);

	List<User> findByName(String name);

	List<User> findByTypeIn(Collection<String> type);
}
