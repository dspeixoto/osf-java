package com.product.apirest.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.product.apirest.model.Staff;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffDto {
	
	private Long id;	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Boolean active;
	private String storeName;
	private String managerName;
	

	public StaffDto(Staff staff) {
		this.id = staff.getId();
		this.firstName = staff.getFirstName();
		this.lastName = staff.getLastName();
		this.email = staff.getEmail();
		this.phone = staff.getPhone();
		this.active = staff.getActive();
		this.storeName = staff.getStore().getName();
		this.managerName = staff.getManager().getFirstName();
	}
	
	public static List<StaffDto> converter(List<Staff> staffs){
		return staffs.stream().map(StaffDto::new).collect(Collectors.toList());
	}
	

}
