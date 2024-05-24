package com.example.springboot.Core.Interfaces.Services;

import com.example.springboot.Core.Dto.TokenDto;
import com.example.springboot.Core.Dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.security.auth.login.LoginException;

public interface IAuthorizationService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
