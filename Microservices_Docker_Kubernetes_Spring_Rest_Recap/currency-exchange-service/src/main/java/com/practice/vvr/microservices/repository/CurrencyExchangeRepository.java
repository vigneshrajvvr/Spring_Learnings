package com.practice.vvr.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.vvr.microservices.entity.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long>{
	
	CurrencyExchange findByFromAndTo(String from, String to);

}
