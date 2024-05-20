package com.example.springboot.Core.Interfaces.Services;

import com.example.springboot.Core.Models.User;

public interface ITokenService {
    String generateToken(User user);

    String getUserByToken(String token);
}
