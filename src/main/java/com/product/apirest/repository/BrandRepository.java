package com.product.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.apirest.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

}
