package com.foodka.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

//	@ExceptionHandler(InventoryItemNotFoundException.class)
//	public ResponseEntity<ErrorResponse> inventoryItemNoutFoundExceptionHandler(InventoryItemNotFoundException e) {
//
//		String message = e.getMessage();
//		ErrorResponse res = new ErrorResponse(message, false, HttpStatus.NOT_FOUND);
//		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
//	}
}
