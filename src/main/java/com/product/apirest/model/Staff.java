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
@Table(name="OSF.STAFFS")
public class Staff {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Boolean active;
	@ManyToOne
	private Store store;
	@ManyToOne
	private Staff manager;
	
	public Staff(String firstName, String lastName, String email, String phone, Boolean active, Store store, Staff manager) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.active = active;
		this.store = store;
		this.manager = manager;
	}
	
}
