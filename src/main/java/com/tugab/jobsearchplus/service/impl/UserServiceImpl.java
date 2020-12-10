package com.tugab.jobsearchplus.service.impl;

import com.tugab.jobsearchplus.domain.entities.User;
import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;
import com.tugab.jobsearchplus.repository.RoleRepository;
import com.tugab.jobsearchplus.repository.UserRepository;
import com.tugab.jobsearchplus.service.RoleService;
import com.tugab.jobsearchplus.service.UserService;
import com.tugab.jobsearchplus.utils.FileUploader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final String PICTURE_PATH = "image/profile";

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final FileUploader fileUploader;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper,
                           UserRepository userRepository,
                           RoleService roleService,
                           FileUploader fileUploader,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.fileUploader = fileUploader;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        this.roleService.saveRolesToDb();
        this.roleService.addUserRole(userServiceModel);
        userServiceModel.setPassword(bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        String pictureName = this.fileUploader.upload(PICTURE_PATH, userServiceModel.getProfilePicture());

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPictureName(pictureName);

        try {
            this.userRepository.save(user);
            return true;
        } catch (RuntimeException ex) {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String facultyNumber) throws UsernameNotFoundException {
        return this.userRepository.findByFacultyNumber(facultyNumber).orElse(null);
    }
}
