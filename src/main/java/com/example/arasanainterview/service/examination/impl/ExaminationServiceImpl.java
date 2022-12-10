package com.example.arasanainterview.service.examination.impl;

import com.example.arasanainterview.api.request.examination.SaveExaminationRequest;
import com.example.arasanainterview.domain.Examination;
import com.example.arasanainterview.domain.Visit;
import com.example.arasanainterview.dto.VisitingExaminationDto;
import com.example.arasanainterview.repository.ExaminationRepository;
import com.example.arasanainterview.service.examination.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationRepository examinationRepository;

    @Override
    public void createExamination(SaveExaminationRequest saveExaminationRequest) {
        this.examinationRepository.save(prepareExamination(saveExaminationRequest.getTreatment()));
    }

    @Override
    public List<Examination> findAll() {
        return examinationRepository.findAllBy();
    }

    @Override
    public void createExaminationByUsingDto(VisitingExaminationDto visitingExaminationDto) {
        this.examinationRepository.save(prepareExaminationForDto(visitingExaminationDto.getTreatment(), visitingExaminationDto.getVisit()));
    }

    private Examination prepareExaminationForDto(String treatment, Visit visit) {
        Examination examination = new Examination();
        examination.setTreatment(treatment);
        examination.setVisit(visit);

        return examination;
    }

    private Examination prepareExamination(String treatment) {
        Examination examination = new Examination();
        examination.setTreatment(treatment);

        return examination;
    }
}
