package com.tugab.jobsearchplus.service.impl;

import com.tugab.jobsearchplus.domain.entities.User;
import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;
import com.tugab.jobsearchplus.repository.UserRepository;
import com.tugab.jobsearchplus.service.RoleService;
import com.tugab.jobsearchplus.service.UserService;
import com.tugab.jobsearchplus.utils.FileUploader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String PICTURE_PATH = "image/profile";
    private static final Integer USERS_PER_PAGE = 2;//8

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
    public UserServiceModel getUserByFacultyNumber(String facultyNumber) {
        Optional<User> user = this.userRepository.findByFacultyNumber(facultyNumber);
        return user.map(u -> this.modelMapper.map(u, UserServiceModel.class)).orElse(null);
    }

    @Override
    public Page<UserServiceModel> getUsers(Integer page, String name, String facultyNumber, Long jobStatusId, Long specialtyId) {
        Sort sortUsers = Sort.by("facultyNumber").descending();
        Pageable pageable = PageRequest.of(page - 1, USERS_PER_PAGE, sortUsers);

        return this.userRepository
                .findAll(name, facultyNumber, jobStatusId, specialtyId, pageable)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class));
    }

    @Override
    public UserDetails loadUserByUsername(String facultyNumber) throws UsernameNotFoundException {
        return this.userRepository.findByFacultyNumber(facultyNumber).orElse(null);
    }
}
