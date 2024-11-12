package com.example.demo.Controllers;

import com.example.demo.Models.absence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import com.example.demo.services.roleService;

import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class roleController {

//    @Autowired
//    roleService roleService;
//
//
//    @GetMapping("/all")
//    public ResponseEntity<List<role>> getAll() {
//        List<role> roles = roleService.getRoles();
//        System.out.println("Fetched absences: " + roles);
//        return new ResponseEntity<>(roles, HttpStatus.OK);
//    }


}
