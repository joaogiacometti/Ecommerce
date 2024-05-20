package com.example.springboot.Core.Interfaces.Services;

import com.example.springboot.Business.Exceptions.RoleNotFoundException;
import com.example.springboot.Core.Models.Role;

public interface IRoleService {
    Role getByName(String roleName) throws RoleNotFoundException;
}
