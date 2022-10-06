package com.product.apirest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.apirest.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	
	List<Stock> findByProductName(String productName);
	
	Optional<Stock> findByStoreId(Long storeId);

}
