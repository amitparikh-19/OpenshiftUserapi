package com.kd.apps.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kd.apps.model.UserRole;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public interface UserRoleRepo extends PagingAndSortingRepository<UserRole, Long> {

	List<UserRole> findByUsername(String username);

}
