package com.tugab.jobsearchplus.service.impl;

import com.tugab.jobsearchplus.domain.models.services.JobFilterServiceModel;
import com.tugab.jobsearchplus.domain.models.services.JobServiceModel;
import com.tugab.jobsearchplus.domain.models.services.JobsServiceModel;
import com.tugab.jobsearchplus.service.JobService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public JobFilterServiceModel getJobs(Integer page, String jobName, String companyName, String region) {
        long startJobIndex = (page - 1) * JOBS_PER_PAGE;
        Stream<JobServiceModel> jobsStream = this.getJobs().stream();

        if (jobName != null) {
            jobsStream = this.addJobFilter(jobsStream, jobName, JobServiceModel::getTitle); //TODO: change title to jobName?
        }

        if (companyName != null) {
            jobsStream = this.addJobFilter(jobsStream, companyName, JobServiceModel::getCompanyName);
        }

        if (region != null) {
            jobsStream = this.addJobFilter(jobsStream, region, JobServiceModel::getRegion);
        }

        List<JobServiceModel> jobServiceModels = jobsStream
                .collect(Collectors.toList());

        long pageCount = this.getPageCount(jobServiceModels);
        List<JobServiceModel> currentJobsPage = jobServiceModels.stream()
                .skip(startJobIndex)
                .limit(JOBS_PER_PAGE)
                .collect(Collectors.toList());

        return new JobFilterServiceModel(currentJobsPage, pageCount);
    }

    @Override
    public JobServiceModel getDetails(Long recordId) {
        Optional<JobServiceModel> job = this.getJobs().stream()
                .filter(j -> j.getRecordId().equals(recordId))
                .findFirst();

        return job.orElse(null);
    }

    private long getPageCount(List<JobServiceModel> jobs) {
        long totalJobs = jobs.size();
        if (totalJobs == 0) {
            return 1;
        }

        return (long) Math.ceil(totalJobs / (double) JOBS_PER_PAGE);
    }

    private Stream<JobServiceModel> addJobFilter(Stream<JobServiceModel> jobsStream, String filterValue, Function<JobServiceModel, String> getSearchField) {
        return jobsStream
                .filter(j -> getSearchField.apply(j).toLowerCase().contains(filterValue.toLowerCase()));
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
