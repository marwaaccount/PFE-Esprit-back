package com.example.demo.Models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@JsonView(Views.Detailed.class)
public class absence {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String motif;
    private String Etat;
    private String justificatif;
    private LocalDateTime datedebut;
    private LocalDateTime datefin;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personnel") // Assurez-vous que le nom de la colonne est correct
    @JsonManagedReference
    //@JsonView(Views.Summary.class)
    @JsonIgnore // Change ici pour éviter la boucle de sérialisation
    private personnel personnel;



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



    public String getEtat() {
        return Etat;
    }

    public void setEtat(String etat) {
        Etat = etat;
    }

    public String getJustificatif() {
        return justificatif;
    }

    public void setJustificatif(String justificatif) {
        this.justificatif = justificatif;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public com.example.demo.Models.personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(com.example.demo.Models.personnel personnel) {
        this.personnel = personnel;
    }
}
