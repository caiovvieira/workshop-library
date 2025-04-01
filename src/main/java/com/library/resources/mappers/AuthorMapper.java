package com.library.resources.mappers;

import org.mapstruct.Mapper;

import com.library.entities.Author;
import com.library.resources.dto.AuthorDTO;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
	
	Author toEntity(AuthorDTO authorDto);
	
	AuthorDTO toDto(Author author);

}

