package com.library.resources.mappers;

import com.library.entities.Client;
import com.library.resources.dto.ClientSearchResultDTO;
import org.mapstruct.Mapper;

import com.library.resources.dto.ClientDTO;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	
	Client toEntity(ClientDTO clientDto);
	
	ClientSearchResultDTO toDto(Client client);

}
