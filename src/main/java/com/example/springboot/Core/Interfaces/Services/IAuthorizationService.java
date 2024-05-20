package com.example.springboot.Core.Interfaces.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthorizationService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
