package com.tugab.jobsearchplus.web.controllers;

import com.tugab.jobsearchplus.domain.entities.User;
import com.tugab.jobsearchplus.domain.models.services.JobFilterServiceModel;
import com.tugab.jobsearchplus.domain.models.services.JobHistoryServiceModel;
import com.tugab.jobsearchplus.domain.models.services.JobServiceModel;
import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;
import com.tugab.jobsearchplus.domain.models.views.*;
import com.tugab.jobsearchplus.service.JobService;
import com.tugab.jobsearchplus.service.RoleService;
import com.tugab.jobsearchplus.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/job")
public class JobController extends BaseController {

    private final JobService jobService;
    private final UserService userService;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public JobController(JobService jobService,
                         UserService userService,
                         RoleService roleService,
                         ModelMapper modelMapper) {
        this.jobService = jobService;
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(value = "jobTitle", required = false) String jobTitle,
                             @RequestParam(value = "companyName", required = false) String companyName,
                             @RequestParam(value = "region", required = false) String region,
                             ModelAndView modelAndView) {//TODO: if string is passed in page
        JobFilterServiceModel jobsServiceModel = this.jobService.getJobs(page, jobTitle, companyName, region);

        List<JobListViewModel> jobsViewModel = jobsServiceModel.getJobServiceModels().stream()
                .map(job -> this.modelMapper.map(job, JobListViewModel.class))
                .collect(Collectors.toList());

        JobFilterViewModel jobFilterViewModel = new JobFilterViewModel();
        jobFilterViewModel.setCurrentPage(page);
        jobFilterViewModel.setTotalPages(jobsServiceModel.getPageCount());
        jobFilterViewModel.setJobTitle(jobTitle);
        jobFilterViewModel.setCompanyName(companyName);
        jobFilterViewModel.setRegion(region);

        modelAndView.addObject("jobsViewModel", jobsViewModel);
        modelAndView.addObject("jobFilterViewModel", jobFilterViewModel);
        return super.view("/job/list", modelAndView);
    }

    @GetMapping("/details/{recordId}")
    public ModelAndView details(@PathVariable("recordId") Long recordId,
                                @AuthenticationPrincipal User user,
                                ModelAndView modelAndView) {
        JobServiceModel jobServiceModel = this.jobService.getDetails(recordId);
        JobDetailsViewModel jobModelView = this.modelMapper.map(jobServiceModel, JobDetailsViewModel.class);

        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        JobHistoryServiceModel lastUserJob = this.jobService.getLastUserJob(userServiceModel);

        JobStatusViewModel jobStatusViewModel = this.modelMapper.map(user.getJobStatus(), JobStatusViewModel.class);

        List<JobHistoryServiceModel> jobsHistory = this.jobService.getJobsHistoryByJob(jobServiceModel);
        List<JobHistoryDetailsViewModel> jobsHistoryViewModel = jobsHistory.stream()
                .map(j -> this.modelMapper.map(j, JobHistoryDetailsViewModel.class))
                .collect(Collectors.toList());

        if (lastUserJob != null) {
            Long lastUserJobId = lastUserJob.getJob().getRecordId();
            modelAndView.addObject("lastUserJobId", lastUserJobId);
        }

        modelAndView.addObject("jobModelView", jobModelView);
        modelAndView.addObject("jobsHistoryViewModel", jobsHistoryViewModel);
        modelAndView.addObject("userStatus", jobStatusViewModel);
        return super.view("/job/details", modelAndView);
    }

    @PostMapping("/{recordId}/status/{statusId}")
    public ModelAndView status(@PathVariable("recordId") Long recordId,
                               @PathVariable("statusId") String status,
                               @AuthenticationPrincipal User user,
                               String facultyNumber,
                               String redirectUrl) {
        boolean isAdmin = this.roleService.userHasRole(user.getRoles(), "ADMIN");
        if (facultyNumber != null && isAdmin) {
            UserServiceModel userServiceModel = this.userService.getUserByFacultyNumber(facultyNumber);
            user = this.modelMapper.map(userServiceModel, User.class);
        }

        this.jobService.changeJobStatus(user, recordId, status);
        return super.redirect(redirectUrl);
    }
}
