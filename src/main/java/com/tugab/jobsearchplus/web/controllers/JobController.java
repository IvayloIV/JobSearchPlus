package com.tugab.jobsearchplus.web.controllers;

import com.tugab.jobsearchplus.domain.models.services.JobFilterServiceModel;
import com.tugab.jobsearchplus.domain.models.views.JobFilterViewModel;
import com.tugab.jobsearchplus.domain.models.views.JobListViewModel;
import com.tugab.jobsearchplus.service.JobService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/job")
public class JobController extends BaseController {

    private final JobService jobService;
    private final ModelMapper modelMapper;

    @Autowired
    public JobController(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(value = "jobName", required = false) String jobName,
                             @RequestParam(value = "companyName", required = false) String companyName,
                             @RequestParam(value = "region", required = false) String region,
                             ModelAndView modelAndView) {//TODO: if string is passed in page
        JobFilterServiceModel jobsServiceModel = this.jobService.getJobs(page, jobName, companyName, region);

        List<JobListViewModel> jobsViewModel = jobsServiceModel.getJobServiceModels().stream()
                .map(job -> this.modelMapper.map(job, JobListViewModel.class))
                .collect(Collectors.toList());

        JobFilterViewModel jobFilterViewModel = new JobFilterViewModel();
        jobFilterViewModel.setCurrentPage(page);
        jobFilterViewModel.setTotalPages(jobsServiceModel.getPageCount());
        jobFilterViewModel.setJobName(jobName);
        jobFilterViewModel.setCompanyName(companyName);
        jobFilterViewModel.setRegion(region);

        modelAndView.addObject("jobsViewModel", jobsViewModel);
        modelAndView.addObject("jobFilterViewModel", jobFilterViewModel);
        return super.view("/job/list", modelAndView);
    }
}
