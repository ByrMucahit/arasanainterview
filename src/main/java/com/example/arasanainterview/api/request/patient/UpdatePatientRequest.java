package com.example.arasanainterview.api.request.patient;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePatientRequest {
    private long id;
    private String name;
    private String surname;
}
