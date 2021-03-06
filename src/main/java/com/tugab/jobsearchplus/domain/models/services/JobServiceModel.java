package com.tugab.jobsearchplus.domain.models.services;

import com.tugab.jobsearchplus.utils.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class JobServiceModel {

    @XmlElement(name = "record_id")
    private Long recordId;

    @XmlElement(name = "name")
    private String title;

    @XmlElement(name = "region")
    private String region;

    @XmlElement(name = "salary")
    private String salary;

    @XmlElement(name = "Job_category")
    private String jobCategory;

    @XmlElement(name = "company")
    private String companyName;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "updated")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date updatedDate;

    @XmlElement(name = "expire")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date expireDate;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
