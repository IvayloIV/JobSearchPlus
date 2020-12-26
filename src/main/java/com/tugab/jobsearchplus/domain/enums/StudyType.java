package com.tugab.jobsearchplus.domain.enums;

import com.tugab.jobsearchplus.utils.ResourceBundleUtil;

public enum StudyType {

    REGULAR("studyType.regular"),
    PARTTIME("studyType.partTime"),
    REMOVE("studyType.remote");

    private StudyType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return ResourceBundleUtil.getBundleMessage(this.value);
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
