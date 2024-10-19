package com.example.demo.services;

import com.example.demo.Models.diplome;
import com.example.demo.Repositories.diplomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class diplomeServiceImp implements diplomeService{

    @Autowired
    diplomeRepository diplomeRep;

    @Override
    public diplome ajouter(diplome diplome) {
        return diplomeRep.save(diplome);
    }
}
