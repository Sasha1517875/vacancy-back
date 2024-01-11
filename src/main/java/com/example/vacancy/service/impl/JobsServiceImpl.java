package com.example.vacancy.service.impl;

import com.example.vacancy.entity.Category;
import com.example.vacancy.entity.Company;
import com.example.vacancy.entity.Job;
import com.example.vacancy.repository.CategoriesRepository;
import com.example.vacancy.repository.CompaniesRepository;
import com.example.vacancy.repository.JobsRepository;
import com.example.vacancy.service.JobsFilter;
import com.example.vacancy.service.JobsService;
import com.example.vacancy.service.model.JobModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class JobsServiceImpl implements JobsService {

    private final JobsRepository jobsRepository;

    private final CategoriesRepository categoryRepository;

    private final CompaniesRepository companyRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public JobModel getJobById(Long id) {
        Job job = jobsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no job with id=" + id));
        return mapper.map(job, JobModel.class);
    }

    @Override
    public Page<JobModel> getJobsByFilter(Pageable pageable, JobsFilter filter) {
        return jobsRepository.findJobsByFilters(
                        filter.getCategoryNames(),
                        filter.getLocations(),
                        filter.getCompanyNames(),
                        filter.getMinSalary(),
                        filter.getMaxSalary(),
                        pageable)
                .map(x -> mapper.map(x, JobModel.class));
    }

    @Override
    public JobModel addJob(JobModel job) {
        Company company = companyRepository.findByCompanyName(job.getCompany())
                .orElseThrow(() -> new IllegalArgumentException("Invalid company name"));

        Category category = categoryRepository.findByCategoryName(job.getCategory())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category name"));

        Job jobEntity = mapper.map(job, Job.class);
        jobEntity.setCategory(category);
        jobEntity.setCompany(company);

        jobEntity = jobsRepository.save(jobEntity);

        return mapper.map(jobEntity, JobModel.class);
    }

    @Override
    public JobModel updateJob(Long id, JobModel job) {

        Job jobEntity = jobsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Job entity doesn't exist"));

        Company company = companyRepository.findByCompanyName(job.getCompany())
                .orElseThrow(IllegalArgumentException::new);

        Category category = categoryRepository.findByCategoryName(job.getCategory())
                .orElseThrow(IllegalArgumentException::new);

        jobEntity.setCategory(category);
        jobEntity.setCompany(company);
        jobEntity.setLocation(job.getLocation());
        jobEntity.setSalary(job.getSalary());
        jobEntity.setDescription(job.getDescription());

        jobEntity = jobsRepository.save(jobEntity);

        return mapper.map(jobEntity, JobModel.class);
    }

    @Override
    public void deleteJobById(Long id) {
        if (!jobsRepository.existsById(id)) {
            throw new RuntimeException("There is no job with id=\" + id.");
        }
        jobsRepository.deleteById(id);
    }
}
