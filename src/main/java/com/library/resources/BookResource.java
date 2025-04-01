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

import com.library.entities.Book;
import com.library.entities.enums.Gender;
import com.library.resources.dto.BookDTO;
import com.library.resources.dto.BookSearchResultDTO;
import com.library.resources.mappers.BookMapper;
import com.library.services.BookService;

@RestController
@RequestMapping("/books")
public class BookResource {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	BookMapper bookMapper;
	
	@GetMapping
	public ResponseEntity<Page<BookSearchResultDTO>> findAll(
			@RequestParam(value = "page", defaultValue = "0")
			Integer page,
			@RequestParam(value = "size", defaultValue = "10")
			Integer size
	){
		Page<Book> books = bookService.findAll(page, size);
		Page<BookSearchResultDTO> result = books.map(bookMapper::toDto);
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping
	public ResponseEntity<BookSearchResultDTO> insert(@RequestBody BookDTO bookDto){
		Book book = bookService.insert(bookMapper.toEntity(bookDto));
		BookSearchResultDTO result = bookMapper.toDto(book);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(book.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(result);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BookSearchResultDTO> findById(@PathVariable Integer id){
		Book book = bookService.findById(id);
		BookSearchResultDTO result = bookMapper.toDto(book);
		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<BookSearchResultDTO> update(@PathVariable Integer id, @RequestBody BookDTO bookDto){
		Book book = bookMapper.toEntity(bookDto);
		BookSearchResultDTO result = bookMapper.toDto(book);
		bookService.update(id, book);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "search")
	public ResponseEntity<Page<BookSearchResultDTO>> search(
			@RequestParam(value = "id", required = false)
			Integer id, 
			@RequestParam(value = "title", required = false)
			String title, 
			@RequestParam(value = "price", required = false)
			Double price, 
			@RequestParam(value = "publicationDate", required = false)
			Integer publicationDate, 
			@RequestParam(value = "gender", required = false)
			Gender gender,
			@RequestParam(value = "authorName", required = false)
			String authorName,
			@RequestParam(value = "page", defaultValue = "0")
			Integer page,
			@RequestParam(value = "size", defaultValue = "10")
			Integer size
	)
	{
		Page<Book> books = bookService.search(id, title, price, publicationDate, gender, authorName, page, size);
		Page<BookSearchResultDTO> result = books.map(bookMapper::toDto);
		return ResponseEntity.ok().body(result);
	}
	
}



