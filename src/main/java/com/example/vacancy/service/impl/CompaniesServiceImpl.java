package com.example.vacancy.service.impl;

import com.example.vacancy.repository.CompaniesRepository;
import com.example.vacancy.service.CompaniesService;
import com.example.vacancy.service.model.CompanyModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class CompaniesServiceImpl implements CompaniesService {

    private final CompaniesRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<CompanyModel> getAllCompanies() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(x -> modelMapper.map(x, CompanyModel.class))
                .toList();
    }
}
