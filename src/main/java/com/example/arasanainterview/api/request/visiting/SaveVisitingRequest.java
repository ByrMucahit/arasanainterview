package com.example.arasanainterview.api.request.visiting;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveVisitingRequest {

    private String name;
    private String surname;
    private String treatment;
    private String nameOfTheDoctor;
}
