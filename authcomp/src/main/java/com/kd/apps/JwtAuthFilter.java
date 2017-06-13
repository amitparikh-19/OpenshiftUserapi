package com.kd.apps;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kd.apps.model.User;

/**
 * 
 * @author Pradyumna  Roy
 *
 */
class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {

	protected final KLog logger = new KLog(this.getClass());
	private final TokenAuthService tokenAuthService;
	private final UserDetailsService userDetailsService;
	private String loginAttemptingUser = "null";

	private Utils utils;

	protected JwtAuthFilter(String urlMapping, TokenAuthService tokenAuthService, UserDetailsService userDetailsService,
			AuthenticationManager authManager, Utils utils) {
		super(new AntPathRequestMatcher(urlMapping));
		this.userDetailsService = userDetailsService;
		this.tokenAuthService = tokenAuthService;
		setAuthenticationManager(authManager);
		this.utils = utils;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		loginAttemptingUser = user.getUsername();
		logger.info(utils.logid(null) + utils.logmethod("attemptAuthentication")
				+ utils.getjson("username", loginAttemptingUser));
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword());
		return getAuthenticationManager().authenticate(loginToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		// Lookup the complete User object from the database and create an
		// Authentication for it
		final User authenticatedUser = userDetailsService.loadUserByUsername(authentication.getName());
		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

		// Add the custom token as HTTP header to the response
		tokenAuthService.addAuthentication(response, userAuthentication, utils);

		// Add the authentication to the Security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);
		logger.info(utils.logid(authenticatedUser.getUsername()) + utils.logmethod("successfulAuthentication"));
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		logger.error(utils.logid(null) + utils.logmethod("unsuccessfulAuthentication")
				+ utils.getjson("username", loginAttemptingUser));
		super.unsuccessfulAuthentication(request, response, failed);
	}

}