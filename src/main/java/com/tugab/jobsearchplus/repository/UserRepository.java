package com.tugab.jobsearchplus.repository;

import com.tugab.jobsearchplus.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByFacultyNumber(String facultyNumber);

    @Query(value = "select u from User u " +
            "inner join u.roles r " +
            "where (:name is null or lower(u.name) like lower(concat('%', :name, '%')) or lower(u.surname) like lower(concat('%', :name, '%'))) " +
            "and (:facultyNumber is null or u.facultyNumber like concat('%', :facultyNumber, '%')) " +
            "and (:jobStatusId is null or u.jobStatus.id = :jobStatusId) " +
            "and (:specialtyId is null or u.specialty.id = :specialtyId)" +
            "and r.authority = 'USER'")
    public Page<User> findAll(@Param("name") String name,
                              @Param("facultyNumber") String facultyNumber,
                              @Param("jobStatusId") Long jobStatusId,
                              @Param("specialtyId") Long specialtyId,
                              Pageable pageable);
}
