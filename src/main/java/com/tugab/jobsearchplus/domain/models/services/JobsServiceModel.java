package com.tugab.jobsearchplus.domain.models.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "jobs")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobsServiceModel {

    @XmlElement(name = "job")
    private List<JobServiceModel> jobs;

    public List<JobServiceModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobServiceModel> jobs) {
        this.jobs = jobs;
    }
}
