package com.tugab.jobsearchplus.repository;

import com.tugab.jobsearchplus.domain.entities.Job;
import com.tugab.jobsearchplus.domain.entities.JobHistory;
import com.tugab.jobsearchplus.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, String> {

    public List<JobHistory> findAllByUserOrderByCreatedDateDesc(User user);

    public List<JobHistory> findAllByJobOrderByCreatedDateDesc(Job job);
}
