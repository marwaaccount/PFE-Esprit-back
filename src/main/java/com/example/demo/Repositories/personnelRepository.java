package com.example.demo.Repositories;


import com.example.demo.Models.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface personnelRepository extends JpaRepository<Personnel, Integer> {

}
