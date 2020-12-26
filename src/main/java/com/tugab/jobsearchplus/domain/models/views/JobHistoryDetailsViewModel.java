package com.tugab.jobsearchplus.domain.models.views;

import com.tugab.jobsearchplus.domain.entities.JobStatus;

import java.util.Date;

public class JobHistoryDetailsViewModel {

    private UserJobDetailsViewModel user;

    private JobStatus newStatusName;

    private Date createdDate;

    public UserJobDetailsViewModel getUser() {
        return user;
    }

    public void setUser(UserJobDetailsViewModel user) {
        this.user = user;
    }

    public JobStatus getNewStatusName() {
        return newStatusName;
    }

    public void setNewStatusName(JobStatus newStatusName) {
        this.newStatusName = newStatusName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
