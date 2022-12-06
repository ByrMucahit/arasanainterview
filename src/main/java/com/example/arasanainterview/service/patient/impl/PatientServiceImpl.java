package com.example.arasanainterview.service.patient.impl;

import com.example.arasanainterview.api.request.SavePatientRequest;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.repository.PatientRepository;
import com.example.arasanainterview.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private PatientUser preparePatientUser(SavePatientRequest savePatientRequest) {
        PatientUser patientUser = new PatientUser();
        patientUser.setName(savePatientRequest.getName());
        patientUser.setSurname(savePatientRequest.getSurName());

        return patientUser;
    }

    @Override
    public void createPatient(SavePatientRequest savePatientRequest) {
        patientRepository.save(preparePatientUser(savePatientRequest));
    }

    @Override
    public List<PatientUser> getAllPatientUsers() {
        return patientRepository.findAll();
    }
}
