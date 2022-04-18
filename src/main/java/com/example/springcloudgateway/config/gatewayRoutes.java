package com.example.springcloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class gatewayRoutes {

    @Bean // RouteLocator 빈을 등록
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // r 이라는 값이 들어오면 path를 확인하고, filter 적용하고, uri로 이동해라
                .route(r -> r.path("/first-service/**")
                        .filters(f-> f.addRequestHeader("first-request","first-request-header")
                                .addResponseHeader("first-response","first-response-header"))
                        .uri("http://localhost:5000"))
                // filter는 Requestheader를 추가하고 response할 때도 header를 추가하는 filter 추가
                .route(r -> r.path("/second-service/**")
                        .filters(f-> f.addRequestHeader("second-request","second-request-header")
                                .addResponseHeader("second-response","second-response-header"))
                        .uri("http://localhost:5002"))
                .build();
    }
}
