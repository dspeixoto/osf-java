package com.product.apirest.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.apirest.controller.dto.CustomerDto;
import com.product.apirest.controller.dto.OrderDto;
import com.product.apirest.model.Brand;
import com.product.apirest.model.Customer;
import com.product.apirest.model.Order;
import com.product.apirest.model.Product;
import com.product.apirest.repository.BrandRepository;
import com.product.apirest.repository.OrderItemRepository;
import com.product.apirest.repository.OrderRepository;
import com.product.apirest.repository.ProductRepository;

@RestController
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@GetMapping("/mostPopularProduct")
	public Product mostPopular() {
		Long id = orderItemRepository.findMostPopularProduct();
		Product product = productRepository.getOne(id);
		return product;
	}
	
	@GetMapping("/brandMostProducts")
	public Brand brandMostProducts() {
		Long id = productRepository.findBrandMostProducts();
		Brand brand = brandRepository.getOne(id);
		return brand;
	}
	
	@GetMapping("/customersMoreOrders")
	public Page<CustomerDto> customersMoreOrders(Pageable paginacao) {
		Page<Customer> customers = orderRepository.findCustomerMoreOrders(paginacao);
		return CustomerDto.converterPage(customers);
	}
	
	@GetMapping("/ordersPerCustomer")
	public Page<OrderDto> listaOrderPorCustomerId(@RequestParam Long customerId, Pageable paginacao) {
		Page<Order> order = orderRepository.findByCustomerId(customerId, paginacao);
		return OrderDto.converterPage(order);
	}
	
	@GetMapping("/orderBetweenDate")
	public Page<OrderDto> listaOrderEntreDatas(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateStart, 
												@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDateEnd,
												Pageable paginacao) {
		Page<Order> order = orderRepository.findByOrderDateBetween(orderDateStart, orderDateEnd, paginacao);
		return OrderDto.converterPage(order);
	}
}
