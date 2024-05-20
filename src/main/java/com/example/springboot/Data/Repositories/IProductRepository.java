package com.example.springboot.Data.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.Core.Models.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, UUID>{

}
