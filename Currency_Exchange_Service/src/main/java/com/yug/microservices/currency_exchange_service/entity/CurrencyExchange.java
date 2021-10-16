package com.yug.microservices.currency_exchange_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CurrencyExchange {

  @Id
  private Long id;

  @Column(name = "currency_from")
  private String from;

  @Column(name = "currency_to")
  private String to;
  private BigDecimal conversionMultiple;
  private String environment;

  public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
    this.id = id;
    this.from = from;
    this.to = to;
    this.conversionMultiple = conversionMultiple;
  }
}
