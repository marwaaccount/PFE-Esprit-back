package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.absenceService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/absence")
@CrossOrigin("*")
public class absenceController {

    @Autowired
    absenceService absenceService;
    @Autowired
    PersonnelService personnelservice;

    @GetMapping("/{id}")
    public int getsoldecng(@PathVariable int id) {
      return this.absenceService.getsolde(id);

    }

    @PostMapping("/add/{id}")
    public ResponseEntity<absence> create(@RequestBody absence absence , @PathVariable int id) {
        absence abs=absenceService.ajouter(absence,id);
        return new ResponseEntity<>(abs, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<absence>> getAll() {
        List<absence> absences = absenceService.getAbsence();
        System.out.println("Fetched absences: " + absences);
        return new ResponseEntity<>(absences, HttpStatus.OK);
}


    /*@GetMapping("/allattente")
    public ResponseEntity<List<absence>> getAttente() {
        List<absence> absences = absenceService.getAbs();
        System.out.println("Fetched absences: " + absences);
        return new ResponseEntity<>(absences, HttpStatus.OK);
    }*/
    @GetMapping("/allattente")
    public ResponseEntity<List<absenceDTO>> getAttente() {
        List<absence> absences = absenceService.getAbs();
        List<absenceDTO> absenceDTOs = absences.stream().map(a -> {
            absenceDTO dto = new absenceDTO();
            dto.setId(a.getId());
            dto.setMotif(a.getMotif());
            dto.setEtat(a.getEtat());
            dto.setJustificatif(a.getJustificatif());
            dto.setDatedebut(a.getDatedebut());
            dto.setDatefin(a.getDatefin());
            dto.setType(a.getType());
            dto.setNom(a.getPersonnel() != null ? a.getPersonnel().getNom() : null);
            dto.setPrenom((a.getPersonnel() != null ? a.getPersonnel().getPrenom() : null));
            return dto;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(absenceDTOs, HttpStatus.OK);
    }

    @GetMapping("/getpersonnelById/{id}")
    public personnel getpersonnel( @PathVariable int id) {
        personnel p = personnelservice.getPersonnelById(id);

        return new ResponseEntity<>(p, HttpStatus.OK).getBody();
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        absence abs=absenceService.getabsencebyid(id);
        if (abs != null) {
            absenceService.deleteabsence(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modif/{id}")
    public ResponseEntity<absence> update(@PathVariable int id, @RequestBody absence absence) {
       absence abs=absenceService.getabsencebyid(id);
        if (abs != null)
        {

            abs.setDatedebut(absence.getDatedebut());
            abs.setDatefin(absence.getDatefin());
            abs.setJustificatif(absence.getJustificatif());
            abs.setType(absence.getType());
            absenceService.updateabsence(abs);

            return new ResponseEntity<>(abs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/approuver")
    public ResponseEntity<Void> approveRequest(@PathVariable int id, @RequestBody ApproveRequest request) {
        int nb = request.getNb();
        System.out.println(nb);
        absenceService.approve(id, nb);
        System.out.println("trace");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Endpoint pour rejeter une demande
    @PutMapping("/{id}/rejeter")
    public ResponseEntity<Void> rejectRequest(@PathVariable int id, @RequestBody RejectRequest request) {
        String motif = request.getMotif();
        absenceService.reject(id, motif);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
