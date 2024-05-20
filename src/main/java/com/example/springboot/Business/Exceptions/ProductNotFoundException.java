package com.example.springboot.Business.Exceptions;

public class ProductNotFoundException extends Exception {
	public ProductNotFoundException(String message) {
        super(message);
    }
}
