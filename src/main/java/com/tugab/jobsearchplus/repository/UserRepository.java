package com.tugab.jobsearchplus.repository;

import com.tugab.jobsearchplus.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByFacultyNumber(String facultyNumber);
}
