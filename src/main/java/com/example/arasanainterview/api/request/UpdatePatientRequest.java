package com.example.arasanainterview.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePatientRequest {
    private String name;
    private String surname;
}
