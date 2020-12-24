package com.tugab.jobsearchplus.domain.models.views;

import java.util.List;

public class UserDetailsArgsViewModel {

    private Integer currentPage;

    private Integer totalPages;

    private String name;

    private String facultyNumber;

    private List<SpecialtyViewModel> specialtyViewModels;

    private Long specialtyId;

    private List<JobStatusViewModel> jobStatusViewModes;

    private Long jobStatusId;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public List<SpecialtyViewModel> getSpecialtyViewModels() {
        return specialtyViewModels;
    }

    public void setSpecialtyViewModels(List<SpecialtyViewModel> specialtyViewModels) {
        this.specialtyViewModels = specialtyViewModels;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public List<JobStatusViewModel> getJobStatusViewModes() {
        return jobStatusViewModes;
    }

    public void setJobStatusViewModes(List<JobStatusViewModel> jobStatusViewModes) {
        this.jobStatusViewModes = jobStatusViewModes;
    }

    public Long getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(Long jobStatusId) {
        this.jobStatusId = jobStatusId;
    }
}
