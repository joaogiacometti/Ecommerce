package com.example.springboot.Core.Dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDto(
		@NotBlank String name, 
		@NotNull BigDecimal value,
		@NotBlank String image
){}
