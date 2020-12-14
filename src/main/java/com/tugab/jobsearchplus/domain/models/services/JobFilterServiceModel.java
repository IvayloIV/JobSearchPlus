package com.tugab.jobsearchplus.domain.models.services;

import java.util.List;

public class JobFilterServiceModel {

    private List<JobServiceModel> jobServiceModels;

    private long pageCount;

    public JobFilterServiceModel(List<JobServiceModel> jobServiceModels, long pageCount) {
        this.jobServiceModels = jobServiceModels;
        this.pageCount = pageCount;
    }

    public List<JobServiceModel> getJobServiceModels() {
        return jobServiceModels;
    }

    public void setJobServiceModels(List<JobServiceModel> jobServiceModels) {
        this.jobServiceModels = jobServiceModels;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
}
