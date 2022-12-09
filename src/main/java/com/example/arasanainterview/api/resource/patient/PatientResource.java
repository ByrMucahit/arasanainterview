package com.example.arasanainterview.api.resource.patient;

import com.example.arasanainterview.api.resource.visiting.ListVisitorResource;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PatientResource {
    private String name;
    private String surname;
    private List<ListVisitorResource> visiting;
}
