package com.example.demo.services;

import com.example.demo.Models.absence;

import java.util.List;

public interface absenceService {

   int getsolde(int id);

   absence ajouter(absence absence, int id);

   List<absence> getAbsence();

   void deleteabsence(int id);

   absence updateabsence(absence absence);

   absence getabsencebyid(int id);


   List<absence> getAbs();

   void approve(int id, int nb);

   void reject(int id,String motif);
}
