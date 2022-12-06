package com.example.arasanainterview.api.controller;

import com.example.arasanainterview.api.request.SavePatientRequest;
import com.example.arasanainterview.api.resource.ListPatientResource;
import com.example.arasanainterview.domain.PatientUser;
import com.example.arasanainterview.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<ListPatientResource> listPatientResource() {
        log.info("LISTING transaction has been started.");
        PatientUser patientUser = new PatientUser();
        return patientUser.builder(patientService.getAllPatientUsers());
    }
}
