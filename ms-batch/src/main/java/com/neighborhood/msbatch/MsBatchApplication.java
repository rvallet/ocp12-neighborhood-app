package com.neighborhood.msbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages="com.neighborhood")
@EnableConfigurationProperties
@EnableDiscoveryClient
@EnableFeignClients("com.neighborhood.msbatch")
public class MsBatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MsBatchApplication.class, args);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MsBatchApplication.class);


    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

    }
}
