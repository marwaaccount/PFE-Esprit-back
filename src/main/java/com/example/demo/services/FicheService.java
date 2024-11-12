package com.example.demo.services;

import com.example.demo.Models.Voyage;
import com.example.demo.Models.fichedepaie;

import java.util.*;
import java.util.List;

public interface FicheService {

    fichedepaie createfiche(fichedepaie fiche,int id);

    List<fichedepaie> getAllfiches();

    fichedepaie getficheById(int id);

    fichedepaie updatefiche( fichedepaie fiche);

    ArrayList<fichedepaie> getficheParPersonne(int cin);

    void deletefiche(int id);

    double calculsalaire (int id);



}
