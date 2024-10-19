package com.example.demo.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity public class contrat {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime dateExpiration;
    private LocalDateTime dateValidation;

}
