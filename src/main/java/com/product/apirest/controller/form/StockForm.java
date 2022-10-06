package com.product.apirest.controller.form;

import javax.validation.constraints.Min;

import com.product.apirest.model.Product;
import com.product.apirest.model.Stock;
import com.product.apirest.model.Store;
import com.product.apirest.repository.ProductRepository;
import com.product.apirest.repository.StockRepository;
import com.product.apirest.repository.StoreRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockForm {

	
	private Long id;
	private Long storeId;
	private Long productId;
	@Min(value = 1, message = "quantity must be greater than zero")
	private Integer quantity;

	public Stock converter(ProductRepository productRepository, StoreRepository storeRepository) {
		Product product = productRepository.getOne(productId);
		Store store = storeRepository.getOne(storeId);
		return new Stock(store, product, quantity);
	}
	
	public Stock atualizar(Long id, StockRepository stockRepository) {
		Stock stock = stockRepository.getOne(id);
		stock.setQuantity(this.quantity);
		return stock;
	}	
}
