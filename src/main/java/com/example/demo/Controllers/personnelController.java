package com.example.demo.Controllers;


import com.example.demo.Models.*;
//import com.example.demo.Repositories.RoleRepository;
import com.example.demo.Repositories.UtilisateurRepository;
import com.example.demo.Repositories.personnelRepository;
import com.example.demo.security.JwtService;
import com.example.demo.services.PersonnelService;
import com.mysql.cj.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personnel")
@CrossOrigin("*")
public class personnelController {
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
		private static final String ROLE_PERS ="ROLE_PERS";
		private static final String ROLE_Admin ="ROLE_ADMIN";
		private static final String DEFAULT_MDP ="1234";

    @Autowired
    PersonnelService personnelservice;
    @Autowired
    JwtService jwtService;
    @Autowired
    personnelRepository personnelRepository;
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable int id) {
    	Personnel personnel  = personnelservice.getPersonnelById(id);
        if (personnel != null) {
            personnelservice.deletePersonnel(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Personnel>> getAll() {
        List<Personnel> list = personnelservice.getAllPersonnel();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/fichepaiepers")
    public ResponseEntity<List<Personnel>> getfichepaiepers( @RequestParam(value = "id") int ClientId) {
    	Personnel personnel = personnelservice.getpersonnelbyid(ClientId);
        List<Personnel> list = new ArrayList<>();
        list.add(personnel);
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
	public ResponseEntity<Personnel> createPersonnel(@RequestBody Personnel personnel) {
		personnel.setSoldeconge(30);
		if (personnel.getMotdepasse() == null || personnel.getMotdepasse().isEmpty() || 
			    personnel.getPassword() == null || personnel.getPassword().isEmpty()) {
			    personnel.setMotdepasse(DEFAULT_MDP);
			}
		String mdpcry = this.passwordEncoder.encode(personnel.getMotdepasse());
		if (personnel.getEmail().indexOf("@") == -1)
			throw new RuntimeException("votre mail est incorrect ");
		Optional<User> userOp = this.utilisateurRepository.findByEmail(personnel.getEmail());
		if (userOp.isPresent())
			throw new RuntimeException("votre mail est utilisé ");
		personnel.setMotdepasse(mdpcry);
		personnel.setActif(true);
		if(!StringUtils.isNullOrEmpty(personnel.getRole_User())) {
			personnel.setRole_User(personnel.getRole_User());
		}else {
			personnel.setRole_User(ROLE_Admin);

		}
		Personnel savedPersonnel = personnelRepository.save(personnel);
		return ResponseEntity.ok(savedPersonnel);
	}
    @PutMapping("/modif/{id}")
	public ResponseEntity<PersonnelDTO> updatefiche(@PathVariable int id, @RequestBody PersonnelDTO updatedPersonnelDTO) {
			// Récupérer le personnel existant par ID
			Personnel existingPersonnel = personnelservice.getPersonnelById(id);

			if (existingPersonnel != null) {
				// Mapper le DTO vers l'entité
				Personnel updatedPersonnel = PersonnelMapper.toEntity(updatedPersonnelDTO);

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
    @PutMapping("/modifpwd")
	public ResponseEntity<Personnel> updatefiche(@RequestBody ChangePassword changePassword) throws Exception {
			// Récupérer le personnel existant par ID
			Personnel existingPersonnel = personnelservice.getPersonnelById(changePassword.getId());
			if (existingPersonnel != null) {
				// Mettez à jour l'entité existante avec les nouvelles valeurs
				Personnel pers = personnelservice.updatePwd(changePassword);

				return new ResponseEntity<>(pers, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}		
	}
  //getByID
  	@GetMapping("/getpersonnel")
  	public ResponseEntity<Personnel> getClientByIdJson(@RequestHeader(value = "Authorization") String Jwt, @RequestParam(value = "id") int ClientId) throws Exception {
  		Personnel client = null;
  		try {
  				client = personnelservice.getPersonnelById(ClientId);
  		} catch (Exception e) {
  			throw new Exception("Incorrect ", e);
  		}
  		return ResponseEntity.ok().body(client);
  	}
}
