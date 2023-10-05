package com.foodka.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodka.config.CustomUserDetails;
import com.foodka.entity.UserCredential;
import com.foodka.repository.UserCredentialRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private  UserCredentialRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserCredential> credential = userRepository.findByName(username);
        
		return credential.map(CustomUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

	}

}
