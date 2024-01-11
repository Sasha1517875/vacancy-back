package com.example.vacancy.controller;


import com.example.vacancy.dto.JobDto;
import com.example.vacancy.dto.PageDto;
import com.example.vacancy.service.JobsFilter;
import com.example.vacancy.service.JobsService;
import com.example.vacancy.service.model.JobModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/jobs")
public class JobsController {
    private final JobsService jobsService;
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/{id}")
    public JobDto getJobById(@PathVariable Long id) {
        return modelMapper.map(jobsService.getJobById(id), JobDto.class);
    }

    @GetMapping
    public Page<JobDto> getJobs(PageDto page) {
        return jobsService.getJobsByFilter(
                        page.getPageable(),
                        modelMapper.map(page, JobsFilter.class))
                .map(x -> modelMapper.map(x, JobDto.class));
    }

    @PostMapping
    public JobDto addJob(@RequestBody JobDto jobDto) {
        JobModel job = modelMapper.map(jobDto, JobModel.class);
        return modelMapper.map(jobsService.addJob(job), JobDto.class);
    }

    @PutMapping("/{id}")
    public JobDto updateJob(@PathVariable Long id, @RequestBody JobDto jobDto) {
        JobModel job = modelMapper.map(jobDto, JobModel.class);
        return modelMapper.map(jobsService.updateJob(id, job), JobDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteJobById(@PathVariable Long id) {
        jobsService.deleteJobById(id);
    }
}
