package com.kd.apps.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kd.apps.model.Permission;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public interface PermissionRepo extends PagingAndSortingRepository<Permission, Long> {

}
