package com.library.services;

import com.library.entities.Consumer;
import com.library.repositories.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    ConsumerRepository consumerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Consumer insert(Consumer consumer ){
       consumer.setConsumerSecret(passwordEncoder.encode(consumer.getConsumerSecret()));
       return consumerRepository.save(consumer);
    }

    public Consumer findByClientId(String clientId){
        return consumerRepository.findByConsumerId(clientId);
    }
}
