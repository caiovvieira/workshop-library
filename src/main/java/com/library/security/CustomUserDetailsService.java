package com.library.security;

import com.library.entities.Client;
import com.library.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientService.getByLogin(email);

        if (client == null) {
            throw new UsernameNotFoundException("client not found");
        }

        return User.builder()
                .username(client.getEmail())
                .password(client.getPassword())
                .roles(client.getRole())
                .build();

    }
}


