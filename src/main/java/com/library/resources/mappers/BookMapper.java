package com.library.resources.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.library.entities.Book;
import com.library.repositories.AuthorRepository;
import com.library.resources.dto.BookDTO;
import com.library.resources.dto.BookSearchResultDTO;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public abstract class BookMapper {
	
	@Autowired
	AuthorRepository authorRepository;

	@Mapping(target = "author", expression = "java(authorRepository.findById(bookDto.getAuthorId()).orElse(null))")
	public abstract Book toEntity(BookDTO bookDto);

	
	public abstract BookSearchResultDTO toDto(Book book);
	
}


