package com.example.springboot.Api.Controllers;

import java.util.List;
import java.util.UUID;

import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.springboot.Core.Dto.ProductDto;
import com.example.springboot.Business.Exceptions.ProductNotFoundException;
import com.example.springboot.Core.Models.Product;
import com.example.springboot.Business.Services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService service;
	
	
	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<Product> create(@RequestBody @Valid ProductDto productDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(productDto));
	}

	@PermitAll
	@GetMapping("/getAll")
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getById/{id}")
	public ResponseEntity<Object> getById(@PathVariable(value="id") UUID id) throws Exception{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
		}catch(ProductNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
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

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value="id") UUID id) throws Exception{
		try {
			service.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
		}catch(ProductNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
}
