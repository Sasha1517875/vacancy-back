package com.example.vacancy.repository;

import com.example.vacancy.entity.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompaniesRepository extends CrudRepository<Company, Integer> {
    Optional<Company> findByCompanyName(String companyName);
}
