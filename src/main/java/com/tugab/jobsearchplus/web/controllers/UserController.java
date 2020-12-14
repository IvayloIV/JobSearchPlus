package com.tugab.jobsearchplus.web.controllers;

import com.tugab.jobsearchplus.domain.enums.StudyType;
import com.tugab.jobsearchplus.domain.models.bindings.UserRegisterBindingModel;
import com.tugab.jobsearchplus.domain.models.services.UserServiceModel;
import com.tugab.jobsearchplus.domain.models.views.SpecialtyRegisterViewModel;
import com.tugab.jobsearchplus.service.SpecialtyService;
import com.tugab.jobsearchplus.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;
    private final SpecialtyService specialtyService;
    private final ModelMapper modelMapper;
    private List<SpecialtyRegisterViewModel> specialties;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService, SpecialtyService specialtyService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.specialtyService = specialtyService;
    }

    @ModelAttribute("registerBindingModel")
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute("specialties")
    public List<SpecialtyRegisterViewModel> specialtyRegisterViewModels() {
        return this.getSpecialties();
    }

    @ModelAttribute("studyTypes")
    public StudyType[] studyTypes() {
        return StudyType.values();
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView) {
        return super.view("user/register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("registerBindingModel") UserRegisterBindingModel registerBindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return super.view("user/register");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(registerBindingModel, UserServiceModel.class);
        this.userService.registerUser(userServiceModel);
        return super.redirect("/"); //TODO: redirect to user profile??
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.addObject("loginBindingModel");
        return super.view("user/login", modelAndView);
    }

    private List<SpecialtyRegisterViewModel> getSpecialties() {
        if (this.specialties == null) {
            this.specialties = this.specialtyService.findAll()
                    .stream()
                    .map(s -> this.modelMapper.map(s, SpecialtyRegisterViewModel.class))
                    .collect(Collectors.toList());
        }

        return specialties;
    }
}