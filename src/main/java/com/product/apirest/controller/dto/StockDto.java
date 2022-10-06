package com.product.apirest.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.product.apirest.model.Stock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDto {
	
	private Long id;
	private String storeName;
	private String productName;
	private Integer quantity;
	

	public StockDto(Stock stock) {
		this.id = stock.getId();
		this.storeName = stock.getStore().getName();
		this.productName = stock.getProduct().getName();
		this.quantity = stock.getQuantity();
	}
	
	public static List<StockDto> converter(List<Stock> stock){
		return stock.stream().map(StockDto::new).collect(Collectors.toList());
	}
	

}
