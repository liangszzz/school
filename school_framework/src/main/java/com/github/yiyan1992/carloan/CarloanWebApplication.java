package com.github.yiyan1992.carloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@EnableConfigurationProperties
@EntityScan("com.github.yiyan1992.carloan.entity")
@SpringBootApplication
public class CarloanWebApplication {

    public static void main(String[] args) {
        System.setProperty("hibernate.dialect.storage_engine","innodb");
        SpringApplication.run(CarloanWebApplication.class, args);
    }

}
