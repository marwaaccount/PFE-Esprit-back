package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Models.User;
import com.example.demo.Repositories.UtilisateurRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
public class UtilisateurService implements UserDetailsService{
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
		private static final String ROLE_PERS ="PERS";
		private static final String ROLE_Admin ="ADMIN";

	public void inscription(User user) {
		
		String mdpcry = this.passwordEncoder.encode(user.getMotdepasse());
			if(user.getEmail().indexOf("@") == -1) throw new RuntimeException("votre mail est incorrect ");
			Optional<User> userOp = this.utilisateurRepository.findByEmail(user.getEmail());
			if(userOp.isPresent()) throw new RuntimeException("votre mail est utilisé ");
				user.setMotdepasse(mdpcry);
				user.setRole_User(ROLE_PERS);
			this.utilisateurRepository.save(user);
	}

	@Override
	public User loadUserByUsername(String email) throws UsernameNotFoundException {
		return this.utilisateurRepository
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("invalide cet identifiat"));
 
	}

}
