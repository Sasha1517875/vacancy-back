package com.example.vacancy.service;

import com.example.vacancy.service.model.CompanyModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompaniesService {
    List<CompanyModel> getAllCompanies();
}
