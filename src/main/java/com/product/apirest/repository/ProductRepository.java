package com.product.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.product.apirest.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByCategoryName(String categoryName);
	
	@Query(nativeQuery = true, value = "SELECT brand_id "
			+ "FROM OSF_PRODUCTS "
			+ "GROUP BY brand_id "
			+ "ORDER BY count(*) DESC LIMIT 1")
	Long findBrandMostProducts();

}
