package com.feng.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class feignConfig {
    @Bean
    Logger.Level feignLogerLevel(){
        return  Logger.Level.FULL;
    }
}
