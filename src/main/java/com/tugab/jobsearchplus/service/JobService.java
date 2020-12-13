package com.tugab.jobsearchplus.service;

import com.tugab.jobsearchplus.domain.models.services.JobServiceModel;

import java.util.List;

public interface JobService {

    public long getPageCount();

    public List<JobServiceModel> getJobs(Integer page);
}
