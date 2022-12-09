package com.example.arasanainterview.dto;

import com.example.arasanainterview.domain.Visit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VisitingExaminationDto {
    private String treatment;
    private Visit visit;
}
