package com.product.apirest.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.product.apirest.controller.dto.StockDto;
import com.product.apirest.controller.form.StockForm;
import com.product.apirest.model.Stock;
import com.product.apirest.repository.BrandRepository;
import com.product.apirest.repository.ProductRepository;
import com.product.apirest.repository.StockRepository;
import com.product.apirest.repository.StoreRepository;

@RestController
@RequestMapping("/stocks")
public class StockController {
	
	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@GetMapping
	public List<StockDto> lista(String productName) {
		if(productName == null) {
			List<Stock> stock = stockRepository.findAll();
			return StockDto.converter(stock);
		} else {
			List<Stock> stock = stockRepository.findByProductName(productName);
			return StockDto.converter(stock);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<StockDto> cadastrar(@RequestBody @Valid StockForm form, UriComponentsBuilder uriBuilder) {
		Stock stock = form.converter(productRepository, storeRepository);
		stockRepository.save(stock);
		
		URI uri = uriBuilder.path("/stocks/{id}").buildAndExpand(stock.getId()).toUri();
		return ResponseEntity.created(uri).body(new StockDto(stock));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StockDto> detalhado(@PathVariable Long id) {
		Optional<Stock> stock = stockRepository.findById(id);	
		if(stock.isPresent()) {
			return ResponseEntity.ok(new StockDto(stock.get()));
		}		
		return ResponseEntity.notFound().build();		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<StockDto> atualizar(@PathVariable Long id, @RequestBody StockForm form) {
		Optional<Stock> optional = stockRepository.findById(id);
		if(optional.isPresent()) {
			Stock stock = form.atualizar(id, stockRepository);
			return ResponseEntity.ok(new StockDto(stock));
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Stock> optional = stockRepository.findById(id);
		if(optional.isPresent()) {
			stockRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();	
	}
}
