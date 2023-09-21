package com.foodka.inventory.exceptions;

public class InventoryItemNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InventoryItemNotFoundException(String message) {
		super(message);
	}

}
