package com.product.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.apirest.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
