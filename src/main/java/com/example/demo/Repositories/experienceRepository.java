package com.example.demo.Repositories;

import com.example.demo.Models.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface experienceRepository extends JpaRepository<Experience ,Integer> {
}
