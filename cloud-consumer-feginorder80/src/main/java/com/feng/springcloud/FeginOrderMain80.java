package com.feng.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeginOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(FeginOrderMain80.class,args);
    }
}
