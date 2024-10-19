package com.example.demo.Controllers;

import com.example.demo.Models.candidat;
import com.example.demo.Models.fichedepaie;
import com.example.demo.services.FicheService;
import com.example.demo.services.candidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidat")
@CrossOrigin("*")
public class candidatController {

    @Autowired
    candidatService candidatService;

    @PostMapping("/add")
    public ResponseEntity<candidat> create(@RequestBody candidat candidat) {
        candidat cand = candidatService.ajout(candidat);
        return new ResponseEntity<>(cand, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/cv")
    public ResponseEntity<byte[]> getCv(@PathVariable int id) {
        byte[] cv = candidatService.getCvById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, "application/pdf"); // Adjust content type as necessary
        headers.setContentLength(cv.length);
        return new ResponseEntity<>(cv, headers, HttpStatus.OK);

    }
}