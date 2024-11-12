package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class candidat {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String num;
    private String poste;
    private String cvfilename;
    @Lob
    private byte[] cv;



    @OneToMany(mappedBy = "candidat",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<diplome> diplomes;


    @OneToMany(mappedBy = "candidat",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "candidat",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
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

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

  public byte[] getCv() {
        return cv;
    }

    public void setCv(byte[] cv) {
        this.cv = cv;
    }


    public List<diplome> getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(List<diplome> diplomes) {
        this.diplomes = diplomes;
    }

    public String getCvfilename() {
        return cvfilename;
    }

    public void setCvfilename(String cvfilename) {
        this.cvfilename = cvfilename;
    }
}


