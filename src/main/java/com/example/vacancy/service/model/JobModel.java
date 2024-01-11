package com.example.vacancy.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobModel {
    private Long id;

    private String title;

    private String description;

    private String category;

    private Integer salary;

    private String company;

    private String location;
}
