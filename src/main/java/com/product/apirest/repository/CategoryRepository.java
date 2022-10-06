package com.product.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.apirest.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
