package com.tugab.jobsearchplus.service;

import com.tugab.jobsearchplus.domain.entities.User;
import com.tugab.jobsearchplus.domain.models.services.*;

public interface JobService {

    public JobFilterServiceModel getJobs(Integer page, String jobName, String companyName, String region);

    public JobServiceModel getDetails(Long recordId);

    public void changeJobStatus(User user, Long recordId, String status);

    public JobHistoryServiceModel getLastUserJob(UserServiceModel userServiceModel);
}
