package com.tugab.jobsearchplus.domain.models.services;

import com.tugab.jobsearchplus.domain.entities.JobHistory;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {

    private String facultyNumber;

    private String password;

    private String name;

    private String middleName;

    private String surname;

    private String egn;

    private String phone;

    private String email;

    private Long specialtyId;

    private String studyType;

    private Double grade;

    private MultipartFile profilePicture;

    private List<JobHistory> jobsHistory;

    private List<RoleServiceModel> roles;

    public UserServiceModel() {
        this.roles = new ArrayList<>();
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public MultipartFile getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MultipartFile profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<JobHistory> getJobsHistory() {
        return jobsHistory;
    }

    public void setJobsHistory(List<JobHistory> jobsHistory) {
        this.jobsHistory = jobsHistory;
    }

    public List<RoleServiceModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleServiceModel> roles) {
        this.roles = roles;
    }
}
