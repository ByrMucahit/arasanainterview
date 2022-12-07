package com.example.arasanainterview.api.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientResource {
    private String name;
    private String surname;
}
