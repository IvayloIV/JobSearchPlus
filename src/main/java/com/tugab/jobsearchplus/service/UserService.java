package com.tugab.jobsearchplus.service;

import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public boolean registerUser(UserServiceModel userServiceModel);

    public UserServiceModel getUserByFacultyNumber(String facultyNumber);

    public List<UserServiceModel> getAllUsers();
}
