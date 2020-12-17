package com.tugab.jobsearchplus.domain.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "jobs_history")
public class JobHistory extends BaseEntity {

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "faculty_number", referencedColumnName = "faculty_number")
    private User user;

    @ManyToOne(targetEntity = Job.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "job_record_id", referencedColumnName = "record_id")
    private Job job;

    @ManyToOne(targetEntity = JobStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "old_status_id", referencedColumnName = "id")
    private JobStatus oldStatus;

    @ManyToOne(targetEntity = JobStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "new_status_id", referencedColumnName = "id")
    private JobStatus newStatus;

    @Column(name = "created_date")
    private Date createdDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public JobStatus getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(JobStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public JobStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(JobStatus newStatus) {
        this.newStatus = newStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
