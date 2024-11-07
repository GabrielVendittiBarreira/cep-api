package com.example.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CepLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String response;
    private LocalDateTime timestamp;

    // Getters and Setters
}