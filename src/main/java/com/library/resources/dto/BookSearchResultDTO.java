package com.library.resources.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.library.entities.enums.Gender;

@Component
public class BookSearchResultDTO{
		private String title; 
		private Double price; 
		private LocalDate publicationDate; 
		private Gender gender; 
		private AuthorDTO author;
		
		public BookSearchResultDTO() {
			
		}

		public BookSearchResultDTO(
				String title, 
				Double price, 
				LocalDate publicationDate, 
				Gender gender, 
				AuthorDTO author
		) {
	
			this.title = title;
			this.price = price;
			this.publicationDate = publicationDate;
			this.gender = gender;
			this.author = author;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public LocalDate getPublicationDate() {
			return publicationDate;
		}

		public void setPublicationDate(LocalDate publicationDate) {
			this.publicationDate = publicationDate;
		}

		public Gender getGender() {
			return gender;
		}

		public void setGender(Gender gender) {
			this.gender = gender;
		}

		public AuthorDTO getAuthor() {
			return author;
		}

		public void setAuthor(AuthorDTO author) {
			this.author = author;
		}
		
		
		
}

