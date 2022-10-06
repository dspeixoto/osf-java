package com.product.apirest.controller.form;

import com.product.apirest.model.Brand;
import com.product.apirest.model.Category;
import com.product.apirest.model.Product;
import com.product.apirest.repository.BrandRepository;
import com.product.apirest.repository.CategoryRepository;
import com.product.apirest.repository.ProductRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

	
	private Long id;
	private String name;
	private Brand brand;
	private int modelYear;
	private Double listPrice;
	private Long categoryId;
	private Long brandId;

	public Product converter(CategoryRepository categoryRepository, BrandRepository brandRepository) {
		Category category = categoryRepository.getOne(categoryId);
		Brand brand = brandRepository.getOne(brandId);
		return new Product(name, modelYear, listPrice, category, brand);
	}
	
	public Product atualizar(Long id, ProductRepository productRepository) {
		Product product = productRepository.getOne(id);
		product.setName(this.name);
		product.setModelYear(this.modelYear);
		product.setListPrice(this.listPrice);
		return product;
	}	
}
