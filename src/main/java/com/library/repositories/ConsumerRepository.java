package com.library.repositories;

import com.library.entities.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsumerRepository extends JpaRepository<Consumer, UUID> {

    Consumer findByConsumerId(String clientId);
}
