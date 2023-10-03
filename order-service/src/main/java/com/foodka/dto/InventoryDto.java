package com.foodka.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private Boolean vegeterian;
	private Date createdAt;
	private Integer quantity;
	private Date updatedAt;
	
}
