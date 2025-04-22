package com.library.services;

import com.library.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public Page<Client> findAll(Integer page, Integer size) {
		Pageable pagination = PageRequest.of(page, size);
		return clientRepository.findAll(pagination);
	}

	public Client getByLogin(String email){
		return clientRepository.findByEmail(email);
	}

	public void insert(Client client) {
		String password = client.getPassword();
		client.setPassword(passwordEncoder.encode(password));
		clientRepository.save(client);
	}
}


