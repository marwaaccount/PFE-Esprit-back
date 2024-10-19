package com.example.demo.services;

import com.example.demo.Models.Voyage;
import com.example.demo.Models.fichedepaie;
import com.example.demo.Models.personnel;
import com.example.demo.Repositories.VoyageRepository;
import com.example.demo.Repositories.ficheRepository;
import com.example.demo.Repositories.personnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;
@Service
public class ficheServiceImp implements FicheService{

    @Autowired
    ficheRepository ficheRep;
    @Autowired
    personnelRepository personnelRepository;
    @Override
    public fichedepaie createfiche(fichedepaie fiche, int id) {
        personnel personnel = personnelRepository.getById(id);

        fichedepaie fichedepaie =ficheRep.save(fiche);
        fichedepaie.setPersonnel(personnel);
        this.calculsalaire(fichedepaie.getId());

        return fichedepaie;

    }

    @Override
    public List<fichedepaie> getAllfiches() {
       List <fichedepaie> fiches=ficheRep.findAll();
        for (fichedepaie fiche : fiches) {
            // Exemple d'appel pour calculer le salaire pour chaque fiche
            this.calculsalaire(fiche.getId()); // Supposons que vous passiez l'ID de chaque fiche pour le calcul
        }

        return fiches;
    }

    @Override
    public fichedepaie getficheById(int id) {
        return ficheRep.findById(id).get();
    }

    @Override
    public fichedepaie updatefiche(fichedepaie fiche) {
        return ficheRep.save(fiche);
    }

    @Override
    public ArrayList<fichedepaie> getficheParPersonne(int cin) {
            List<fichedepaie> list=ficheRep.findAll();
            ArrayList<fichedepaie> list2 = new ArrayList<>();
            for (fichedepaie fiche:list) {
                this.calculsalaire(fiche.getId()); // Supposons que vous passiez l'ID de chaque fiche pour le calcul
                if(fiche.getPersonnel().getCin()==cin)
                {

                    list2.add(fiche);
                }
            }
            return list2;
        }



    @Override
    public void deletefiche(int id) {
        ficheRep.deleteById(id);

    }

    @Override
    public double calculsalaire(int id) {
        double salaire=ficheRep.findById(id).get().getSalairenet();
        double salaireBrut=0;
        personnel p=ficheRep.findById(id).get().getPersonnel();
        fichedepaie f=ficheRep.findById(id).get();
       if (p.getGrade().equals("directeur")) {f.setRemHeure(31.25);}
       else if (p.getGrade().equals("sous-directeur")) {f.setRemHeure(25);}
       else if (p.getGrade().equals("chef-sevice")) {f.setRemHeure(18.75);}
       else if (p.getGrade().equals("sous-chef service")) {f.setRemHeure(12.5);}
       else if (p.getGrade().equals("secretaire")) {f.setRemHeure(6.25);}


       if(p.getCategorie().equals("cadre superieur")) {f.setRemHeuresupp(30);}
       else if (p.getCategorie().equals("cadre intermediaire")) {f.setRemHeuresupp(20);}
       else if (p.getCategorie().equals("cadre jeune")) {f.setRemHeuresupp(10);}
       else if (p.getCategorie().equals("employe")) {f.setRemHeuresupp(20);}
       else if (p.getCategorie().equals("ouvrier")) {f.setRemHeuresupp(10);}

       salaireBrut=f.getNbheuressupp()*f.getRemHeuresupp()+f.getRemHeure()*f.getNbheurestr();

       if (p.getEnfantsacharge()==1) {salaireBrut+=10;}
       else if (p.getEnfantsacharge()==2) {salaireBrut+=20; }
       else if (p.getEnfantsacharge()==3) {salaireBrut+=30; }
       else if (p.getEnfantsacharge()>3) {salaireBrut+=40; }

       if (p.getCategorie().equals("cadre superieur")) {salaireBrut+=500; f.setIndemnitetransport(500);}
       else if (p.getCategorie().equals("cadre intermediaire")) {salaireBrut+=400; f.setIndemnitetransport(400);}

        f.setCotisationAccident(salaireBrut*0.01);
       salaireBrut*=0.99;

       f.setRetenueCNSS(salaireBrut*0.0918);


       if(salaireBrut*12<5000) {f.setSalairenet(salaireBrut*0.9082);}
       else if(salaireBrut*12<20000 && salaireBrut*12>=5000) {f.setSalairenet((salaireBrut*0.9082)*0.85);}
       else if(salaireBrut*12<30000 && salaireBrut*12>=20000) {f.setSalairenet((salaireBrut*0.9082)*0.8);}
       else if(salaireBrut*12<50000 && salaireBrut*12>=30000) {f.setSalairenet((salaireBrut*0.9082)*0.75);}
       else if(salaireBrut*12>50000) {f.setSalairenet((salaireBrut*0.9082)*0.7);}
       ficheRep.save(f);
    return f.getSalairenet();
 }


}
