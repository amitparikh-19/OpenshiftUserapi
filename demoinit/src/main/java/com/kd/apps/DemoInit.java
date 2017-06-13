package com.kd.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Amit Parikh
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class DemoInit extends SpringBootServletInitializer {

	protected final KLog logger = new KLog(this.getClass());

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("initializing the api...");
		return application.sources(DemoInit.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoInit.class, args);
	}
}