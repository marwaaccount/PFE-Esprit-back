package com.example.demo.Repositories;

import com.example.demo.Models.OffreEmploi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
@Repository
public interface OffreRepository extends JpaRepository<OffreEmploi, Integer>
{

     @Query("SELECT j FROM OffreEmploi j WHERE (:title IS NULL OR j.titre LIKE %:title%) AND (:location IS NULL OR j.localisation LIKE %:location%) AND (:contractType IS NULL OR j.typeContrat = :contractType)")
        List<OffreEmploi> findByCriteria(@Param("title") String title,
                                      @Param("location") String location,
                                      @Param("contractType") String contractType);


}


