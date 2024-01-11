package com.example.vacancy.controller;

import com.example.vacancy.dto.CompanyDto;
import com.example.vacancy.service.CompaniesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/companies")
public class CompaniesController {
    private final ModelMapper mapper = new ModelMapper();
    private final CompaniesService service;

    @GetMapping
    public List<CompanyDto> getAllCategories() {
        return service.getAllCompanies().stream()
                .map(x -> mapper.map(x, CompanyDto.class))
                .toList();
    }
}
