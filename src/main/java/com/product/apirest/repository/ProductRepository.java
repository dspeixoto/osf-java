package com.product.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.apirest.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByCategoryName(String categoryName);

}
