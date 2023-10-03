package com.foodka.inventory.services.impls;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.foodka.inventory.dtos.InventoryDto;
import com.foodka.inventory.entities.Inventory;
import com.foodka.inventory.exceptions.InventoryItemNotFoundException;
import com.foodka.inventory.repositories.InventoryRepository;
import com.foodka.inventory.services.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private InventoryRepository inventoryRepository;

	// Add New Food
	@Override
	public InventoryDto addFood(InventoryDto inventoryDto) {

		Inventory inventory = this.modelMapper.map(inventoryDto, Inventory.class);
		inventory.setCreatedAt(new Date());

		// saving food
		Inventory savedInventory = this.inventoryRepository.save(inventory);
		// mapping inventory class to inventoryDto class
		InventoryDto savedInventoryDto = modelMapper.map(savedInventory, InventoryDto.class);
		return savedInventoryDto;
	}

	@Override
	public InventoryDto updatedExistingFood(Long id, InventoryDto inventoryDto) {

		// check inventory id is valid or not
		Inventory inventory = this.inventoryRepository.findById(id)
				.orElseThrow(() -> new InventoryItemNotFoundException("Inventory Item with id - " + id + " not found!!!"));

		inventory.setName(inventoryDto.getName());
		inventory.setDescription(inventoryDto.getDescription());
		inventory.setPrice(inventoryDto.getPrice());
		inventory.setVegetarian(inventoryDto.getVegeterian());
		inventory.setUpdatedAt(new Date());

		Inventory updatedInventory = this.inventoryRepository.save(inventory);
		InventoryDto updatedInventoryDto = this.modelMapper.map(updatedInventory, InventoryDto.class);
		return updatedInventoryDto;
	}

	@Override
	public void deleteInventory(Long id) {

		// check inventory id is valid or not
		Inventory inventory = this.inventoryRepository.findById(id)
				.orElseThrow(() -> new InventoryItemNotFoundException("Inventory Item with id - " + id + " not found!!!"));

		this.inventoryRepository.delete(inventory);
	}

	@Override
	public InventoryDto getFoodDetailsById(Long id) {
		// check inventory id is valid or not
		Inventory inventory = this.inventoryRepository.findById(id)
				.orElseThrow(() -> new InventoryItemNotFoundException("Inventory Item with id - " + id + " not found!!!"));

		InventoryDto inventoryDto = this.modelMapper.map(inventory, InventoryDto.class);
		return inventoryDto;
	}

	@Override
	public List<InventoryDto> getAllFoodDetails() {

		List<InventoryDto> inventoryDtos = this.inventoryRepository.findAll().stream()
				.map(inventory -> this.modelMapper.map(inventory, InventoryDto.class)).collect(Collectors.toList());
		return inventoryDtos;
	}
	
	@Override
	public List<InventoryDto> checkQuantity(List<Long>itemIds) {
		
		List<Inventory>items=inventoryRepository.findAllById(itemIds);
		
		List<Inventory>availableItems=items.stream()
				.filter(item->item.getQuantity()>1)
				.collect(Collectors.toList());
		
	    if(availableItems.size()!=itemIds.size()) {
	    	throw new InventoryItemNotFoundException("Some of the items are not available");
	    }
		
		List<InventoryDto> inventoryDtos = availableItems.stream()
				.map(inventory -> this.modelMapper.map(inventory, InventoryDto.class)).collect(Collectors.toList());
		return inventoryDtos;
	}

}
