package com.smartCode.ecommerce.configuration.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class CardFeignConfig {

    @Bean
    public RequestInterceptor basicAuthRequestInterceptorToWallet() {
        return requestTemplate -> {
            String authHeader = "Basic " + Base64.getEncoder().encodeToString(("ecommerceClient" + ":" + "password").getBytes());
            requestTemplate.header("Authorization", authHeader);
        };
    }
}