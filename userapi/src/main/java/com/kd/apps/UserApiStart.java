package com.kd.apps;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kd.apps.enums.UserState;
import com.kd.apps.enums.UserType;
import com.kd.apps.model.Permission;
import com.kd.apps.model.Role;
import com.kd.apps.model.RolePermission;
import com.kd.apps.model.User;
import com.kd.apps.model.UserRole;
import com.kd.apps.repo.PermissionRepo;
import com.kd.apps.repo.RolePermissionRepo;
import com.kd.apps.repo.RoleRepo;
import com.kd.apps.repo.UserRepo;
import com.kd.apps.repo.UserRoleRepo;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class UserApiStart extends SpringBootServletInitializer {

	protected final KLog logger = new KLog(this.getClass());

	@Bean
	CommandLineRunner init(Utils utils, UserRepo userRepo, RoleRepo roleRepo, UserRoleRepo userRoleRepo,
			PermissionRepo permissionRepo, RolePermissionRepo rolePermissionRepo) {
		if (null == userRepo.findOne(Const.DEFAULT_SUPERADMIN)) {
			logger.info(utils.logid(null) + utils.logmethod("init")
					+ utils.getjson("creation-of-user", Const.DEFAULT_SUPERADMIN));
			return (evt) -> {
				Role role = new Role();
				role.setName("superadmin");
				role.setCreated(new Date());
				Permission permission = new Permission();
				permission.setName("ROLE_ALL_PERMISSION");
				permission.setCreated(new Date());
				roleRepo.save(role);
				permissionRepo.save(permission);

				RolePermission rolePermission = new RolePermission();
				rolePermission.setRole(role.getName());
				rolePermission.setPermission(permission.getName());
				rolePermission.setCreated(new Date());
				rolePermissionRepo.save(rolePermission);

				User user = new User();
				user.setUsername(Const.DEFAULT_SUPERADMIN);

				UserRole userRole = new UserRole();
				userRole.setUsername(user.getUsername());
				userRole.setRole(role.getName());
				userRole.setCreated(new Date());
				userRoleRepo.save(userRole);

				user.setPassword(new BCryptPasswordEncoder().encode(Const.DEFAULT_PASSWORD));
				user.setName(Const.DEFAULT_SUPERADMIN);
				user.setType(UserType.SUPERADMIN.getType());
				user.setEmail("sa@ot.com");
				user.setMobile("9876543210");
				user.setState(UserState.ACTIVE.getStateValue());
				user.setLevel(0);
				user.setJobtitle(Const.DEFAULT_SUPERADMIN);
				user.setCreated(new Date());
				userRepo.save(user);
			};
		} else {
			logger.info(utils.logid(null) + utils.logmethod("init")
					+ utils.getjson("user-already-exist", Const.DEFAULT_SUPERADMIN));
		}
		return null;
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("initializing the api...");
		return application.sources(UserApiStart.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UserApiStart.class, args);
	}
}