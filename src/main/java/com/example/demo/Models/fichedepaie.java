package com.example.demo.Models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class fichedepaie {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private int nbheurestr;
    private int nbheuressupp;
    private double remHeure;
    private double remHeuresupp;
    private double indemnitetransport;
    private double retenueCNSS;
    private double cotisationAccident;
    private double salairenet;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personnel")
    @JsonBackReference
    private personnel personnel;


    public personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(personnel personnel) {
        this.personnel = personnel;
    }

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

    public int getNbheurestr() {
        return nbheurestr;
    }

    public void setNbheurestr(int nbheurestr) {
        this.nbheurestr = nbheurestr;
    }

    public int getNbheuressupp() {
        return nbheuressupp;
    }

    public void setNbheuressupp(int nbheuressupp) {
        this.nbheuressupp = nbheuressupp;
    }

    public double getRemHeure() {
        return remHeure;
    }

    public void setRemHeure(double remHeure) {
        this.remHeure = remHeure;
    }

    public double getRemHeuresupp() {
        return remHeuresupp;
    }

    public void setRemHeuresupp(double remHeuresupp) {
        this.remHeuresupp = remHeuresupp;
    }

    public double getIndemnitetransport() {
        return indemnitetransport;
    }

    public void setIndemnitetransport(double indemnitetransport) {
        this.indemnitetransport = indemnitetransport;
    }

    public double getRetenueCNSS() {
        return retenueCNSS;
    }

    public void setRetenueCNSS(double retenueCNSS) {
        this.retenueCNSS = retenueCNSS;
    }

    public double getCotisationAccident() {
        return cotisationAccident;
    }

    public void setCotisationAccident(double cotisationAccident) {
        this.cotisationAccident = cotisationAccident;
    }

    public double getSalairenet() {
        return salairenet;
    }

    public void setSalairenet(double salairenet) {
        this.salairenet = salairenet;
    }
}
