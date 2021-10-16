package com.yug.microservices.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {

  @Bean
  public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
    return builder.routes()
            // if the request comes for the /get
            .route(predicateSpec -> predicateSpec.path("/get")
                    // before redirecting the user to the specified url add my custom header
                    // it can be used in the auth
                    .filters(f -> f
                            .addRequestHeader("MyHeader", "MyUri")
                            .addRequestParameter("Param", "MyValue"))
                    // then we redirect the user to the specified url
                    .uri("http://httpbin.org:80"))
            .route(predicateSpec -> predicateSpec.path("/currency-exchange/**")
                    .uri("lb://currency-exchange"))
            .route(predicateSpec -> predicateSpec.path("/currency-conversion/**")
                    .uri("lb://currency-conversion"))
            .route(predicateSpec -> predicateSpec.path("/currency-conversion-feign/**")
                    // here we are saying that the load balancer is going to decide which
                    // to service to use ,so we are providing the service name
                    // registered with the given name
                    .uri("lb://currency-conversion"))
            .route(predicateSpec -> predicateSpec.path("/currency-conversion-new/**")
                    .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath(
                            // anything followed by the given pattern store the thing in segment
                            "/currency-conversion-new/(?<segment>.*)",
                            // replace the pattern with given and add the same segment to it
                            "/currency-conversion-feign/${segment}"
                    ))
                    .uri("lb://currency-conversion"))
            .build();
  }
}
