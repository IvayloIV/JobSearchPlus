package com.tugab.jobsearchplus.domain.enums;

import com.tugab.jobsearchplus.utils.ResourceBundleUtil;

public enum JobPosition {

    Unemployed("jobPosition.unemployed"),
    Applied("jobPosition.applied"),
    Rejected("jobPosition.rejected"),
    Accepted("jobPosition.accepted"),
    Left("jobPosition.left"),
    Surrendered("jobPosition.surrendered");

    private JobPosition(String value) {
        this.value = value;
    }

    private String value;

    public String getPositionName() {
        return this.name();
    }

    public String getValue() {
        return ResourceBundleUtil.getBundleMessage(this.value);
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
