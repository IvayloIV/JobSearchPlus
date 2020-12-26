package com.tugab.jobsearchplus.repository;

import com.tugab.jobsearchplus.domain.entities.JobStatus;
import com.tugab.jobsearchplus.domain.enums.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobStatusRepository extends JpaRepository<JobStatus, Long> {

    public Optional<JobStatus> findByName(JobPosition jobPosition);
}
