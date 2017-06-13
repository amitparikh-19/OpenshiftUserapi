package com.kd.apps.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kd.apps.model.RolePermission;

/**
 * 
 * @author Pradyumna Roy
 *
 */
public interface RolePermissionRepo extends PagingAndSortingRepository<RolePermission, Long> {
	List<RolePermission> findByRole(String role);
}
