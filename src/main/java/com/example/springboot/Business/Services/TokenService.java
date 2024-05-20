package com.example.springboot.Business.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springboot.Core.Interfaces.Services.ITokenService;
import com.example.springboot.Core.Models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService implements ITokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    @Override
    public String generateToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        }catch(JWTCreationException ex){
            throw new RuntimeException("Error creating token", ex);
        }
    }

    @Override
    public String getUserByToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException ex){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
