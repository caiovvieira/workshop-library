package com.library.resources.dto;

import java.time.LocalDate;

import com.library.entities.Book;
import com.library.entities.enums.Gender;

public record BookDTO(String title, Double price, LocalDate publicationDate, Gender gender, AuthorDTO author) {

	public static BookDTO convertToBookDto(Book book) {
		return new BookDTO(book.getTitle(), book.getPrice(), book.getPublicationDate(), book.getGender(), AuthorDTO.convertToAuthorDto(book.getAuthor()));
	}
	

}
