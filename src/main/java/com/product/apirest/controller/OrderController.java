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

import com.product.apirest.controller.dto.OrderDto;
import com.product.apirest.controller.form.OrderForm;
import com.product.apirest.controller.form.OrderStatusForm;
import com.product.apirest.model.Order;
import com.product.apirest.model.Stock;
import com.product.apirest.repository.CustomerRepository;
import com.product.apirest.repository.OrderRepository;
import com.product.apirest.repository.StaffRepository;
import com.product.apirest.repository.StockRepository;
import com.product.apirest.repository.StoreRepository;
import com.product.apirest.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	StockRepository stockRepository;
	
	@GetMapping
	public List<OrderDto> lista() {
		List<Order> order = orderRepository.findAll();
		return OrderDto.converter(order);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<OrderDto> cadastrar(@RequestBody OrderForm form, UriComponentsBuilder uriBuilder) {		
		Optional<Stock> optional = stockRepository.findByStoreId(form.getStoreId());
		if(optional.isPresent()) {
			Order order = orderService.create(form);
			URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri();
			return ResponseEntity.created(uri).body(new OrderDto(order));
		} else {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> detalhado(@PathVariable Long id) {
		Optional<Order> order = orderRepository.findById(id);	
		if(order.isPresent()) {
			return ResponseEntity.ok(new OrderDto(order.get()));
		}		
		return ResponseEntity.notFound().build();		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<OrderDto> atualizar(@PathVariable Long id, @RequestBody OrderForm form) {
		Optional<Order> optional = orderRepository.findById(id);
		if(optional.isPresent()) {
			Order order = orderService.atualizar(id, form);
			return ResponseEntity.ok(new OrderDto(order));
		}
		return ResponseEntity.notFound().build();	
	}
	
	@PutMapping("/status/{id}")
	@Transactional
	public ResponseEntity<OrderDto> atualizarStatus(@PathVariable Long id, @RequestBody OrderStatusForm form) {
		Optional<Order> optional = orderRepository.findById(id);
		if(optional.isPresent()) {
			Order order = form.atualizar(id, orderRepository);
			return ResponseEntity.ok(new OrderDto(order));
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Order> optional = orderRepository.findById(id);
		if(optional.isPresent()) {
			orderRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();	
	}
}
