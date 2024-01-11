package com.example.vacancy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobDto {
    private Long id;

    private String title;

    private String description;

    private String category;

    private Integer salary;

    private String company;

    private String location;
}
