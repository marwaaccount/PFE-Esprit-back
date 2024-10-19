package com.example.demo.Models;

import jakarta.persistence.*;
//import javax.persistence.Entity;
//import javax.persistence.Id;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        private String nom;
        private String prenom;
        private String adresse;
        private String email;
        private String motdepasse;
        private String numTelephone;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public void setNumTelephone(String numTelephone) {
        this.numTelephone = numTelephone;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public String getNumTelephone() {
        return numTelephone;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }
// autres champs communs
    }

