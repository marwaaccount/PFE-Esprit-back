package com.example.demo.Repositories;

import com.example.demo.Models.OffreEmploi;
import com.example.demo.Models.absence;
import com.example.demo.Models.candidat;
import com.example.demo.Models.candidatoffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<absence,Integer> {
    @Query("SELECT a FROM absence a JOIN a.personnel p WHERE a.Etat like 'en attente%' group by a.id")
    List<absence> findAllWithPersonnel();
}
