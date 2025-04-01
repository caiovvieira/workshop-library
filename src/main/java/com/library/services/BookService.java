package com.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.entities.Author;
import com.library.entities.Book;
import com.library.entities.enums.Gender;
import com.library.repositories.AuthorRepository;
import com.library.repositories.BookRepository;
import com.library.repositories.specifications.BookSpecification;

@Service
public class BookService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	public Page<Book> findAll(Integer page, Integer size) {
		Pageable pagination = PageRequest.of(page, size);
		return bookRepository.findAll(pagination);
	}

	public Book findById(Integer id) {
		Book book = bookRepository.findById(id).orElse(null);
		return book;
	}

	@Transactional
	public Book insert(Book book) {
		Author author = authorRepository.getReferenceById(book.getAuthor().getId());
		book.setAuthor(author);
		return bookRepository.save(book);
	}

	@Transactional
	public Book update(Integer id, Book book) {
		Book bookReference = bookRepository.getReferenceById(id);
		bookReference.setTitle(book.getTitle());
		bookReference.setPrice(book.getPrice());
		bookReference.setPublicationDate(book.getPublicationDate());
		bookReference.setGender(book.getGender());
		bookReference.setAuthor(book.getAuthor());
		return bookReference;
	}

	@Transactional
	public void delete(Integer id) {
		bookRepository.deleteById(id);
	}

	public Page<Book> search(
			Integer id, 
			String title, 
			Double price, 
			Integer publicationDate, 
			Gender gender,
			String authorName,
			Integer page, 
			Integer size
	) {

		Specification<Book> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(id != null) {
			specs = specs.and(BookSpecification.searchId(id));
		}
		
		if(title != null) {
			specs = specs.and(BookSpecification.searchTitle(title));
		}
		
		if(publicationDate != null) {
			specs = specs.and(BookSpecification.searchPublicationDate(publicationDate));
		}
		
		if(authorName != null) {
			specs = specs.and(BookSpecification.searchAuthorName(authorName));
		}
		
		Pageable pagination = PageRequest.of(page, size);
		
		return bookRepository.findAll(specs, pagination);
	}
	
}



