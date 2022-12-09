package com.example.arasanainterview.service.patient;

import com.example.arasanainterview.api.request.patient.SavePatientRequest;
import com.example.arasanainterview.api.request.patient.UpdatePatientRequest;
import com.example.arasanainterview.domain.PatientUser;

import java.util.List;

public interface PatientService {
    void createPatient(SavePatientRequest savePatientRequest);

    PatientUser createPatientByDto(SavePatientRequest savePatientRequest);

    List<PatientUser> getAllPatientUsers();

    void updatePatient(UpdatePatientRequest updatePatientRequest);

    PatientUser findOnePatient(long id);
}
