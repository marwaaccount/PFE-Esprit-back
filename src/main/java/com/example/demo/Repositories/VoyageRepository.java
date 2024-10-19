package com.example.demo.Repositories;

import com.example.demo.Models.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Integer> {
}

