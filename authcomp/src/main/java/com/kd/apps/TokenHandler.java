package com.kd.apps;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kd.apps.model.User;

/**
 * 
 * @author Pradyumna  Roy
 *
 */
public final class TokenHandler {

	protected final KLog logger = new KLog(this.getClass());

	private static final String HMAC_ALGO = "HmacSHA256";
	private static final String SEPARATOR = ".";
	private static final String SEPARATOR_SPLITTER = "\\.";
	private static final long SET_PASSWORD_TOKEN_EXPIRY = 1000 * 60 * 30;// 30minutes

	private final Mac hmac;

	public TokenHandler(byte[] secretKey) {
		try {
			hmac = Mac.getInstance(HMAC_ALGO);
			hmac.init(new SecretKeySpec(secretKey, HMAC_ALGO));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No Such Algorithm -> failed to initialize HMAC: " + e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new IllegalStateException("Invalid Key -> failed to initialize HMAC: " + e.getMessage(), e);
		}
	}

	public User parseUserFromToken(String token, Utils utils) {
		final String[] parts = token.split(SEPARATOR_SPLITTER);
		if (parts.length == 2 && parts[0].length() > 0 && parts[1].length() > 0) {
			try {
				final byte[] userBytes = fromBase64(parts[0]);
				final byte[] hash = fromBase64(parts[1]);

				boolean validHash = Arrays.equals(createHmac(userBytes), hash);
				if (validHash) {
					final User user = fromJSON(userBytes);
					if (new Date().getTime() < user.getExpires()) {
						return user;
					}
				}
			} catch (IllegalArgumentException e) {
				logger.error(utils.logid(null) + utils.logmethod("parseUserFromToken")
						+ utils.getjson("tampered-token", token));
			}
		}
		return null;
	}

	public String createTokenForUser(User user, Utils utils) {
		logger.debug(utils.logid(user.getUsername()) + utils.logmethod("createTokenForUser")
				+ utils.getjson("token-creation-for-user", user.getUsername()));
		byte[] userBytes = toJSON(user);
		byte[] hash = createHmac(userBytes);
		final StringBuilder sb = new StringBuilder(170);
		sb.append(toBase64(userBytes));
		sb.append(SEPARATOR);
		sb.append(toBase64(hash));
		return sb.toString();
	}

	public String createTokenForSetPassword(User user, Utils utils) {
		logger.debug(utils.logid(user.getUsername()) + utils.logmethod("createTokenForSetPassword")
				+ utils.getjson("set-password-token-creation-for-user", user.getUsername()));
		long HALF_HOUR = SET_PASSWORD_TOKEN_EXPIRY;
		user.setExpires(System.currentTimeMillis() + HALF_HOUR);
		byte[] userBytes = toJSON(user);
		byte[] hash = createHmac(userBytes);
		final StringBuilder sb = new StringBuilder(170);
		sb.append(toBase64(userBytes));
		sb.append(SEPARATOR);
		sb.append(toBase64(hash));
		return sb.toString();
	}

	private User fromJSON(final byte[] userBytes) {
		try {
			return new ObjectMapper().readValue(new ByteArrayInputStream(userBytes), User.class);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private byte[] toJSON(User user) {
		try {
			return new ObjectMapper().writeValueAsBytes(user);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}

	private String toBase64(byte[] content) {
		return DatatypeConverter.printBase64Binary(content);
	}

	private byte[] fromBase64(String content) {
		return DatatypeConverter.parseBase64Binary(content);
	}

	// synchronized to guard internal hmac object
	private synchronized byte[] createHmac(byte[] content) {
		return hmac.doFinal(content);
	}
}
