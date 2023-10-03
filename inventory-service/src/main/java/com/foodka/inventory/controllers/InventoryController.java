package com.foodka.inventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodka.inventory.dtos.InventoryDto;
import com.foodka.inventory.payloads.ApiResponse;
import com.foodka.inventory.payloads.CustomResponse;
import com.foodka.inventory.services.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	// Add new Food
	@PostMapping("/")
	public ResponseEntity<ApiResponse> addFood(@RequestBody InventoryDto inventoryDto) {

		InventoryDto savedInventoryDto = this.inventoryService.addFood(inventoryDto);
		ApiResponse response = new ApiResponse(savedInventoryDto, "Food added successfully!!!", true,
				HttpStatus.CREATED);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
	}

	// Update Existing Food
	@PutMapping("/{inventoryId}")
	public ResponseEntity<ApiResponse> updateExistingFoodDetails(@PathVariable Long inventoryId,
			@RequestBody InventoryDto inventoryDto) {

		InventoryDto updatedInventory = this.inventoryService.updatedExistingFood(inventoryId, inventoryDto);
		ApiResponse response = new ApiResponse(updatedInventory, "Inventory updated successfully", true, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	// Delete Inventory By Id
	@DeleteMapping("/{inventoryId}")
	public ResponseEntity<CustomResponse> deleteInventoryById(@PathVariable Long inventoryId) {

		this.inventoryService.deleteInventory(inventoryId);
		CustomResponse response = new CustomResponse("Inventory with id - " + inventoryId + " deleted successfully!!!",
				true, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	// get inventory by id
	@GetMapping("/{inventoryId}")
	public ResponseEntity<ApiResponse> getInventoryById(@PathVariable Long inventoryId) {

		InventoryDto inventoryDto = this.inventoryService.getFoodDetailsById(inventoryId);
		ApiResponse response = new ApiResponse(inventoryDto,
				"Inventory with id - " + inventoryId + " fetched successfully!!!", true, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/")
	public ResponseEntity<List<InventoryDto>> getAllInventoryDetails() {

		List<InventoryDto> inventoryDtos = this.inventoryService.getAllFoodDetails();
		return ResponseEntity.ok(inventoryDtos);
	}
	
	
	//Check if all the items with the given ids are available in the inventory
	@GetMapping("/checkQuantity/{itemIds}")
	public ResponseEntity<List<InventoryDto>> checkInventory(@PathVariable List<Long> itemIds) {

		List<InventoryDto> inventoryDtos = this.inventoryService.checkQuantity(itemIds);
		return ResponseEntity.ok(inventoryDtos);
	}

}
