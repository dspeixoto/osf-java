package com.product.apirest.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.product.apirest.model.Customer;
import com.product.apirest.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	Page<Order> findByCustomerId(Long customerId, Pageable paginacao);
	
	Page<Order> findByOrderDateBetween(Date orderDateStart, Date orderDateEnd, Pageable paginacao);	
	
	@Query(nativeQuery = true, value = "SELECT customer_id "
			+ "FROM OSF_ORDERS "
			+ "GROUP BY customer_id "
			+ "ORDER BY count(*) DESC")
	Page<Customer> findCustomerMoreOrders(Pageable paginacao);
	
}
