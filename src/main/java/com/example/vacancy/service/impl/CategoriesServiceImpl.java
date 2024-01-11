package com.example.vacancy.service.impl;

import com.example.vacancy.repository.CategoriesRepository;
import com.example.vacancy.service.CategoriesService;
import com.example.vacancy.service.model.CategoryModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<CategoryModel> getAllCategories() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(x -> modelMapper.map(x, CategoryModel.class))
                .toList();
    }
}
