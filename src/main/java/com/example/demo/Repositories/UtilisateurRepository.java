package com.example.demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.User;
@Repository
public interface UtilisateurRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String Email);

}
