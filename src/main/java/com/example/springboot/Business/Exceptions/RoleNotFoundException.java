package com.example.springboot.Business.Exceptions;

public class RoleNotFoundException extends Exception {
	public RoleNotFoundException(String message) {
        super(message);
    }
}
