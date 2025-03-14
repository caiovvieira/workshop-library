package com.library.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.library.entities.Author;
import com.library.resources.dto.AuthorDTO;
import com.library.services.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorResource {
	
	@Autowired
	AuthorService authorService;
	
	@GetMapping
	public ResponseEntity<List<AuthorDTO>> findAll(
			@RequestParam(value="name", required = false) String name,
			@RequestParam(value="nationality", required = false) String nationality
		){
		
		List<Author> listOfAuthors = authorService.findByExample(name, nationality);
		List<AuthorDTO> listOfAuthorsDto = listOfAuthors.stream()
				.map(author -> AuthorDTO.convertToAuthorDto(author))
				.collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listOfAuthorsDto);
	}
	
	@PostMapping
	public ResponseEntity<AuthorDTO> insert(@RequestBody @Valid AuthorDTO authorDto){
		Author author = authorService.insert(authorDto.convertToAuthor());
		AuthorDTO convertedAuthor = AuthorDTO.convertToAuthorDto(author);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(author.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(convertedAuthor);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AuthorDTO> findById(@PathVariable Integer id){
		Author author = authorService.findById(id);
		AuthorDTO convertedAuthor = AuthorDTO.convertToAuthorDto(author);
		return ResponseEntity.ok().body(convertedAuthor);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AuthorDTO> update(@PathVariable Integer id, @RequestBody @Valid AuthorDTO authorDto){
		Author author = authorDto.convertToAuthor();
		authorService.update(id, author);
		return ResponseEntity.ok().body(authorDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		authorService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
