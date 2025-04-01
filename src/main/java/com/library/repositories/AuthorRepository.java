package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.library.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>,  JpaSpecificationExecutor<Author>{
	
}
