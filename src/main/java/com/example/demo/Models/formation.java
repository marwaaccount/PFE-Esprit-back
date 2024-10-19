package com.example.demo.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class formation {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private String periode;
    private String formateur;
    private String theme;
    private String cout;

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getFormateur() {
        return formateur;
    }

    public void setFormateur(String formateur) {
        this.formateur = formateur;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCout() {
        return cout;
    }

    public void setCout(String cout) {
        this.cout = cout;
    }
}
