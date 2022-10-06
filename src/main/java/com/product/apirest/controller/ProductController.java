package com.product.apirest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.product.apirest.controller.dto.ProductDto;
import com.product.apirest.controller.form.ProductForm;
import com.product.apirest.model.Product;
import com.product.apirest.repository.BrandRepository;
import com.product.apirest.repository.CategoryRepository;
import com.product.apirest.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@GetMapping
	public List<ProductDto> lista(String categoryName) {
		if(categoryName == null) {
			List<Product> product = productRepository.findAll();
			return ProductDto.converter(product);
		} else {
			List<Product> product = productRepository.findByCategoryName(categoryName);
			return ProductDto.converter(product);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProductDto> cadastrar(@RequestBody ProductForm form, UriComponentsBuilder uriBuilder) {
		Product product = form.converter(categoryRepository, brandRepository);
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> detalhado(@PathVariable Long id) {
		Optional<Product> product = productRepository.findById(id);	
		if(product.isPresent()) {
			return ResponseEntity.ok(new ProductDto(product.get()));
		}		
		return ResponseEntity.notFound().build();		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ProductDto> atualizar(@PathVariable Long id, @RequestBody ProductForm form) {
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			Product product = form.atualizar(id, productRepository);
			return ResponseEntity.ok(new ProductDto(product));
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();	
	}
}
