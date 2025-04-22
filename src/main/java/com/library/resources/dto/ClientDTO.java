package com.library.resources.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ClientDTO {

	@Email(message = "required field")
	private String email;
	@NotBlank(message = "required field")
	private String password;
	@NotNull(message = "required field")
	private String role;
	
	public ClientDTO() {

	}
	
	public ClientDTO(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
