package com.neighborhood.msneighborhood.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.neighborhood.msneighborhood")
@PropertySource("classpath:application-test.properties")
@EnableTransactionManagement
public class TestsConfig {

}
