package com.example.springboot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.dto.ProductDto;
import com.example.springboot.exceptions.ProductNotFoundException;
import com.example.springboot.interfaces.IProductService;
import com.example.springboot.models.Product;
import com.example.springboot.repositories.ProductRepository;

@Service
public class ProductService implements IProductService{
	private final ProductRepository repository;

	@Autowired
	public ProductService(ProductRepository repository) {
		this.repository = repository; 
	}

	@Override
	public Product create(ProductDto productDto) {
		var product = new Product();
		BeanUtils.copyProperties(productDto, product);
		
		repository.save(product);
		
		return product;
	}

	@Override
	public List<Product> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Product> getById(UUID id) throws Exception {
		Optional<Product> product = repository.findById(id);
		
		if(product.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		}
		
		return product;
	}

	@Override
	public Optional<Product> update(UUID id, ProductDto productDto) throws Exception {
		Optional<Product> product = getById(id);
		
		var newProduct = product.get();
		BeanUtils.copyProperties(productDto, newProduct);
		
		repository.save(newProduct);
		
		return product;
	}

	@Override
	public void delete(UUID id) throws Exception  {
		Optional<Product> product = getById(id);
		
		repository.delete(product.get());
	}
}
