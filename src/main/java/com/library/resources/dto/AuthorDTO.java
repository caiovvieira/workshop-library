package com.library.resources.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Component
public class AuthorDTO{
		
		@NotBlank(message = "required field")
		private String name;
		
		@NotBlank(message = "required field")
		private String nationality;
		
		@NotNull(message = "required field")
		@Past
		private LocalDate dateBirth;
		
		public AuthorDTO() {
			
		}

		public AuthorDTO(String name, String nationality, LocalDate dateBirth) {
			this.name = name;
			this.nationality = nationality;
			this.dateBirth = dateBirth;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNationality() {
			return nationality;
		}

		public void setNationality(String nationality) {
			this.nationality = nationality;
		}

		public LocalDate getDateBirth() {
			return dateBirth;
		}

		public void setDateBirth(LocalDate dateBirth) {
			this.dateBirth = dateBirth;
		}
}

