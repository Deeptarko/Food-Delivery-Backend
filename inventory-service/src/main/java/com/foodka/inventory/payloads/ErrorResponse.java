package com.foodka.inventory.payloads;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse extends CustomResponse {

	public ErrorResponse(String message, boolean success, HttpStatus status) {
		super(message, success, status);
	}
}
