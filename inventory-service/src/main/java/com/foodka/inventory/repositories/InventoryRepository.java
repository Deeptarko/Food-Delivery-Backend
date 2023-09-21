package com.foodka.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodka.inventory.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
