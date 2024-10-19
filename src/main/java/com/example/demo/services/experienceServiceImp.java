package com.example.demo.services;

import com.example.demo.Models.Experience;
import com.example.demo.Repositories.experienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class experienceServiceImp implements experienceService {

    @Autowired
    experienceRepository experienceRep;
    @Override
    public Experience ajout(Experience exp) {
        return experienceRep.save(exp);
    }
}
