package com.example.demo.Repositories;


import com.example.demo.Models.candidat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<candidat, Integer>
{
}
