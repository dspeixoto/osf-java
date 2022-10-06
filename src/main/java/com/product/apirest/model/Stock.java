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
@Table(name="OSF.STOCKS")
public class Stock {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Store store;
	@ManyToOne
	private Product product;
	private Integer quantity;
	
	public Stock(Store store, Product product, Integer quantity) {
		this.store = store;
		this.product = product;
		this.quantity = quantity;
	}
	
}
