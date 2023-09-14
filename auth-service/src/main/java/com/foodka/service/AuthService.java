package com.foodka.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foodka.entity.UserCredential;
import com.foodka.repository.UserCredentialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserCredentialRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	public String saveUser(UserCredential credential) {
		
		credential.setPassword(passwordEncoder.encode(credential.getPassword()));
		userRepository.save(credential);
		return "User added to the system";
	}
	
	public String generateToken(String username){
		return jwtService.generateToken(username);
	}
	
	public void validateToken(String token){
		jwtService.validateToken(token);
	}
	
	
}
