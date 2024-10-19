package com.example.demo.services;

import com.example.demo.Models.OffreEmploi;
import com.example.demo.Models.candidat;
import com.example.demo.Models.candidatoffre;
import com.example.demo.Models.Entretien;
import com.example.demo.Repositories.CandidatOffreRepository;
import com.example.demo.Repositories.CandidatRepository;
import com.example.demo.Repositories.OffreRepository;
import com.example.demo.Repositories.entretienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class candidatOffreServiceImp implements candidatOffreService{

    @Autowired
    CandidatOffreRepository candidatOffreRep;
    @Autowired
    entretienRepository entretienRep;
    @Autowired
    CandidatRepository candidatRepository;
    @Autowired
    OffreRepository offreRepository;
    @Autowired
    candidatService candidatService;
    @Autowired
    OffreService offreService;
    @Override
    public candidatoffre candidatOffreAjout(candidatoffre candidatOffre, int idoffre) {
        candidatoffre candidatoffre= new candidatoffre();
        //candidatOffre.setCandidat(this.candidatService.getCandidatById(idcandidat));
        candidatOffre.setOffre(this.offreService.getOffreById(idoffre));
        return candidatOffreRep.save(candidatOffre);
    }

    @Override
    public candidatoffre getCandidatOffreById(int id) {
        return candidatOffreRep.getById(id);
    }

    @Override
    public List<candidatoffre> getCandidatOffre() {
        return candidatOffreRep.findAll();
    }

    @Override
    public List<candidat> getByIdOffre(int id) {
        List<candidatoffre> list=candidatOffreRep.findAll();
        List<candidat> list2 = new ArrayList<>();
        for (candidatoffre element : list) {
           if ((element.getOffre() != null)
                   && (element.getOffre().getId()) == id
                   &&(( element.getEtat() == null ||element.getEtat().isEmpty()) ||element.getEtat().equals("accepte")  ) ){
               list2.add(element.getCandidat());

           }
        }
        return list2;
    }

    @Override
    public Entretien approuver(int offerid,int candidatid, Entretien e) {
        OffreEmploi offre = offreRepository.findById(offerid).orElseThrow(() -> new RuntimeException("Offre not found"));
        candidat candidat = candidatRepository.findById(candidatid).orElseThrow(() -> new RuntimeException("Candidat not found"));
        candidatoffre cnd =candidatOffreRep.findByOffreAndCandidat(offre,candidat);
        System.out.println("offre"+cnd);
        cnd.setEtat("accepte");
        e.setCandidatOffre(cnd);
        // Save and return the updated Entretien entity
        return entretienRep.save(e);
    }


    @Override
    public candidatoffre rejeter(int offerid,int candidatid) {
        OffreEmploi offre = offreRepository.findById(offerid).orElseThrow(() -> new RuntimeException("Offre not found"));
        candidat candidat = candidatRepository.findById(candidatid).orElseThrow(() -> new RuntimeException("Candidat not found"));
        candidatoffre cnd =candidatOffreRep.findByOffreAndCandidat(offre,candidat);
        System.out.println("offre"+cnd);

        cnd.setEtat("rejete");
    return candidatOffreRep.save(cnd);
    }

    @Override
    public String getemail(int id) {
       return candidatRepository.findById(id).get().getEmail();
    }
}
