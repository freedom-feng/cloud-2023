package com.feng.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentHysteixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHysteixMain8001.class,args);
    }
}
