package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class OffreEmploi {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime datePub;
    private String details;
    private String titre;
    private String exigences;
    private String typeContrat;
    private String localisation;
    private LocalDateTime datefin;

    @OneToMany(mappedBy = "offre")
    @JsonManagedReference(value="candidat-offreemploi")
    private List<candidatoffre> candidatOffres; // Relation avec la table de jointure

    public List<candidatoffre> getCandidatOffres() {
        return candidatOffres;
    }

    public void setCandidatOffres(List<candidatoffre> candidatOffres) {
        this.candidatOffres = candidatOffres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDatePub() {
        return datePub;
    }

    public void setDatePub(LocalDateTime datePub) {
        this.datePub = datePub;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getExigences() {
        return exigences;
    }

    public void setExigences(String exigences) {
        this.exigences = exigences;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public LocalDateTime getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDateTime datefin) {
        this.datefin = datefin;
    }
}
