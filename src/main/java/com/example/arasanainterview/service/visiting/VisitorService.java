package com.example.arasanainterview.service.visiting;

import com.example.arasanainterview.api.request.visiting.SaveVisitingRequest;
import com.example.arasanainterview.domain.Visit;

import java.util.List;

public interface VisitorService {

    void createDetail(SaveVisitingRequest saveVisitingRequest);

    List<Visit> listAllVisitors();
}
