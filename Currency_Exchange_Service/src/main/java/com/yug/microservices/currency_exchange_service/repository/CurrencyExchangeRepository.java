package com.yug.microservices.currency_exchange_service.repository;

import com.yug.microservices.currency_exchange_service.entity.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
  Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
