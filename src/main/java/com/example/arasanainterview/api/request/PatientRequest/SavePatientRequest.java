package com.example.arasanainterview.api.request.PatientRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SavePatientRequest {
    private String name;
    private String surname;
}
