package com.example.springboot.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.springboot.dto.ProductDto;
import com.example.springboot.models.Product;

public interface IProductService {
	Product create(ProductDto productDto);
	
	List<Product> getAll();
	
	Optional<Product> getById (UUID id) throws Exception;
	
	Optional<Product> update (UUID id, ProductDto productDto) throws Exception;
	
	void delete (UUID id) throws Exception;
}
