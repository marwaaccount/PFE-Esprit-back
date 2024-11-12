package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.boot.jaxb.internal.stax.LocalSchemaLocator;

import java.time.LocalDateTime;

@Entity
public class Entretien {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private String lieu;
    private String etat;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "candidat_offre_id")
    private candidatoffre candidatOffre;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personnel")
    @JsonBackReference
    private Personnel personnel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public candidatoffre getCandidatOffre() {
        return candidatOffre;
    }

    public void setCandidatOffre(candidatoffre candidatOffre) {
        this.candidatOffre = candidatOffre;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
