package com.foodka.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.foodka.entity.UserCredential;

import lombok.ToString;

@ToString
public class CustomUserDetails implements UserDetails {

	private String username;
	private String password;
	private Integer userId;
	

	public CustomUserDetails(UserCredential userCredential) {

		this.username = userCredential.getName();
		this.password = userCredential.getPassword();
		this.userId=userCredential.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return username;
	}
	
	public Integer getUserId() {
		return userId;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
