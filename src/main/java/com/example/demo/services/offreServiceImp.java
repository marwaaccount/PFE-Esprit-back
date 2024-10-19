package com.example.demo.services;

import com.example.demo.Models.OffreEmploi;
import com.example.demo.Models.fichedepaie;
import com.example.demo.Repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class offreServiceImp implements OffreService{

    @Autowired
    OffreRepository offreRep;

    @Override
    public OffreEmploi createOffre(OffreEmploi offre) {
        return offreRep.save(offre);
    }

    @Override
    public void deleteOffre(int id) {
        offreRep.deleteById(id);

    }

    @Override
    public List<OffreEmploi> getoffres() {
        return offreRep.findAll();
    }

    @Override
    public OffreEmploi updateOffre(OffreEmploi offre) {
        return offreRep.save(offre);
    }

    @Override
    public OffreEmploi getOffreById(int id) {
        return offreRep.findById(id).get();


    }

    @Override
    public List<OffreEmploi> searchOffers(String title, String location, String contractType) {
            // Cette méthode appelle le repository pour obtenir les offres filtrées
            return offreRep.findByCriteria(title, location, contractType);
        }
    }

