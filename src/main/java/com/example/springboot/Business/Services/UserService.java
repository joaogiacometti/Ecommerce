package com.example.springboot.Business.Services;

import com.example.springboot.Business.Exceptions.RoleNotFoundException;
import com.example.springboot.Business.Exceptions.UserNotFoundException;
import com.example.springboot.Core.Dto.UserDto;
import com.example.springboot.Core.Interfaces.Services.IRoleService;
import com.example.springboot.Core.Models.Role;
import com.example.springboot.Data.Repositories.IUserRepository;
import com.example.springboot.Core.Interfaces.Services.IUserService;
import com.example.springboot.Core.Models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final IUserRepository repository;

    private final IRoleService roleService;

    public UserService(IUserRepository repository, IRoleService roleService) {
        this.repository = repository;
        this.roleService = roleService;
    }

    @Override
    public User create(UserDto user) throws RoleNotFoundException {
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());

        Role role = roleService.getByName("USER");
        User newUser = new User(user.login(), encryptedPassword, role);

        repository.save(newUser);

        return newUser;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getById(UUID id) throws Exception {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return user.get();
    }

    @Override
    public UserDetails getByLogin(String loginDto) {
        return repository.findByLogin(loginDto);
    }

    @Override
    public User update(UUID id, UserDto userDto) throws Exception {
        User user = getById(id);
        BeanUtils.copyProperties(userDto, user);

        repository.save(user);

        return user;
    }

    @Override
    public void delete(UUID id) throws Exception {
        User user = getById(id);

        repository.delete(user);
    }
}
