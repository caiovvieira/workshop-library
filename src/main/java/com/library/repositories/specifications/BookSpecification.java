package com.library.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.library.entities.Book;

public class BookSpecification {

	public static Specification<Book> searchId(Integer id){
		return (root, query, cb) -> cb.equal(root.get("id"), id);
	};
	
	public static Specification<Book> searchTitle(String title){
		return (root, query, cb) -> cb.like(cb.upper(root.get("title")), "% "  + title.toUpperCase() + "%");
	};
	
	public static Specification<Book> searchPublicationDate(Integer publicationDate){
		return (root, query, cb) -> cb.equal(
				cb.function("year", String.class, root.get("publicationDate")), publicationDate.toString()
				
		);
	};
	
	public static Specification<Book> searchAuthorName(String authorName){
		return (root, query, cb) -> {
			return cb.like(cb.upper(root.get("author").get("name")), "%" + authorName.toUpperCase() + "%");
		};
	};
	

	
}

 