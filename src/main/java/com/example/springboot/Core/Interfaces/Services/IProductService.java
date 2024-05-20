package com.example.springboot.Core.Interfaces.Services;

import java.util.List;
import java.util.UUID;

import com.example.springboot.Core.Dto.ProductDto;
import com.example.springboot.Core.Models.Product;

public interface IProductService {
	Product create(ProductDto productDto);
	
	List<Product> getAll();
	
	Product getById (UUID id) throws Exception;

	Product update (UUID id, ProductDto productDto) throws Exception;
	
	void delete (UUID id) throws Exception;
}
