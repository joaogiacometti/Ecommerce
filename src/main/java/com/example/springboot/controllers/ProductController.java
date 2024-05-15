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
import com.example.springboot.models.Product;
import com.example.springboot.repositories.ProductRepository;

import jakarta.validation.Valid;

@RestController
public class ProductController {

	@Autowired
	ProductRepository _repo;
	
	@PostMapping("/products")
	public ResponseEntity<Product> create(@RequestBody @Valid ProductDto productDto){
		var product = new Product();
		BeanUtils.copyProperties(productDto, product);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(_repo.save(product));
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(_repo.findAll());
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Object> getById(@PathVariable(value="id") UUID id){
		Optional<Product> product = _repo.findById(id);
		
		if(product.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(product.get());
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Object> update(
			@PathVariable(value="id") UUID id, 
			@RequestBody @Valid ProductDto productDto ){
		Optional<Product> product = _repo.findById(id);
		
		if(product.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
		
		var newProduct = product.get();
		BeanUtils.copyProperties(productDto, newProduct);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(_repo.save(newProduct));
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Object> delete(
			@PathVariable(value="id") UUID id){
		Optional<Product> product = _repo.findById(id);
		
		if(product.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
		
		_repo.delete(product.get());
		
		return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
	}
}
