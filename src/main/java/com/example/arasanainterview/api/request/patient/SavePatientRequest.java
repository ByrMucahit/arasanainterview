package com.example.arasanainterview.api.request.patient;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SavePatientRequest {
    private String name;
    private String surname;
}
