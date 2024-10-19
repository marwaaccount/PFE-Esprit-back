package com.example.demo.Repositories;

import com.example.demo.Models.OffreEmploi;
import com.example.demo.Models.candidat;
import com.example.demo.Models.candidatoffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CandidatOffreRepository extends JpaRepository<candidatoffre, Integer> {

    @Query("SELECT c FROM candidatoffre c WHERE c.offre = :offre AND c.candidat = :candidat")
    candidatoffre findByOffreAndCandidat(@Param("offre") OffreEmploi offre, @Param("candidat") candidat candidat);
}
