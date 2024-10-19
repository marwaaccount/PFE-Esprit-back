package com.example.demo.Controllers;

import com.example.demo.Models.candidat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.diplomeService;
import com.example.demo.Models.diplome;
@RestController
@RequestMapping("/diplome")
@CrossOrigin("*")

public class diplomeController {

    @Autowired
    diplomeService diplomeService;

    @PostMapping("/add")
    public ResponseEntity<diplome> create(@RequestBody diplome diplome) {
        diplome dp = diplomeService.ajouter(diplome);
        return new ResponseEntity<>(dp, HttpStatus.CREATED);
    }

}
