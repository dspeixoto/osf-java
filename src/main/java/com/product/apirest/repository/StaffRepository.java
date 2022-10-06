package com.product.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.apirest.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long>{
	
	List<Staff> findByStoreName(String storeName);

}
