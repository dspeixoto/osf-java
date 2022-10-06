package com.product.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.apirest.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
