package com.product.apirest.model;

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
@Table(name="OSF.ORDERS_ITEMS")
public class OrderItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Order order;
	@ManyToOne
	private Product product;	
	private Integer quantity;
	private Double listPrice;
	private Double discount;
	
	public OrderItem(Order order, Product product, Integer quantity, Double listPrice, Double discount) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.listPrice = listPrice;
		this.discount = discount;
	}	
}
