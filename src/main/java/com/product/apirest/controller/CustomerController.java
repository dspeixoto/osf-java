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

import com.product.apirest.controller.dto.CustomerDto;
import com.product.apirest.controller.form.CustomerForm;
import com.product.apirest.model.Customer;
import com.product.apirest.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping
	public List<CustomerDto> lista() {
		List<Customer> customer = customerRepository.findAll();
		return CustomerDto.converter(customer);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CustomerDto> cadastrar(@RequestBody CustomerForm form, UriComponentsBuilder uriBuilder) {
		Customer customer = form.converter();
		customerRepository.save(customer);
		
		URI uri = uriBuilder.path("/customers/{id}").buildAndExpand(customer.getId()).toUri();
		return ResponseEntity.created(uri).body(new CustomerDto(customer));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> detalhado(@PathVariable Long id) {
		Optional<Customer> customer = customerRepository.findById(id);	
		if(customer.isPresent()) {
			return ResponseEntity.ok(new CustomerDto(customer.get()));
		}		
		return ResponseEntity.notFound().build();		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CustomerDto> atualizar(@PathVariable Long id, @RequestBody CustomerForm form) {
		Optional<Customer> optional = customerRepository.findById(id);
		if(optional.isPresent()) {
			Customer customer = form.atualizar(id, customerRepository);
			return ResponseEntity.ok(new CustomerDto(customer));
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Customer> optional = customerRepository.findById(id);
		if(optional.isPresent()) {
			customerRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();	
	}
}
