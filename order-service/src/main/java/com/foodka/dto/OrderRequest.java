package com.foodka.dto;

import com.foodka.entity.OrderItem;
import com.foodka.entity.OrderStatus;

import lombok.Data;

@Data
public class OrderRequest {
  
	private Integer [] ids;
	
}
