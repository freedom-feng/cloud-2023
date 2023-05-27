package com.feng.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder route= routeLocatorBuilder.routes();
        route.route("path_route_feng",
                r -> r.path("/guonei").uri("http://news.baidu.com/")).build();
        return  route.build();
    }
}
