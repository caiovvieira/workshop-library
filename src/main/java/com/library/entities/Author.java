package com.library.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String nationality;
	private LocalDate dateBirth;
	
	@JsonIgnore
	@OneToMany(mappedBy = "author")
	private List<Book> books = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
}
