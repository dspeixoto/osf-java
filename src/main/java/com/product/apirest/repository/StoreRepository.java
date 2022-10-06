package com.product.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.apirest.model.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{

}
