package com.example.arasanainterview.api.controller;

import com.example.arasanainterview.api.request.examination.SaveExaminationRequest;
import com.example.arasanainterview.api.resource.examination.ListExaminationResource;
import com.example.arasanainterview.domain.Examination;
import com.example.arasanainterview.service.examination.ExaminationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/examination/")
@Slf4j
@RequiredArgsConstructor
public class ExaminationController {

    private final ExaminationService examinationService;

    @PostMapping("create")
    public void createExamination(@RequestBody SaveExaminationRequest saveExaminationRequest) {
        log.debug("The transaction to create detail that is about examination has been just started.");
        examinationService.createExamination(saveExaminationRequest);
    }

    @GetMapping("list")
    public ResponseEntity<List<ListExaminationResource>> findAllExaminations() {
        log.debug("The transaction to find all examinations of patient has been just started.");
        Examination examination = new Examination();
        return ResponseEntity.ok(examination.builder(examinationService.findAll()));
    }
}
