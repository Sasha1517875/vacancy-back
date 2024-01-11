package com.example.vacancy.controller;


import com.example.vacancy.dto.CategoryDto;
import com.example.vacancy.service.CategoriesService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final ModelMapper mapper = new ModelMapper();
    private final CategoriesService service;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return service.getAllCategories().stream()
                .map(x -> mapper.map(x, CategoryDto.class))
                .toList();
    }
}
