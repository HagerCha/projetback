package com.MissionNDF.springjwt.repository;

import com.MissionNDF.springjwt.models.DemandeNDF;
import com.MissionNDF.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeNDFRepository extends JpaRepository<DemandeNDF, Long> {


}
