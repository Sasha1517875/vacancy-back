package com.example.vacancy.repository;

import com.example.vacancy.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoriesRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByCategoryName(String categoryName);
}
