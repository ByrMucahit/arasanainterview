package com.example.arasanainterview.service.patient;

import com.example.arasanainterview.api.request.PatientRequest.SavePatientRequest;
import com.example.arasanainterview.api.request.PatientRequest.UpdatePatientRequest;
import com.example.arasanainterview.domain.PatientUser;

import java.util.List;

public interface PatientService {
    void createPatient(SavePatientRequest savePatientRequest);

    List<PatientUser> getAllPatientUsers();

    void updatePatient(UpdatePatientRequest updatePatientRequest);

    PatientUser findOnePatient(long id);
}
