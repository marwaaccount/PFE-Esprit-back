package com.example.demo.Repositories;

import com.example.demo.Models.diplome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface diplomeRepository extends JpaRepository<diplome, Integer > {
}
