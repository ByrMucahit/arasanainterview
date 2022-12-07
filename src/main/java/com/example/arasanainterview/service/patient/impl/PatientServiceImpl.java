package com.example.arasanainterview.service.patient.impl;

import com.example.arasanainterview.api.request.SavePatientRequest;
import com.example.arasanainterview.api.request.UpdatePatientRequest;
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

    private PatientUser preparePatientUserToSave(SavePatientRequest savePatientRequest) {
        PatientUser patientUser = new PatientUser();
        patientUser.setName(savePatientRequest.getName());
        patientUser.setSurname(savePatientRequest.getSurname());

        return patientUser;
    }

    private PatientUser preparePatientUserToUpdate(UpdatePatientRequest updatePatientRequest) {
        PatientUser patientUser = new PatientUser();
        patientUser.setName(updatePatientRequest.getName());
        patientUser.setSurname(updatePatientRequest.getSurname());

        return patientUser;
    }

    @Override
    public void createPatient(SavePatientRequest savePatientRequest) {
        patientRepository.save(preparePatientUserToSave(savePatientRequest));
    }

    @Override
    public List<PatientUser> getAllPatientUsers() {
        return patientRepository.findAll();
    }

    @Override
    public void updatePatient(UpdatePatientRequest updatePatientRequest) {
        patientRepository.save(preparePatientUserToUpdate(updatePatientRequest));
    }

    @Override
    public PatientUser findOnePatient(long id) {
        return patientRepository.findById(id).orElseThrow();
    }
}
