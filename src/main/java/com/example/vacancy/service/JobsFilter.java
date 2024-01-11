package com.example.vacancy.service;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class JobsFilter {
    private Set<String> categoryNames;

    private Set<String> locations;

    private Set<String> companyNames;

    private Integer minSalary;

    private Integer maxSalary;
}
