package com.tugab.jobsearchplus.service;

import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;

public interface RoleService {

    public void saveRolesToDb();

    public void addUserRole(UserServiceModel userServiceModel);
}
