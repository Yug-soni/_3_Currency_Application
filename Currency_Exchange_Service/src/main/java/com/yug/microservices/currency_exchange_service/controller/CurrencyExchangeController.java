package com.yug.microservices.currency_exchange_service.controller;

import com.yug.microservices.currency_exchange_service.entity.CurrencyExchange;
import com.yug.microservices.currency_exchange_service.service.CurrencyExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class CurrencyExchangeController {

  private Environment environment;
  private CurrencyExchangeService currencyExchangeService;

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
    String port = environment.getProperty("local.server.port");
    Optional<CurrencyExchange> optional = this.currencyExchangeService.findByFromAndTo(from, to);
    if(optional.isEmpty()) {
      throw new IllegalStateException("Conversion not found.");
    }
    CurrencyExchange currencyExchange = optional.get();
    currencyExchange.setEnvironment(port);
    return currencyExchange;
  }
}
