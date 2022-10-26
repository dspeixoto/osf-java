package com.product.apirest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="OSF.STORES")
public class Store {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private String name;
	private String phone;
	private String email;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	
	public Store(String name, String phone, String email, String street, String city, String state, String zipCode) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	
}
