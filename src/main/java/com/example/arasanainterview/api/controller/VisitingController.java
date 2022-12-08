package com.example.arasanainterview.api.controller;

import com.example.arasanainterview.api.request.VisitingRequest.SaveVisitingRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/visitor/")
@Slf4j
public class VisitingController {

    @PostMapping("create")
    public void createVisiting(@RequestBody SaveVisitingRequest saveVisitingRequest) {
        log.debug("The transaction to Create Detail that is about visitor has been started.");


    }
}
