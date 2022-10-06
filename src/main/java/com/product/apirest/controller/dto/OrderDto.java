package com.product.apirest.controller.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.product.apirest.model.Order;
import com.product.apirest.model.Store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
	
	private Long id;
	private String customer;	
	private Integer orderStatus;
	private Date orderDate;
	private Date requiredDate;
	private Date shippedDate;
	private Store store;
	private String staff;
	

	public OrderDto(Order order) {
		this.id = order.getId();
		this.customer = order.getCustomer().getFirstName();
		this.orderStatus = order.getOrderStatus();
		this.orderDate = order.getOrderDate();
		this.requiredDate = order.getRequiredDate();
		this.shippedDate = order.getShippedDate();
		this.store = order.getStore();
		this.staff = order.getStaff().getFirstName();
	}
	
	public static List<OrderDto> converter(List<Order> orders){
		return orders.stream().map(OrderDto::new).collect(Collectors.toList());
	}
	

}
