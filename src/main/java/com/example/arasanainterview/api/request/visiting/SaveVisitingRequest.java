package com.example.arasanainterview.api.request.visiting;

import lombok.Data;

@Data
public class SaveVisitingRequest {

    private String name;
    private String surname;
    private String treatment;
    private String nameOfTheDoctor;
}
