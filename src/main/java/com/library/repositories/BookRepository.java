package com.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.library.entities.Author;
import com.library.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book>{


}
