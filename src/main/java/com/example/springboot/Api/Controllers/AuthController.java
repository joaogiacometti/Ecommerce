package com.example.springboot.Api.Controllers;

import com.example.springboot.Core.Dto.TokenDto;
import com.example.springboot.Core.Dto.UserDto;
import com.example.springboot.Core.Dto.LoginResponseDto;
import com.example.springboot.Core.Interfaces.Services.IAuthorizationService;
import com.example.springboot.Core.Interfaces.Services.ITokenService;
import com.example.springboot.Core.Models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthenticationManager authManager;
    private final ITokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authManager, ITokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserDto userDto) {
        try{
            var userPassword = new UsernamePasswordAuthenticationToken(userDto.login(), userDto.password());
            var auth = authManager.authenticate(userPassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            var roles = ((User) auth.getPrincipal()).getRoles();

            TokenDto tokenResponse = new TokenDto(userDto.login(), token, roles);
            return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
        }catch(AuthenticationException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex);
        }
    }
}
