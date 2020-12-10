package com.tugab.jobsearchplus.service;

import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public boolean registerUser(UserServiceModel userServiceModel);
}
