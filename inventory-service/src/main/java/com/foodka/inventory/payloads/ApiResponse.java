package com.foodka.inventory.payloads;

import org.springframework.http.HttpStatus;

import com.foodka.inventory.dtos.InventoryDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse extends CustomResponse {

	private InventoryDto food;

	public ApiResponse(InventoryDto inventoryDto, String message, Boolean success, HttpStatus status) {
		super(message, success, status);
		this.food = inventoryDto;
	}
}
