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
@Table(name="OSF.PRODUCTS")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	@ManyToOne
	private Brand brand;
	@ManyToOne
	private Category category;
	private int modelYear;
	private Double listPrice;
	
	public Product(String name, int modelYear, Double listPrice, Category category, Brand brand) {
		this.name = name;
		this.modelYear = modelYear;
		this.listPrice = listPrice;
		this.category = category;
		this.brand = brand;
	}
	
}
