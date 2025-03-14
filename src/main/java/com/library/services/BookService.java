package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.entities.Author;
import com.library.entities.Book;
import com.library.repositories.AuthorRepository;
import com.library.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAll() {
		return bookRepository.findAll();
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
		return bookReference;
	}
	
	@Transactional
	public void delete(Integer id) {
		bookRepository.deleteById(id);
	}
}
