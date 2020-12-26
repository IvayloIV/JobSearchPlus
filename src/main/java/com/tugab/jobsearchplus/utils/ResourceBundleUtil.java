package com.tugab.jobsearchplus.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtil {

    @Autowired
    private MessageSource messageSource;

    private String getMessage(String key, Object[] params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, params, locale);
    }

    public String getMessage(String key) {
        return this.getMessage(key, null);
    }

    public static String getBundleMessage(String key) { //Not the best practice
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("locale/global", locale);
        return rb.getString(key);
    }
}
