package com.foodka.inventory.dtos;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
