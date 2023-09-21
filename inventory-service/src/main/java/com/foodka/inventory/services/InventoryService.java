package com.foodka.inventory.services;

import java.util.List;
import com.foodka.inventory.dtos.InventoryDto;

public interface InventoryService {

	// add new food
	InventoryDto addFood(InventoryDto inventoryDto);

	// update existing food
	InventoryDto updatedExistingFood(Long id, InventoryDto inventoryDto);

	// delete food
	void deleteInventory(Long id);

	// get single food by id
	InventoryDto getFoodDetailsById(Long id);

	// get all foods
	List<InventoryDto> getAllFoodDetails();

}
