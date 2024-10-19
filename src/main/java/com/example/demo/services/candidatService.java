package com.example.demo.services;

import com.example.demo.Models.candidat;
import com.example.demo.Models.fichedepaie;

public interface candidatService {

    candidat ajout(candidat candidat);
    candidat getCandidatById(int id);
    byte[] getCvById(int id);

}
