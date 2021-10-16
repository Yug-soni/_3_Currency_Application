package com.yug.microservices.currency_exchange_service.service;

import com.yug.microservices.currency_exchange_service.entity.CurrencyExchange;
import com.yug.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CurrencyExchangeService {
  private CurrencyExchangeRepository currencyExchangeRepository;

  public Optional<CurrencyExchange> findByFromAndTo(String from, String to) {
    return this.currencyExchangeRepository.findByFromAndTo(from, to);
  }
}
