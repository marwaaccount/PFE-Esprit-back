package com.example.demo.Controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.User;
import com.example.demo.dto.AuthentificationDto;
import com.example.demo.security.JwtService;
import com.example.demo.services.UtilisateurService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UtilisateurController {
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private JwtService jwtService;
	@PostMapping("/connexion")
	public Map<String, String> connexion(@RequestBody AuthentificationDto authentificationDto) {
	    try {
	        Authentication authenticate = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(authentificationDto.getEmail(), authentificationDto.getPassword()));
	        if(authenticate.isAuthenticated()) {
	            return this.jwtService.generate(authentificationDto.getEmail());
	        }
	        return Map.of("message", "Login successful");
	    } catch (Exception e) {
	        // Log the exception
	        System.err.println("Authentication failed: " + e.getMessage());
	        throw new RuntimeException("Authentication failed");
	    }
	}

}
