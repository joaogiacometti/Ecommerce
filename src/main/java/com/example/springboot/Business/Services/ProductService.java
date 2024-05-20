package com.example.springboot.Business.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.springboot.Business.Exceptions.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.Core.Dto.ProductDto;
import com.example.springboot.Core.Interfaces.Services.IProductService;
import com.example.springboot.Core.Models.Product;
import com.example.springboot.Data.Repositories.IProductRepository;

@Service
public class ProductService implements IProductService{
	private final IProductRepository repository;

	@Autowired
	public ProductService(IProductRepository repository) {
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
	public Product getById(UUID id) throws Exception {
		Optional<Product> product = repository.findById(id);
		
		if(product.isEmpty()) {
			throw new ProductNotFoundException("Product not found");
		}
		
		return product.get();
	}

	@Override
	public Product update(UUID id, ProductDto productDto) throws Exception {
		Product product = getById(id);

        BeanUtils.copyProperties(productDto, product);
		
		repository.save(product);
		
		return product;
	}

	@Override
	public void delete(UUID id) throws Exception  {
		
		repository.delete(getById(id));
	}
}
