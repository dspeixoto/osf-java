package com.product.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.apirest.controller.form.OrderForm;
import com.product.apirest.model.Order;
import com.product.apirest.repository.CustomerRepository;
import com.product.apirest.repository.OrderRepository;
import com.product.apirest.repository.StaffRepository;
import com.product.apirest.repository.StoreRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	public Order create(OrderForm form) {
		Order order = form.converter(customerRepository, storeRepository, staffRepository);
		form.setOrderStatus(1);
		orderRepository.save(order);
		return order;
	}
	
	public Order atualizar (Long id, OrderForm form) {
		Order order = form.atualizar(id, orderRepository);
		return null;
	}

}
