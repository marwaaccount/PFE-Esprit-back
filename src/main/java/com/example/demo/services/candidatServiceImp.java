package com.example.demo.services;
import com.example.demo.Models.candidat;
import com.example.demo.Repositories.CandidatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;


@Service
public class candidatServiceImp implements candidatService{
    private static final Logger logger = LoggerFactory.getLogger(candidatService.class);

    @Autowired
    CandidatRepository candidatRep;

    @Override
    public candidat ajout(candidat candidat) {
        return candidatRep.save(candidat);
    }

    @Override
    public candidat getCandidatById(int id) {
        return candidatRep.getReferenceById(id);
    }

    @Override
    public byte[] getCvById(int id) {
        candidat candidate = candidatRep.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
        byte[] cv = candidate.getCv();
        logger.debug("Retrieved CV byte array length: {}", cv.length);
        return cv;
    }
}
