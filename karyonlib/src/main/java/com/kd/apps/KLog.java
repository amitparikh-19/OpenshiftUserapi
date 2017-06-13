package com.kd.apps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Delegate logger intended to implement async logging at a later stage
 * 
 * @author Pradyumna Roy
 *
 */
public class KLog implements Log {

	private Log logger;

	public KLog(Class clazz) {
		super();
		logger = LogFactory.getLog(clazz);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public boolean isFatalEnabled() {
		return logger.isFatalEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public void trace(Object message) {
		logger.trace(message);
	}

	@Override
	public void trace(Object message, Throwable t) {
		logger.trace(message, t);
	}

	@Override
	public void debug(Object message) {
		logger.debug(message);
	}

	@Override
	public void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	@Override
	public void info(Object message) {
		logger.info(message);
	}

	@Override
	public void info(Object message, Throwable t) {
		logger.info(message, t);
	}

	@Override
	public void warn(Object message) {
		logger.warn(message);
	}

	@Override
	public void warn(Object message, Throwable t) {
		logger.warn(message, t);
	}

	@Override
	public void error(Object message) {
		logger.error(message);
	}

	@Override
	public void error(Object message, Throwable t) {
		logger.error(message, t);
	}

	@Override
	public void fatal(Object message) {
		logger.fatal(message);
	}

	@Override
	public void fatal(Object message, Throwable t) {
		logger.fatal(message, t);
	}

}
