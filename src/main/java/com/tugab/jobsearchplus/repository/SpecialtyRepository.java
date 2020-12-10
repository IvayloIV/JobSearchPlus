package com.tugab.jobsearchplus.repository;

import com.tugab.jobsearchplus.domain.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    public List<Specialty> findAllByOrderByCreatedDateDesc();
}
