package com.library.resources.dto;

import java.time.LocalDate;

import com.library.entities.Author;
import com.library.entities.Book;
import com.library.entities.enums.Gender;

public record BookCreateDTO(String title, Double price, LocalDate publicationDate, Gender gender, Integer author_id) {


}
