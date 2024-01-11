package com.example.vacancy.repository;

import com.example.vacancy.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

public interface JobsRepository extends PagingAndSortingRepository<Job, Long>, CrudRepository<Job, Long> {
    @Query("SELECT j FROM Job j WHERE " +
            "( :categoryNames IS NULL OR j.category.categoryName IN :categoryNames ) AND " +
            "( :locations IS NULL OR j.location IN :locations ) AND " +
            "( :companyNames IS NULL OR j.company.companyName IN :companyNames ) AND " +
            "( :minSalary IS NULL OR j.salary >= :minSalary ) AND " +
            "( :maxSalary IS NULL OR j.salary <= :maxSalary )")

    Page<Job> findJobsByFilters(
            Set<String> categoryNames,
            Set<String> locations,
            Set<String> companyNames,
            Integer minSalary,
            Integer maxSalary,
            Pageable pageable
    );
}
