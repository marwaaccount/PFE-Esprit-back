package com.example.demo.services;

import com.example.demo.Models.OffreEmploi;
import com.example.demo.Models.fichedepaie;

import java.util.List;

public interface OffreService {

    OffreEmploi createOffre(OffreEmploi offre);
    void deleteOffre(int id);
    List<OffreEmploi> getoffres();
    OffreEmploi updateOffre(OffreEmploi offre);
    OffreEmploi getOffreById(int id);

    List<OffreEmploi> searchOffers(String title, String location, String contractType);


}
