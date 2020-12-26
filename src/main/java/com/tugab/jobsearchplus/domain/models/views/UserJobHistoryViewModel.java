package com.tugab.jobsearchplus.domain.models.views;

import com.tugab.jobsearchplus.domain.entities.JobStatus;

import java.util.Date;

public class UserJobHistoryViewModel {

    private String jobRecordId;

    private String jobTitle;

    private JobStatus newStatusName;

    private Date createdDate;

    public String getJobRecordId() {
        return jobRecordId;
    }

    public void setJobRecordId(String jobRecordId) {
        this.jobRecordId = jobRecordId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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
