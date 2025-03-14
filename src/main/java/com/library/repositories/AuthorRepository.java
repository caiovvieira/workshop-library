package com.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
	List<Author> findByNameAndNationality(String name, String nationality);
	List<Author> findByName(String name);
	List<Author> findByNationality(String nationality);
}
