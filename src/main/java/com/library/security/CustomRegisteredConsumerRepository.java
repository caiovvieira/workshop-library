package com.library.security;

import com.library.entities.Consumer;
import com.library.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomRegisteredConsumerRepository implements RegisteredClientRepository {

    private final ConsumerService consumerService;
    private final TokenSettings tokenSettings;
    private final ClientSettings clientSettings;

    @Override
    public void save(RegisteredClient registeredClient) {}

    @Override
    public RegisteredClient findById(String id) {
        return null;
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
       Consumer consumer = consumerService.findByClientId(clientId);

       if (consumer == null){
           return null;
       }

       return RegisteredClient
               .withId(consumer.getId().toString())
               .clientId(consumer.getConsumerId())
               .clientSecret(consumer.getConsumerSecret())
               .redirectUri(consumer.getRedirectUri())
               .scope(consumer.getScope())
               .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
               .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
               .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
               .tokenSettings(tokenSettings)
               .clientSettings(clientSettings)
               .build();
    }
}

