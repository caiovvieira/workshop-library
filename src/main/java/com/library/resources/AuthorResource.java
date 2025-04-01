package com.library.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.library.entities.Book;
import com.library.entities.enums.Gender;
import com.library.resources.dto.AuthorDTO;
import com.library.resources.dto.BookSearchResultDTO;
import com.library.resources.mappers.AuthorMapper;
import com.library.services.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorResource {
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	AuthorMapper authorMapper;
	
	@GetMapping
	public ResponseEntity<Page<AuthorDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0")
			Integer page,
			@RequestParam(value = "size", defaultValue = "10")
			Integer size
	){
		Page<Author> authors = authorService.findAll(page, size);
		Page<AuthorDTO> result = authors.map(authorMapper::toDto);
		return ResponseEntity.ok().body(result);
		
	}
	
	@PostMapping
	public ResponseEntity<AuthorDTO> insert(@RequestBody @Valid AuthorDTO authorDto){
		
		Author author = authorMapper.toEntity(authorDto);
		authorService.insert(author);
		AuthorDTO authorConvertedToDto = authorMapper.toDto(author);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(author.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(authorConvertedToDto);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AuthorDTO> findById(@PathVariable Integer id){
		Author author = authorService.findById(id).orElse(null);
		AuthorDTO convertedAuthor = authorMapper.toDto(author);
		return ResponseEntity.ok().body(convertedAuthor);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AuthorDTO> update(@PathVariable Integer id, @RequestBody @Valid AuthorDTO authorDto){
		Author author = authorMapper.toEntity(authorDto);
		authorService.update(id, author);
		return ResponseEntity.ok().body(authorDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		authorService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "search")
	public ResponseEntity<Page<AuthorDTO>> search(
			@RequestParam(value = "id", required = false)
			Integer id, 
			@RequestParam(value = "name", required = false)
			String name, 
			@RequestParam(value = "nacionality", required = false)
			String nacionality, 
			@RequestParam(value = "dateBirth", required = false)
			Integer dateBirth, 
			@RequestParam(value = "page", defaultValue = "0")
			Integer page,
			@RequestParam(value = "size", defaultValue = "10")
			Integer size
	)
	{
		Page<Author> authors = authorService.search(id, name, nacionality, dateBirth, page, size);
		Page<AuthorDTO> result = authors.map(authorMapper::toDto);
		return ResponseEntity.ok().body(result);
	}
}
