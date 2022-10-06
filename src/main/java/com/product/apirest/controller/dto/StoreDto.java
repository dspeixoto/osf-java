package com.product.apirest.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.product.apirest.model.Store;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDto {
		
	private Long id;
	private String name;
	private String phone;
	private String email;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	

	public StoreDto(Store store) {
		this.id = store.getId();
		this.name = store.getName();
		this.phone = store.getPhone();
		this.email = store.getEmail();
		this.street = store.getStreet();
		this.city = store.getCity();
		this.state = store.getState();
		this.zipCode = store.getZipCode();
	}
	
	public static List<StoreDto> converter(List<Store> stores){
		return stores.stream().map(StoreDto::new).collect(Collectors.toList());
	}
	

}
