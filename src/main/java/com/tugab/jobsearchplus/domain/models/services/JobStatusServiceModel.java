package com.tugab.jobsearchplus.domain.models.services;

import com.tugab.jobsearchplus.domain.enums.JobPosition;

public class JobStatusServiceModel {

    private Long id;

    private JobPosition name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobPosition getName() {
        return name;
    }

    public void setName(JobPosition name) {
        this.name = name;
    }
}
