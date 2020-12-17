package com.tugab.jobsearchplus.repository;

import com.tugab.jobsearchplus.domain.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
