package com.webflux.f1.webfluxf1api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class WebfluxF1apiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxF1apiApplication.class, args);
    }

}
