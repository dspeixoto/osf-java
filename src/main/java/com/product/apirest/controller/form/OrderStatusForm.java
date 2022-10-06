package com.product.apirest.controller.form;

import com.product.apirest.model.Order;
import com.product.apirest.repository.OrderRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStatusForm {	
		
	private Integer orderStatus;
	
	public Order atualizar(Long id, OrderRepository orderRepository) {
		Order order = orderRepository.getOne(id);
		order.setOrderStatus(this.orderStatus);
		return order;
	}	
}
