package com.tugab.jobsearchplus.service.impl;

import com.tugab.jobsearchplus.domain.entities.Role;
import com.tugab.jobsearchplus.domain.models.services.RoleServiceModel;
import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;
import com.tugab.jobsearchplus.repository.RoleRepository;
import com.tugab.jobsearchplus.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveRolesToDb() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.save(new Role("USER"));
            this.roleRepository.save(new Role("ADMIN"));
        }
    }

    @Override
    public void addUserRole(UserServiceModel userServiceModel) {
        Role role = this.roleRepository.findByAuthority("USER");
        RoleServiceModel roleServiceModel = this.modelMapper.map(role, RoleServiceModel.class);
        userServiceModel.getRoles().add(roleServiceModel);
    }

    @Override
    public boolean userHasRole(List<Role> roles, String roleName) {
        Role role = this.roleRepository.findByAuthority(roleName);
        return roles.stream().anyMatch(r -> r.getId().equals(role.getId()));
    }
}
