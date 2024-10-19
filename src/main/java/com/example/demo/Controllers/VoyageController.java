package com.example.demo.Controllers;

import com.example.demo.Models.Voyage;
import com.example.demo.services.VoyageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacations") // Base URL mapping for the controller
public class VoyageController {
@Autowired
    VoyageService voyageService;
    @PostMapping("/add")
    public ResponseEntity<Voyage> createVacation(@RequestBody Voyage vacation) {
        Voyage createdVacation = voyageService.createVacation(vacation);
        return new ResponseEntity<>(createdVacation, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Voyage>> getAllVacations() {
        List<Voyage> vacations = voyageService.getAllVacations();
        return new ResponseEntity<>(vacations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voyage> getVacationById(@PathVariable int id) {
        Voyage vacation = voyageService.getVacationById(id);
        if (vacation != null) {
            return new ResponseEntity<>(vacation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voyage> updateVacation(@PathVariable int id, @RequestBody Voyage updatedVacation) {
        Voyage vacation = voyageService.getVacationById(id);
        if (vacation != null) {
            vacation.setDestination(updatedVacation.getDestination());
            vacation.setDateDebut(updatedVacation.getDateDebut());
            vacation.setDateFin(updatedVacation.getDateFin());
            vacation.setPrix(updatedVacation.getPrix());
            voyageService.updateVacation(vacation);
            return new ResponseEntity<>(vacation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVacation(@PathVariable int id) {
        Voyage vacation = voyageService.getVacationById(id);
        if (vacation != null) {
            voyageService.deleteVacation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
