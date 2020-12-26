package com.tugab.jobsearchplus.domain.models.views;

import com.tugab.jobsearchplus.domain.enums.JobPosition;

public class UserListViewModel {

    private String facultyNumber;

    private String name;

    private String surname;

    private String pictureName;

    private JobPosition jobStatusName;

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public JobPosition getJobStatusName() {
        return jobStatusName;
    }

    public void setJobStatusName(JobPosition jobStatusName) {
        this.jobStatusName = jobStatusName;
    }
}
