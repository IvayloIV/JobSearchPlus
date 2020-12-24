package com.tugab.jobsearchplus.service.impl;

import com.tugab.jobsearchplus.domain.entities.Job;
import com.tugab.jobsearchplus.domain.entities.JobHistory;
import com.tugab.jobsearchplus.domain.entities.JobStatus;
import com.tugab.jobsearchplus.domain.entities.User;
import com.tugab.jobsearchplus.domain.models.services.*;
import com.tugab.jobsearchplus.repository.JobHistoryRepository;
import com.tugab.jobsearchplus.repository.JobRepository;
import com.tugab.jobsearchplus.repository.JobStatusRepository;
import com.tugab.jobsearchplus.repository.UserRepository;
import com.tugab.jobsearchplus.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JobServiceImpl implements JobService {

    private static final int JOBS_PER_PAGE = 20;

    private final JobRepository jobRepository;
    private final JobStatusRepository jobStatusRepository;
    private final JobHistoryRepository jobHistoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final WebClient webClient;

    private List<JobServiceModel> jobs;

    @Value("${jobTiger.jobsUrl}")
    private String jobsUrl;

    public JobServiceImpl(JobRepository jobRepository,
                          JobStatusRepository jobStatusRepository,
                          UserRepository userRepository,
                          JobHistoryRepository jobHistoryRepository,
                          ModelMapper modelMapper,
                          WebClient webClient) {
        this.jobRepository = jobRepository;
        this.jobStatusRepository = jobStatusRepository;
        this.jobHistoryRepository = jobHistoryRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.webClient = webClient;
    }

    @Override
    public JobFilterServiceModel getJobs(Integer page, String jobName, String companyName, String region) {
        long startJobIndex = (page - 1) * JOBS_PER_PAGE;
        Stream<JobServiceModel> jobsStream = this.getJobs().stream();

        if (jobName != null) {
            jobsStream = this.addJobFilter(jobsStream, jobName, JobServiceModel::getTitle);
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
        Optional<JobServiceModel> jobServiceModel = this.getJobs().stream()
                .filter(j -> j.getRecordId().equals(recordId))
                .findFirst();

        if (!jobServiceModel.isPresent()) {
            Optional<Job> job = this.jobRepository.findById(recordId);

            if (job.isPresent()) {
                jobServiceModel = Optional.of(this.modelMapper.map(job.get(), JobServiceModel.class));
            }
        }

        return jobServiceModel.orElse(null);
    }

    @Override
    public void changeJobStatus(User user, Long recordId, String status) {
        JobServiceModel jobServiceModel = this.getJobs().stream()
                .filter(j -> j.getRecordId().equals(recordId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Job does not exist!"));
        Job job = this.modelMapper.map(jobServiceModel, Job.class);

        boolean jobExist = this.jobRepository.existsById(recordId);
        if (!jobExist) {
            this.jobRepository.save(job);
        }

        JobStatus newJobStatus = this.jobStatusRepository.findByName(status)
                .orElseThrow(() -> new IllegalArgumentException("Job status does not exist!"));

        JobHistory jobHistory = new JobHistory();
        jobHistory.setUser(user);
        jobHistory.setJob(job);
        jobHistory.setOldStatus(user.getJobStatus());
        jobHistory.setNewStatus(newJobStatus);
        jobHistory.setCreatedDate(new Date());

        List<JobHistory> jobsHistory = user.getJobsHistory();
        jobsHistory.add(jobHistory);

        user.setJobStatus(newJobStatus);
        this.userRepository.save(user);
    }

    @Override
    public JobHistoryServiceModel getLastUserJob(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        List<JobHistory> jobsHistory = this.jobHistoryRepository.findAllByUserOrderByCreatedDateDesc(user);

        if (jobsHistory.size() == 0) {
            return null;
        }

        return this.modelMapper.map(jobsHistory.get(0), JobHistoryServiceModel.class);
    }

    @Override
    public List<JobHistoryServiceModel> getJobsHistoryByJob(JobServiceModel jobServiceModel) {
        Job job = this.modelMapper.map(jobServiceModel, Job.class);
        List<JobHistory> jobsHistory = this.jobHistoryRepository.findAllByJobOrderByCreatedDateDesc(job);

        return jobsHistory.stream()
                .map(j -> this.modelMapper.map(j, JobHistoryServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobHistoryServiceModel> getJobsHistoryByUser(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        List<JobHistory> jobsHistory = this.jobHistoryRepository.findAllByUserOrderByCreatedDateDesc(user);

        return jobsHistory.stream()
                .map(j -> this.modelMapper.map(j, JobHistoryServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobStatusServiceModel> getJobsStatus() {
        return this.jobStatusRepository.findAll()
                .stream()
                .map(js -> this.modelMapper.map(js, JobStatusServiceModel.class))
                .collect(Collectors.toList());
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
