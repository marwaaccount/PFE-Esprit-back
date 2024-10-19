package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Experience {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String periode;
    private String nomSociete;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference (value="candidat-experience")
    @JoinColumn(name = "candidat_id") // Colonne de clé étrangère
    private candidat candidat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public com.example.demo.Models.candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(com.example.demo.Models.candidat candidat) {
        this.candidat = candidat;
    }
}
