package com.example.arasanainterview.service.visiting.impl;

import com.example.arasanainterview.api.request.patient.SavePatientRequest;
import com.example.arasanainterview.api.request.visiting.SaveVisitingRequest;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.domain.Visit;
import com.example.arasanainterview.dto.VisitingExaminationDto;
import com.example.arasanainterview.repository.PatientRepository;
import com.example.arasanainterview.repository.VisitingRepository;
import com.example.arasanainterview.service.examination.ExaminationService;
import com.example.arasanainterview.service.patient.PatientService;
import com.example.arasanainterview.service.visiting.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final PatientRepository patientRepository;

    private final PatientService patientService;

    private final VisitingRepository visitingRepository;

    private final ExaminationService examinationService;

    @Override
    @Transactional
    public void createDetail(SaveVisitingRequest saveVisitingRequest) {

        PatientUser user = this.patientRepository
                .findByNameAndSurname(saveVisitingRequest.getName(), saveVisitingRequest.getSurname())
                .orElse(patientService.createPatientByDto(SavePatientRequest.builder()
                        .name(saveVisitingRequest.getName())
                        .surname(saveVisitingRequest.getSurname()).build()));

        this.visitingRepository.save(prepareVisit(user));
        this.examinationService.createExaminationByUsingDto(prepareVisitingExaminationDto(saveVisitingRequest.getTreatment(), user));

    }

    @Override
    public List<Visit> listAllVisitors() {
        return visitingRepository.findAllBy();
    }

    private Visit prepareVisit(PatientUser patientUser) {
        Visit visit = new Visit();
        visit.setVisitingDate(LocalDate.now());
        visit.setPatientUser(patientUser);

        return visit;
    }

    private VisitingExaminationDto prepareVisitingExaminationDto(String treatment, PatientUser user) {
        return VisitingExaminationDto.builder().treatment(treatment).visit(prepareVisit(user)).build();
    }
}
