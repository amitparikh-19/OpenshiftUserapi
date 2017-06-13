package com.kd.apps.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kd.apps.model.Role;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public interface RoleRepo extends PagingAndSortingRepository<Role, Long> {

}
