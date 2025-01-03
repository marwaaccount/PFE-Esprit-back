package com.example.demo.Models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
//import org.springframework.data.annotation.Id;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@JsonView(Views.Summary.class)
public class Personnel extends User {

    private String poste;
    private String grade;
    private long idcnss;
    private long cin;
    private byte enfantsacharge;
    private String categorie;
    private int soldeconge;

    public int getSoldeconge() {
        return soldeconge;
    }

    public void setSoldeconge(int soldeconge) {
        this.soldeconge = soldeconge;
    }

    public String getCategorie() {
        return categorie;
    }



    @OneToMany(mappedBy = "personnel", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<fichedepaie> fichesdepaie;
    @OneToMany(mappedBy = "personnel")
    @JsonManagedReference
    private List<Entretien> entretiens;

    @OneToMany(mappedBy = "personnel", fetch = FetchType.EAGER , cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonIgnore // Évite la sérialisation des absences pour éviter les boucles
    @JsonBackReference
    private List<absence> absences;

    public long getIdcnss() {
        return idcnss;
    }

    public void setIdcnss(long idcnss) {
        this.idcnss = idcnss;
    }

    public long getCin() {
        return cin;
    }

    public void setCin(long cin) {
        this.cin = cin;
    }

    public List<absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<absence> absences) {
        this.absences = absences;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }




    public void setEnfantsacharge(byte enfantsacharge) {
        this.enfantsacharge = enfantsacharge;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public List<fichedepaie> getFichesdepaie() {
        return fichesdepaie;
    }

    public void setFichesdepaie(List<fichedepaie> fichesdepaie) {
        this.fichesdepaie = fichesdepaie;
    }

    public List<Entretien> getEntretiens() {
        return entretiens;
    }

    public void setEntretiens(List<Entretien> entretiens) {
        this.entretiens = entretiens;
    }

    public String getGrade() {
        return grade;
    }


    public byte getEnfantsacharge() {
        return enfantsacharge;
    }

    public String getPoste() {
        return poste;
    }

}

