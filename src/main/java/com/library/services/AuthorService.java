package com.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.entities.Author;
import com.library.repositories.AuthorRepository;
import com.library.repositories.specifications.AuthorSpecification;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;
	
	
	
	public Page<Author> findAll(Integer page, Integer size) {
		Pageable pagination = PageRequest.of(page, size);
		return authorRepository.findAll(pagination);
		
	}

	
	public Optional<Author> findById(Integer id) {
		return authorRepository.findById(id);
	}
	
	
	@Transactional
	public Author insert(Author author) {
		return authorRepository.save(author);
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
	
	public Page<Author> search(
			Integer id, 
			String name, 
			String nacionality, 
			Integer dateBirth,
			Integer page, 
			Integer size
	) {

		Specification<Author> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(id != null) {
			specs = specs.and(AuthorSpecification.searchId(id));
		}
		
		if(name != null) {
			specs = specs.and(AuthorSpecification.searchName(name));
		}
		
		if(nacionality != null) {
			specs = specs.and(AuthorSpecification.searchNationality(nacionality));
		}
		
		if(dateBirth != null) {
			specs = specs.and(AuthorSpecification.searchDateBirth(dateBirth));
		}
		
		Pageable pagination = PageRequest.of(page, size);
		
		return authorRepository.findAll(specs, pagination);
	}
}
