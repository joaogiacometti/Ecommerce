package com.example.springboot.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springboot.dto.ProductDto;
import com.example.springboot.models.Product;

import jakarta.validation.Valid;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{

}
