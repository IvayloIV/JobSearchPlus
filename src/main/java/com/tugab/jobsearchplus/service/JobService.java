package com.tugab.jobsearchplus.service;

import com.tugab.jobsearchplus.domain.models.services.JobFilterServiceModel;
import com.tugab.jobsearchplus.domain.models.services.JobServiceModel;

public interface JobService {

    public JobFilterServiceModel getJobs(Integer page, String jobName, String companyName, String region);

    public JobServiceModel getDetails(Long recordId);
}
