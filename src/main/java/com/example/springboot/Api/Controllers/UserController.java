package com.example.springboot.Api.Controllers;

import com.example.springboot.Business.Exceptions.ProductNotFoundException;
import com.example.springboot.Business.Exceptions.RoleNotFoundException;
import com.example.springboot.Core.Dto.ProductDto;
import com.example.springboot.Core.Dto.UserDto;
import com.example.springboot.Core.Interfaces.Services.IUserService;
import com.example.springboot.Core.Models.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService service;

    public UserController(IUserService userService) {
        this.service = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody @Valid UserDto userDto) throws RoleNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(userDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value="id") UUID id) throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
        }catch(ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(
            @PathVariable(value="id") UUID id,
            @RequestBody @Valid UserDto userDto ) throws Exception{
        try {
            var newProduct = service.update(id, userDto);

            return ResponseEntity.status(HttpStatus.OK).body(newProduct);
        }catch(ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value="id") UUID id) throws Exception{
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted");
        }catch(ProductNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
