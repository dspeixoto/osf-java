package com.product.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.product.apirest.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	@Query(nativeQuery = true, value = "SELECT product_id "
			+ "FROM OSF_ORDERS_ITEMS "
			+ "GROUP BY product_id "
			+ "ORDER BY count(*) DESC LIMIT 1")
	Long findMostPopularProduct();

}
