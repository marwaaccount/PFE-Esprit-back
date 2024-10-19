package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class role{
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String description;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    //@JsonIgnore // Évite la sérialisation des absences pour éviter les boucles
    @JsonManagedReference("personnel-role")
    private List<personnel> personnel;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<com.example.demo.Models.personnel> getPersonnel() {
        return personnel;
    }

    public void setPersonnel(List<com.example.demo.Models.personnel> personnel) {
        this.personnel = personnel;
    }
}
