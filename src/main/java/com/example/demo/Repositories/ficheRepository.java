package com.example.demo.Repositories;


import com.example.demo.Models.fichedepaie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ficheRepository extends JpaRepository<fichedepaie, Integer>
{

}
