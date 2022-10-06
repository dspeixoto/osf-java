package com.product.apirest.controller.form;

import java.util.Date;

import com.product.apirest.model.Customer;
import com.product.apirest.model.Order;
import com.product.apirest.model.Staff;
import com.product.apirest.model.Store;
import com.product.apirest.repository.CustomerRepository;
import com.product.apirest.repository.OrderRepository;
import com.product.apirest.repository.StaffRepository;
import com.product.apirest.repository.StoreRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm {

	
	private Long id;
	private Long customerId;	
	private Integer orderStatus;
	private Date orderDate;
	private Date requiredDate;
	private Date shippedDate;
	private Long storeId;
	private Long staffId;

	public Order converter(CustomerRepository customerRepository, StoreRepository storeRepository, StaffRepository staffRepository) {
		Customer customer = customerRepository.getOne(customerId);
		Store store = storeRepository.getOne(storeId);
		Staff staff = staffRepository.getOne(staffId);
		return new Order(customer, orderStatus, orderDate, requiredDate, shippedDate, store, staff);
	}
	
	public Order atualizar(Long id, OrderRepository orderRepository) {
		Order order = orderRepository.getOne(id);
		order.setOrderStatus(this.orderStatus);
		order.setOrderDate(this.orderDate);
		order.setRequiredDate(this.requiredDate);
		order.setShippedDate(this.shippedDate);
		return order;
	}	
}
