package com.tugab.jobsearchplus.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

public abstract class BaseController {

    @Autowired
    private MessageSource messageSource;

    protected ModelAndView view(String file, ModelAndView modelAndView) {
        modelAndView.addObject("view", file);
        modelAndView.setViewName("fragments/base-layout");
        return modelAndView;
    }

    protected ModelAndView view(String file) {
        return this.view(file, new ModelAndView());
    }

    protected  ModelAndView redirect(String path, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:" + path);
        return modelAndView;
    }

    protected ModelAndView redirect(String path) {
        return this.redirect(path, new ModelAndView());
    }

    protected String getMessage(String key, Object[] params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("test", params, locale);
    }

    protected String getMessage(String key) {
        return this.getMessage(key, null);
    }
}
