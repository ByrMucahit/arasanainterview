package com.example.arasanainterview.api.controller;

import com.example.arasanainterview.api.request.visiting.SaveVisitingRequest;
import com.example.arasanainterview.api.resource.visiting.ListVisitorResource;
import com.example.arasanainterview.domain.Visit;
import com.example.arasanainterview.service.visiting.VisitorService;
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
@RequestMapping("/api/visitor/")
@Slf4j
@RequiredArgsConstructor
public class VisitingController {

    private final VisitorService visitorService;

    @PostMapping("create")
    public void createVisiting(@RequestBody SaveVisitingRequest saveVisitingRequest) {
        log.debug("The transaction to Create Detail that is about visitor has been started.");
        visitorService.createDetail(saveVisitingRequest);
    }

    @GetMapping("list")
    public ResponseEntity<List<ListVisitorResource>> listVisiting() {
        log.debug("The transaction to list visitors has been just started.");
        List<Visit> response = visitorService.listAllVisitors();

        Visit visit = new Visit();
        return ResponseEntity.ok(visit.builder(response));
    }
}
