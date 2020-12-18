package com.tugab.jobsearchplus.domain.models.services;

import java.util.Date;

public class JobHistoryServiceModel {

    private String id;

    private UserServiceModel user;

    private JobServiceModel job;

    private JobStatusServiceModel oldStatus;

    private JobStatusServiceModel newStatus;

    private Date createdDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserServiceModel getUser() {
        return user;
    }

    public void setUser(UserServiceModel user) {
        this.user = user;
    }

    public JobServiceModel getJob() {
        return job;
    }

    public void setJob(JobServiceModel job) {
        this.job = job;
    }

    public JobStatusServiceModel getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(JobStatusServiceModel oldStatus) {
        this.oldStatus = oldStatus;
    }

    public JobStatusServiceModel getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(JobStatusServiceModel newStatus) {
        this.newStatus = newStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
