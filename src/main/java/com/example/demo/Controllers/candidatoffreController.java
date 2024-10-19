
package com.example.demo.Controllers;
import com.example.demo.Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.services.candidatOffreService;
import com.example.demo.services.candidatService;
import com.example.demo.services.diplomeService;
import com.example.demo.services.experienceService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidatoffre")
@CrossOrigin("*")
public class candidatoffreController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    candidatService candidatService;
    @Autowired experienceService experienceService;
    @Autowired diplomeService diplomeService;
    @Autowired candidatOffreService candidatoffreService;

    public candidatoffreController(candidatService candidatService, experienceService experienceService,
                                   diplomeService diplomeService, candidatOffreService candidatoffreService) {
        this.candidatService = candidatService;
        this.experienceService = experienceService;
        this.diplomeService = diplomeService;
        this.candidatoffreService = candidatoffreService;
    }
    @GetMapping("/all/{id}")
    public ResponseEntity<List<candidat>> get(@PathVariable int  id) {
        List<candidat> list = candidatoffreService.getByIdOffre(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("approuver/{offerid}/{candidatid}")
    public ResponseEntity<Entretien> approuverCandidature( @PathVariable int offerid, @PathVariable int candidatid,@RequestBody Entretien entretien)
    {

        Entretien updatedEntretien=candidatoffreService.approuver(offerid,candidatid,entretien);
        // Return the updated Entretien object with a 200 OK status
        return new ResponseEntity<>(updatedEntretien, HttpStatus.OK);

    // Handle exceptions and return a 500 Internal Server Error

    }
    @PostMapping("/rejeter/{offerid}/{candidatid}")
    public ResponseEntity<candidatoffre> rejeterCandidature( @PathVariable int offerid, @PathVariable int candidatid)
    {
        try{
            candidatoffre cnd=candidatoffreService.rejeter(offerid,candidatid);
            // Return the updated Entretien object with a 200 OK status
            return new ResponseEntity<>(cnd, HttpStatus.OK);}
        catch (Exception e) {
            // Handle exceptions and return a 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Optionally, provide a response body or error message
        }
    }

    @PostMapping("/add/{idoffre}")
    public ResponseEntity<candidatoffre> create(
            @PathVariable int idoffre,
            @RequestParam("candidatoffre") String candidatoffreJson,
            @RequestParam("candidat") String candidatJson,
            @RequestParam("experience") String experienceJson,
            @RequestParam("diplome") String diplomeJson,
            @RequestParam(value = "cv", required = false) MultipartFile cv,
            @RequestParam(value = "lettremotivation", required = false) MultipartFile lettremotivation) throws JsonMappingException {

        try {
            // Parse JSON strings
            JsonNode candidatoffreNode = objectMapper.readTree(candidatoffreJson);
            JsonNode candidatNode = objectMapper.readTree(candidatJson);
            JsonNode experienceNode = objectMapper.readTree(experienceJson);
            JsonNode diplomeNode = objectMapper.readTree(diplomeJson);

            // Convert JsonNode to your entity classes
            candidatoffre candidatoffre = objectMapper.convertValue(candidatoffreNode, candidatoffre.class);
            candidat candidat = objectMapper.convertValue(candidatNode,  candidat.class);
            Experience experience = objectMapper.convertValue(experienceNode, Experience.class);
            diplome diplome = objectMapper.convertValue(diplomeNode, diplome.class);

            // Handle file uploads
            if (cv != null) {
                candidat.setCv(cv.getBytes()); // Save the file content
                candidat.setCvfilename(cv.getOriginalFilename()); // Save the file name
            }

            if (lettremotivation != null) {
                candidatoffre.setLettremotivation(lettremotivation.getBytes()); // Save the file content
                candidatoffre.setLettremotivationFilename(lettremotivation.getOriginalFilename()); // Save the file name
            }

            // Save the Candidat entity (which includes the files)
            candidat cand = candidatService.ajout(candidat);

            // Continue with other entities
            experience.setCandidat(candidat);
            Experience exp = experienceService.ajout(experience);
            diplome.setCandidat(candidat);
            diplome dep = diplomeService.ajouter(diplome);
            candidatoffre.setCandidat(candidat);
            candidatoffre candoffre = candidatoffreService.candidatOffreAjout(candidatoffre, idoffre);

            return new ResponseEntity<>(candoffre, HttpStatus.CREATED);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/sendEmail")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailRequest emailRequest) {
        // Logique pour récupérer l'email du candidat en fonction de candidatId
        String email = getEmailByCandidatId(emailRequest.getCandidatId());
        System.out.println("/////////////////"+email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);

        if ("approve".equals(emailRequest.getAction())) {
            message.setSubject("Candidature Approuvée");
            message.setText("Votre candidature a été approuvée avec succès.\n" +
                    "Date de l'entretien: " + emailRequest.getInterviewDate() + "\n" +
                    "Lieu de l'entretien: Dant notre local à " + emailRequest.getInterviewLocation());
        } else if ("reject".equals(emailRequest.getAction())) {
            message.setSubject("Candidature Rejetée");
            message.setText("Bonjour \n Nous regrettons de vous informer que votre candidature a été rejetée. \nCordialement");
        }

        mailSender.send(message);
        return ResponseEntity.ok().build();
    }
    public static class EmailRequest {
        private String action;
        private int candidatId;
        private String interviewDate;
        private String interviewLocation;

        public int getCandidatId() {
            return candidatId;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getInterviewDate() {
            return interviewDate;
        }

        public void setInterviewDate(String interviewDate) {
            this.interviewDate = interviewDate;
        }

        public String getInterviewLocation() {
            return interviewLocation;
        }

        public void setInterviewLocation(String interviewLocation) {
            this.interviewLocation = interviewLocation;
        }

        public void setCandidatId(int candidatId) {
            this.candidatId = candidatId;
        }
    }

    private String getEmailByCandidatId(int candidatId) {

        return candidatoffreService.getemail(candidatId);
    }



}
