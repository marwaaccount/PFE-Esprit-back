package com.example.demo.services;

import com.example.demo.Models.ChangePassword;
import com.example.demo.Models.OffreEmploi;
import com.example.demo.Models.Personnel;
import com.example.demo.Models.fichedepaie;

import java.util.List;

public interface PersonnelService {


    List<Personnel> getAllPersonnel();
    Personnel getPersonnelById(int id);
    void deletePersonnel(int id);
    Personnel createPersonnel(Personnel personnel);
    Personnel updatePersonnel(Personnel personnel);
    Personnel updatePwd(ChangePassword changePassword) throws Exception;

    String getNomByCin(int cin);

    Personnel getpersonnelbyid(int id);

}
