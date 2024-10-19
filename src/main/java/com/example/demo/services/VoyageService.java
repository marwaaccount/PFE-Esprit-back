package com.example.demo.services;

import com.example.demo.Models.Voyage;

import java.util.List;

public interface VoyageService {
  Voyage createVacation( Voyage vacation);

    List<Voyage> getAllVacations();

    Voyage getVacationById(int id);

    Voyage updateVacation( Voyage vacation);

    void deleteVacation(int id);
}
