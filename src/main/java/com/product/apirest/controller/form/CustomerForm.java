package com.product.apirest.controller.form;

import com.product.apirest.model.Customer;
import com.product.apirest.repository.CustomerRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerForm {

	
	private Long id;	
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String street;
	private String city;
	private String state;
	private String zipCode;

	public Customer converter() {
		return new Customer(firstName, lastName, phone, email, street, city, state, zipCode);
	}
	
	public Customer atualizar(Long id, CustomerRepository customerRepository) {
		Customer customer = customerRepository.getOne(id);
		customer.setFirstName(this.firstName);
		customer.setLastName(this.lastName);
		customer.setPhone(this.phone);
		customer.setEmail(this.email);
		customer.setStreet(this.street);
		customer.setCity(this.city);
		customer.setState(this.state);
		customer.setZipCode(this.zipCode);
		return customer;
	}	
}
