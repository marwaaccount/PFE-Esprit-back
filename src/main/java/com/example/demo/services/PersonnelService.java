package com.example.demo.services;

import com.example.demo.Models.OffreEmploi;
import com.example.demo.Models.fichedepaie;
import com.example.demo.Models.personnel;

import java.util.List;

public interface PersonnelService {


    List<personnel> getAllPersonnel();
    personnel getPersonnelById(int id);
    void deletePersonnel(int id);
    personnel createPersonnel(personnel personnel);
    personnel updatePersonnel(personnel personnel);
    String getNomByCin(int cin);

    personnel getpersonnelbyid(int id);

}
