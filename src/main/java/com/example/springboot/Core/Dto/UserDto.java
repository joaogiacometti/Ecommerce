package com.example.springboot.Core.Dto;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank String login,
        @NotBlank String password) {
}
