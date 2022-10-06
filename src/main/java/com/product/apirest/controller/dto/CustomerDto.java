package com.product.apirest.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.product.apirest.model.Customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
		
	private Long id;	
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	

	public CustomerDto(Customer customer) {
		this.id = customer.getId();
		this.firstName = customer.getFirstName();
		this.lastName = customer.getLastName();
		this.phone = customer.getPhone();
		this.email = customer.getEmail();
		this.street = customer.getStreet();
		this.city = customer.getCity();
		this.state = customer.getState();
		this.zipCode = customer.getZipCode();
	}
	
	public static List<CustomerDto> converter(List<Customer> customers){
		return customers.stream().map(CustomerDto::new).collect(Collectors.toList());
	}
	

}
