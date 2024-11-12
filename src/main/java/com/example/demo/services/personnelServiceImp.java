package com.example.demo.services;

import com.example.demo.Models.ChangePassword;
import com.example.demo.Models.Personnel;
import com.example.demo.Repositories.personnelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class personnelServiceImp implements PersonnelService{

    @Autowired
    personnelRepository personnelRep;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public List<Personnel> getAllPersonnel() {
        List<Personnel> list= personnelRep.findAll();
        return list;
    }

    @Override
    public Personnel getPersonnelById(int id) {
        return personnelRep.findById(id).get();
    }

    @Override
    public void deletePersonnel(int id) {
        personnelRep.deleteById(id);


    }

    @Override
    public Personnel createPersonnel(Personnel personnel) {
        personnel.setSoldeconge(30);
        if (personnel.getRole_User() == null) {
            System.out.println("le role est manquant");// Renvoyer une erreur si le rôle est manquant
        }
        return personnelRep.save(personnel);

    }

	@Override
	public Personnel updatePwd(ChangePassword changePassword) throws Exception {
		Personnel existingPersonnel = personnelRep.findById(changePassword.getId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("Personnel not found"));
        // Check if the current password matches
        if (!bCryptPasswordEncoder.matches(changePassword.getPasswordold(), existingPersonnel.getMotdepasse())) {
            throw new Exception("Current password is incorrect");
        }
	        existingPersonnel.setMotdepasse(bCryptPasswordEncoder.encode(changePassword.getPassword()));
	 
	 return personnelRep.save(existingPersonnel);

	}
    @Override
    public Personnel updatePersonnel(Personnel updatedPersonnel) {

        // Récupérer l'entité existante
    	Personnel existingPersonnel = personnelRep.findById(updatedPersonnel.getId().intValue())
                .orElseThrow(() -> new EntityNotFoundException("Personnel not found"));

        // Mettez à jour les attributs de l'entité existante
        existingPersonnel.setNom(updatedPersonnel.getNom());
        existingPersonnel.setPrenom(updatedPersonnel.getPrenom());
        existingPersonnel.setAdresse(updatedPersonnel.getAdresse());
        existingPersonnel.setEmail(updatedPersonnel.getEmail());
      //  existingPersonnel.setMotdepasse(updatedPersonnel.getMotdepasse());
        existingPersonnel.setNumTelephone(updatedPersonnel.getNumTelephone());
        existingPersonnel.setPoste(updatedPersonnel.getPoste());
        existingPersonnel.setGrade(updatedPersonnel.getGrade());
        existingPersonnel.setIdcnss(updatedPersonnel.getIdcnss());
        existingPersonnel.setCin(updatedPersonnel.getCin());
        existingPersonnel.setEnfantsacharge(updatedPersonnel.getEnfantsacharge());
        existingPersonnel.setCategorie(updatedPersonnel.getCategorie());
      //  existingPersonnel.setSoldeconge(updatedPersonnel.getSoldeconge());

        // Enregistrer et retourner l'entité mise à jour
        return personnelRep.save(existingPersonnel);
    }

    @Override
    public String getNomByCin(int cin) {
        List<Personnel> list=personnelRep.findAll();
        for (Personnel personnel : list) {
            if (personnel.getCin() == cin) { // Vérifie si le cin correspond
                return personnel.getNom() + " " + personnel.getPrenom(); // Retourne le nom et le prénom
            }
        }
        return null;
    }

    @Override
    public Personnel getpersonnelbyid(int id) {
    	return personnelRep.findById(id).get();
    }


}
