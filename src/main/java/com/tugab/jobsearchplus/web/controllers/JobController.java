package com.tugab.jobsearchplus.web.controllers;

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
    public ModelAndView list(@RequestParam(value = "page", required = false) Integer page,
                             ModelAndView modelAndView) {//TODO: if string is passed in page
        if (page == null) {
            page = 1;
        }
        List<JobListViewModel> jobsViewModel = this.jobService.getJobs(page).stream()
                .map(job -> this.modelMapper.map(job, JobListViewModel.class))
                .collect(Collectors.toList());

        long totalPages = this.jobService.getPageCount();

        modelAndView.addObject("jobsViewModel", jobsViewModel);
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("totalPages", totalPages);
        return super.view("/job/list", modelAndView);
    }
}
