package com.library.security;

import com.library.entities.Client;
import com.library.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String passwordEntered = authentication.getCredentials().toString();

        Client clientFounded = clientService.getByLogin(login);

        if (clientFounded == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String encryptedPassword = clientFounded.getPassword();

        boolean isCorrectPassword = passwordEncoder.matches(passwordEntered, encryptedPassword);

        if (isCorrectPassword) {
            return new CustomAuthentication(clientFounded);
        }

        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

}

