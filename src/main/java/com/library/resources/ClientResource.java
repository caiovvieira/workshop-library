package com.library.resources;

import com.library.entities.Client;
import com.library.resources.dto.ClientSearchResultDTO;
import com.library.resources.mappers.ClientMapper;
import com.library.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.resources.dto.ClientDTO;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	ClientMapper clientMapper;
	
	@GetMapping
	public ResponseEntity<Page<ClientSearchResultDTO>> findAll(
		@RequestParam(value = "page", defaultValue = "0")
		Integer page,
		@RequestParam(value = "size", defaultValue = "10")
		Integer size
	)
	{
		Page<Client> clients = clientService.findAll(page, size);
		Page<ClientSearchResultDTO> result = clients.map(clientMapper::toDto);
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ClientDTO clientDTO){
		Client client = clientMapper.toEntity(clientDTO);
		clientService.insert(client);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}
}



