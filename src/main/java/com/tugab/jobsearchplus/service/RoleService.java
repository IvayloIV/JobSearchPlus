package com.tugab.jobsearchplus.service;

import com.tugab.jobsearchplus.domain.entities.Role;
import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;

import java.util.List;

public interface RoleService {

    public void saveRolesToDb();

    public void addUserRole(UserServiceModel userServiceModel);

    public boolean userHasRole(List<Role> roles, String roleName);
}
