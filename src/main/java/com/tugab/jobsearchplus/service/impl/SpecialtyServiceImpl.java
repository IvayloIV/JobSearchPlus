package com.tugab.jobsearchplus.service.impl;

import com.tugab.jobsearchplus.domain.entities.Specialty;
import com.tugab.jobsearchplus.domain.models.services.SpecialtyServiceModel;
import com.tugab.jobsearchplus.repository.SpecialtyRepository;
import com.tugab.jobsearchplus.service.SpecialtyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository, ModelMapper modelMapper) {
        this.specialtyRepository = specialtyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SpecialtyServiceModel> findAll() {
        List<Specialty> specialties = this.specialtyRepository.findAllByOrderByCreatedDateDesc();
        return specialties.stream()
                .map(s -> this.modelMapper.map(s, SpecialtyServiceModel.class))
                .collect(Collectors.toList());
    }
}
