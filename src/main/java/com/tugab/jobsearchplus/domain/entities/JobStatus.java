package com.tugab.jobsearchplus.domain.entities;

import com.tugab.jobsearchplus.domain.enums.JobPosition;

import javax.persistence.*;

@Entity
@Table(name = "jobs_status")
public class JobStatus {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
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
