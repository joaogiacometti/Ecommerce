package com.example.springboot.Business.Services;

import com.example.springboot.Data.Repositories.IUserRepository;
import com.example.springboot.Core.Interfaces.Services.IAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements IAuthorizationService {
    private final IUserRepository repository;

    @Autowired
    public AuthorizationService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = repository.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
