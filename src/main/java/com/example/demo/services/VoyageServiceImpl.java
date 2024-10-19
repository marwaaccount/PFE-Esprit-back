package com.example.demo.services;

import com.example.demo.Models.Voyage;
import com.example.demo.Repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoyageServiceImpl implements  VoyageService {
    @Autowired
    VoyageRepository voyageRepository;
    @Override
    public Voyage createVacation(Voyage vacation) {
        return  voyageRepository.save(vacation);
    }

    @Override
    public List<Voyage> getAllVacations() {
        return  voyageRepository.findAll();
    }

    @Override
    public Voyage getVacationById(int id) {
        return voyageRepository.findById(id).orElse(null);
    }

    @Override
    public Voyage updateVacation(Voyage vacation) {
        return  voyageRepository.save(vacation);
    }

    @Override
    public void deleteVacation(int id) {
        voyageRepository.deleteById(id);
    }
}
