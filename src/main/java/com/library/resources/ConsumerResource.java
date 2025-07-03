package com.library.resources;

import com.library.entities.Consumer;
import com.library.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/consumer")
@PreAuthorize("hasRole('OPERATOR')")
public class ConsumerResource {

    @Autowired
    ConsumerService consumerService;

    @PostMapping
    public ResponseEntity<Consumer> insert(@RequestBody Consumer consumer){
        Consumer result = consumerService.insert(consumer);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
