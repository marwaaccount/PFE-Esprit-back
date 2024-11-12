package com.example.demo.Models;

public class PersonnelMapper {
    public static PersonnelDTO toDTO(Personnel p) {
        return new PersonnelDTO(
                p.getNom(),
                p.getPrenom(),
                p.getAdresse(),
                p.getEmail(),
                p.getMotdepasse(),
                p.getNumTelephone(),
                p.getId(),
                p.getPoste(),
                p.getGrade(),
                p.getIdcnss(),
                p.getCin(),
                p.getEnfantsacharge(),
                p.getCategorie(),
                p.getSoldeconge()
        );
    }

    public static Personnel toEntity(PersonnelDTO dto) {
    	Personnel p = new Personnel();
        p.setNom(dto.getNom());
        p.setPrenom(dto.getPrenom());
        p.setAdresse(dto.getAdresse());
        p.setEmail(dto.getEmail());
        p.setMotdepasse(dto.getMotdepasse());
        p.setNumTelephone(dto.getNumTelephone());
        p.setPoste(dto.getPoste());
        p.setGrade(dto.getGrade());
        p.setIdcnss(dto.getIdcnss());
        p.setCin(dto.getCin());
        p.setEnfantsacharge(dto.getEnfantsacharge());
        p.setCategorie(dto.getCategorie());
        p.setSoldeconge(dto.getSoldeconge());
        return p;
    }
}
