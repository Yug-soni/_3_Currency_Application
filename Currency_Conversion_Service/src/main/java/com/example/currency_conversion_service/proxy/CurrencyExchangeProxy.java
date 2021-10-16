package com.example.currency_conversion_service.proxy;

import com.example.currency_conversion_service.entity.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "localhost:8000")
// Here the currency-conversion and both currency-exchange both are registered with the
// eureka client
// if I remove the url then, it will talk to the eureka client and automatic url is given to him.
// And also the eureka server automatically balance the load between them.
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
