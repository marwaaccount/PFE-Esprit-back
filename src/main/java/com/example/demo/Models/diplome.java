package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class diplome {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String faculte;
    private String intitule;
    private LocalDateTime date;


    @ManyToOne
    @JsonBackReference (value="candidat-diplome")
    @JoinColumn(name = "candidat_id") // Colonne de clé étrangère
    private candidat candidat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFaculte() {
        return faculte;
    }

    public void setFaculte(String faculte) {
        this.faculte = faculte;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public com.example.demo.Models.candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(com.example.demo.Models.candidat candidat) {
        this.candidat = candidat;
    }
}
