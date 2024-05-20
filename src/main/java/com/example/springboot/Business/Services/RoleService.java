package com.example.springboot.Business.Services;

import com.example.springboot.Business.Exceptions.RoleNotFoundException;
import com.example.springboot.Core.Interfaces.Services.IRoleService;
import com.example.springboot.Core.Models.Role;
import com.example.springboot.Data.Repositories.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    private final IRoleRepository repository;

    @Autowired
    public RoleService(IRoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role getByName(String roleName) throws RoleNotFoundException {
        Optional<Role> role = repository.findByName(roleName);

        if(role.isEmpty()) {
            throw new RoleNotFoundException("Product not found");
        }

        return role.get();
    }
}
