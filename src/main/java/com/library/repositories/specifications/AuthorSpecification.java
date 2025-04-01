package com.library.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.library.entities.Author;
import com.library.entities.Book;

public class AuthorSpecification {

	public static Specification<Author> searchId(Integer id){
		return (root, query, cb) -> cb.equal(root.get("id"), id);
	};
	
	public static Specification<Author> searchName(String name){
		return (root, query, cb) -> cb.like(cb.upper(root.get("name")), "% "  + name.toUpperCase() + "%");
	};
	
	public static Specification<Author> searchNationality(String nationality){
		return (root, query, cb) -> cb.like(cb.upper(root.get("nationality")), "% "  + nationality.toUpperCase() + "%");
	};
	
	public static Specification<Author> searchDateBirth(Integer dateBirth){
		return (root, query, cb) -> cb.equal(
				cb.function("year", String.class, root.get("dateBirth")), dateBirth.toString()
				
		);
	};
	
}

 