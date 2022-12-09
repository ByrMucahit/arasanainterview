package com.example.arasanainterview.service.examination;

import com.example.arasanainterview.api.request.examination.SaveExaminationRequest;
import com.example.arasanainterview.domain.Examination;
import com.example.arasanainterview.dto.VisitingExaminationDto;

import java.util.List;

public interface ExaminationService {

    void createExamination(SaveExaminationRequest saveExaminationRequest);

    List<Examination> findAll();

    void createExaminationByUsinDto(VisitingExaminationDto visitingExaminationDto);
}
