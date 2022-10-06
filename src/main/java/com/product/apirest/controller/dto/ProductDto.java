package com.product.apirest.controller.dto;

import java.util.List;
import java.util.stream.Collectors;


import com.product.apirest.model.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	
	private Long id;
	private String name;
	private int modelYear;
	private Double listPrice;
	private String categoryName;
	private String brandName;
	

	public ProductDto(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.modelYear = product.getModelYear();
		this.listPrice = product.getListPrice();
		this.categoryName = product.getCategory().getName();
		this.brandName = product.getBrand().getName();
	}
	
	public static List<ProductDto> converter(List<Product> products){
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}
	

}
