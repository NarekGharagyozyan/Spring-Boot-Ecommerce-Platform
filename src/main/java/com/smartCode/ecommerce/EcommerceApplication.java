package com.smartCode.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableAsync
@SpringBootApplication
@EnableGlobalMethodSecurity(
        jsr250Enabled = true,
        prePostEnabled = true,
        securedEnabled = true)
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

}
