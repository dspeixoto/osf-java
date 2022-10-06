package com.product.apirest.controller.form;

import com.product.apirest.model.Category;
import com.product.apirest.model.Product;
import com.product.apirest.model.Store;
import com.product.apirest.repository.CategoryRepository;
import com.product.apirest.repository.ProductRepository;
import com.product.apirest.repository.StoreRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreForm {

	
	private Long id;
	private String name;
	private String phone;
	private String email;
	private String street;
	private String city;
	private String state;
	private String zipCode;

	public Store converter() {
		return new Store(name, phone, email, street, city, state, zipCode);
	}
	
	public Store atualizar(Long id, StoreRepository storeRepository) {
		Store store = storeRepository.getOne(id);
		store.setName(this.name);
		store.setPhone(this.phone);
		store.setEmail(this.email);
		store.setStreet(this.street);
		store.setCity(this.city);
		store.setState(this.state);
		store.setZipCode(this.zipCode);
		return store;
	}	
}
