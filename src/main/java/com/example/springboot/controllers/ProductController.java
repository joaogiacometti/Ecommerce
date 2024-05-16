package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.ProductDto;
import com.example.springboot.exceptions.ProductNotFoundException;
import com.example.springboot.models.Product;
import com.example.springboot.repositories.ProductRepository;
import com.example.springboot.services.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {

	private final ProductService service;
	
	
	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
	}
	

	@PostMapping("/products")
	public ResponseEntity<Product> create(@RequestBody @Valid ProductDto productDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(productDto));
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Object> getById(@PathVariable(value="id") UUID id) throws Exception{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
		}catch(ProductNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Object> update(
			@PathVariable(value="id") UUID id, 
			@RequestBody @Valid ProductDto productDto ) throws Exception{
		try {
			var newProduct = service.update(id, productDto);
			
			return ResponseEntity.status(HttpStatus.OK).body(newProduct);
		}catch(ProductNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value="id") UUID id) throws Exception{
		try {
			service.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
		}catch(ProductNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
}
