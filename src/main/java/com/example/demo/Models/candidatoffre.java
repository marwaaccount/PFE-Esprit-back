package com.example.demo.Models;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class candidatoffre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etat;
    @Lob
    private byte[] lettremotivation; // Stocke le contenu de la lettre de motivation en tant que tableau d'octets
    private String lettremotivationFilename; // Nom du fichier lettre de motivation


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "candidat_id")
    private candidat candidat;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "offre_id") 
    private OffreEmploi offre;

    @OneToMany(mappedBy = "candidatOffre")
    @JsonIgnore
    private List<Entretien> entretiens; // Relation avec Entretien


    private LocalDateTime date;

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.example.demo.Models.candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(com.example.demo.Models.candidat candidat) {
        this.candidat = candidat;
    }

    public OffreEmploi getOffre() {
        return offre;
    }

    public void setOffre(OffreEmploi offre) {
        this.offre = offre;
    }

    public List<Entretien> getEntretiens() {
        return entretiens;
    }

    public void setEntretiens(List<Entretien> entretiens) {
        this.entretiens = entretiens;
    }

    @JsonIgnore public byte[] getLettremotivation() {
        return lettremotivation;
    }

    public void setLettremotivation(byte[] lettremotivation) {
        this.lettremotivation = lettremotivation;
    }

    @JsonIgnore public String getLettremotivationFilename() {
        return lettremotivationFilename;
    }

    public void setLettremotivationFilename(String lettremotivationFilename) {
        this.lettremotivationFilename = lettremotivationFilename;
    }
}
