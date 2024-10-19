package com.example.demo.services;

import com.example.demo.Models.Entretien;
import com.example.demo.Models.candidat;
import com.example.demo.Models.candidatoffre;
import java.util.List;
import java.util.Date;
public interface candidatOffreService {

    candidatoffre candidatOffreAjout(candidatoffre candidatOffre, int idoffre);
    candidatoffre getCandidatOffreById(int id);
    List<candidatoffre> getCandidatOffre();

    List<candidat> getByIdOffre(int id);

    Entretien approuver(int offerid,int candidatid, Entretien e);
    candidatoffre rejeter (int offerid,int candidatid);
    String getemail(int id);

}
