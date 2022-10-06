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

import com.product.apirest.controller.dto.StoreDto;
import com.product.apirest.controller.form.StoreForm;
import com.product.apirest.model.Store;
import com.product.apirest.repository.StoreRepository;

@RestController
@RequestMapping("/stores")
public class StoreController {
	
	@Autowired
	StoreRepository storeRepository;
	
	@GetMapping
	public List<StoreDto> lista() {
		List<Store> store = storeRepository.findAll();
		return StoreDto.converter(store);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<StoreDto> cadastrar(@RequestBody StoreForm form, UriComponentsBuilder uriBuilder) {
		Store store = form.converter();
		storeRepository.save(store);
		
		URI uri = uriBuilder.path("/stores/{id}").buildAndExpand(store.getId()).toUri();
		return ResponseEntity.created(uri).body(new StoreDto(store));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StoreDto> detalhado(@PathVariable Long id) {
		Optional<Store> store = storeRepository.findById(id);	
		if(store.isPresent()) {
			return ResponseEntity.ok(new StoreDto(store.get()));
		}		
		return ResponseEntity.notFound().build();		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<StoreDto> atualizar(@PathVariable Long id, @RequestBody StoreForm form) {
		Optional<Store> optional = storeRepository.findById(id);
		if(optional.isPresent()) {
			Store store = form.atualizar(id, storeRepository);
			return ResponseEntity.ok(new StoreDto(store));
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Store> optional = storeRepository.findById(id);
		if(optional.isPresent()) {
			storeRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();	
	}
}
