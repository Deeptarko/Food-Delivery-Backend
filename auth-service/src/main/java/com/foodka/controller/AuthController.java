package com.foodka.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodka.dto.AuthRequest;
import com.foodka.dto.AuthResponse;
import com.foodka.dto.UserInfo;
import com.foodka.entity.UserCredential;
import com.foodka.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService service;

	private final AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public String addNewUser(@RequestBody UserCredential user) {
		return service.saveUser(user);
	}

	@PostMapping("/token")
	public AuthResponse getToken(@RequestBody AuthRequest authRequest) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authenticate.isAuthenticated()) {

			UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

			String token = service.generateToken(authRequest.getUsername());
			AuthResponse response = new AuthResponse();
			UserInfo userInfo = new UserInfo();
			userInfo.setUsername(userDetails.getUsername());
			response.setToken(token);
			response.setUser(userInfo);
			return response;

		} else {
			throw new RuntimeException("invalid access");
		}

	}

	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		service.validateToken(token);
		return "Token is valid";
	}
}
