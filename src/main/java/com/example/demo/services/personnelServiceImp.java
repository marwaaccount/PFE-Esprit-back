package com.example.demo.services;

import com.example.demo.Models.fichedepaie;
import com.example.demo.Models.personnel;
import com.example.demo.Repositories.personnelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class personnelServiceImp implements PersonnelService{

    @Autowired
    personnelRepository personnelRep;
    @Override
    public List<personnel> getAllPersonnel() {
        List<personnel> list= personnelRep.findAll();
        return list;
    }

    @Override
    public personnel getPersonnelById(int id) {
        return personnelRep.findById(id).get();
    }

    @Override
    public void deletePersonnel(int id) {
        personnelRep.deleteById(id);


    }

    @Override
    public personnel createPersonnel(personnel personnel) {
        personnel.setSoldeconge(30);
        if (personnel.getRole() == null) {
            System.out.println("le role est manquant");// Renvoyer une erreur si le rôle est manquant
        }
        return personnelRep.save(personnel);

    }

   /* @Override
    public personnel updatePersonnel(personnel personnel) {
        return personnelRep.save(personnel);
    }*/
    @Override
    public personnel updatePersonnel(personnel updatedPersonnel) {

        // Récupérer l'entité existante
        personnel existingPersonnel = personnelRep.findById(updatedPersonnel.getId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("Personnel not found"));

        // Mettez à jour les attributs de l'entité existante
       // existingPersonnel.setNom(updatedPersonnel.getNom());
      //  existingPersonnel.setPrenom(updatedPersonnel.getPrenom());
        existingPersonnel.setAdresse(updatedPersonnel.getAdresse());
        existingPersonnel.setEmail(updatedPersonnel.getEmail());
      //  existingPersonnel.setMotdepasse(updatedPersonnel.getMotdepasse());
        existingPersonnel.setNumTelephone(updatedPersonnel.getNumTelephone());
        existingPersonnel.setPoste(updatedPersonnel.getPoste());
        existingPersonnel.setGrade(updatedPersonnel.getGrade());
      //  existingPersonnel.setIdcnss(updatedPersonnel.getIdcnss());
      //  existingPersonnel.setCin(updatedPersonnel.getCin());
        existingPersonnel.setEnfantsacharge(updatedPersonnel.getEnfantsacharge());
        existingPersonnel.setCategorie(updatedPersonnel.getCategorie());
      //  existingPersonnel.setSoldeconge(updatedPersonnel.getSoldeconge());

        // Enregistrer et retourner l'entité mise à jour
        return personnelRep.save(existingPersonnel);
    }

    @Override
    public String getNomByCin(int cin) {
        List<personnel> list=personnelRep.findAll();
        for (personnel personnel : list) {
            if (personnel.getCin() == cin) { // Vérifie si le cin correspond
                return personnel.getNom() + " " + personnel.getPrenom(); // Retourne le nom et le prénom
            }
        }
        return null;
    }

    @Override
    public personnel getpersonnelbyid(int id) {
        return personnelRep.findById(id).get();
    }
}
