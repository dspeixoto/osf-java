package com.product.apirest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="OSF.ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Customer customer;	
	private Integer orderStatus;
	private Date orderDate;
	private Date requiredDate;
	private Date shippedDate;
	@ManyToOne
	private Store store;
	@ManyToOne
	private Staff staff;
	
	public Order(Customer customer, Integer orderStatus, Date orderDate, Date requiredDate, Date shippedDate,
			Store store, Staff staff) {
		this.customer = customer;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.store = store;
		this.staff = staff;
	}	
}
