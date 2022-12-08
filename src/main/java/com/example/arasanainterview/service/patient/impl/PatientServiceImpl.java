package com.example.arasanainterview.service.patient.impl;

import com.example.arasanainterview.api.request.PatientRequest.SavePatientRequest;
import com.example.arasanainterview.api.request.PatientRequest.UpdatePatientRequest;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.exception.UserAlreadyExistException;
import com.example.arasanainterview.exception.UserNotFoundException;
import com.example.arasanainterview.repository.PatientRepository;
import com.example.arasanainterview.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private void checksPatientExist(String name, String surname) {
        if (patientRepository.existsByNameAndSurname(name, surname)) {
            throw new UserAlreadyExistException("Employee ID is null");
        }

    }

    @Override
    public void createPatient(SavePatientRequest savePatientRequest) {
        this.checksPatientExist(savePatientRequest.getName(), savePatientRequest.getSurname());
        patientRepository.save(preparePatientUserToSave(savePatientRequest));
    }

    @Override
    public PatientUser findOnePatient(long id) {
        return patientRepository.findById(id).orElseThrow();
    }

    @Override
    public List<PatientUser> getAllPatientUsers() {
        return patientRepository.findAll();
    }

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
    public void updatePatient(UpdatePatientRequest updatePatientRequest) {
        patientRepository.save(preparePatientUserToUpdate(updatePatientRequest));

        PatientUser user = patientRepository.findById(updatePatientRequest.getId()).orElseThrow(() -> {
            throw new UserNotFoundException("userNotFound", "User Not Found");
        });

        user.setName(updatePatientRequest.getName());
        user.setSurname(updatePatientRequest.getSurname());

        patientRepository.save(user);
    }
}
