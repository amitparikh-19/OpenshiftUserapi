package com.kd.apps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.kd.apps.model.User;

/**
 * 
 * @author Pradyumna  Roy
 *
 */
@Service
public class TokenAuthService {

	protected final KLog logger = new KLog(this.getClass());

	private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	private static final long USER_TOKEN_EXPIRY = 1000 * 60 * 60 * 12;// 12hrs

	private final TokenHandler tokenHandler;

	@Autowired
	public TokenAuthService(@Value("${token.secret}") String secret) {
		tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication, Utils utils) {
		final User user = authentication.getDetails();
		user.setExpires(System.currentTimeMillis() + USER_TOKEN_EXPIRY);
		response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user, utils));
		logger.info(utils.logid(user.getUsername()) + utils.logmethod("addAuthentication")
				+ utils.getjson("AUTH_HEADER_NAME", response.getHeader(AUTH_HEADER_NAME)));
	}

	public String getPasswordChangeToken(User user, Utils utils) {
		return tokenHandler.createTokenForSetPassword(user, utils);
	}

	public Authentication getAuthentication(HttpServletRequest request, Utils utils) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		String tokenStringForLogging = "null";
		if (null != token)
			tokenStringForLogging = token;
		logger.debug(utils.logid(null) + utils.logmethod("getAuthentication")
				+ utils.getjson("retrieved-token", tokenStringForLogging));
		if (token != null) {
			final User user = tokenHandler.parseUserFromToken(token, utils);
			if (user != null) {
				logger.debug(utils.logid(user.getUsername()) + utils.logmethod("getAuthentication")
						+ utils.getjson("retrieved-user-from-token", user.getUsername()));
				return new UserAuthentication(user);
			}
		}
		return null;
	}
}
