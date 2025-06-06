package com.library.resources.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.library.entities.enums.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Component
public class BookDTO{
		@NotBlank(message = "required field")
		private String title;
		@NotNull(message = "required field")
		private Double price;
		@NotNull(message = "required field")
		@Past
		private LocalDate publicationDate;
		@NotBlank(message = "required field")
		private Gender gender;
		@NotNull(message = "required field")
		private Integer authorId;
		
		public BookDTO() {}
		
		public BookDTO(String title, Double price, LocalDate publicationDate, Gender gender, Integer author_id) {
			this.title = title;
			this.price = price;
			this.publicationDate = publicationDate;
			this.gender = gender;
			this.authorId = author_id;
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

		public Integer getAuthorId() {
			return authorId;
		}

		public void setAuthorId(Integer authorId) {
			this.authorId = authorId;
		}
		
}



