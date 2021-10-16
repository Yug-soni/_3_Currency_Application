package com.yug.microservices.currency_exchange_service.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

  private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

  @GetMapping("/sample-api")
//  @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
//  @CircuitBreaker(name = "default", fallbackMethod = "hardCodedResponse")
//  @RateLimiter(name = "default")
  @Bulkhead(name = "default")
  public String sampleApi() {
    logger.info("Sample Api Call Received.");
    ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8000/some-dummy-url", String.class);
    return forEntity.getBody();
  }

  // Here the fallback method should have argument as the Throwable
  public String hardCodedResponse(Exception exception) {
    return "fallback-method-response";
  }
}
