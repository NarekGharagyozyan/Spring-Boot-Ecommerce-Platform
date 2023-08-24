package com.smartCode.ecommerce.configuration.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
@Configuration
public class NotificationFeignConfig {

    @Bean
    public RequestInterceptor basicAuthRequestInterceptorToNotificator() {
        return requestTemplate -> {
            String authHeader = "Basic " + Base64.getEncoder().encodeToString(("ecommerceClient" + ":" + "password").getBytes());
            requestTemplate.header("Authorization", authHeader);
        };
    }


}