package com.tugab.jobsearchplus.service;

import com.tugab.jobsearchplus.domain.entities.User;
import com.tugab.jobsearchplus.domain.models.services.*;

import java.util.List;

public interface JobService {

    public JobFilterServiceModel getJobs(Integer page, String jobName, String companyName, String region);

    public JobServiceModel getDetails(Long recordId);

    public void changeJobStatus(User user, Long recordId, String status);

    public JobHistoryServiceModel getLastUserJob(UserServiceModel userServiceModel);

    public List<JobHistoryServiceModel> getJobsHistoryByJob(JobServiceModel jobServiceModel);

    public List<JobHistoryServiceModel> getJobsHistoryByUser(UserServiceModel userServiceModel);

    public List<JobStatusServiceModel> getJobsStatus();
}
