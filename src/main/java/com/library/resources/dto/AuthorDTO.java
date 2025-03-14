package com.library.resources.dto;

import java.time.LocalDate;

import com.library.entities.Author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record AuthorDTO(
		
		@NotBlank(message = "required field")
		String name, 
		
		@NotBlank(message = "required field")
		String nationality,
		
		@NotNull(message = "required field")
		@Past
		LocalDate dateBirth
) {
	
	public static AuthorDTO convertToAuthorDto(Author author) {
		return new AuthorDTO(author.getName(), author.getNationality(), author.getDateBirth());
	}
	
	public Author convertToAuthor() {
		Author author = new Author();
		author.setName(name);
		author.setNationality(nationality);
		author.setDateBirth(dateBirth);
		return author;
	}
}

