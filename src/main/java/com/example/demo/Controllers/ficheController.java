package com.example.demo.Controllers;

import com.example.demo.Models.Voyage;
import com.example.demo.Models.fichedepaie;
import com.example.demo.services.FicheService;
import com.example.demo.services.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fiche")
@CrossOrigin("*")
public class ficheController {

    @Autowired
    FicheService ficheService;
    @PostMapping("/add/{id}")
    public ResponseEntity<fichedepaie> createVacation(@RequestBody fichedepaie fichedepaie,@PathVariable int id) {
        fichedepaie createdfiche = ficheService.createfiche(fichedepaie,id);
        return new ResponseEntity<>(createdfiche, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<fichedepaie>> getAllfiche() {
        List<fichedepaie> fiches = ficheService.getAllfiches();
        return new ResponseEntity<>(fiches, HttpStatus.OK);
    }

    @GetMapping("/allparpersonne/{id}")
    public ResponseEntity<List<fichedepaie>> getficheParPersonne(@PathVariable int id) {
        List<fichedepaie> fiches = ficheService.getficheParPersonne(id);
        return new ResponseEntity<>(fiches, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<fichedepaie> getficheById(@PathVariable int id) {
        fichedepaie fiche = ficheService.getficheById(id);
        if (fiche != null) {
            return new ResponseEntity<>(fiche, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("/modif/{id}")
    public ResponseEntity<fichedepaie> updatefiche(@PathVariable int id, @RequestBody fichedepaie updatedfiche) {
        fichedepaie fiche = ficheService.getficheById(id);
        if (fiche != null)
        {
            fiche.setNbheuressupp(updatedfiche.getNbheuressupp());
            fiche.setNbheurestr(updatedfiche.getNbheurestr());
            fiche.setDate(updatedfiche.getDate());
            ficheService.calculsalaire(id);
            ficheService.updatefiche(fiche);

            return new ResponseEntity<>(fiche, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletefiche(@PathVariable int id) {
        fichedepaie fiche = ficheService.getficheById(id);
        if (fiche != null) {
            ficheService.deletefiche(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/calculsalaire/{id}")
    public ResponseEntity<fichedepaie> calculsalaire(@PathVariable int id) {
        fichedepaie fiche = ficheService.getficheById(id);
        ficheService.calculsalaire(id);
        return new ResponseEntity<>(fiche, HttpStatus.OK);

    }
}
