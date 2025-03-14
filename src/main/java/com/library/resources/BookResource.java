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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.library.entities.Book;
import com.library.resources.dto.BookCreateDTO;
import com.library.resources.dto.BookDTO;
import com.library.services.BookService;

@RestController
@RequestMapping("/books")
public class BookResource {
	@Autowired
	BookService bookService;
	
	@GetMapping
	public ResponseEntity<List<BookDTO>> findAll(){
		List<Book> listOfBooks = bookService.findAll();
		List<BookDTO> listOfBooksDto = listOfBooks.stream()
				.map(book -> BookDTO.convertToBookDto(book))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listOfBooksDto);
	}
	
	@PostMapping
	public ResponseEntity<BookDTO> insert(@RequestBody BookCreateDTO bookCreateDTO){
		Book book = bookService.findById(bookCreateDTO.author_id());
		Book book2 = bookService.insert(book);
		BookDTO convertedBook = BookDTO.convertToBookDto(book2);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(book2.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(convertedBook);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BookDTO> findById(@PathVariable Integer id){
		Book book = bookService.findById(id);
		BookDTO convertedBook = BookDTO.convertToBookDto(book);
		return ResponseEntity.ok().body(convertedBook);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<BookDTO> update(@PathVariable Integer id, @RequestBody BookCreateDTO bookCreateDTO){
		Book book = bookService.findById(bookCreateDTO.author_id());
		BookDTO convertedBook = BookDTO.convertToBookDto(book);
		bookService.update(id, book);
		return ResponseEntity.ok().body(convertedBook);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
