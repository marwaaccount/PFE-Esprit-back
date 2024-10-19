package com.example.demo.Controllers;

import com.example.demo.Models.Experience;
import com.example.demo.Models.diplome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.experienceService;
@RestController
@RequestMapping("/experience")
@CrossOrigin("*")
public class experienceController {

    @Autowired
    experienceService experienceService;

    @PostMapping("/add")
    public ResponseEntity<Experience> create(@RequestBody Experience experience) {
        Experience exp = experienceService.ajout(experience);
        return new ResponseEntity<>(exp, HttpStatus.CREATED);
    }

}
