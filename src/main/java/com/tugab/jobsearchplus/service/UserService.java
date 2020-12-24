package com.tugab.jobsearchplus.service;

import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public boolean registerUser(UserServiceModel userServiceModel);

    public UserServiceModel getUserByFacultyNumber(String facultyNumber);

    public Page<UserServiceModel> getUsers(Integer page, String name, String facultyNumber, Long jobStatusId, Long specialtyId);
}
