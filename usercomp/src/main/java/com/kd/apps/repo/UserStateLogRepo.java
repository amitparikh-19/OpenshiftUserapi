package com.kd.apps.repo;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kd.apps.model.UserStateLog;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public interface UserStateLogRepo extends PagingAndSortingRepository<UserStateLog, Long> {

	UserStateLog findTopByStateInAndUsernameOrderByOpdatetimeDesc(Collection<String> states, String username);
}
