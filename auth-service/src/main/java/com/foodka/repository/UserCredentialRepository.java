package com.foodka.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodka.entity.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
	
	Optional<UserCredential>findByName(String name);
}
