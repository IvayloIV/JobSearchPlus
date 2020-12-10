package com.tugab.jobsearchplus.domain.models.bindings;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class UserRegisterBindingModel {

    @NotNull
    @Size(min = 10, max = 10, message = "{registerUser.facultyNumberSize}")
    private String facultyNumber;

    @NotNull
    @Size(min = 3, max = 20, message = "{registerUser.passwordSize}")
    private String password;

    @NotNull
    @Size(min = 2, max = 15, message = "{registerUser.nameSize}")
    private String name;

    private String middleName;

    @NotNull
    @Size(min = 2, max = 15, message = "{registerUser.surnameSize}")
    private String surname;

    @NotNull
    @Size(min = 10, max = 10, message = "{registerUser.egnSize}")
    private String egn;

    @NotNull
    @Size(min = 5, max = 20, message = "{registerUser.phoneSize}")
    private String phone;

    @NotNull
    @Size(min = 4, max = 20, message = "{registerUser.emailSize}")
    private String email;

    @NotNull(message = "{registerUser.specialtyNotNull}")
    private Long specialtyId;

    @NotNull
    @NotEmpty(message = "{registerUser.studyTypeNotNull}")
    private String studyType;

    @NotNull(message = "{registerUser.gradeNotNull}")
    @DecimalMin("2.0")
    @DecimalMax("6.0")
    private Double grade;

    private MultipartFile profilePicture;

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
}
