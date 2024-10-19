package com.example.demo.services;

import com.example.demo.Models.absence;

import com.example.demo.Models.personnel;
import com.example.demo.Repositories.AbsenceRepository;
import com.example.demo.Repositories.personnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class absenceServiceImp implements absenceService{

    @Autowired
    AbsenceRepository absenceRepo;

    @Autowired
    personnelRepository personnelRep;

    @Override
    public int getsolde(int id) {
        return personnelRep.findById(id).get().getSoldeconge();
    }

   /* @Override
    public absence ajouter(absence absence, int id) {
        absenceRepo.save(absence);
        personnel p=personnelRep.findById(id).get();
        absence.setPersonnel(p);
        absence.setEtat("en attente");
        absenceRepo.save(absence);
        return absenceRepo.save(absence);
    }*/

    @Override
    public absence ajouter(absence absence, int id) {
        personnel p = personnelRep.findById(id).get();
        absence.setPersonnel(p);
        absence.setEtat("en attente");
        return absenceRepo.save(absence);
    }

    @Override
    public List<absence> getAbsence() {
        return absenceRepo.findAll();
    }

    @Override
    public void deleteabsence(int id) {
        absenceRepo.deleteById(id);
    }

    @Override
    public absence updateabsence(absence absence) {

        return absenceRepo.save(absence);
    }

    @Override
    public absence getabsencebyid(int id) {
        return absenceRepo.findById(id).get();
    }
    @Override
    public List<absence> getAbs() {
//        List<absence> list = absenceRepo.findAll();
        List<absence> list =  absenceRepo.findAllWithPersonnel();
//        List<absence> absencesEnAttente = new ArrayList<>();
//
//        for (absence abs : list) {
//            if ("en attente".equals(abs.getEtat())) {
//                // Get the related Personnel object directly
//                personnel personnel = abs.getPersonnel(); // Ensure this fetches the related personnel
//
//                // Create a new absence instance (or modify the existing one as needed)
//                absence demande = new absence();
//                demande.setId(abs.getId());
//                demande.setType(abs.getType());
//                demande.setDatedebut(abs.getDatedebut());
//                demande.setDatefin(abs.getDatefin());
//                demande.setJustificatif(abs.getJustificatif());
//                demande.setEtat(abs.getEtat());
//                demande.setPersonnel(personnel); // Set the actual personnel object
//                System.out.println(personnel.getNom()+personnel.getPrenom());
//                absencesEnAttente.add(demande);
//            }
//        }
//        System.out.println(absencesEnAttente);
        return list;

    }


    @Override
    public void approve(int id, int nb) {
        absence abs=absenceRepo.findById(id).get();
        personnel p=abs.getPersonnel();
        p.setSoldeconge((p.getSoldeconge()-nb));
       // System.out.println(p.getSoldeconges());
        System.out.println(nb);
        abs.setEtat("approuve");
        absenceRepo.save(abs);
        personnelRep.save(p);
    }

    @Override
    public void reject(int id,String motif) {
        absence abs=absenceRepo.findById(id).get();
        abs.setMotif(motif);
        abs.setEtat("rejete");
        absenceRepo.save(abs);
    }

}
