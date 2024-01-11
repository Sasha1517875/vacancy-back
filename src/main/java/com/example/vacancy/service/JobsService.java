package com.example.vacancy.service;

import com.example.vacancy.entity.Job;
import com.example.vacancy.service.model.JobModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobsService {
    JobModel getJobById(Long id);

    Page<JobModel> getJobsByFilter(Pageable pageable, JobsFilter filter);

    JobModel addJob(JobModel job);

    JobModel updateJob(Long id, JobModel job);

    void deleteJobById(Long id);
}
