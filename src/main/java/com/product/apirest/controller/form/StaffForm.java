package com.product.apirest.controller.form;

import com.product.apirest.model.Staff;
import com.product.apirest.model.Store;
import com.product.apirest.repository.StaffRepository;
import com.product.apirest.repository.StoreRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffForm {

	
	private Long id;	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Boolean active;
	private Long storeId;

	public Staff converter(StoreRepository storeRepository, StaffRepository staffRepository) {
		Store store = storeRepository.getOne(storeId);
		Staff manager = staffRepository.getOne(storeId);
		return new Staff(firstName, lastName, email, phone, active, store, manager);
	}
	
	public Staff atualizar(Long id, StaffRepository staffRepository) {
		Staff staff = staffRepository.getOne(id);
		staff.setFirstName(this.firstName);
		staff.setLastName(this.lastName);
		staff.setEmail(this.email);
		staff.setPhone(this.phone);
		staff.setActive(this.active);
		return staff;
	}	
}
