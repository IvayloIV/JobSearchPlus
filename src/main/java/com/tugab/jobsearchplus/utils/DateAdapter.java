package com.tugab.jobsearchplus.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public synchronized String marshal(Date v) throws Exception {
        return simpleDateFormat.format(v);
    }

    @Override
    public synchronized Date unmarshal(String v) throws Exception {
        return simpleDateFormat.parse(v);
    }
}
