package com.example.springboot.Business.Exceptions;

public class UserNotFoundException extends Exception {
	public UserNotFoundException(String message) {
        super(message);
    }
}
