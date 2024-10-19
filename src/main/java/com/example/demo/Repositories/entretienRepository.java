package com.example.demo.Repositories;

import com.example.demo.Models.Entretien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface entretienRepository extends JpaRepository<Entretien,Integer> {
}
