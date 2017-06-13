package com.kd.apps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * @author Pradyumna Roy
 *
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class UserApiSecConf extends WebSecurityConfigurerAdapter {

	@Autowired
	Utils utils;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenAuthService tokenAuthService;

	public UserApiSecConf() {
		super(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling().and().anonymous().and().servletApi().and().headers().and().authorizeRequests()

				// allow anonymous resource requests
				.antMatchers("/").permitAll().antMatchers("/index.html").permitAll().antMatchers("/favicon.ico")
				.permitAll().antMatchers("/resources/**").permitAll()

				// allow anonymous GETs to API
				.antMatchers(HttpMethod.GET, "/").permitAll()

				// allow anonymous POSTs to login
				.antMatchers(HttpMethod.POST, "/auth").permitAll()

				// allow anonymous PUT for users
				.antMatchers(HttpMethod.PUT, "/users/**").permitAll()

				// allow anonymous GETs to API
				.antMatchers(HttpMethod.GET, "/users/**").permitAll()

				// allow anonymous DELETE's to API
				.antMatchers(HttpMethod.DELETE, "/users/**").permitAll()

				// allow anonymous GETs to API
				.antMatchers(HttpMethod.GET, "/test/**").permitAll()

				// allow anonymous POSTs to login
				.antMatchers(HttpMethod.POST, "/users/**").permitAll()

				// all other request need to be authenticated
				.anyRequest().hasAnyRole("ALL_PERMISSION").and()

				// custom JSON based authentication by POST of
				// {
				// "username":"<name>",
				// "password":"<password>" ,
				// "authchannel":"<webapp | api | mobileapp | sms | ussd |
				// webpos>"
				// }
				// which sets the token header upon authentication
				.addFilterBefore(new JwtAuthFilter("/auth", tokenAuthService, userDetailsService,
						authenticationManager(), utils), UsernamePasswordAuthenticationFilter.class)

				// custom Token based authentication based on the header
				// previously given to the client
				.addFilterBefore(new JwtTokenFilter(tokenAuthService, utils),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}
}
