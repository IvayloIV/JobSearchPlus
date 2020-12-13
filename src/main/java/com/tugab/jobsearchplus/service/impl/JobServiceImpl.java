package com.tugab.jobsearchplus.service.impl;

import com.tugab.jobsearchplus.domain.models.services.JobServiceModel;
import com.tugab.jobsearchplus.domain.models.services.JobsServiceModel;
import com.tugab.jobsearchplus.service.JobService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private static final int JOBS_PER_PAGE = 20;

    private final WebClient webClient;

    private List<JobServiceModel> jobs;

    @Value("${jobTiger.jobsUrl}")
    private String jobsUrl;

    public JobServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public long getPageCount() {
        long totalJobs = this.getJobs().size();
        if (totalJobs == 0) {
            return 1;
        }

        return (long) Math.ceil(totalJobs / (double) JOBS_PER_PAGE);
    }

    @Override
    public List<JobServiceModel> getJobs(Integer page) {
        long startJobIndex = (page - 1) * JOBS_PER_PAGE;

        return this.getJobs().stream()
                .skip(startJobIndex)
                .limit(JOBS_PER_PAGE)
                .collect(Collectors.toList());
    }

    private List<JobServiceModel> getJobs() { //TODO: move to repository??
        if (jobs == null) {
            WebClient.RequestBodySpec requestBodySpec = this.webClient
                    .method(HttpMethod.GET)
                    .uri(this.jobsUrl);

            JobsServiceModel jobs = requestBodySpec
                    .exchangeToMono(j -> j.bodyToMono(JobsServiceModel.class))
                    .block();

            this.jobs = jobs.getJobs();
        }

        return this.jobs;
    }
}
