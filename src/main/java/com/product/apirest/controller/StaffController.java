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

import com.product.apirest.controller.dto.StaffDto;
import com.product.apirest.controller.form.StaffForm;
import com.product.apirest.model.Staff;
import com.product.apirest.repository.StaffRepository;
import com.product.apirest.repository.StoreRepository;

@RestController
@RequestMapping("/staffs")
public class StaffController {
	
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	StoreRepository storeRepository;
	
	@GetMapping
	public List<StaffDto> lista(String storeName) {
		if(storeName == null) {
			List<Staff> staff = staffRepository.findAll();
			return StaffDto.converter(staff);
		} else {
			List<Staff> staff = staffRepository.findByStoreName(storeName);
			return StaffDto.converter(staff);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<StaffDto> cadastrar(@RequestBody StaffForm form, UriComponentsBuilder uriBuilder) {
		Staff staff = form.converter(storeRepository, staffRepository);
		staffRepository.save(staff);
		
		URI uri = uriBuilder.path("/staffs/{id}").buildAndExpand(staff.getId()).toUri();
		return ResponseEntity.created(uri).body(new StaffDto(staff));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StaffDto> detalhado(@PathVariable Long id) {
		Optional<Staff> staff = staffRepository.findById(id);	
		if(staff.isPresent()) {
			return ResponseEntity.ok(new StaffDto(staff.get()));
		}		
		return ResponseEntity.notFound().build();		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<StaffDto> atualizar(@PathVariable Long id, @RequestBody StaffForm form) {
		Optional<Staff> optional = staffRepository.findById(id);
		if(optional.isPresent()) {
			Staff staff = form.atualizar(id, staffRepository);
			return ResponseEntity.ok(new StaffDto(staff));
		}
		return ResponseEntity.notFound().build();	
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Staff> optional = staffRepository.findById(id);
		if(optional.isPresent()) {
			staffRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();	
	}
}
