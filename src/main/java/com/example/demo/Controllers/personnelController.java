package com.example.demo.Controllers;


import com.example.demo.Models.*;
import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.personnelRepository;
import com.example.demo.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/personnel")
@CrossOrigin("*")
public class personnelController {

    @Autowired
    PersonnelService personnelservice;
    @Autowired
    RoleRepository RoleRepository;
    @Autowired
    personnelRepository personnelRepository;
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable int id) {
        personnel personnel  = personnelservice.getPersonnelById(id);
        if (personnel != null) {
            personnelservice.deletePersonnel(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<personnel>> getAll() {
        List<personnel> list = personnelservice.getAllPersonnel();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("bycin/{cin}")
    public ResponseEntity<String> getByCin(@PathVariable int cin) {
        String nom = personnelservice.getNomByCin(cin);
        return new ResponseEntity<>(nom, HttpStatus.OK);
    }
   /* @PostMapping("/add")
    public ResponseEntity<personnel> createOffre(@RequestBody personnel personnel) {
        personnel createdpersonnel = personnelservice.createPersonnel(personnel);
        return new ResponseEntity<>(createdpersonnel, HttpStatus.CREATED);
    }*/
   @PostMapping("/add")
   public ResponseEntity<personnel> createPersonnel(@RequestBody personnel personnel) {
       if (personnel.getRole() == null) {
           return ResponseEntity.badRequest().body(null); // Renvoyer une erreur si le rôle est manquant
       }
       Optional<role> optionalRole = RoleRepository.findById(personnel.getRole().getId());
       if (!optionalRole.isPresent()) {
           return ResponseEntity.badRequest().body(null); // Renvoyer une erreur si le rôle n'existe pas
       }
       role existingRole = optionalRole.get(); // Obtenir le rôle existant
       personnel.setRole(existingRole); // Assigner le rôle existant
       personnel.setSoldeconge(30);

       personnel savedPersonnel = personnelRepository.save(personnel);
       return ResponseEntity.ok(savedPersonnel);
   }
   /* @PostMapping("/add")
    public ResponseEntity<PersonnelDTO> createOffre(@RequestBody PersonnelDTO personnelDTO) {
        personnel personnel = PersonnelMapper.toEntity(personnelDTO);
        personnel createdPersonnel = personnelservice.createPersonnel(personnel);
        PersonnelDTO createdPersonnelDTO = PersonnelMapper.toDTO(createdPersonnel);
        return new ResponseEntity<>(createdPersonnelDTO, HttpStatus.CREATED);
    } */

    @PutMapping("/modif/{id}")
    public ResponseEntity<PersonnelDTO> updatefiche(@PathVariable int id, @RequestBody PersonnelDTO updatedPersonnelDTO) {
        // Récupérer le personnel existant par ID
        personnel existingPersonnel = personnelservice.getPersonnelById(id);

        if (existingPersonnel != null) {
            // Mapper le DTO vers l'entité
            personnel updatedPersonnel = PersonnelMapper.toEntity(updatedPersonnelDTO);

            // Mettez à jour l'entité existante avec les nouvelles valeurs
            updatedPersonnel.setId(existingPersonnel.getId()); // Assurez-vous de conserver l'ID
            personnelservice.updatePersonnel(updatedPersonnel);

            // Mapper l'entité mise à jour vers le DTO
            PersonnelDTO responseDTO = PersonnelMapper.toDTO(updatedPersonnel);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  /*  @PutMapping("/modif/{id}")
    public ResponseEntity<personnel> updatePersonel(@PathVariable int id, @RequestBody personnel updatedpersonnel) {
        personnel personnel = personnelservice.getPersonnelById(id);
        if (personnel != null)
        {

            personnelservice.updatePersonnel(personnel);

            return new ResponseEntity<>(personnel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } */
}
