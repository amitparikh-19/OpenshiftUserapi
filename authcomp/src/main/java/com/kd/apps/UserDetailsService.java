package com.kd.apps;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kd.apps.dto.UserAuthority;
import com.kd.apps.model.RolePermission;
import com.kd.apps.model.User;
import com.kd.apps.model.UserRole;
import com.kd.apps.repo.RolePermissionRepo;
import com.kd.apps.repo.UserRepo;
import com.kd.apps.repo.UserRoleRepo;

/**
 * 
 * @author Pradyumna  Roy
 *
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserRoleRepo userRoleRepo;

	@Autowired
	RolePermissionRepo rolePermissionRepo;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public final User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsernameOrEmailOrMobile(username, username, username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		user.setAuthorities(resolveRoles(user.getUsername()));
		detailsChecker.check(user);
		return user;
	}

	public Set<UserAuthority> resolveRoles(String username) {
		List<UserRole> userRoles = userRoleRepo.findByUsername(username);
		UserAuthority userAuthority;
		Set<UserAuthority> authorities = new HashSet<UserAuthority>();
		for (UserRole userRole : userRoles) {
			List<RolePermission> rolePermissions = rolePermissionRepo.findByRole(userRole.getRole());
			for (RolePermission rolePermission : rolePermissions) {
				userAuthority = new UserAuthority();
				userAuthority.setAuthority(rolePermission.getPermission());
				authorities.add(userAuthority);
			}
		}
		return authorities;
	}
}
