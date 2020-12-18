package com.tugab.jobsearchplus.domain.models.views;

import java.util.Date;

public class JobHistoryDetailsViewModel {

    private UserJobDetailsViewModel user;

    private String newStatusName;

    private Date createdDate;

    public UserJobDetailsViewModel getUser() {
        return user;
    }

    public void setUser(UserJobDetailsViewModel user) {
        this.user = user;
    }

    public String getNewStatusName() {
        return newStatusName;
    }

    public void setNewStatusName(String newStatusName) {
        this.newStatusName = newStatusName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
