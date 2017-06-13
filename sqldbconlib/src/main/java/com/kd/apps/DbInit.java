package com.kd.apps;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Javaconfig for setting datasource connection properties and jpa vendor
 * properties
 * 
 * @author Pradyumna Roy
 */
@Configuration
@EnableJpaRepositories
@PropertySources(value = { @PropertySource(value = "classpath:db.properties", ignoreResourceNotFound = true),
		@PropertySource(value = "file:/opt/ot/dwallet/tomcat/conf/db.properties", ignoreResourceNotFound = true) })
public class DbInit {

	@Autowired
	Environment env;

	/**
	 * set database connection properties
	 * 
	 * @return DataSource
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}

	/**
	 * get local container entity manager factory bean
	 * 
	 * @param dataSource
	 *            Datasource object
	 * @param jpaVendorAdapter
	 *            JpaVendorAdapter object
	 * @return LocalContainerEntityManagerFactoryBean
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan(env.getProperty("db.package.toscan"));
		return lef;
	}

	/**
	 * set JpaVendor and properties and return it
	 * 
	 * @return JpaVendorAdapter
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(Boolean.parseBoolean(env.getProperty("db.showsql")));
		hibernateJpaVendorAdapter.setGenerateDdl(Boolean.parseBoolean(env.getProperty("db.generateddl")));
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	/**
	 * get transaction manager
	 * 
	 * @return PlatformTransactionManager
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
}
