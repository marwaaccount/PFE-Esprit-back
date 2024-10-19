package com.example.demo.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Voyage")
public class Voyage {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "destination")
    private String destination;
    @Column(name = "dateDebut")
    private Date dateDebut;
    @Column(name = "dateFin")
    private Date dateFin;
    @Column(name = "prix")
    private double prix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}

