package com.example.arasanainterview.api.controller;

import com.example.arasanainterview.api.request.PatientRequest.SavePatientRequest;
import com.example.arasanainterview.api.request.PatientRequest.UpdatePatientRequest;
import com.example.arasanainterview.api.resource.PatientResource;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient/")
@Slf4j
@RequiredArgsConstructor
public class PatientContoller {

    private final PatientService patientService;

    @PostMapping("create")
    public void createPatient(@RequestBody SavePatientRequest savePatientRequest) {
        log.info("SAVING transaction has been started. The name of the user is {}", savePatientRequest.getName());
        patientService.createPatient(savePatientRequest);
    }

    @GetMapping("list")
    public List<PatientResource> listPatientResource() {
        log.info("LISTING transaction has been started.");
        PatientUser patientUser = new PatientUser();
        return patientUser.builder(patientService.getAllPatientUsers());
    }

    @GetMapping("one/{id}")
    public PatientResource findOnePatient(@PathVariable long id) {
        log.info("TRANSACTION TO FINDING ONE PATIENT  has been started.");
        PatientUser patientUser = new PatientUser();
        return patientUser.toDto(patientService.findOnePatient(id));
    }

    @PutMapping("update")
    public void updatePatient(@RequestBody UpdatePatientRequest updatePatientRequest) {
        log.info("UPDATING transaction has been started.");
        patientService.updatePatient(updatePatientRequest);
    }
}
