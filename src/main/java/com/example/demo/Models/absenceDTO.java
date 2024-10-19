package com.example.demo.Models;

import java.time.LocalDateTime;

public class absenceDTO {

    private int id;
    private String nom;
    private String prenom;
    private String type;
    private LocalDateTime datedebut;
    private LocalDateTime datefin;
    private String justificatif;
    private String etat;
    private String motif;

    public absenceDTO() {

    }

    // Constructeur, getters et setters
    public absenceDTO(absence demande) {
        this.id = demande.getId();
        this.nom = demande.getPersonnel() != null ? demande.getPersonnel().getNom() : "Non spécifié";
        this.prenom = demande.getPersonnel() != null ? demande.getPersonnel().getPrenom() : "Non spécifié";
        this.type = demande.getType();
        this.datedebut = demande.getDatedebut();
        this.datefin = demande.getDatefin();
        this.justificatif = demande.getJustificatif();
        this.etat = demande.getEtat();
        this.motif=demande.getMotif();
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(LocalDateTime datedebut) {
        this.datedebut = datedebut;
    }

    public LocalDateTime getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDateTime datefin) {
        this.datefin = datefin;
    }

    public String getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
