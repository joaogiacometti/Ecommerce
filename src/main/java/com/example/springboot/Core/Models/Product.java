package com.example.springboot.Core.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="PRODUCTS")
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id;
	
	private String name;
	
	private BigDecimal value;

	private String image;
}
