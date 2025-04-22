package com.library.resources.dto;

import org.springframework.stereotype.Component;

@Component
public class ClientSearchResultDTO {
	
	private String email;
	private String role;
	
	public ClientSearchResultDTO() {

	}
	
	public ClientSearchResultDTO(String email, String passowrd, String role) {
		this.email = email;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
