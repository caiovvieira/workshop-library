package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.entities.Author;
import com.library.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	public List<Author> findAll() {
		return authorRepository.findAll();
	}
	
	public Author findById(Integer id) {
		Optional<Author> author = authorRepository.findById(id);
		return author.get();
	}
	
	@Transactional
	public Author insert(Author author) {
		return authorRepository.save(author);
	}
	

	public List<Author> find(String name, String nationality) {
		if(name != null && nationality != null) {
			return authorRepository.findByNameAndNationality(name, nationality);
		}
		
		if(name != null) {
			return authorRepository.findByName(name);
		}
		
		if(nationality != null) {
			return authorRepository.findByNationality(nationality);
		}
		
		return authorRepository.findAll();
	}
	
	
	public List<Author> findByExample(String name, String nationality) {
		Author author = new Author();
		author.setName(name);
		author.setNationality(nationality);
		
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Author> authorExample = Example.of(author, matcher);
		
		return authorRepository.findAll(authorExample);
	}
	
	
	@Transactional
	public Author update(Integer id, Author author) {
		Author authorReference = authorRepository.getReferenceById(id);
		authorReference.setName(author.getName());
		authorReference.setNationality(author.getNationality());
		authorReference.setDateBirth(author.getDateBirth());
		return authorReference;
	}
	
	@Transactional
	public void delete(Integer id) {
		authorRepository.deleteById(id);
	}
	
}
