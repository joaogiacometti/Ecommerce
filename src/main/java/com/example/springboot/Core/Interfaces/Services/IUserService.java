package com.example.springboot.Core.Interfaces.Services;


import com.example.springboot.Business.Exceptions.RoleNotFoundException;
import com.example.springboot.Core.Dto.UserDto;
import com.example.springboot.Core.Models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    User create(UserDto user) throws RoleNotFoundException;

    List<User> getAll();

    User getById (UUID id) throws Exception;

    User update (UUID id, UserDto userDto) throws Exception;

    void delete (UUID id) throws Exception;

    UserDetails getByLogin(String login);
}
