package com.example.springboot.Core.Dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDto(@NotBlank String login, @NotBlank String token, java.util.Set<com.example.springboot.Core.Models.Role> roles) {
}
