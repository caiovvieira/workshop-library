package com.library.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String consumerId;
    private String consumerSecret;
    private String redirectUri;
    private String scope;

}
