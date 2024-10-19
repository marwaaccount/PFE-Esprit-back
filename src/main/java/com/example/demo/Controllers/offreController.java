package com.example.demo.Controllers;

import com.example.demo.Models.OffreEmploi;
import com.example.demo.Models.Voyage;
import com.example.demo.Models.fichedepaie;
import com.example.demo.services.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offre")
@CrossOrigin("*")
public class offreController {

    @Autowired
    OffreService offreService;

    @PostMapping("/add")
    public ResponseEntity<OffreEmploi> createOffre(@RequestBody OffreEmploi offre) {
        OffreEmploi createdoffre = offreService.createOffre(offre);
        return new ResponseEntity<>(createdoffre, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OffreEmploi>> getAll() {
        List<OffreEmploi> offres = offreService.getoffres();
        return new ResponseEntity<>(offres, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable int id) {
        OffreEmploi offreEmploi = offreService.getOffreById(id);
        if (offreEmploi != null) {
            offreService.deleteOffre(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/modif/{id}")
    public ResponseEntity<OffreEmploi> updatefiche(@PathVariable int id, @RequestBody OffreEmploi updatedOffreEmploi) {
        OffreEmploi offreEmploi = offreService.getOffreById(id);
        if (offreEmploi != null)
        {
            offreEmploi.setTitre(updatedOffreEmploi.getTitre());
            offreEmploi.setDetails(updatedOffreEmploi.getDetails());
            offreEmploi.setDatefin(updatedOffreEmploi.getDatefin());
            offreEmploi.setExigences(updatedOffreEmploi.getExigences());
            offreEmploi.setLocalisation(updatedOffreEmploi.getLocalisation());
            offreEmploi.setTypeContrat(updatedOffreEmploi.getTypeContrat());
            offreService.updateOffre(offreEmploi);

            return new ResponseEntity<>(offreEmploi, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<OffreEmploi> getById(@PathVariable int id) {
        OffreEmploi offre = offreService.getOffreById(id);
        return new ResponseEntity<>(offre, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<OffreEmploi>> searchOffers(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String contractType) {
        List<OffreEmploi> jobOffers = offreService.searchOffers(title, location, contractType);
        return ResponseEntity.ok(jobOffers);
    }
}
